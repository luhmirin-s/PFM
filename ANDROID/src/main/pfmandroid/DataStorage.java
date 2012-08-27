package main.pfmandroid;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

/*
 * Internal data storage class, to keep all the data in one place, made static, because it is used more or less globally.
 */

public class DataStorage {
	static int userId;
	static String currentUsername;
	static ArrayList<Wallet> listOfWallets = new ArrayList<Wallet>();
    static ArrayList<String> listOfSources = new ArrayList<String>();
    static ArrayList<String> listOfCategories = new ArrayList<String>();
    static ArrayList<Transaction> listOfTransactions = new ArrayList<Transaction>();
    static ArrayList<String> typesOfCurrency = new ArrayList<String>();
    
    /*
     * Method for populating all the tables with various data to test the presentation of the data in the application.
     */
    public void getData(){
    	RetrieveData task = new RetrieveData();
    	task.execute();
    }
    
    //Asynchronous task for retrieving ALL the data.
    private class RetrieveData extends AsyncTask<String, Void, String>{
    	String currency = "";
		String wallets = "";
		String categories = "";
		
		@Override
		protected String doInBackground(String... arg0) {

		    HttpGet getCurrency = new HttpGet("http://10.0.1.59/PFMWebService/jaxrs/currency/list");
		    HttpGet getWallets = new HttpGet("http://10.0.1.59/PFMWebService/jaxrs/account/list/" + userId);
		    HttpGet getCategory = new HttpGet("http://10.0.1.59/PFMWebService/jaxrs/category/list/1");
		    getCurrency.addHeader("Accepts", "application/json");
	        getWallets.addHeader("Accepts", "application/json");
	        getCategory.addHeader("Accepts", "application/json");
	        
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
	        
	        try {
	        	AndroidHttpClient client3 = AndroidHttpClient.newInstance("Android");
	        	HttpResponse executeCategory = client3.execute(getCategory);
	        	
	        	if(executeCategory.getStatusLine().getStatusCode() == 200){
		        	InputStream categoryContent = executeCategory.getEntity().getContent();
		        	BufferedReader categoryBuffer = new BufferedReader(new InputStreamReader(categoryContent));
		        	
		        	String s = "";
		            while ((s = categoryBuffer.readLine()) != null) {
		        	  categories += s;
		            }
	        	}
	            client3.close();
	        } catch (Exception e) {
		          e.printStackTrace();
		    }
	        
	        return "";
		}
		
		@Override
		protected void onPostExecute(String result) {
			Log.d("Received currency", currency);
			Log.d("Received wallets", wallets);
			Log.d("Received category", categories);
			
			//Attempt to extract an array of currencies (more than one in database)
			try {
				JSONArray getArray = new JSONObject(currency).getJSONArray("currency");
				
				for(int i = 0; i < getArray.length(); i++){
					String code = ((JSONObject) getArray.get(i)).getString("code");
					typesOfCurrency.add(new String(code));
				}
			} catch (JSONException e) {
				//If we reach here, it means we couldn't find currency array (only one in database)					
				//Create a single currency, extracted from database.
				try {
					JSONObject getObject = new JSONObject(currency);
					String code = getObject.getString("code");
					typesOfCurrency.add(new String(code));
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
					String name = ((JSONObject) getArray.get(i)).getString("name");
					listOfWallets.add(new Wallet(name));
					for(int j = 0; j < typesOfCurrency.size(); j++)
						listOfWallets.get(i).addMoney(new Money(typesOfCurrency.get(j)));
				}
			} catch (JSONException e) {					
				//If we are here, there is no array -> Only one wallet for the user, extract it.
				try {
					JSONObject getObject = new JSONObject(wallets);
					String name = getObject.getString("name");
					listOfWallets.add(new Wallet(name));
					for(int j = 0; j < typesOfCurrency.size(); j++)
						listOfWallets.get(listOfWallets.size()-1).addMoney(new Money(typesOfCurrency.get(j)));
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			
			//Try to extract array of categories
			try {
				JSONArray getArray = new JSONObject(categories).getJSONArray("category");
				
				for(int i = 0; i < getArray.length(); i++){
					String name = ((JSONObject) getArray.get(i)).getString("name");
					listOfCategories.add(new String(name));
				}
			} catch (JSONException e) {				
				//Only one category defined (no array)
				try {
					JSONObject getObject = new JSONObject(categories);
					String name = getObject.getString("name");
					listOfCategories.add(new String(name));
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
    }
}
