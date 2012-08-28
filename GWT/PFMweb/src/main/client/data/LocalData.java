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
	private static ArrayList<Category> categoryList;
	private static ArrayList<Source> sourceList;
	
	public static void initLogin(int id, String username, String password, String email){
		
		user= new User(id, username, password, email);
		
		//accountList=new ArrayList<Account>();
		//categoryList=new ArrayList<Category>();
		//sourceList=new ArrayList<Source>();
		
	}
	
	public static void initLogout(){
		
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
	
}
