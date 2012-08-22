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

public class TransactionMaker {

	private static VerticalPanel panel;
	private static TextBox inputBox = new TextBox();
	private static Button makeButton = new Button("Make transaction");
	private static Button dirButton = new Button("Pay");
	private static Button delWalletButton = new Button("<- Remove wallet");
	//private static MultiWordSuggestOracle wallets = new MultiWordSuggestOracle(); 
	//private static SuggestBox searchWallet = new SuggestBox();
	private static ListBox walletBox = new ListBox();
	private static Label lE = new Label("Enter amount: ");
	private static Label lS = new Label("Select wallet: ");
	private static HorizontalPanel buttons = new HorizontalPanel();
	private static HorizontalPanel walletPanel = new HorizontalPanel();
	
	private static boolean pay=true;
	private static String wallet="<empty>";
	private static int value=0;
	 
	public static TextBox getInputBox(){
		return inputBox;
	}
	
	public static Button getMakeButton(){
		return makeButton;
	}
	
	public static void init(VerticalPanel myPanel){
		
		panel = myPanel;
		inputBox.setText("0");
		makeButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {				
				
				Transactions.makeTransaction(new Transaction(pay, wallet, value));
				
			}
		});
		
		dirButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {				
				
				if(pay){
					dirButton.setText("Deposit");
					pay=false;
				} else {
					dirButton.setText("Pay");
					pay=true;
				}
				
			}
		});
		/*
		delWalletButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {				
				
				Wallets.removeWallet(walletBox.getSelectedIndex());
						
				if(walletBox.getItemCount()<1){
					delWalletButton.setText("<none>");
				} else {
					delWalletButton.setText("<- Remove wallet");
				}
				
			}
		});
		*/
		panel.add(inputBox);
		panel.add(buttons);
		buttons.add(makeButton);
		buttons.add(dirButton);		
		panel.add(lS);
		panel.add(walletPanel);
		walletPanel.add(walletBox);
		//walletPanel.add(delWalletButton);
		refreshData();

	}
	
	public static ListBox getWalletBox(){
		return walletBox;
	}
	
	public static void refreshData(){
		//get from server
		
		fillBox();
	}
	
	private static void fillBox(){
		walletBox.clear();
		for(Wallet w:Wallets.getList())
			walletBox.addItem(w.getName());
		walletBox.setItemSelected(walletBox.getItemCount()-1, true);
	}
	
	public static void focus(){
		inputBox.setFocus(true);
	}
	 
}
