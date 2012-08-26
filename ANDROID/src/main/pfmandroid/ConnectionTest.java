package main.pfmandroid;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

/*
 * Class implements several HTTP requests methods (GET, POST, DELETE, PUT), tests sending JSON object to the server,
 * also contains JSON parsing (manual) methods in the onPostExecute() method. 
 */

public class ConnectionTest extends Activity {
	
	TextView changable;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_test);
        
        changable = (TextView) findViewById(R.id.connectText);
        ReadContent task = new ReadContent();
        task.execute();
    }
    
    private class ReadContent extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... arg0) {
			String response = "";

		    HttpClient client = AndroidHttpClient.newInstance("Android");
			
		    //HttpGet httpGet = new HttpGet("http://10.0.1.59/PFMWebService/jaxrs/user");
		    //HttpPost httpGet = new HttpPost("http://10.0.1.59/PFMWebService/jaxrs/user");
		    HttpPut httpGet = new HttpPut("http://10.0.1.59/PFMWebService/jaxrs/user");
		    //HttpDelete httpGet = new HttpDelete("http://10.0.1.59/PFMWebService/jaxrs/user");
		    
		    //String jsonString="{\"username\" : \"Vasja\", \"password\" : \"javasja\", \"email\" : \"vasja@pupkin.ru\"}";
		    JSONObject send = new JSONObject();
		    
		    try {
		    	send.put("email", "masja@vasja.vasja666");
		    	send.put("password", "javasja");
		    	send.put("username", "Asshole666");
			} catch (JSONException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

		    try {
		    	//First method of sending.
				/*
				 httpGet.setEntity(new ByteArrayEntity(
					    send.toString().getBytes("UTF8")));
				 httpGet.addHeader("Content-Type", "application/json");
				*/
		    	
		    	//Second method of sending.
		    	StringEntity se = new StringEntity(send.toString());  
	            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
	            httpGet.setEntity(se);
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//HttpGet httpGet = new HttpGet("http://10.0.1.59/PFMWebService/jaxrs/user");
		    //HttpDelete httpGet = new HttpDelete("http://10.0.1.59/PFMWebService/jaxrs/source/");
	        //httpGet.addHeader("Accept", "application/json");
	        //httpGet.addHeader("Accept", "text/plain");
	        try {
	          HttpResponse execute = client.execute(httpGet);
	          InputStream content = execute.getEntity().getContent();

	          BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
	          String s = "";
	          while ((s = buffer.readLine()) != null) {
	            response += s;
	          }
	        } catch (Exception e) {
	          e.printStackTrace();
	        }
			
	        return response;
		}
		
		@Override
		protected void onPostExecute(String result) {
			changable.setText(result);
			
			/*
			String parsedResult = "";
			try {
				JSONObject test = new JSONObject(result);
				JSONObject retrieve;
				
				parsedResult += ("Source id: " + test.getInt("id") + "\n");
				parsedResult += ("Source name: " + test.getString("name") + "\n\n");
				
				
				retrieve = test.getJSONObject("user");
				parsedResult += ("User email: " + retrieve.getString("email") + "\n");
				parsedResult += ("User login: " + retrieve.getString("username") + "\n");
				parsedResult += ("User password: " + retrieve.getString("password") + "\n");
				
				changable.setText(parsedResult);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
		    }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_connection_test, menu);
        return true;
    }
}
