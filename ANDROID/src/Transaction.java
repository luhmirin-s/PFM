import java.util.Calendar;
import java.util.Date;


public class Transaction {
	
	private boolean pay;
	private String wallet;
	private double value;
	private Date date;
	
	public Transaction(boolean pay, String wallet, int value){
		this.pay = pay;
		this.wallet = wallet;
		this.value = value;
		date = Calendar.getInstance().getTime();
	}
}
