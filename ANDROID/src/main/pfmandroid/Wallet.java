package main.pfmandroid;

import java.util.ArrayList;

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
	
	public void editCurrency(String code, double amount){
		for(int i=0; i < type.size(); i++){
			if(code.equals(type.get(i).getCode())){
				type.get(i).editMoney(amount);
				return;
			}
		}
	}
	
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