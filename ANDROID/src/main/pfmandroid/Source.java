package main.pfmandroid;

public class Source {
	private int id;
	private String name;
	
	Source(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	@Override
	public String toString(){
		return name;
	}
}
