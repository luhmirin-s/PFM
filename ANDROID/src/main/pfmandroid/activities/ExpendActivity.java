package main.pfmandroid.activities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

import main.pfmandroid.CategoryListener;
import main.pfmandroid.MoneyListener;
import main.pfmandroid.R;
import main.pfmandroid.WalletListener;
import main.pfmandroid.R.id;
import main.pfmandroid.R.layout;
import main.pfmandroid.R.menu;
import main.pfmandroid.data.Category;
import main.pfmandroid.data.Currency;
import main.pfmandroid.data.DataStorage;
import main.pfmandroid.data.PositionContainer;
import main.pfmandroid.data.Wallet;
import main.pfmandroid.getdata.RetrieveExpendData;

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
 * Class represents the Expenses option of the Main Menu. Responsible for allowing the user to make a "purchase"
 * Very similar to EarnActivity, hence, less comments, because implementation is mostly the same.
 */

public class ExpendActivity extends Activity {
	
    CategoryListener categorylistener;
    MoneyListener moneylistener;
    WalletListener walletlistener;
    PositionContainer poscon;
    
    Wallet[] wallets;
    Category[] categories;
    Currency[] currencies;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        RetrieveExpendData task = new RetrieveExpendData(this);
        task.execute();
        
        try{
        	task.get(3, TimeUnit.SECONDS);
        }catch(Exception e){
        	
        }
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
    }
    
    public void returnBack(View view){
    	
    	EditText textfield = (EditText) findViewById(R.id.editTextEx2);
    	
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
    	
    	CreateExpense task = new CreateExpense(amount);
    	task.execute();
    	this.finish();
    }
    
    private class CreateExpense extends AsyncTask<String, Void, String>{
    	
    	private double amount;
    	
    	CreateExpense(double amount){
    		this.amount = amount;
    	}

		@Override
		protected String doInBackground(String... params) {
			AndroidHttpClient client = AndroidHttpClient.newInstance("Android");
			
			HttpPost httpPost = new HttpPost("http://10.0.1.59/PFMWebService/jaxrs/expense");
			String response = "";
			
			JSONObject send = new JSONObject();
		    
		    try {
		    	send.put("amount", amount);
		    	send.put("accountId", walletlistener.returnId());
		    	send.put("categoryId", categorylistener.returnId());
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
        
        categories = new Category[DataStorage.listOfCategories.size()];
        for(int i = 0; i < categories.length; i++)
        	categories[i] = DataStorage.listOfCategories.get(i);
        
        currencies = new Currency[DataStorage.typesOfCurrency.size()];
        for(int i = 0; i < currencies.length; i++)
        	currencies[i] = DataStorage.typesOfCurrency.get(i);
        
        setContentView(R.layout.activity_expend);
        
        TextView changable = (TextView) findViewById(R.id.textViewEx3);
        poscon = new PositionContainer();
                        
        Spinner spinner = (Spinner) findViewById(R.id.spinnerEx1);
        ArrayAdapter<Wallet> adapter = new ArrayAdapter<Wallet>(this, R.layout.spinneritem, R.id.spinneritem, wallets);
	    //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner.setAdapter(adapter);
	    adapter.notifyDataSetChanged();
	    if(DataStorage.listOfWallets.size() > 0){
	    	walletlistener = new WalletListener(changable, poscon);
	    	spinner.setOnItemSelectedListener(walletlistener);
	    }
	    
	    Spinner spinner2 = (Spinner) findViewById(R.id.spinnerEx2);
	    ArrayAdapter<Category> adapter2 = new ArrayAdapter<Category>(this, R.layout.spinneritem, R.id.spinneritem, categories);
	    //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner2.setAdapter(adapter2);
	    if(DataStorage.listOfCategories.size() > 0){
		    categorylistener = new CategoryListener();
		    spinner2.setOnItemSelectedListener(categorylistener);
	    }
	    
	    Spinner spinner3 = (Spinner) findViewById(R.id.spinnerEx3);
	    ArrayAdapter<Currency> adapter3 = new ArrayAdapter<Currency>(this, R.layout.spinneritem, R.id.spinneritem, currencies);
	    //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner3.setAdapter(adapter3);
	    if(DataStorage.typesOfCurrency.size() > 0){
	    	moneylistener = new MoneyListener(changable, poscon);
	    	spinner3.setOnItemSelectedListener(moneylistener);
	    }
    }
}
