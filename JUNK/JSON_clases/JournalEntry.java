package main.client;

public class JournalEntry {

	
	private String accountName;
	private String amount;
	private String date;
	private String text;
	private int transactionId;
	private int type;
	

	public JournalEntry() {}

	
	public JournalEntry(String accountName, String amount, String date,
			String text, int transactionId, int type) {
		setAccountName(accountName);
		setAmount(amount);
		setDate(date);
		setText(text);
		setTransactionId(transactionId);
		setType(type);
	}





	public String getAccountName() {
		return accountName;
	}


	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}


	public String getAmount() {
		return amount;
	}


	public void setAmount(String amount) {
		this.amount = amount;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public int getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}
	
	
	
	
	
	
}
