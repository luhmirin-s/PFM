package main.pfmandroid;

import main.pfmandroid.data.DataStorage;
import main.pfmandroid.data.PositionContainer;
import main.pfmandroid.data.Wallet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

/*
 * Class performs listening to the account (wallet) spinner change, allows us to track which wallet user chose.
 */

public class WalletListener implements OnItemSelectedListener{
	
	private Wallet wallet = null;
	private TextView changable = null;
	PositionContainer test = null;
	private int id;
	
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
		
		wallet = (Wallet) parent.getItemAtPosition(pos);
		this.id = wallet.getId();
		
		if(test != null)
			test.posOfWallet = pos;
		
		if(changable != null){
			double amount = DataStorage.listOfWallets.get(test.posOfWallet).getMoney().get(test.posOfCode).getAmount();
			
			CharSequence message = String.valueOf(amount);
			
			changable.setText(message);
		}
    }

	@Override
    public void onNothingSelected(AdapterView<?> parent) {
        
    }
	
	public Wallet returnObject(){
		return wallet;
	}
	
	public int returnId(){
		return id;
	}
	
	public WalletListener(TextView changable, PositionContainer mine){
		test = mine;
		this.changable = changable;
	}
	
	public WalletListener(){
		
	}
}
