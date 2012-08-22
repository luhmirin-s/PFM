package main.client.manager;

import main.client.balance.Balance;
import main.client.transactions.TransferTransactions;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Manager {

	private static TabPanel managerTabs = new TabPanel();
	
	public static TabPanel getTabPanel(){
		return managerTabs;
	}
	public static TabPanel init(){
		
		managerTabs.add(AccountManager.init(), "Accounts");		
		managerTabs.selectTab(0);
		
		/*
		addWalletButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {				
				String name = inputNameBox.getText().trim();
				String amount = inputAmountBox.getText().trim();
				if(name.matches("^[0-9A-Za-z\\s]{1,16}$") && amount.matches("[0-9]{1,10}$")){
					lE.setText("ok");
					//Balance.addWallet(name, Integer.valueOf(amount));
					inputNameBox.setText("My Wallet");
					inputAmountBox.setText("0");
				} else {
					lE.setText("Check your input!");
				}
				
			}
		});
		
		delWalletButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {				
				
				Balance.removeWallet(ExpenseTransactions.getWalletBox().getSelectedIndex());
						
				if(ExpenseTransactions.getWalletBox().getItemCount()<1){
					delWalletButton.setText("<none>");
				} else {
					delWalletButton.setText("Remove selected wallet");
				}
				
			}
		});
		*/
	
		return managerTabs;
	}
	

	
	public static void refreshData(){
		//get from server
		
		//fillBox();
	}

}
