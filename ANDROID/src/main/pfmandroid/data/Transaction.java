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
	
	private String wallet;
	private String code;
	private double value;
	private Date date;
	
	public Transaction(String wallet, String code, double value){
		this.wallet = wallet;
		this.code = code;
		this.value = value;
		date = new Date();
	}
	
	public String getName(){
		return wallet;
	}
	
	public String getCode(){
		return code;
	}
	
	public double getAmount(){
		return value;
	}
	
	public Date getDate(){
		return date;
	}
}
