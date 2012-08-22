package main.client;

public class Account {
	
	private int id;
	private String name;
	private Money[] money;
	
	public Account(int id, String name, int[] value, String[] currency){
		this.id=id;
		this.name=name;
		this.money = new Money[value.length];
		for(int i=0; i<value.length; i++){
			money[i].value=value[i];
			money[i].currency=currency[i];
		}
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

	class Money{
		
		private int value;
		private String currency;
		
		public Money(int value, String currency){
			this.value=value;
			this.currency=currency;
		}
	}
	
}
