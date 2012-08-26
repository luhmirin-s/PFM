package main.pfmandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

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
    	this.finish();
    	Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent);
    }
}
