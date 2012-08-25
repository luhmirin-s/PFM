package main.client.data;

import java.util.ArrayList;

public class LocalData {
	
	private static User user;
	private static ArrayList<Account> accountList;
	private static ArrayList<Category> categoryList;
	private static ArrayList<Source> sourceList;
	//multiple/deeper inheritance:
	private static ArrayList<MoneyAccount> moneyAccountList;
	private static ArrayList<Transfer> transferList; 
	private static ArrayList<Expense> expenseList;
	private static ArrayList<Income> incomeList;
	
	public static void initLogin(int id, String username, String password, String email){
		
		user= new User(id, username, password, email);
		
		accountList=new ArrayList<Account>();
		categoryList=new ArrayList<Category>();
		sourceList=new ArrayList<Source>();
		
		moneyAccountList=new ArrayList<MoneyAccount>();
		transferList=new ArrayList<Transfer>();
		expenseList=new ArrayList<Expense>();
		incomeList=new ArrayList<Income>();
		
	}
	
	public static void initLogout(){
		incomeList=null;
		expenseList=null;
		transferList=null;
		moneyAccountList=null;
		
		sourceList=null;
		categoryList=null;
		accountList=null;
		
		user=null;
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
	public static ArrayList<MoneyAccount> moneyaccounts(){
		return moneyAccountList;
	}
	public static ArrayList<Transfer> transfers(){
		return transferList;
	}
	public static ArrayList<Expense> expenses(){
		return expenseList;
	}
	public static ArrayList<Income> incomeList(){
		return incomeList;
	}
	
}
