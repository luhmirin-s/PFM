package main.pfmandroid;

/*
 * Money class, represents a certain currency in a wallet, has a code and how much of that currency is there.
 */

public class Money {
	private int id;
	private String cashCode;
	private double amount;
	
	Money(int id, String code, double cash){
		this.id = id;
		cashCode = code;
		amount = cash;
	}
	
	Money(int id, String code){
		this.id = id;
		cashCode = code;
		amount = 0;
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
