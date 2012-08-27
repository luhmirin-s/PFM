package main.pfmandroid;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/*
 * Login action. Class responsible for accepting username/password and checking it using the web service.
 */

public class LoginActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login, menu);
        return true;
    }
    
    public void login(View view){
    	TextView userinput = (TextView) findViewById(R.id.usernameentry);
    	String username = userinput.getText().toString();
    	
    	TextView passinput = (TextView) findViewById(R.id.passwordentry);
    	String password = passinput.getText().toString();
    	
    	tryLogin task = new tryLogin(username, password);
    	task.execute();
    }
    
    private class tryLogin extends AsyncTask<String, Void, String>{
    	private String username;
    	private String password;
    	
    	tryLogin(String user, String pass){
    		username = user;
    		password = pass;
    	}
    	
		@Override
		protected String doInBackground(String... arg0) {
			
			String response = "";

		    AndroidHttpClient client = AndroidHttpClient.newInstance("Android");
			
		    HttpPost httpGet = new HttpPost("http://10.0.1.59/PFMWebService/jaxrs/user");
	        try {
	          httpGet.addHeader("Content-Type", "application/x-www-form-urlencodedForm");
	          httpGet.addHeader("Accept", "application/json");
	          
	          List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	          nameValuePairs.add(new BasicNameValuePair("username", username));
	          nameValuePairs.add(new BasicNameValuePair("password", password));
	          httpGet.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	          HttpResponse execute = client.execute(httpGet);
	          if( execute.getStatusLine().getStatusCode() == 404 )
	        	  return "";
	          
	          InputStream content = execute.getEntity().getContent();

	          BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
	          String s = "";
	          while ((s = buffer.readLine()) != null) {
	            response += s;
	          }
	        } catch (Exception e) {
	          e.printStackTrace();
	        }
	        
	        client.close();
	        return response;
		}
		
		@Override
		protected void onPostExecute(String result) {
			try {
				if(result.length() > 0){
					JSONObject getObject = new JSONObject(result);
					
					String retrievedUsername = getObject.optString("username");
					if(retrievedUsername.length() > 0){
						DataStorage.currentUsername = retrievedUsername;
						DataStorage.userId = getObject.getInt("id");
						Intent intent = new Intent(getApplicationContext() ,MainActivity.class);
				    	startActivity(intent);
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
}
