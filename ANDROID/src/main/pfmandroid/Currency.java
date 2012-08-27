package main.pfmandroid;

public class Currency {
	private int id;
	private String code;
	
	Currency(int id, String name) {
		this.id = id;
		code = name;
	}
	
	public int getId(){
		return id;
	}
	
	public String getCode() {
		return code;
	}
	
	@Override
	public String toString() {
		return code;
	}
}
