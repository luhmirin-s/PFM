package main.client;

import com.google.gwt.i18n.client.DateTimeFormat;
import java.util.Date;
/**
 * 
 * This class needs to be renewed
 *
 */
public class Transaction {
	
	private boolean pay;
	private String wallet;
	private int value;
	private Date date;
	
	public Transaction(boolean pay, String wallet, int value){
		this.pay=pay;
		this.wallet=wallet;
		this.value=value;
	}
	
}
