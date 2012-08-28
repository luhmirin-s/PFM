package main.pfmandroid;

import main.pfmandroid.data.Currency;
import main.pfmandroid.data.PositionContainer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

/*
 * Class performs listening to the currency spinner change, allows us to track which currency user chose.
 */

public class MoneyListener implements OnItemSelectedListener{
	
	private Currency currency;
	private int curid;
	private String currencyCode;
	private TextView changable;
	PositionContainer test;
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View arg1, int pos,
			long id) {
		
		currency = (Currency) parent.getItemAtPosition(pos);
		curid = currency.getId();
		
		if(test != null)
			test.posOfCode = pos;
		
		if(changable != null){
			//double amount = DataStorage.listOfWallets.get(test.posOfWallet).getMoney().get(test.posOfCode).getAmount();
			
			//CharSequence message = String.valueOf(amount);
			
			//changable.setText(message);
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public String returnCurrencyType(){
		return currencyCode;
	}
	
	public int returnId(){
		return curid;
	}
	
	public MoneyListener(){
		
	}
	
	public MoneyListener(TextView what, PositionContainer mine){
		changable = what;
		test = mine;
	}
}
