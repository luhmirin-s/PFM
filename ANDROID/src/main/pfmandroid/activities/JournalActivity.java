package main.pfmandroid.activities;

import main.pfmandroid.R;
import main.pfmandroid.TimeframeListener;
import main.pfmandroid.data.DataStorage;
import main.pfmandroid.data.Transaction;
import main.pfmandroid.getdata.RetrieveTransactionData;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/*
 * Class represents the Transaction Journal option from the Main Menu. Class responsible for displaying the transaction
 * history of the user, depending on the chosen timeframe.
 */

public class JournalActivity extends Activity{
	Transaction[] transactions;
	String[] timeFrames = {"Today", "Last week", "Last month", "Last year"};
	TableLayout journal;
	TimeframeListener timeframe;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        transactions = new Transaction[DataStorage.listOfTransactions.size()];
        for(int i = 0; i < DataStorage.listOfTransactions.size(); i++){
        	transactions[i] = DataStorage.listOfTransactions.get(i);
        }
        
        setContentView(R.layout.activity_journal);
        
        journal = (TableLayout) findViewById(R.id.tablejournal);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerJo1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinneritem, R.id.spinneritem, timeFrames);
	    //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner.setAdapter(adapter);
	    //Add listener to redisplay the transactions on change of the spinner
	    timeframe = new TimeframeListener();
	    spinner.setOnItemSelectedListener(timeframe);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_journal, menu);
        return true;
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

	public void populateTable(){
		//Remove all that was in the table before and repopulate it
		journal.removeAllViews();
        for (Transaction x : DataStorage.listOfTransactions){           
        	LayoutInflater inflater = getLayoutInflater();
        	
        	//Display wallet name, currency code, amount and the date of transaction
            TableRow tr = (TableRow)inflater.inflate(R.layout.journalrow, journal, false);
            
            TextView trType = (TextView)tr.findViewById(R.id.trType);
            TextView trAmount = (TextView)tr.findViewById(R.id.trAmount);
            
            switch(x.getType()){
	            case 1:
	            	trType.setText("Ex");
	            	trAmount.setTextColor(Color.RED);
	            	break;
	            case 2:
	            	trType.setText("In");
	            	trAmount.setTextColor(Color.GREEN);
	            	break;
	            case 3:
	            	trType.setText("Tr");
	            	trAmount.setTextColor(Color.BLACK);
	            	break;
            }
            
            TextView trWallet = (TextView)tr.findViewById(R.id.trWallet);
            trWallet.setText(x.getWallet());
            
            trAmount.setText(x.getAmount());
            
            TextView trText = (TextView)tr.findViewById(R.id.trText);
            trText.setText(x.getText());
            
            TextView trDate = (TextView)tr.findViewById(R.id.trDate);
            trDate.setText(x.getDate());
            
            journal.addView(tr);
        }
	}
	
	public void drawData(View view){
		RetrieveTransactionData task = new RetrieveTransactionData(this, timeframe.getPos());
		task.execute();
	}
}
