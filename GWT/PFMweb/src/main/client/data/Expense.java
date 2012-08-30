package main.client.data;

public class Expense {

	
	private int amount;
	private int accountId;
	private int currencyId;
	private int categoryId;
	

	public Expense() {}


	public Expense(int amount, int accountId, int currencyId, int categoryId) {
		setAccountId(accountId);
		setAmount(amount);
		setCategoryId(categoryId);
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


	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

}
