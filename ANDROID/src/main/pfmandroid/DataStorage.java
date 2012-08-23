package main.pfmandroid;

import java.util.ArrayList;

public class DataStorage {
	static ArrayList<Wallet> listOfWallets = new ArrayList<Wallet>();
    static ArrayList<String> listOfSources = new ArrayList<String>();
    static ArrayList<String> listOfCategories = new ArrayList<String>();
    static ArrayList<Transaction> listOfTransactions = new ArrayList<Transaction>();
    static String[] typesOfCurrency = {"USD", "RUB", "LVL"};
    
    static public void populate(){
    	Wallet p = new Wallet("My account");
    	int i = 100;
    	for(String x : typesOfCurrency){
    		p.addMoney(x, i);
    		i*=10;
    	}
    	listOfWallets.add(p);
    	
    	p = new Wallet("His account");
    	i = 200;
    	for(String x : typesOfCurrency){
    		p.addMoney(x, i);
    		i*=10;
    	}
    	listOfWallets.add(p);
    	
    	p = new Wallet("Her account");
    	i = 200;
    	for(String x : typesOfCurrency){
    		p.addMoney(x, i);
    		i*=10;
    	}
    	listOfWallets.add(p);
    	
    	p = new Wallet("Our account");
    	i = 200;
    	for(String x : typesOfCurrency){
    		p.addMoney(x, i);
    		i*=10;
    	}
    	listOfWallets.add(p);
    	
    	p = new Wallet("Their account");
    	i = 200;
    	for(String x : typesOfCurrency){
    		p.addMoney(x, i);
    		i*=10;
    	}
    	listOfWallets.add(p);
        
    	p = new Wallet("Noones account");
    	i = 200;
    	for(String x : typesOfCurrency){
    		p.addMoney(x, i);
    		i*=10;
    	}
    	listOfWallets.add(p);
    	
    	p = new Wallet("Everyones account");
    	i = 200;
    	for(String x : typesOfCurrency){
    		p.addMoney(x, i);
    		i*=10;
    	}
    	listOfWallets.add(p);
    	
    	p = new Wallet("Empty account");
    	i = 200;
    	for(String x : typesOfCurrency){
    		p.addMoney(x, i);
    		i*=10;
    	}
    	listOfWallets.add(p);
    	
    	p = new Wallet("Full account");
    	i = 200;
    	for(String x : typesOfCurrency){
    		p.addMoney(x, i);
    		i*=10;
    	}
    	listOfWallets.add(p);
    	
        listOfSources.add(new String("Paycheck"));
        listOfSources.add(new String("Bank"));
        
        listOfCategories.add(new String("-No category-"));
        listOfCategories.add(new String("Food"));
        listOfCategories.add(new String("House morgage"));
        listOfCategories.add(new String("Clothing"));
        
        listOfTransactions.add(new Transaction( listOfWallets.get(0).getName(), listOfWallets.get(0).getMoney().get(0).getCode(), 100) );
        listOfTransactions.add(new Transaction( listOfWallets.get(0).getName(), listOfWallets.get(0).getMoney().get(2).getCode(), 600) );
    }
}
