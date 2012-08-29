package main.pfmandroid.getdata;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

import main.pfmandroid.activities.BalanceActivity;
import main.pfmandroid.data.Currency;
import main.pfmandroid.data.DataStorage;
import main.pfmandroid.data.Money;
import main.pfmandroid.data.Wallet;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

public class RetrieveBalanceData  extends AsyncTask<String, Void, String>{
	private String currency = "";
	private String wallets = "";
	
	protected BalanceActivity context;
	protected ProgressDialog dialog;
	
	public RetrieveBalanceData(BalanceActivity test){
		context = test;
		dialog = new ProgressDialog(context);
		DataStorage.clear();
	}
	
	@Override
	protected void onPreExecute() {
        this.dialog.setMessage("Collecting data, please wait...");
        this.dialog.show();
    }
	
	@Override
	protected String doInBackground(String... arg0) {

	    HttpGet getCurrency = new HttpGet(DataStorage.domain + "currency/list");
	    HttpGet getWallets = new HttpGet(DataStorage.domain + "account/list/" + DataStorage.userId);
	    
	    getCurrency.addHeader("Accepts", "application/json");
        getWallets.addHeader("Accepts", "application/json");
        
        try {
        	AndroidHttpClient client1 = AndroidHttpClient.newInstance("Android");
        	HttpResponse executeCurrency = client1.execute(getCurrency);
          	          
        	if(executeCurrency.getStatusLine().getStatusCode() == 200){
        		InputStream currencyContent = executeCurrency.getEntity().getContent();
        		BufferedReader currencyBuffer = new BufferedReader(new InputStreamReader(currencyContent));
	          	              
        		String s = "";
        		while ((s = currencyBuffer.readLine()) != null) {
        			currency += s;
        		}
        	}
        	client1.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
        
        try {
        	AndroidHttpClient client2 = AndroidHttpClient.newInstance("Android");
        	HttpResponse executeWallets = client2.execute(getWallets);
        	
        	if(executeWallets.getStatusLine().getStatusCode() == 200){
	        	InputStream walletContent = executeWallets.getEntity().getContent();
	        	BufferedReader walletBuffer = new BufferedReader(new InputStreamReader(walletContent));
	        	
	        	String s = "";
	            while ((s = walletBuffer.readLine()) != null) {
	              wallets += s;
	            }
        	}
            client2.close();
        } catch (Exception e) {
	          e.printStackTrace();
	    }
                
        return "";
	}
	
	@Override
	protected void onPostExecute(String result) {
		//Attempt to extract an array of currencies (more than one in database)
		try {
			JSONArray getArray = new JSONObject(currency).getJSONArray("currency");
			
			for(int i = 0; i < getArray.length(); i++){
				int id = ((JSONObject) getArray.get(i)).getInt("id");
				String code = ((JSONObject) getArray.get(i)).getString("code");
				DataStorage.typesOfCurrency.add(new Currency(id, code));
			}
		} catch (JSONException e) {
			//If we reach here, it means we couldn't find currency array (only one in database)					
			//Create a single currency, extracted from database.
			try {
				JSONObject getObject = new JSONObject(currency).getJSONObject("currency");
				int id = getObject.getInt("id");
				String code = getObject.getString("code");
				DataStorage.typesOfCurrency.add(new Currency(id, code));
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		//Try to extract an array of wallets.
		try {
			JSONArray getArray = new JSONObject(wallets).getJSONArray("account");
			
			//Add all wallets and for each wallet, every currency without any amount.
			for(int i = 0; i < getArray.length(); i++){
				int id = ((JSONObject) getArray.get(i)).getInt("id");
				String name = ((JSONObject) getArray.get(i)).getString("name");
				DataStorage.listOfWallets.add(new Wallet(id, name));
				for(int j = 0; j < DataStorage.typesOfCurrency.size(); j++){
					DataStorage.listOfWallets.get(i).addMoney(new Money(
								DataStorage.typesOfCurrency.get(j).getId(),
								DataStorage.typesOfCurrency.get(j).getCode()
								)
							);
				}
			}
		} catch (JSONException e) {					
			//If we are here, there is no array -> Only one wallet for the user, extract it.
			try {
				JSONObject getObject = new JSONObject(wallets).getJSONObject("account");
				int id = getObject.getInt("id");
				String name = getObject.getString("name");
				DataStorage.listOfWallets.add(new Wallet(id, name));
				for(int j = 0; j < DataStorage.typesOfCurrency.size(); j++){
					DataStorage.listOfWallets.get(DataStorage.listOfWallets.size()-1).addMoney(new Money(
								DataStorage.typesOfCurrency.get(j).getId(),
								DataStorage.typesOfCurrency.get(j).getCode()
								)
							);
				}
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		GetBalanceInfo innertask = new GetBalanceInfo();
		innertask.execute();
		
		try {
			innertask.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (dialog.isShowing()) {
            dialog.dismiss();
        }
		context.fillBalanceTable();
	}
}