package main.pfmandroid;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

/*
 * Class represents transfer activity (Transfers option). Allows the user to transfer currency from one wallet to another.
 */

public class TransferActivity extends Activity {
	Wallet[] wallets;
	
    MoneyListener moneylistener;
    WalletListener walletlistener;
    WalletListener walletlistenerto;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if(DataStorage.listOfWallets.size()==0)
        	DataStorage.populate();
        
        wallets = new Wallet[DataStorage.listOfWallets.size()];
        
        for(int i = 0; i<DataStorage.listOfWallets.size(); i++){
        	wallets[i] = DataStorage.listOfWallets.get(i);
        }
        
        setContentView(R.layout.activity_transfer);
        
        TextView changable = (TextView) findViewById(R.id.textViewTr3);
        PositionContainer poscon = new PositionContainer();
        
        //First spinner will update the remaining amount of money (sender).
        Spinner spinner = (Spinner) findViewById(R.id.spinnerTr1);
        ArrayAdapter<Wallet> adapter = new ArrayAdapter<Wallet>(this, R.layout.spinneritem, R.id.spinneritem, wallets);
	    //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner.setAdapter(adapter);
	    walletlistener = new WalletListener(changable, poscon);
	    spinner.setOnItemSelectedListener(walletlistener);
	    
	    //Second spinner only tracks which wallet to transfer to, hence, doesn't need to synchronise.
	    Spinner spinner2 = (Spinner) findViewById(R.id.spinnerTr2);
	    //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner2.setAdapter(adapter);
	    walletlistenerto = new WalletListener();
	    spinner2.setOnItemSelectedListener(walletlistenerto);
	    
	    //Money spinner listener.
	    Spinner spinner3 = (Spinner) findViewById(R.id.spinnerTr3);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.spinneritem, R.id.spinneritem, DataStorage.typesOfCurrency);
	    //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner3.setAdapter(adapter3);
	    moneylistener = new MoneyListener(changable, poscon);
	    spinner3.setOnItemSelectedListener(moneylistener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_transfer, menu);
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

    protected void onStop(){
    	super.onStop();
    	wallets = null;
    }
    
    public void returnBack(View view){
    	this.finish();
    }
}
