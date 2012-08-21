package main.client;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Wallets {

	private static VerticalPanel panel;
	private static FlexTable walletTable = new FlexTable();		
	//private static RadioButton walletSelectors = new RadioButton();
	private static ArrayList<Wallet> walletList = new ArrayList<Wallet>();
	
	public static FlexTable getWalletTable(){
		return walletTable;
	}
	
	public static void setText(int r, int c, String txt){
		walletTable.setText(r, c, txt);		
	}
	
	public static ArrayList<Wallet> getList(){
		return walletList;
	}
	
	public static void init(VerticalPanel myPanel){
		
		
		panel = myPanel;
		
		setText(0, 0, "Name ");
		setText(0, 1, "Value");		  
		
	  	walletTable.setBorderWidth(1);
	  	walletTable.setCellPadding(5);
	  	//set size to 100%!
	  	
	  	walletList.add(new Wallet("Main income", 450));
	  	walletList.add(new Wallet("Bank", 1000));
	  	walletList.add(new Wallet("Gifts", 50));
	  	
	  	panel.add(walletTable);
	  	refreshData();
	}
	
	public static void refreshData(){
		//send to server
		
		//get from server
		
		fillTable();
		
	}
	
	public static void addWallet(String name, int amount){
		Wallet w = new Wallet(name, amount);
		walletList.add(w);
		refreshData();
		TransactionMaker.refreshData();
	}
	
	public static boolean removeWallet(int walletID){
		if(walletID!=-1){
			if(!walletList.isEmpty()) walletList.remove(walletID);
			//if(walletTable.getRowCount() > 1) walletTable.removeRow(walletID+1);
			refreshData();
			TransactionMaker.refreshData();
			return true;
		}
		return false; //return false if now empty or none selected
	}
	
	private static void fillTable(){
		int c=1;
		walletTable.clear();
		
		setText(0, 0, "Name ");
		setText(0, 1, "Value");	
		
		for(Wallet w: walletList){
			setText(c, 0, w.getName());
			setText(c, 1, String.valueOf(w.getValue()));
			c++;
		}
	}
	
}
