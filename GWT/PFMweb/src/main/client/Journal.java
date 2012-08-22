package main.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Journal {
	
	private static VerticalPanel panel;
	private static FlexTable transactionTable = new FlexTable();
	private static ArrayList<Transaction> transList = new ArrayList<Transaction>();
	
	public static FlexTable getTransactionTable(){
		return transactionTable;
	}
	
	public static void setText(int r, int c, String txt){
		transactionTable.setText(r, c, txt);
	}
	
	public static void makeTransaction(Transaction t){
		
		//DateTimeFormat.getMediumDateTimeFormat().format(new Date() //String
		
	}
	
	public static void init(VerticalPanel myPanel){
		
		panel = myPanel;
		
		setText(0, 0, "Value ");
	    setText(0, 1, "Action ");
	    setText(0, 2, "Wallet ");
	  
	    setText(1, 0, "50 $");
	    setText(1, 1, "to");
	    setText(1, 2, "Income");
	    
	    transactionTable.setBorderWidth(1);
	    transactionTable.setCellPadding(5);
	    panel.add(transactionTable);
	}
}
