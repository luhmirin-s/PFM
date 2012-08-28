package main.client.data;

import java.util.ArrayList;

public class LocalData {
	
	private static User user;
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
	
}
