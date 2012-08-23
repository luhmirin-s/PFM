package main.client;

public class Money {

	private String code;
	private int value;
	
	public Money(String code,int value){
		setCode(code);
		setValue(value);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
