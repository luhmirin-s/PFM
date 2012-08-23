package main.pfmandroid;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

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
		        DefaultHttpClient client = new DefaultHttpClient();

				HttpGet httpGet = new HttpGet("http://www.google.com");
		        //httpGet.addHeader("Accept", "application/json");
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
		    }
    	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_connection_test, menu);
        return true;
    }
}
