package main.pfmandroid;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

public class MoneyListener implements OnItemSelectedListener{
	
	private String currencyCode;
	private TextView changable;
	PositionContainer test;
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View arg1, int pos,
			long id) {
		
		if(test != null)
			test.posOfCode = pos;
		
		if(changable != null){
			double amount = DataStorage.listOfWallets.get(test.posOfWallet).getMoney().get(test.posOfCode).getAmount();
			
			CharSequence message = String.valueOf(amount);
			
			Log.d("MLposOfWallet", String.valueOf(test.posOfWallet));
			Log.d("MLposOfCode", String.valueOf(test.posOfCode));
			
			changable.setText(message);
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public String returnCurrencyType(){
		return currencyCode;
	}
	
	public int returnPos(){
		return 0;
	}
	
	MoneyListener(){
		
	}
	
	MoneyListener(TextView what, PositionContainer mine){
		changable = what;
		test = mine;
	}
}
