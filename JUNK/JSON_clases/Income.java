package main.client;

public class Income {

	
	private int ammount;
	private int accountId;
	private int currencyId;
	private int sourceId;
	

	public Income() {}


	public Income(int ammount, int accountId, int currencyId, int sourceId) {
		setAccountId(accountId);
		setAmmount(ammount);
		setSourceId(sourceId);
		setCurrencyId(currencyId);
	}


	public int getAmmount() {
		return ammount;
	}


	public void setAmmount(int ammount) {
		this.ammount = ammount;
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
