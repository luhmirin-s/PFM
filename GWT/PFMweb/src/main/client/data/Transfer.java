package main.client.data;

import java.util.Date;

public class Transfer {

	private int id;
	private Date date;
	private MoneyAccount moneyAccount1;
	private MoneyAccount moneyAccount2;

	public Transfer() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
