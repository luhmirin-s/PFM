package main.pfmandroid;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

public class WalletListener implements OnItemSelectedListener{
	
	private Wallet wallet;
	private TextView changable;
	PositionContainer test;
	private int pos;
	
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
		
		if(test != null)
			test.posOfWallet = pos;
		
		this.pos = pos;
		
		if(changable != null){
			double amount = DataStorage.listOfWallets.get(test.posOfWallet).getMoney().get(test.posOfCode).getAmount();
			
			CharSequence message = String.valueOf(amount);
			
			Log.d("WLposOfWallet", String.valueOf(test.posOfWallet));
			Log.d("WLposOfCode", String.valueOf(test.posOfCode));
			
			changable.setText(message);
		}
    }

	@Override
    public void onNothingSelected(AdapterView<?> parent) {
        
    }
	
	public Wallet returnObject(){
		return wallet;
	}
	
	public int returnPosition(){
		return pos;
	}
	
	WalletListener(TextView changable, PositionContainer mine){
		test = mine;
		this.changable = changable;
	}
	
	WalletListener(){
		
	}
}
