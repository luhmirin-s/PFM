package main.pfmandroid.getdata;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.pfmandroid.activities.JournalActivity;
import main.pfmandroid.data.DataStorage;
import main.pfmandroid.data.Transaction;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

public class RetrieveTransactionData extends AsyncTask<String, Void, String> {
	private String transactions = "";
	
	private JournalActivity context;
	private ProgressDialog dialog;
	private int timeframe;
	
	public RetrieveTransactionData(JournalActivity test, int pos){
		context = test;
		dialog = new ProgressDialog(context);
		timeframe = pos+1;
		DataStorage.clear();
	}
	
	@Override
	protected void onPreExecute() {
        this.dialog.setMessage("Collecting data, please wait...");
        this.dialog.show();
    }
	
	@Override
	protected String doInBackground(String... arg0) {
		HttpGet getTransactions = new HttpGet(DataStorage.domain + "journal/list?userid=" + DataStorage.userId + "&timeframe=" + timeframe);
	    
	    getTransactions.addHeader("Accepts", "application/json");
        
        try {
        	AndroidHttpClient client1 = AndroidHttpClient.newInstance("Android");
        	HttpResponse executeTransactions = client1.execute(getTransactions);
          	          
        	if(executeTransactions.getStatusLine().getStatusCode() == 200){
        		InputStream transactionsContent = executeTransactions.getEntity().getContent();
        		BufferedReader currencyBuffer = new BufferedReader(new InputStreamReader(transactionsContent));
	          	              
        		String s = "";
        		while ((s = currencyBuffer.readLine()) != null) {
        			transactions += s;
        		}
        	}
        	client1.close();
        } catch (Exception e) {
          e.printStackTrace();
        }               
        return "";
	}
	
	@Override
	protected void onPostExecute(String result) {
		//Attempt to extract an array of currencies (more than one in database)
		try {
			JSONArray getArray = new JSONObject(transactions).getJSONArray("journalEntry");
			
			for(int i = 0; i < getArray.length(); i++){
				String wallet = ((JSONObject) getArray.get(i)).getString("accountName");
				String amount = ((JSONObject) getArray.get(i)).getString("amount");
				String date = ((JSONObject) getArray.get(i)).getString("date");
				String text = ((JSONObject) getArray.get(i)).getString("text");
				int id = ((JSONObject) getArray.get(i)).getInt("transactionId");
				int type = ((JSONObject) getArray.get(i)).getInt("type");
				DataStorage.listOfTransactions.add(new Transaction(type, id, wallet, amount, text, date));
			}
		} catch (JSONException e) {
			//If we reach here, it means we couldn't find currency array (only one in database)					
			//Create a single currency, extracted from database.
			try {
				JSONObject getObject = new JSONObject(transactions).getJSONObject("journalEntry");
				String wallet = getObject.getString("accountName");
				String amount = getObject.getString("amount");
				String date = getObject.getString("date");
				String text = getObject.getString("text");
				int id = getObject.getInt("transactionId");
				int type = getObject.getInt("type");
				DataStorage.listOfTransactions.add(new Transaction(type, id, wallet, amount, text, date));
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		if (dialog.isShowing()) {
            dialog.dismiss();
        }
		
		context.populateTable();
	}
}
