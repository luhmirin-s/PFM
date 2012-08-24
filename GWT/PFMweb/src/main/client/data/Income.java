package main.client.data;

import java.util.Date;

public class Income {
	
	private int id;
	private int amount;
	private Date date;
	private MoneyAccount moneyAccount;
	private Source source;

	public Income() {
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
