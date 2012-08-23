package main.pfmandroid;
import java.util.Calendar;
import java.util.Date;


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
