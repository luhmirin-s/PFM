package main.client.data;

public class Account {
	
	private int id;
	private String name;
	private User user;
	
	public Account(){
	}
	
	public Account(int id, String name, User user){
		this.id=id;
		this.name=name;
		this.user=user;
	}
	
	public int getID(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public User getUser(){
		return this.user;
	}
	
	public void setUser(User user){
		this.user=user;
	}
	
}
