package main.pfmandroid;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

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
        
        Spinner spinner = (Spinner) findViewById(R.id.spinnerTr1);
        // Create an ArrayAdapter using the Wallet array and a default spinner layout
        ArrayAdapter<Wallet> adapter = new ArrayAdapter<Wallet>(this, android.R.layout.simple_spinner_item, wallets);
	    // Specify the layout to use when the list of choices appears
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    // Apply the adapter to the spinner
	    spinner.setAdapter(adapter);
	    walletlistener = new WalletListener(changable, poscon);
	    spinner.setOnItemSelectedListener(walletlistener);
	    
	    Spinner spinner2 = (Spinner) findViewById(R.id.spinnerTr2);
	    // Specify the layout to use when the list of choices appears
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    // Apply the adapter to the spinner
	    spinner2.setAdapter(adapter);
	    walletlistenerto = new WalletListener();
	    spinner2.setOnItemSelectedListener(walletlistenerto);
	    
	    Spinner spinner3 = (Spinner) findViewById(R.id.spinnerTr3);
        // Create an ArrayAdapter using the String array and a default spinner layout
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, DataStorage.typesOfCurrency);
	    // Specify the layout to use when the list of choices appears
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    // Apply the adapter to the spinner
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
}
