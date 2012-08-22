package main.pfmandroid;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class WalletListener implements OnItemSelectedListener{

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
		Wallet test = (Wallet) parent.getItemAtPosition(pos);
    	Log.d("Wallet", test.getName());
    }

	@Override
    public void onNothingSelected(AdapterView<?> parent) {
        
    }

}
