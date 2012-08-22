package main.pfmandroid;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
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
        TableRow row;
        TextView t1, t2, t3;
        Money curmon;
        Wallet curwal;
        //Converting to dip unit
        int dip = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                (float) 1, getResources().getDisplayMetrics());
        
        for (int wallet = 0; wallet < DataStorage.listOfWallets.size(); wallet++){
        	curwal = DataStorage.listOfWallets.get(wallet);
        	boolean addedname = false;
        	for(int money = 0; money < DataStorage.listOfWallets.get(wallet).getMoney().size(); money++){
	            
        		curmon = DataStorage.listOfWallets.get(wallet).getMoney().get(money);
        		row = new TableRow(this);
        		
	            t1 = new TextView(this);
	            t2 = new TextView(this);
	            t3 = new TextView(this);
	            
	            if(!addedname){
	            	t1.setText(curwal.getName());
	            	addedname = true;
	            }else
	            	t1.setText("");
	            
	            t2.setText(curmon.getCode());
	            t3.setText(String.valueOf(curmon.getAmount()));
	 
	            t1.setTypeface(null, 1);
	            t2.setTypeface(null, 1);
	            t3.setTypeface(null, 1);
	 
	            t1.setTextSize(18);
	            t2.setTextSize(18);
	            t3.setTextSize(18);
	 
	            t1.setWidth(180);
	            t2.setWidth(100);
	            t3.setWidth(100);
	            t1.setPadding(20*dip, 0, 0, 0);
	            
	            
	            row.addView(t1);
	            row.addView(t2);
	            row.addView(t3);
	 
	            balanceTable.addView(row, new TableLayout.LayoutParams(
	                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	 
	        }
        }
    }
}
