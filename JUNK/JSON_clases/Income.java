package main.client;

public class Income {

	
	private int amount;
	private int accountId;
	private int currencyId;
	private int sourceId;
	

	public Income() {}


	public Income(int amount, int accountId, int currencyId, int sourceId) {
		setAccountId(accountId);
		setAmount(amount);
		setSourceId(sourceId);
		setCurrencyId(currencyId);
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public int getAccountId() {
		return accountId;
	}


	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}


	public int getCurrencyId() {
		return currencyId;
	}


	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}


	public int getSourceId() {
		return sourceId;
	}


	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}
	
}
