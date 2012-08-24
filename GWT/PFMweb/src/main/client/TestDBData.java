package main.client;

import java.util.ArrayList;

import main.client.data.Account;

public class TestDBData {
	
	public static ArrayList<Account> accountList = new ArrayList<Account>();
	
	public static void initData(){
		/*
		accountList.add(new Account(0, "Sberbank", new int[] {50, 250, 700}, new String[] {"USD", "RUR", "LVL"}));
		accountList.add(new Account(1, "Hansabank", new int[] {300, 250, 500}, new String[] {"USD", "RUR", "LVL"}));
		accountList.add(new Account(2, "Wallet", new int[] {75, 1000, 550}, new String[] {"USD", "RUR", "LVL"}));
		*/
	}
	
	public static ArrayList<Account> retrieveData(){
		return accountList;
	}
	
	public static void addAccount(Account acc){
		accountList.add(acc);
	}
	
	public static void removeAccount(Account acc){
		int id = accountList.indexOf(acc);
		accountList.remove(id);
	}
}
