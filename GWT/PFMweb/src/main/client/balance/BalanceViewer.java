package main.client.balance;

import java.util.ArrayList;

import main.client.PFMweb;
import main.client.RefreshingClasses;
import main.client.SystemPanel;
import main.client.data.LocalData;
import main.client.data.ParseJson;
import main.client.transactions.ExpenseTransactions;
import main.client.transactions.IncomeTransactions;
import main.client.transactions.TransferTransactions;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BalanceViewer {

	private static TabPanel balanceTabs = new TabPanel();
	private static VerticalPanel panel = new VerticalPanel();
	private static HorizontalPanel totalPanel = new HorizontalPanel();
	private static FlexTable balanceTable = new FlexTable();
	//private static Label lTotal = new Label("Total:");
	private static FlexTable totalBalanceTable = new FlexTable();
	private static ArrayList<main.client.data.Balance> balanceList;
	private static int counter;
	
	public static TabPanel init(){
		
		panel.add(balanceTable);
		//totalPanel.add(lTotal);
		totalPanel.add(totalBalanceTable);
		panel.add(totalPanel);
		balanceTable.setVisible(true);
		balanceTable.setText(0, 0, "Name ");
		balanceTable.setText(0, 1, "Value");	
		balanceTable.setText(0, 2, "Currency ");
		
	  	balanceTable.setCellSpacing(0);
		balanceTable.setBorderWidth(1);
	  	balanceTable.setCellPadding(5);
	  	//set size to 100%!
	  	/*
	  	balanceTable.setText(1, 0, "Sberbank");
	  	balanceTable.setText(1, 1, "500");
	  	balanceTable.setText(1, 2, "USD");
	  	balanceTable.setText(2, 1, "100");
	  	balanceTable.setText(2, 2, "RUR");
	  	*/
	  	balanceTabs.add(panel, "Balance");
	  	balanceTabs.selectTab(0);
	  	
	  	//refreshData();
	  	
	  	balanceTabs.addSelectionHandler(new SelectionHandler<Integer>() {
			
			@Override
			public void onSelection(SelectionEvent<Integer> event) {		
				
				//Window.alert("tab "+event.getSelectedItem()+" clicked!");
				switch(event.getSelectedItem()){
					case 0:{
						refreshData();					
						break;
					}
					default: refreshData();
				}
			}
		});
	  	
	  	return balanceTabs;
	}
	
	//not used for now
	public static void calcTotal(){
		int total=0, row=1;
		while(row<balanceTable.getRowCount()){
			if(balanceTable.getText(row, 1).matches("^[0-9]+$"))
				total+=Integer.valueOf(balanceTable.getText(row, 1));
			row++;
		}
		totalBalanceTable.setText(0, 0, String.valueOf(total));
		
	}
	
	public static void cleanup(){
		balanceTable.removeAllRows();
		balanceTable.setText(0, 0, "Nothing to show");
	}
	
	public static void refreshData(){
		balanceTable.removeAllRows();
		balanceTable.setVisible(false);
		balanceTable.setText(0, 0, "Name ");
		balanceTable.setText(0, 1, "Value");	
		balanceTable.setText(0, 2, "Currency ");
		SystemPanel.out("Retrieving balance...");
		PFMweb.download(PFMweb.dataURL, "/account/list/" + LocalData.getUser().getId(), "Accepts", RequestBuilder.GET);
		SystemPanel.statusSetOp("refreshing accounts");
		SystemPanel.out("Refreshing accounts");
		PFMweb.requestRefresh(RefreshingClasses.BAL_ACC);
		
	}
	
	public static void handleAccounts(){
		if (PFMweb.getJSONdata() != null) {
			if(PFMweb.getJSONdata().equals("null")){
				balanceTable.setText(1, 0, "No accounts yet");
				SystemPanel.statusDone();
				return;
			}
			LocalData.setAccountList(ParseJson.parseAccount(PFMweb.getJSONdata()));
			if (LocalData.getAccountList().size() > 0) {
				/*
				for (int i = 0; i < LocalData.getAccountList().size(); i++) {
					accountBox.addItem(LocalData.getAccountList().get(i).getName());
				}*/
				SystemPanel.statusDone();
			} else {
				SystemPanel.out("Account list is empty");
				SystemPanel.statusError();
				return; //probably needs rethinking
			}

		} else {
			SystemPanel.out("Error receiving JSON");
			SystemPanel.statusError();
			return;
		}		
		
		//currency
		PFMweb.download(PFMweb.dataURL, "/currency/list", "Accepts", RequestBuilder.GET);
		SystemPanel.statusSetOp("refreshing currencies");
		PFMweb.requestRefresh(RefreshingClasses.BAL_CUR);
	}
	
	
	public static void handleCurrencies(){
		if (PFMweb.getJSONdata() != null) {
			if(PFMweb.getJSONdata().equals("null")){
				SystemPanel.clearStatus();
				return;
			}
			LocalData.setCurrencyList(ParseJson.parseCurrency(PFMweb.getJSONdata()));
			if (LocalData.getCurrencyList().size() > 0) {
				//for (int i = 0; i < LocalData.getCurrencyList().size(); i++) {
					//currencyBox.addItem(LocalData.getCurrencyList().get(i).getCode());
				//}
				SystemPanel.statusDone();
			} else {
				SystemPanel.out("Currency list is empty");
				SystemPanel.statusError();
				return;
			}

		} else {
			SystemPanel.out("Error receiving JSON");
			SystemPanel.statusError();
			return;
		}				
		
		//category
		//PFMweb.download(PFMweb.dataURL, "/category/list/"+ LocalData.getUser().getId(), "Accepts", RequestBuilder.GET);
		SystemPanel.statusSetOp("retrieving balance");
		counter=0;
		PFMweb.requestRefresh(RefreshingClasses.BAL_AMNT); //amount is Balance
	}
	
	public static void retrieveBalance(){
		if(counter>0){
			//parse
			if (PFMweb.getJSONdata() != null) {
				if(!PFMweb.getJSONdata().equals("null")){
					balanceList = ParseJson.parseBalance(PFMweb.getJSONdata());
					printCurrentAccount(balanceList);
				}
				SystemPanel.out("got balance list for "+Integer.valueOf(counter-1));					
				if (balanceList.size() > 0) {
					SystemPanel.statusDone();
				} else {
					SystemPanel.out("Balance list is empty");
					SystemPanel.statusDone();
					//return; //probably needs rethinking
				}

			} else {
				SystemPanel.out("Error receiving JSON");
				SystemPanel.statusError();
				//return;
			}		
		}
		if(counter>=LocalData.getAccountList().size()){
			SystemPanel.statusDone();
			balanceTable.setVisible(true);
			return;
		}
		SystemPanel.out("Retrieving amount "+ counter + " for "+LocalData.getAccountList().get(counter).getId()+"...");
		PFMweb.download(PFMweb.dataURL, "/balance/list/" + LocalData.getAccountList().get(counter).getId(), "Accepts", RequestBuilder.GET);
		//SystemPanel.statusSetOp("refreshing amount");
		//balanceList = new ArrayList<main.client.data.Balance>();
		counter++;
		PFMweb.requestRefresh(RefreshingClasses.BAL_AMNT);
	}
	
	private static void printCurrentAccount(ArrayList<main.client.data.Balance> list){
		int s = balanceTable.getRowCount();
		balanceTable.setText(s, 0, LocalData.getAccountList().get(counter-1).getName());
		for(int i=0; i<list.size(); i++){
			balanceTable.setText(s+i, 1, String.valueOf(list.get(i).getSum()));
			balanceTable.setText(s+i, 2, LocalData.getCurrencyCode(list.get(i).getCurrencyId()));
		}
	}
	
	public static void reselectCurrentTab(){
		balanceTabs.getTabBar().selectTab(balanceTabs.getTabBar().getSelectedTab());
	}
}
