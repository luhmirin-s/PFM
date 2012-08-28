package main.pfmandroid.data;

import java.util.ArrayList;

/*
 * Internal representation of a Wallet, contains the following:
 * 1. Name of wallet
 * 2. Array of types of currency (Using Money array) that the user has.
 */

public class Wallet {
	private int id;
	private String name;
	private ArrayList<Money> type;
	
	public Wallet(){
		name = "default";
		type = new ArrayList<Money>();
	}
	
	public Wallet(int id, String name){
		this.id = id;
		this.name = name;
		type = new ArrayList<Money>();
	}
		
	public void setName(String name){
		this.name = name;
	}
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public void addMoney(Money mon){
		type.add(mon);
	}
	
	public void addMoney(int id, String name, double currency){
		type.add(new Money(id, name, currency));
	}
	
	public ArrayList<Money> getMoney(){
		return type;
	}
	
	public boolean editCurrency(int id, double amount){
		for(int i=0; i < type.size(); i++){
			if(id == type.get(i).getId()){
				type.get(i).editMoney(amount);
				return true;
			}
		}
		return false;
	}
	
	//Search for the currency by the given id.
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