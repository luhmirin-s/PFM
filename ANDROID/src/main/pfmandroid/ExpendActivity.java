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

public class ExpendActivity extends Activity {
	Wallet[] wallets;
	String[] categories;
	
    CategoryListener categorylistener;
    MoneyListener moneylistener;
    WalletListener walletlistener;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if(DataStorage.listOfWallets.size()==0)
        	DataStorage.populate();
        
        wallets = new Wallet[DataStorage.listOfWallets.size()];
        categories = new String[DataStorage.listOfCategories.size()];
        
        for(int i = 0; i < DataStorage.listOfWallets.size(); i++){
        	wallets[i] = DataStorage.listOfWallets.get(i);
        }
        
        for(int i = 0; i<DataStorage.listOfCategories.size(); i++){
        	categories[i] = DataStorage.listOfCategories.get(i);
        }
        
        setContentView(R.layout.activity_expend);
        
        TextView changable = (TextView) findViewById(R.id.textViewEx3);
        PositionContainer poscon = new PositionContainer();
        
        Spinner spinner = (Spinner) findViewById(R.id.spinnerEx1);
        // Create an ArrayAdapter using the Wallet array and a default spinner layout
        ArrayAdapter<Wallet> adapter = new ArrayAdapter<Wallet>(this, R.layout.spinneritem, R.id.spinneritem, wallets);
	    // Specify the layout to use when the list of choices appears
	    //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    // Apply the adapter to the spinner
	    spinner.setAdapter(adapter);
	    walletlistener = new WalletListener(changable, poscon);
	    spinner.setOnItemSelectedListener(walletlistener);
	    
	    Spinner spinner2 = (Spinner) findViewById(R.id.spinnerEx2);
        // Create an ArrayAdapter using the String array and a default spinner layout
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.spinneritem, R.id.spinneritem, categories);
	    // Specify the layout to use when the list of choices appears
	    //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    // Apply the adapter to the spinner
	    spinner2.setAdapter(adapter2);
	    categorylistener = new CategoryListener();
	    spinner2.setOnItemSelectedListener(categorylistener);
	    
	    Spinner spinner3 = (Spinner) findViewById(R.id.spinnerEx3);
        // Create an ArrayAdapter using the String array and a default spinner layout
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.spinneritem, R.id.spinneritem, DataStorage.typesOfCurrency);
	    // Specify the layout to use when the list of choices appears
	    //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    // Apply the adapter to the spinner
	    spinner3.setAdapter(adapter3);
	    moneylistener = new MoneyListener(changable, poscon);
	    spinner3.setOnItemSelectedListener(moneylistener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_expend, menu);
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
    protected void onStop(){
    	super.onStop();
    	wallets = null;
    	categories = null;
    }
    
    public void returnBack(View view){
    	this.finish();
    }
}
