package main.pfmandroid.activities;

import main.pfmandroid.R;
import main.pfmandroid.R.id;
import main.pfmandroid.R.layout;
import main.pfmandroid.R.menu;
import main.pfmandroid.data.DataStorage;
import main.pfmandroid.data.Money;
import main.pfmandroid.data.Wallet;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/*
 * Class is responsible for displaying the current Balance (funds on all of the user wallets)
 */

public class BalanceActivity extends Activity {
	
	//The table variable that we will use to manually populate the table
	TableLayout balanceTable;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        
        //Find table to reference it later
        balanceTable = (TableLayout) findViewById(R.id.balance_table);
        fillBalanceTable();
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_balance, menu);
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
    
    public void fillBalanceTable(){
    	//Iterate through all of the user wallets and all of the currencies inside each wallet.
        for (Wallet x : DataStorage.listOfWallets){
        	boolean addedname = false;
        	for(Money y : x.getMoney()){

        		LayoutInflater inflater = getLayoutInflater();
            	
        		//Create new table row, using our own created balancerow layout (defined in res/layout)
                TableRow tr = (TableRow)inflater.inflate(R.layout.balancerow, balanceTable, false);
                
                //Fill in the name, if it has not yet been added, otherwise, make it empty.
                TextView trWallet = (TextView)tr.findViewById(R.id.trBalanceWallet);
                if(!addedname){
                	trWallet.setText(x.getName());
                	addedname = true;
                }else
                	trWallet.setText("");
                
                TextView trCode = (TextView)tr.findViewById(R.id.trBalanceCode);
                trCode.setText(y.getCode());
                
                TextView trAmount = (TextView)tr.findViewById(R.id.trBalanceAmount);
                trAmount.setText(String.valueOf(y.getAmount()));
                
                balanceTable.addView(tr);
	        }
        	LayoutInflater inflater = getLayoutInflater();
        	
        	//Add empty row, for separation purposes.
            TableRow tr = (TableRow)inflater.inflate(R.layout.balancerow, balanceTable, false);
            balanceTable.addView(tr);
        }
    }
    
    public void returnBack(View view){
    	this.finish();
    }
}
