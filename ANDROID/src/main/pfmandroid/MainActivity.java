package main.pfmandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

/*
 * Main menu, does exactly what it says on the tin, several buttons for several options, doesn't perform computation.
 */

public class MainActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void expenditureTransaction(View view){
    	Intent intent = new Intent(this, ExpendActivity.class);
    	startActivity(intent);
    }
    
    public void earnTransaction(View view){
    	Intent intent = new Intent(this, EarnActivity.class);
    	startActivity(intent);
    }
    
    public void transferTransaction(View view){
    	Intent intent = new Intent(this, TransferActivity.class);
    	startActivity(intent);
    }
    
    public void balanceShow(View view){
    	Intent intent = new Intent(this, BalanceActivity.class);
    	startActivity(intent);
    }
    
    public void journalShow(View view){
    	Intent intent = new Intent(this, JournalActivity.class);
    	startActivity(intent);
    }
    
    public void connectionTest(View view){
    	Intent intent = new Intent(this, ConnectionTest.class);
    	startActivity(intent);
    }
}
