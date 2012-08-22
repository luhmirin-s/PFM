package main.client.transactions;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Transactions {

	private static TabPanel transTabs = new TabPanel();
	
	public static TabPanel getTabPanel(){
		return transTabs;
	}
	
	public static TabPanel init(){
		
		transTabs.add(ExpenseTransactions.init(), "Expenses");
		transTabs.add(IncomeTransactions.init(), "Income");
		transTabs.add(TransferTransactions.init(), "Transfers");
		
		transTabs.selectTab(0);

		/*
		transTabs.getWidget(0).addSelectionHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {				
				
				Journal.makeTransaction(new Transaction(pay, wallet, value));
				
			}
		});
		*/
		//refreshData();
		return transTabs;
	}
	
	public static void refreshData(){
		//get from server
	}
	 
}
