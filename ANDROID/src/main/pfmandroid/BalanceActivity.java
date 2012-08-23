package main.pfmandroid;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.format.DateFormat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;

public class BalanceActivity extends Activity {

	TableLayout balanceTable;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        
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
        for (Wallet x : DataStorage.listOfWallets){
        	boolean addedname = false;
        	for(Money y : x.getMoney()){
	            
        		LayoutInflater inflater = getLayoutInflater();
            	
                TableRow tr = (TableRow)inflater.inflate(R.layout.balancerow, balanceTable, false);

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
        }
    }
    
    public void returnBack(View view){
    	this.finish();
    }
}
