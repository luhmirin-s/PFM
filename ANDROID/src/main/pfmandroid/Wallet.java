package main.pfmandroid;

public class Wallet {
	private String name;
	private double amount;
	
	Wallet(){
		name = "default";
		amount = 0;
	}
	
	Wallet(String name){
		this.name = name;
		amount = 0;
	}
	
	Wallet(String name, double amount){
		this.name = name;
		this.amount = amount;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setAmount(double amount){
		this.amount = amount;
	}
	
	public double getAmount(){
		return amount;
	}
	
	@Override
	public String toString(){
		return name;
	}
}