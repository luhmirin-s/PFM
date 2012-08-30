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

public class IncomeTransactions {

	private static VerticalPanel incomePanel = new VerticalPanel();
	private static Label lAccount = new Label("Account:");
	private static ListBox accountBox = new ListBox();
	private static ListBox currencyBox = new ListBox();
	//private static Label lAmountLeft = new Label("0");
	private static Label lAmount = new Label("Amount:");
	private static TextBox amountInput = new TextBox();
	private static Label lError = new Label("");
	//private static Label lCurrency = new Label("<none>");
	private static Label lSrc = new Label("Source:");
	private static ListBox srcBox = new ListBox();
	private static Button saveButton = new Button("Save");
	
	private static HorizontalPanel accountPanel = new HorizontalPanel();
	private static HorizontalPanel amountPanel = new HorizontalPanel();
	private static HorizontalPanel categoryPanel = new HorizontalPanel();
	private static HorizontalPanel buttonPanel = new HorizontalPanel();
	
	public static Panel init(){

		accountPanel.add(lAccount);
		accountPanel.add(accountBox);
		accountPanel.add(currencyBox);
		//accountPanel.add(lAmountLeft);
		amountPanel.add(lAmount);
		amountPanel.add(amountInput);
		//amountPanel.add(lCurrency);
		categoryPanel.add(lSrc);
		categoryPanel.add(srcBox);
		buttonPanel.add(saveButton);
		lError.setText("");
		buttonPanel.add(lError);
		
		incomePanel.add(accountPanel);
		incomePanel.add(amountPanel);
		incomePanel.add(categoryPanel);
		incomePanel.add(buttonPanel);		
		
		saveButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {				
				
				String name = amountInput.getText().trim();
				if (accountBox.getItemCount() > 0
						&& currencyBox.getItemCount() > 0
						&& srcBox.getItemCount() > 0) {
					if (name.length() > 0 && name.matches("^[0-9\\.]{1,16}$")) {
						lError.setText("");
						SystemPanel.out("Creating new income transaction...");
						SystemPanel.statusSetOp("making an income transaction");
						saveButton.setEnabled(false);
						PFMweb.upload(PFMweb.dataURL, "/income", CreateJson.toJsonIncome(
										Double.valueOf(name),
										LocalData.getAccountList().get(accountBox.getSelectedIndex()).getId(),
										LocalData.getSourceList().get(srcBox.getSelectedIndex()).getId(),
										LocalData.getCurrencyList().get(currencyBox.getSelectedIndex()).getId()),
										"Content-Type", RequestBuilder.POST);
						SystemPanel.out("Requesting refresh...");
						PFMweb.requestRefresh(RefreshingClasses.TRA_INC);
					} else {
						lError.setText("Please specify a valid amount!");
						SystemPanel.clearStatus();
					}
				}
				
			}
		});
		
		return incomePanel;
	}
	
	public static void focus(){
		amountInput.setFocus(true);
	}
	 
	public static void cleanup(){
		 accountBox.clear();
		 currencyBox.clear();
		 srcBox.clear();		 
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
		PFMweb.requestRefresh(RefreshingClasses.TRA_INC_ACC);
	}
	
	public static void handleAccounts(){
		if (PFMweb.getJSONdata() != null) {
			if(PFMweb.getJSONdata().equals("null")){
				lError.setText("No accounts yet");
				SystemPanel.statusDone();
				saveButton.setEnabled(false);
				return;
			}
			LocalData.setAccountList(ParseJson.parseAccount(PFMweb.getJSONdata()));
			if (LocalData.getAccountList().size() > 0) {
				for (int i = 0; i < LocalData.getAccountList().size(); i++) {
					accountBox.addItem(LocalData.getAccountList().get(i).getName());
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
		PFMweb.requestRefresh(RefreshingClasses.TRA_INC_CUR);
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
					currencyBox.addItem(LocalData.getCurrencyList().get(i).getCode());
				}
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
		
		//source
		PFMweb.download(PFMweb.dataURL, "/source/list/"+ LocalData.getUser().getId(), "Accepts", RequestBuilder.GET);
		SystemPanel.statusSetOp("refreshing categories");
		PFMweb.requestRefresh(RefreshingClasses.TRA_INC_SRC);
	}
	
	public static void handleSources(){
		if (PFMweb.getJSONdata() != null) {
			if(PFMweb.getJSONdata().equals("null")){
				lError.setText("No sources yet!");
				SystemPanel.statusDone();
				saveButton.setEnabled(false);
				return;
			}
			LocalData.setSourceList(ParseJson.parseSource(PFMweb.getJSONdata()));
			if (LocalData.getSourceList().size() > 0) {
				for (int i = 0; i < LocalData.getSourceList().size(); i++) {
					srcBox.addItem(LocalData.getSourceList().get(i).getName());
				}
				saveButton.setEnabled(true);
				SystemPanel.statusDone();
			} else {
				SystemPanel.out("Source list is empty");
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