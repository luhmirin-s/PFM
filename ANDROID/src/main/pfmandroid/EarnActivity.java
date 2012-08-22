package main.pfmandroid;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class EarnActivity extends Activity{
    Wallet[] wallets;
    String[] categories;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        DataStorage.listOfWallets.add(new Wallet("Test", 555));
        DataStorage.listOfWallets.add(new Wallet("Test2", 666));
        DataStorage.listOfWallets.add(new Wallet("Test3", 888));
        
        DataStorage.listOfSources.add(new String("Paycheck"));
        DataStorage.listOfSources.add(new String("Bank"));
        
        wallets = new Wallet[DataStorage.listOfWallets.size()];
        categories = new String[DataStorage.listOfSources.size()];
        
        for(int i = 0; i<DataStorage.listOfWallets.size(); i++){
        	wallets[i] = DataStorage.listOfWallets.get(i);
        }
        
        for(int i = 0; i<DataStorage.listOfSources.size(); i++){
        	categories[i] = DataStorage.listOfSources.get(i);
        }
        
        setContentView(R.layout.activity_earn);
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        // Create an ArrayAdapter using the Wallet array and a default spinner layout
        ArrayAdapter<Wallet> adapter = new ArrayAdapter<Wallet>(this, android.R.layout.simple_spinner_item, wallets);
	    // Specify the layout to use when the list of choices appears
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    // Apply the adapter to the spinner
	    spinner.setAdapter(adapter);
	    WalletListener walletlistener = new WalletListener();
	    spinner.setOnItemSelectedListener(walletlistener);
	    
	    Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        // Create an ArrayAdapter using the String array and a default spinner layout
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
	    // Specify the layout to use when the list of choices appears
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    // Apply the adapter to the spinner
	    spinner2.setAdapter(adapter2);
	    CategoryListener categorylistener = new CategoryListener();
	    spinner2.setOnItemSelectedListener(categorylistener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_earn, menu);
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
}
