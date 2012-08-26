package main.pfmandroid;

/*
 * Money class, represents a certain currency in a wallet, has a code and how much of that currency is there.
 */

public class Money {
	private String cashCode;
	private double amount;
	
	Money(String code, double cash){
		cashCode = code;
		amount = cash;
	}
	
	public void editMoney(double newcash){
		amount = newcash;
	}
	
	public String getCode(){
		return cashCode;
	}
	
	public double getAmount(){
		return amount;
	}
	
	public String toString(){
		return cashCode;
	}
}
