package main.client.transactions;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class IncomeTransactions {

	private static VerticalPanel incomePanel = new VerticalPanel();
	private static Label lAccount = new Label("Account:");
	private static ListBox balanceBox = new ListBox();
	private static ListBox currencyBox = new ListBox();
	private static Label lAmountLeft = new Label("0");
	private static Label lAmount = new Label("Amount:");
	private static TextBox amountInput = new TextBox();
	private static Label lCurrency = new Label("<none>");
	private static Label lSrc = new Label("Source:");
	private static ListBox srcBox = new ListBox();
	private static Button saveButton = new Button("Save");
	
	private static HorizontalPanel accountPanel = new HorizontalPanel();
	private static HorizontalPanel amountPanel = new HorizontalPanel();
	private static HorizontalPanel categoryPanel = new HorizontalPanel();
	private static HorizontalPanel buttonPanel = new HorizontalPanel();
	
	public static Panel init(){

		accountPanel.add(lAccount);
		accountPanel.add(balanceBox);
		accountPanel.add(currencyBox);
		accountPanel.add(lAmountLeft);
		amountPanel.add(lAmount);
		amountPanel.add(amountInput);
		amountPanel.add(lCurrency);
		categoryPanel.add(lSrc);
		categoryPanel.add(srcBox);
		buttonPanel.add(saveButton);
		
		incomePanel.add(accountPanel);
		incomePanel.add(amountPanel);
		incomePanel.add(categoryPanel);
		incomePanel.add(buttonPanel);		
		
		saveButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {				
				
				
				
			}
		});
		
		return incomePanel;
	}
	
	public static void refreshData(){
		//get from server
	}
	 
}
