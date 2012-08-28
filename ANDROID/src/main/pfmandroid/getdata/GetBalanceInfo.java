package main.pfmandroid.getdata;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.pfmandroid.data.DataStorage;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

public class GetBalanceInfo extends AsyncTask<String, Void, String>{
	@Override
	protected String doInBackground(String... arg0) {
		
		String balance = "";
		for(int i = 0; i < DataStorage.listOfWallets.size(); i++){
			balance = "";
			HttpGet getBalance = new HttpGet("http://10.0.1.59/PFMWebService/jaxrs/balance/list/" + DataStorage.listOfWallets.get(i).getId());
	        getBalance.addHeader("Accepts", "application/json");
	        
	        try {
	        	AndroidHttpClient client4 = AndroidHttpClient.newInstance("Android");
	        	HttpResponse executeBalance = client4.execute(getBalance);
	        	
	        	if(executeBalance.getStatusLine().getStatusCode() == 200){
		        	InputStream balanceContent = executeBalance.getEntity().getContent();
		        	BufferedReader balanceBuffer = new BufferedReader(new InputStreamReader(balanceContent));
		        	
		        	String s = "";
		            while ((s = balanceBuffer.readLine()) != null) {
		        	  balance += s;
		            }
	        	}
	            client4.close();
	        } catch (Exception e) {
		          e.printStackTrace();
		    }
	        
	        Log.d("Received balance", balance);
	        
			try {
				JSONArray getArray = new JSONObject(balance).getJSONArray("balance");
				
				for(int j = 0; j < getArray.length(); j++){
					int id = ((JSONObject) getArray.get(j)).getInt("currencyId");
					double amount = ((JSONObject) getArray.get(j)).getDouble("sum");
					DataStorage.listOfWallets.get(i).editCurrency(id, amount);
				}
			} catch (JSONException e) {					
				try {
					JSONObject getObject = new JSONObject(balance);
					int id = getObject.getInt("currencyId");
					double amount = getObject.getDouble("sum");
					DataStorage.listOfWallets.get(i).editCurrency(id, amount);
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		return "";
	}
}