package main.pfmandroid.activities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

import main.pfmandroid.MoneyListener;
import main.pfmandroid.R;
import main.pfmandroid.WalletListener;
import main.pfmandroid.R.id;
import main.pfmandroid.R.layout;
import main.pfmandroid.R.menu;
import main.pfmandroid.data.Currency;
import main.pfmandroid.data.DataStorage;
import main.pfmandroid.data.PositionContainer;
import main.pfmandroid.data.Wallet;
import main.pfmandroid.getdata.RetrieveTransferData;

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
 * Class represents transfer activity (Transfers option). Allows the user to transfer currency from one wallet to another.
 */

public class TransferActivity extends Activity {
	static TransferActivity reference;
    MoneyListener moneylistener;
    WalletListener walletlistenerfrom;
    WalletListener walletlistenerto;
	
    Wallet[] wallets;
    Currency[] currencies;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        RetrieveTransferData task = new RetrieveTransferData(this);
        task.execute();
        
        try{
        	task.get(3, TimeUnit.SECONDS);
        }catch(Exception e){
        	
        }
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
    }
    
    public void returnBack(View view){
    	
    	EditText textfield = (EditText) findViewById(R.id.editTextTr2);
    	
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
    	
    	if(walletlistenerfrom.returnId() == walletlistenerto.returnId()){
    		Context context = getApplicationContext();
    		CharSequence text = "The sender and recipient wallets must not be the same!";
    		int duration = Toast.LENGTH_LONG;

    		Toast toast = Toast.makeText(context, text, duration);
    		toast.setGravity(Gravity.CENTER, 0, 0);
    		toast.show();
    		return;
    	}
    	
    	CreateTransfer task = new CreateTransfer(amount);
    	task.execute();
    	this.finish();
    }
    
    private class CreateTransfer extends AsyncTask<String, Void, String>{
    	
    	private double amount;
    	
    	CreateTransfer(double amount){
    		this.amount = amount;
    	}

		@Override
		protected String doInBackground(String... params) {
			AndroidHttpClient client = AndroidHttpClient.newInstance("Android");
			
			HttpPost httpPost = new HttpPost("http://10.0.1.59/PFMWebService/jaxrs/transfer");
			String response = "";
			
			JSONObject send = new JSONObject();
		    
		    try {
		    	send.put("amount", amount);
		    	send.put("fromAccountId", walletlistenerfrom.returnId());
		    	send.put("toAccountId", walletlistenerto.returnId());
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
        
        currencies = new Currency[DataStorage.typesOfCurrency.size()];
        for(int i = 0; i < currencies.length; i++)
        	currencies[i] = DataStorage.typesOfCurrency.get(i);
        
        setContentView(R.layout.activity_transfer);
        
        TextView changable = (TextView) findViewById(R.id.textViewTr3);
        PositionContainer poscon = new PositionContainer();
        
        //First spinner will update the remaining amount of money (sender).
        Spinner spinner = (Spinner) findViewById(R.id.spinnerTr1);
        ArrayAdapter<Wallet> adapter = new ArrayAdapter<Wallet>(this, R.layout.spinneritem, R.id.spinneritem, wallets);
	    //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner.setAdapter(adapter);
	    walletlistenerfrom = new WalletListener(changable, poscon);
	    spinner.setOnItemSelectedListener(walletlistenerfrom);
	    
	    //Second spinner only tracks which wallet to transfer to, hence, doesn't need to synchronise.
	    Spinner spinner2 = (Spinner) findViewById(R.id.spinnerTr2);
	    //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner2.setAdapter(adapter);
	    walletlistenerto = new WalletListener();
	    spinner2.setOnItemSelectedListener(walletlistenerto);
	    
	    //Money spinner listener.
	    Spinner spinner3 = (Spinner) findViewById(R.id.spinnerTr3);
	    ArrayAdapter<Currency> adapter3 = new ArrayAdapter<Currency>(this, R.layout.spinneritem, R.id.spinneritem, currencies);
	    //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner3.setAdapter(adapter3);
	    if(DataStorage.typesOfCurrency.size() > 0){
		    moneylistener = new MoneyListener(changable, poscon);
		    spinner3.setOnItemSelectedListener(moneylistener);
	    }
    }
}
