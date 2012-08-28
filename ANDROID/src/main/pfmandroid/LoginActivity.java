package main.pfmandroid;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import main.pfmandroid.activities.MainActivity;
import main.pfmandroid.data.DataStorage;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
    	
    	if(password.length() == 0 || username.length() == 0){
    		Context context = getApplicationContext();
			CharSequence text = "Please enter both username and password.";
			int duration = Toast.LENGTH_LONG;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
			return;
    	}
    	
    	tryLogin task = new tryLogin(username, password, this);
    	task.execute();
    }
    
    private class tryLogin extends AsyncTask<String, Void, String>{
    	private String username;
    	private String password;
    	private LoginActivity context;
    	private ProgressDialog dialog;
    	
    	tryLogin(String user, String pass, LoginActivity ref){
    		username = user;
    		password = pass;
    		context = ref;
    		dialog = new ProgressDialog(context);
    	}
    	
    	@Override
    	protected void onPreExecute() {
            this.dialog.setMessage("Logging in...");
            this.dialog.show();
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
	          if( execute.getStatusLine().getStatusCode() == 404 ){
	        	  client.close();
	        	  return "";
	          }

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
				else{
					Context context = getApplicationContext();
					CharSequence text = "You have entered incorrect username/password combination.";
					int duration = Toast.LENGTH_LONG;

					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
			}
			
			if (dialog.isShowing()) {
	            dialog.dismiss();
	        }
		}
    }
}
