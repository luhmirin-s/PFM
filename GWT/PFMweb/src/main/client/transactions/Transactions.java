package main.client.transactions;

import main.client.SystemPanel;
import main.client.manager.CategoryManager;
import main.client.manager.SourceManager;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.TabPanel;

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

		initListeners();
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
	
	public static void reselectCurrentTab(){
		transTabs.getTabBar().selectTab(transTabs.getTabBar().getSelectedTab());
		SystemPanel.out(String.valueOf(transTabs.getTabBar().getSelectedTab()));
	}
	
	public static void initListeners(){
		transTabs.addSelectionHandler(new SelectionHandler<Integer>() {
			
			@Override
			public void onSelection(SelectionEvent<Integer> event) {		
				
				//Window.alert("tab "+event.getSelectedItem()+" clicked!");
				switch(event.getSelectedItem()){
					case 0:{
						ExpenseTransactions.refreshData();
						ExpenseTransactions.focus();						
						break;
					}
					case 1:{
						IncomeTransactions.refreshData();
						IncomeTransactions.focus();
						break;
					}
					case 2:{
						TransferTransactions.refreshData();
						TransferTransactions.focus();
						break;
					}
					default: ExpenseTransactions.refreshData();
				}
			}
		});
	}
	 
}
