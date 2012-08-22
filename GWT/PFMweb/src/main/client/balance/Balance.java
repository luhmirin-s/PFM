package main.client.balance;

import java.util.ArrayList;

import main.client.Wallet;
import main.client.transactions.ExpenseTransactions;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Balance {

	private static TabPanel balanceTabs = new TabPanel();
	private static VerticalPanel panel = new VerticalPanel();
	private static HorizontalPanel totalPanel = new HorizontalPanel();
	private static FlexTable balanceTable = new FlexTable();
	private static Label lTotal = new Label("Total:");
	private static FlexTable totalBalanceTable = new FlexTable();
	//private static ArrayList<Wallet> walletList = new ArrayList<Wallet>();
	
	public static TabPanel init(){
		
		panel.add(balanceTable);
		totalPanel.add(lTotal);
		totalPanel.add(totalBalanceTable);
		panel.add(totalPanel);
		
		balanceTable.setText(0, 0, "Name ");
		balanceTable.setText(0, 1, "Value");	
		balanceTable.setText(0, 2, "Currency ");
		
	  	balanceTable.setBorderWidth(1);
	  	balanceTable.setCellPadding(5);
	  	//set size to 100%!
	  	
	  	balanceTable.setText(1, 0, "Sberbank");
	  	balanceTable.setText(1, 0, "500");
	  	balanceTable.setText(1, 0, "USD");
	  	balanceTable.setText(2, 0, "100");
	  	balanceTable.setText(2, 0, "RUR");
	  	
	  	balanceTabs.add(panel, "Balance");
	  	balanceTabs.selectTab(0);
	  	
	  	return balanceTabs;
	}
	
	public static void refreshData(){
	
		//get from server
		
	}
	/*
	public static void addWallet(String name, int amount){
		Wallet w = new Wallet(name, amount);
		walletList.add(w);
		refreshData();
		ExpenseTransactions.refreshData();
	}
	
	public static boolean removeWallet(int walletID){
		if(walletID!=-1){
			if(!walletList.isEmpty()) walletList.remove(walletID);
			if(walletTable.getRowCount() > 1) walletTable.removeRow(walletID+1);
			refreshData();
			ExpenseTransactions.refreshData();
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
	*/
}
