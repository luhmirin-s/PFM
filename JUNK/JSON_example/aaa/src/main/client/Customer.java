package main.client;

import java.util.ArrayList;

public class Customer {

	
	private String firstName;
	private String lastName;
	private ArrayList<Money> money;
	
	public Customer(String firstName,String lastName, ArrayList<Money> money){
		setFirstName(firstName);
		setLastName(lastName);
		setMoney(money);	
		
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public ArrayList<Money> getMoney() {
		return money;
	}
	public void setMoney(ArrayList<Money> money) {
		this.money = money;
	}
	
	
}
