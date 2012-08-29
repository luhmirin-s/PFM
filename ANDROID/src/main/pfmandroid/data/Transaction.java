package main.pfmandroid.data;
import java.util.Date;

/*
 * Class is the internal representation of a transaction within the application.
 * Transaction contains:
 * 1. Wallet which was used for transaction;
 * 2. Currency code;
 * 3. Currency amount that was expended/added/transfered;
 * 4. Date of transaction.
 * 
 */

public class Transaction {
	
	private int type;
	private int id;
	private String wallet;
	private String amount;
	private String text;
	private String date;
	
	public Transaction(int type, int id, String wallet, String amount, String text, String date){
		this.type = type;
		this.id = id;
		this.wallet = wallet;
		this.amount = amount;
		this.text = text;
		this.date = date;
	}
	
	public int getType(){
		return type;
	}
	
	public int getId(){
		return id;
	}
	
	public String getWallet(){
		return wallet;
	}
	
	public String getAmount(){
		return amount;
	}
	
	public String getText(){
		return text;
	}
	
	public String getDate(){
		return date;
	}
}