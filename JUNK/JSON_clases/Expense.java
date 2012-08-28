package main.client;

public class Expense {

	
	private int ammount;
	private int accountId;
	private int currencyId;
	private int categoryId;
	

	public Expense() {}


	public Expense(int ammount, int accountId, int currencyId, int categoryId) {
		setAccountId(accountId);
		setAmmount(ammount);
		setCategoryId(categoryId);
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


	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

}
