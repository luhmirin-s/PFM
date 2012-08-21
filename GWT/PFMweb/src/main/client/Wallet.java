package main.client;

public class Wallet {
	
	private String name;
	private int value;
	
	public Wallet(String name, int value){
		this.name=name;
		this.value=value;
	}
	
	public String getName(){
		return name;
	}
	
	public int getValue(){
		return value;
	}
	
}
