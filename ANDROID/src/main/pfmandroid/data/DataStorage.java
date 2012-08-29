package main.pfmandroid.data;

import java.util.ArrayList;

/*
 * Internal data storage class, to keep all the data in one place, made static, because it is used more or less globally.
 */

public class DataStorage {
	static public int userId;
	static public String currentUsername;
	static public String domain = "http://10.0.1.59/PFMWebService/jaxrs/";
	//Placeholder - http://85.254.250.27/PFMWebService/jaxrs/
	static public ArrayList<Wallet> listOfWallets = new ArrayList<Wallet>();
    static public ArrayList<Source> listOfSources = new ArrayList<Source>();
    static public ArrayList<Category> listOfCategories = new ArrayList<Category>();
    static public ArrayList<Transaction> listOfTransactions = new ArrayList<Transaction>();
    static public ArrayList<Currency> typesOfCurrency = new ArrayList<Currency>();
    
    static public void clear(){
    	listOfWallets.clear();
    	listOfSources.clear();
    	listOfCategories.clear();
    	listOfTransactions.clear();
    	typesOfCurrency.clear();
    }
}