package main.client.data;

import java.util.ArrayList;

public class LocalData {
	
	private static User user;
	
	public static User getUser() {
		return user;
	}
	//not needed
	public static void setUser(User user) {
		LocalData.user = user;
	}
	private static ArrayList<Account> accountList;
	private static ArrayList<Currency> currencyList;
	private static ArrayList<Category> categoryList;
	private static ArrayList<Source> sourceList;
	private static ArrayList<Balance> balanceList;
	private static ArrayList<JournalEntry> journalList;
	
	public static void initLogin(int id, String username, String password, String email){
		
		user= new User(id, username, password, email);
	
	}
	
	public static void initLogout(){
		
		sourceList=null;
		categoryList=null;
		accountList=null;
		
		user=null;
	}
	
	//currency name by id
	
	public static String getCurrencyCode(int curID){
		
		for(Currency c:currencyList){
			if(c.getId()==curID) return c.getCode();
		}
		
		return "";
	}
	
	public static ArrayList<Account> accounts(){
		return accountList;
	}
	public static ArrayList<Category> categories(){
		return categoryList;
	}
	public static ArrayList<Source> sources(){
		return sourceList;
	}
	public static ArrayList<Account> getAccountList() {
		return accountList;
	}
	public static void setAccountList(ArrayList<Account> accountList) {
		LocalData.accountList = accountList;
	}
	public static ArrayList<Category> getCategoryList() {
		return categoryList;
	}
	public static void setCategoryList(ArrayList<Category> categoryList) {
		LocalData.categoryList = categoryList;
	}
	public static ArrayList<Source> getSourceList() {
		return sourceList;
	}
	public static void setSourceList(ArrayList<Source> sourceList) {
		LocalData.sourceList = sourceList;
	}
	public static ArrayList<Currency> getCurrencyList() {
		return currencyList;
	}
	public static void setCurrencyList(ArrayList<Currency> currencyList) {
		LocalData.currencyList = currencyList;
	}
	public static ArrayList<Balance> getBalanceList() {
		return balanceList;
	}
	public static void setBalanceList(ArrayList<Balance> balanceList) {
		LocalData.balanceList = balanceList;
	}
	public static ArrayList<JournalEntry> getJournalList() {
		return journalList;
	}
	public static void setJournalList(ArrayList<JournalEntry> journalList) {
		LocalData.journalList = journalList;
	}
	
}
