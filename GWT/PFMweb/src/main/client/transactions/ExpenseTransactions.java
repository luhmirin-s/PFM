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

public class ExpenseTransactions {

	private static VerticalPanel expensePanel = new VerticalPanel();
	private static Label lAccount = new Label("Account:");
	private static ListBox accountBox = new ListBox();
	private static ListBox currencyBox = new ListBox();
	//private static Label lAmountLeft = new Label("0");
	private static Label lAmount = new Label("Amount:");
	private static TextBox amountInput = new TextBox();
	private static Label lError = new Label("");
	//private static Label lCurrency = new Label("<none>");	//unused for now
	private static Label lCat = new Label("Category:");
	private static ListBox catBox = new ListBox();
	private static Button saveButton = new Button("Save");
	
	private static HorizontalPanel accountPanel = new HorizontalPanel();
	private static HorizontalPanel amountPanel = new HorizontalPanel();
	private static HorizontalPanel categoryPanel = new HorizontalPanel();
	private static HorizontalPanel buttonPanel = new HorizontalPanel();
	
	public static Panel init(){

		accountPanel.add(lAccount);
		accountPanel.add(accountBox);
		accountPanel.add(currencyBox);
		//accountPanel.add(lAmountLeft); //unused for now
		amountPanel.add(lAmount);
		amountPanel.add(amountInput);
		//amountPanel.add(lCurrency);
		categoryPanel.add(lCat);
		categoryPanel.add(catBox);
		buttonPanel.add(saveButton);
		lError.setText("");
		buttonPanel.add(lError);
		
		expensePanel.add(accountPanel);
		expensePanel.add(amountPanel);
		expensePanel.add(categoryPanel);
		expensePanel.add(buttonPanel);
		
		saveButton.setEnabled(true);
		saveButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				String name = amountInput.getText().trim();
				if (accountBox.getItemCount() > 0
						&& currencyBox.getItemCount() > 0
						&& catBox.getItemCount() > 0) {
					if (name.length() > 0 && name.matches("^[0-9\\.]{1,16}$")) {
						lError.setText("");
						SystemPanel.out("Creating new expense transaction...");
						SystemPanel.statusSetOp("making an expense transaction");
						saveButton.setEnabled(false);
						PFMweb.upload(PFMweb.dataURL, "/expense", CreateJson.toJsonExpenses(
										Double.valueOf(name),
										LocalData.getAccountList().get(accountBox.getSelectedIndex()).getId(),
										LocalData.getCategoryList().get(catBox.getSelectedIndex()).getId(),
										LocalData.getCurrencyList().get(currencyBox.getSelectedIndex()).getId()),
										"Content-Type", RequestBuilder.POST);
						SystemPanel.out("Requesting refresh...");
						PFMweb.requestRefresh(RefreshingClasses.TRA_EXP);
					} else {
						lError.setText("Please specify a valid amount!");
						SystemPanel.clearStatus();
					}
				}
			}
		});
		
		return expensePanel;
	}
	
	public static void focus(){
		amountInput.setFocus(true);
	}
	
	 private static void cleanup(){
		 accountBox.clear();
		 currencyBox.clear();
		 catBox.clear();		 
		 amountInput.setText("");
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
		PFMweb.requestRefresh(RefreshingClasses.TRA_EXP_ACC);
	}
	
	public static void handleAccounts(){
		if (PFMweb.getJSONdata() != null) {
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
		PFMweb.requestRefresh(RefreshingClasses.TRA_EXP_CUR);
	}
	
	public static void handleCurrencies(){
		if (PFMweb.getJSONdata() != null) {
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
		
		//category
		PFMweb.download(PFMweb.dataURL, "/category/list/"+ LocalData.getUser().getId(), "Accepts", RequestBuilder.GET);
		SystemPanel.statusSetOp("refreshing categories");
		PFMweb.requestRefresh(RefreshingClasses.TRA_EXP_CAT);
	}
	
	public static void handleCategories(){
		if (PFMweb.getJSONdata() != null) {
			LocalData.setCategoryList(ParseJson.parseCategory(PFMweb.getJSONdata()));
			if (LocalData.getCategoryList().size() > 0) {
				for (int i = 0; i < LocalData.getCategoryList().size(); i++) {
					catBox.addItem(LocalData.getCategoryList().get(i).getName());
				}
				saveButton.setEnabled(true);
				SystemPanel.statusDone();
			} else {
				SystemPanel.out("Category list is empty");
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