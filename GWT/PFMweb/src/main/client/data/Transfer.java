package main.client.data;

public class Transfer {

	
	private int ammount;
	private int toAccountId;
	private int currencyId;
	private int fromAccountId;
	

	public Transfer() {}


	public Transfer(int ammount, int toAccountId, int currencyId, int fromAccountId) {
		setToAccountId(toAccountId);
		setAmmount(ammount);
		setFromAccountId(fromAccountId);
		setCurrencyId(currencyId);
	}


	public int getAmmount() {
		return ammount;
	}


	public void setAmmount(int ammount) {
		this.ammount = ammount;
	}


	public int getToAccountId() {
		return toAccountId;
	}


	public void setToAccountId(int toAccountId) {
		this.toAccountId = toAccountId;
	}


	public int getCurrencyId() {
		return currencyId;
	}


	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}


	public int getFromAccountId() {
		return fromAccountId;
	}


	public void setFromAccountId(int fromAccountId) {
		this.fromAccountId = fromAccountId;
	}
	
	
}
