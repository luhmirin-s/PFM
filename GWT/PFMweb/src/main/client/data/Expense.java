package main.client.data;

import java.util.Date;

public class Expense {
	
	private int id;
	private int amount;
	private Date date;
	private Category category;
	private MoneyAccount moneyAccount;

	public Expense() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
