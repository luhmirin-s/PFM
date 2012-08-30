package main.client.transactions;

import main.client.PFMweb;
import main.client.RefreshingClasses;
import main.client.SystemPanel;
import main.client.data.CreateJson;
import main.client.data.LocalData;
import main.client.data.ParseJson;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TransferTransactions {

	private static VerticalPanel transfersPanel = new VerticalPanel();
	private static Label lFrom = new Label("From:");
	private static ListBox accountBoxFrom = new ListBox();
	private static ListBox currencyBoxFrom = new ListBox();
	//private static Label lAmountLeftFrom = new Label("0");
	private static Label lTo = new Label("To:");
	private static ListBox accountBoxTo = new ListBox();
	//private static ListBox currencyBoxTo = new ListBox();
	//private static Label lAmountLeftTo = new Label("0");
	private static Label lAmount = new Label("Amount:");
	private static TextBox amountInput = new TextBox();
	private static Label lError = new Label("");
	private static Button saveButton = new Button("Save");
	
	private static HorizontalPanel fromPanel = new HorizontalPanel();
	private static HorizontalPanel toPanel = new HorizontalPanel();
	private static HorizontalPanel amountPanel = new HorizontalPanel();
	private static HorizontalPanel buttonPanel = new HorizontalPanel();
	
	public static Panel init(){

		fromPanel.add(lFrom);
		fromPanel.add(accountBoxFrom);
		fromPanel.add(currencyBoxFrom);
		//fromPanel.add(lAmountLeftFrom);
		toPanel.add(lTo);
		toPanel.add(accountBoxTo);
		//toPanel.add(currencyBoxTo);
		//toPanel.add(lAmountLeftTo);		
		amountPanel.add(lAmount);
		amountPanel.add(amountInput);
		buttonPanel.add(saveButton);
		lError.setText("");
		buttonPanel.add(lError);
		
		transfersPanel.add(fromPanel);
		transfersPanel.add(toPanel);
		transfersPanel.add(amountPanel);
		transfersPanel.add(buttonPanel);		
		
		saveButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {				
				
				String name = amountInput.getText().trim();
				if (accountBoxFrom.getItemCount() > 0
						&& accountBoxTo.getItemCount() > 0
						&& currencyBoxFrom.getItemCount() > 0
						&& accountBoxFrom.getSelectedIndex()!= accountBoxTo.getSelectedIndex()
						) {
					if (name.length() > 0 && name.matches("^[0-9\\.]{1,16}$")) {
						lError.setText("");
						SystemPanel.out("Creating new transfer transaction...");
						SystemPanel.statusSetOp("making a transfer transaction");
						saveButton.setEnabled(false);
						PFMweb.upload(PFMweb.dataURL, "/transfer", CreateJson.toJsonTransfer(
										Double.valueOf(name),
										LocalData.getAccountList().get(accountBoxFrom.getSelectedIndex()).getId(),
										LocalData.getAccountList().get(accountBoxTo.getSelectedIndex()).getId(),
										LocalData.getCurrencyList().get(currencyBoxFrom.getSelectedIndex()).getId()),
										"Content-Type", RequestBuilder.POST);
						SystemPanel.out("Requesting refresh...");
						PFMweb.requestRefresh(RefreshingClasses.TRA_TRANSF);
					} else {
						lError.setText("Please specify a valid amount!");
						SystemPanel.clearStatus();
					}
				} else {
					lError.setText("Accounts must not match!");
					SystemPanel.clearStatus();
				}
				
			}
		});
		
		return transfersPanel;
	}

	public static void focus(){
		amountInput.setFocus(true);
	}
	public static void cleanup(){
		 accountBoxFrom.clear();
		 accountBoxTo.clear();
		 currencyBoxFrom.clear();		 
		 amountInput.setText("");
		 lError.setText("");
	 }
	
	/**
	 * Data refreshing chain starts here... 
	 * refreshAccounts->handleAccounts->handleCurrencies->handleCategories
	 */	
	public static void refreshData(){
		cleanup();
		SystemPanel.out("Downloading accounts...");
		PFMweb.download(PFMweb.dataURL, "/account/list/" + LocalData.getUser().getId(), "Accepts", RequestBuilder.GET);
		SystemPanel.statusSetOp("refreshing accounts");
		SystemPanel.out("Refreshing accounts");
		PFMweb.requestRefresh(RefreshingClasses.TRA_TRANSF_ACC);
	}
	
	public static void handleAccounts(){
		if (PFMweb.getJSONdata() != null) {
			if(PFMweb.getJSONdata().equals("null")){
				lError.setText("No accounts yet!");
				SystemPanel.statusDone();
				saveButton.setEnabled(false);
				return;
			}
			LocalData.setAccountList(ParseJson.parseAccount(PFMweb.getJSONdata()));
			if (LocalData.getAccountList().size() > 0) {
				for (int i = 0; i < LocalData.getAccountList().size(); i++) {
					accountBoxFrom.addItem(LocalData.getAccountList().get(i).getName());
				}
				for (int i = 0; i < LocalData.getAccountList().size(); i++) {
					accountBoxTo.addItem(LocalData.getAccountList().get(i).getName());
				}
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
		PFMweb.requestRefresh(RefreshingClasses.TRA_TRANSF_CUR);
	}
	
	public static void handleCurrencies(){
		if (PFMweb.getJSONdata() != null) {
			if(PFMweb.getJSONdata().equals("null")){
				lError.setText("No currencies defined in database");
				SystemPanel.statusDone();
				saveButton.setEnabled(false);
				return;
			}
			LocalData.setCurrencyList(ParseJson.parseCurrency(PFMweb.getJSONdata()));
			if (LocalData.getCurrencyList().size() > 0) {
				for (int i = 0; i < LocalData.getCurrencyList().size(); i++) {
					currencyBoxFrom.addItem(LocalData.getCurrencyList().get(i).getCode());
				}
				saveButton.setEnabled(true);
				SystemPanel.statusDone();
			} else {
				SystemPanel.out("Currency list is empty");
				SystemPanel.statusError();
				saveButton.setEnabled(true);
				return;
			}

		} else {
			SystemPanel.out("Error receiving JSON");
			SystemPanel.statusError();
			saveButton.setEnabled(true);
			return;
		}		
	}	 
}
