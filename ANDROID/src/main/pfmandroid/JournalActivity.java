package main.pfmandroid;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class JournalActivity extends Activity implements OnItemSelectedListener{
	Transaction[] transactions;
	String[] timeFrames = {"Today", "One week", "One month", "One year"};
	TableLayout journal;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if(DataStorage.listOfWallets.size()==0)
        	DataStorage.populate();
 
        transactions = new Transaction[DataStorage.listOfTransactions.size()];
        for(int i = 0; i < DataStorage.listOfTransactions.size(); i++){
        	transactions[i] = DataStorage.listOfTransactions.get(i);
        }
        
        setContentView(R.layout.activity_journal);
        
        journal = (TableLayout) findViewById(R.id.tablejournal);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerJo1);
        // Create an ArrayAdapter using the Wallet array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinneritem, R.id.spinneritem, timeFrames);
	    // Specify the layout to use when the list of choices appears
	    //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    // Apply the adapter to the spinner
	    spinner.setAdapter(adapter);
	    spinner.setOnItemSelectedListener(this);
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

	@Override
	public void onItemSelected(AdapterView<?> parent, View arg1, int pos,
			long arg3) {
		populateTable();
		/*Date today = new Date();
		
		for(Transaction tr : DataStorage.listOfTransactions){
			Date trdate = tr.getDate();
			switch(pos){
			case 0:
				if(trdate.getYear() == today.getYear())
					if(trdate.getMonth() == today.getMonth())
						if(trdate.getDay() == today.getDay())
							newlist.add(tr);
				break;
			case 1:
				if(trdate.getYear() == today.getYear())
					if(trdate.getMonth() == today.getMonth())
						if(trdate.getDay() <= today.getDay())
							if(trdate.getDay() >= today.getDay()-7)
								newlist.add(tr);
				break;
			case 2:
				if(trdate.getYear() == today.getYear())
					if(trdate.getMonth() <= today.getMonth())
						if(trdate.getMonth() >= today.getMonth()-1)
							newlist.add(tr);
				break;
			case 3:
				if(trdate.getYear() <= today.getYear())
					if(trdate.getYear() >= today.getYear()-1)
						newlist.add(tr);
				break;
			}
		}*/
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void populateTable(){
		journal.removeAllViews();
        for (Transaction x : DataStorage.listOfTransactions){           
        	LayoutInflater inflater = getLayoutInflater();
        	
            TableRow tr = (TableRow)inflater.inflate(R.layout.journalrow, journal, false);

            TextView trWallet = (TextView)tr.findViewById(R.id.trWallet);
            trWallet.setText(x.getName());

            // Add the 3rd Column
            TextView trCode = (TextView)tr.findViewById(R.id.trCode);
            trCode.setText(x.getCode());
            
            TextView trAmount = (TextView)tr.findViewById(R.id.trAmount);
            trAmount.setText(String.valueOf(x.getAmount()));
            
            TextView trDate = (TextView)tr.findViewById(R.id.trDate);
            trDate.setText(DateFormat.format("yyyy-MM-dd", x.getDate()));
            
            journal.addView(tr);
        }
	}
}
