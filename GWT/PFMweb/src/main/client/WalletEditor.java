package main.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class WalletEditor {

	private static VerticalPanel panel;
	private static TextBox inputNameBox = new TextBox();
	private static TextBox inputAmountBox = new TextBox();
	private static Button addWalletButton = new Button("Add wallet");
	private static Button delWalletButton = new Button("Remove selected wallet");
	private static Label lN = new Label("Enter new wallet name");
	private static Label lA = new Label("Enter new wallet amount");
	private static Label lE = new Label("ok");
	private static HorizontalPanel buttons = new HorizontalPanel();
	 
	public static TextBox getInputNameBox(){
		return inputNameBox;
	}
	
	public static void init(VerticalPanel myPanel){
		
		panel = myPanel;
		inputNameBox.setText("My Wallet");
		inputAmountBox.setText("0");
		
		addWalletButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {				
				String name = inputNameBox.getText().trim();
				String amount = inputAmountBox.getText().trim();
				if(name.matches("^[0-9A-Za-z\\s]{1,16}$") && amount.matches("[0-9]{1,10}$")){
					lE.setText("ok");
					Wallets.addWallet(name, Integer.valueOf(amount));
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
				
				Wallets.removeWallet(TransactionMaker.getWalletBox().getSelectedIndex());
						
				if(TransactionMaker.getWalletBox().getItemCount()<1){
					delWalletButton.setText("<none>");
				} else {
					delWalletButton.setText("Remove selected wallet");
				}
				
			}
		});
		panel.add(lN);
		panel.add(inputNameBox);
		panel.add(lA);
		panel.add(inputAmountBox);
		panel.add(buttons);
		buttons.add(addWalletButton);
		buttons.add(lE);
		panel.add(delWalletButton);		
		refreshData();

	}
	

	
	public static void refreshData(){
		//get from server
		
		//fillBox();
	}
	
	public static void focus(){
		inputNameBox.setFocus(true);
	}
}
