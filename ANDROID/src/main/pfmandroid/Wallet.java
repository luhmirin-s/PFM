package main.pfmandroid;

import java.util.ArrayList;

/*
 * Internal representation of a Wallet, contains the following:
 * 1. Name of wallet
 * 2. Array of types of currency (Using Money array) that the user has.
 */

public class Wallet {
	private String name;
	private ArrayList<Money> type;
	
	Wallet(){
		name = "default";
		type = new ArrayList<Money>();
	}
	
	Wallet(String name){
		this.name = name;
		type = new ArrayList<Money>();
	}
		
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void addMoney(Money mon){
		type.add(mon);
	}
	
	public void addMoney(String name, double currency){
		type.add(new Money(name, currency));
	}
	
	public ArrayList<Money> getMoney(){
		return type;
	}
	
	//Not used right now
	public void editCurrency(String code, double amount){
		for(int i=0; i < type.size(); i++){
			if(code.equals(type.get(i).getCode())){
				type.get(i).editMoney(amount);
				return;
			}
		}
	}
	
	//Search for the currency by the given parameter (string).
	public Money findCurrency(String code){
		for(int i=0; i < type.size(); i++){
			if(code.equals(type.get(i).getCode()))
				return type.get(i);
		}
		return null;
	}
	@Override
	public String toString(){
		return name;
	}
}