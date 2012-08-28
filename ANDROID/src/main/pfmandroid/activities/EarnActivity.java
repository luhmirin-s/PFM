package main.pfmandroid.activities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

import main.pfmandroid.MoneyListener;
import main.pfmandroid.R;
import main.pfmandroid.SourceListener;
import main.pfmandroid.WalletListener;
import main.pfmandroid.R.id;
import main.pfmandroid.R.layout;
import main.pfmandroid.R.menu;
import main.pfmandroid.data.Currency;
import main.pfmandroid.data.DataStorage;
import main.pfmandroid.data.PositionContainer;
import main.pfmandroid.data.Source;
import main.pfmandroid.data.Wallet;
import main.pfmandroid.getdata.RetrieveEarnData;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/*
 * Class represents the Income option from the Main Menu. Class responsible for allowing the user to make
 * transaction that increases the wallets current amount of money from some sort of income source.
 */

public class EarnActivity extends Activity{
	
    SourceListener sourcelistener;
    MoneyListener moneylistener;
    WalletListener walletlistener;
    PositionContainer poscon;
   
    Wallet[] wallets;
    Source[] sources;
    Currency[] currencies;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        RetrieveEarnData task = new RetrieveEarnData(this);
        task.execute();
        
        try{
        	task.get(3, TimeUnit.SECONDS);
        }catch(Exception e){
        	
        }
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
    
    @Override
    protected void onStop(){
    	super.onStop();
    }
    
public void returnBack(View view){
    	
    	EditText textfield = (EditText) findViewById(R.id.editTextEa2);
    	
    	String value = textfield.getText().toString();
    	
    	if(value.length() == 0){
    		Context context = getApplicationContext();
    		CharSequence text = "Please enter amount!";
    		int duration = Toast.LENGTH_LONG;

    		Toast toast = Toast.makeText(context, text, duration);
    		toast.setGravity(Gravity.CENTER, 0, 0);
    		toast.show();
    		return;
    	}
    	
    	double amount = Double.parseDouble(value);
    	
    	if(amount == 0 || amount < 0){
    		Context context = getApplicationContext();
    		CharSequence text = "Please enter non-zero positive amount!";
    		int duration = Toast.LENGTH_LONG;

    		Toast toast = Toast.makeText(context, text, duration);
    		toast.setGravity(Gravity.CENTER, 0, 0);
    		toast.show();
    		return;
    	}
    	
    	CreateIncome task = new CreateIncome(amount);
    	task.execute();
    	this.finish();
    }
    
    private class CreateIncome extends AsyncTask<String, Void, String>{
    	
    	private double amount;
    	
    	CreateIncome(double amount){
    		this.amount = amount;
    	}

		@Override
		protected String doInBackground(String... params) {
			AndroidHttpClient client = AndroidHttpClient.newInstance("Android");
			
			HttpPost httpPost = new HttpPost("http://10.0.1.59/PFMWebService/jaxrs/income");
			String response = "";
			
			JSONObject send = new JSONObject();
		    
		    try {
		    	send.put("amount", amount);
		    	send.put("accountId", walletlistener.returnId());
		    	send.put("sourceId", sourcelistener.returnId());
		    	send.put("currencyId", moneylistener.returnId());
			} catch (JSONException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		    
		    try {
		    	StringEntity se = new StringEntity(send.toString());  
	            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
	            httpPost.setEntity(se);
		    } catch (UnsupportedEncodingException e){
		    	
		    }
		    
		    try {
				HttpResponse execute = client.execute(httpPost);
				
				InputStream content = execute.getEntity().getContent();

		        BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
		        String s = "";
		        while ((s = buffer.readLine()) != null) {
		            response += s;
		        }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    Log.d("Received response", response);
			return null;
		}
    }
    
    public void displayData(){
    	wallets = new Wallet[DataStorage.listOfWallets.size()];
        for(int i = 0; i < wallets.length; i++)
        	wallets[i] = DataStorage.listOfWallets.get(i);
        
        sources = new Source[DataStorage.listOfSources.size()];
        for(int i = 0; i < sources.length; i++)
        	sources[i] = DataStorage.listOfSources.get(i);
        
        currencies = new Currency[DataStorage.typesOfCurrency.size()];
        for(int i = 0; i < currencies.length; i++)
        	currencies[i] = DataStorage.typesOfCurrency.get(i);
        
        setContentView(R.layout.activity_earn);
        
        TextView changable = (TextView) findViewById(R.id.textViewEa3);
        //Create a class in which two integers are incapsulated, so that the listeners can share it and change it together.
        poscon = new PositionContainer();
        
        //
        Spinner spinner = (Spinner) findViewById(R.id.spinnerEa1);
        // Create an ArrayAdapter using the Wallet array and a custom spinneritem layout (res/layout)
        ArrayAdapter<Wallet> adapter = new ArrayAdapter<Wallet>(this, R.layout.spinneritem, R.id.spinneritem, wallets);
	    // Specify the layout to use when the list of choices appears
	    //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    // Apply the adapter to the spinner and add a listener so that we can track changes
	    spinner.setAdapter(adapter);
	    if(DataStorage.listOfWallets.size() > 0){
		    walletlistener = new WalletListener(changable, poscon);
		    spinner.setOnItemSelectedListener(walletlistener);
	    }
	    
	    //Same as previous, only different spinner and different listener (category this time)
	    Spinner spinner2 = (Spinner) findViewById(R.id.spinnerEa2);
        ArrayAdapter<Source> adapter2 = new ArrayAdapter<Source>(this, R.layout.spinneritem, R.id.spinneritem, sources);
	    //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner2.setAdapter(adapter2);
	    if(DataStorage.listOfSources.size() > 0){
		    sourcelistener = new SourceListener();
		    spinner2.setOnItemSelectedListener(sourcelistener);
	    }
	    
	    //Same thing with currency type spinner, assign a money listener.
	    Spinner spinner3 = (Spinner) findViewById(R.id.spinnerEa3);
        ArrayAdapter<Currency> adapter3 = new ArrayAdapter<Currency>(this, R.layout.spinneritem, R.id.spinneritem, currencies);
	    //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner3.setAdapter(adapter3);
	    if(DataStorage.typesOfCurrency.size() > 0){
	    	moneylistener = new MoneyListener(changable, poscon);
	    	spinner3.setOnItemSelectedListener(moneylistener);
	    }
    }
}
