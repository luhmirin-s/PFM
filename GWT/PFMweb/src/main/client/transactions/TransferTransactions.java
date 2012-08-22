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

public class TransferTransactions {

	private static VerticalPanel transfersPanel = new VerticalPanel();
	private static Label lFrom = new Label("From:");
	private static ListBox balanceBoxFrom = new ListBox();
	private static ListBox currencyBoxFrom = new ListBox();
	private static Label lAmountLeftFrom = new Label("0");
	private static Label lTo = new Label("To:");
	private static ListBox balanceBoxTo = new ListBox();
	private static ListBox currencyBoxTo = new ListBox();
	private static Label lAmountLeftTo = new Label("0");
	private static Label lAmount = new Label("Amount:");
	private static TextBox amountInput = new TextBox();
	private static Button saveButton = new Button("Save");
	
	private static HorizontalPanel fromPanel = new HorizontalPanel();
	private static HorizontalPanel toPanel = new HorizontalPanel();
	private static HorizontalPanel amountPanel = new HorizontalPanel();
	private static HorizontalPanel buttonPanel = new HorizontalPanel();
	
	public static Panel init(){

		fromPanel.add(lFrom);
		fromPanel.add(balanceBoxFrom);
		fromPanel.add(currencyBoxFrom);
		fromPanel.add(lAmountLeftFrom);
		toPanel.add(lTo);
		toPanel.add(balanceBoxTo);
		toPanel.add(currencyBoxTo);
		toPanel.add(lAmountLeftTo);		
		amountPanel.add(lAmount);
		amountPanel.add(amountInput);
		buttonPanel.add(saveButton);
		
		transfersPanel.add(fromPanel);
		transfersPanel.add(toPanel);
		transfersPanel.add(amountPanel);
		transfersPanel.add(buttonPanel);		
		
		saveButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {				
				
				
				
			}
		});
		
		return transfersPanel;
	}
	
	public static void refreshData(){
		//get from server
	}
	 
}
