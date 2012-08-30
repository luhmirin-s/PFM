package main.client.data;

public class Transfer {

	
	private int amount;
	private int toAccountId;
	private int currencyId;
	private int fromAccountId;
	

	public Transfer() {}


	public Transfer(int amount, int toAccountId, int currencyId, int fromAccountId) {
		setToAccountId(toAccountId);
		setAmount(amount);
		setFromAccountId(fromAccountId);
		setCurrencyId(currencyId);
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
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
