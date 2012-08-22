package main.client.journal;

import java.util.ArrayList;
import java.util.Date;

import main.client.Transaction;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Journal {
	
	private static TabPanel journalTabs = new TabPanel();
	
	private static VerticalPanel panel = new VerticalPanel();
	private static HorizontalPanel typePanel = new HorizontalPanel();
	private static HorizontalPanel timeframePanel = new HorizontalPanel();
	private static HorizontalPanel buttonPanel = new HorizontalPanel();	
	private static Label lType = new Label("Type:");
	private static ListBox typeBox = new ListBox();
	private static Label lTimeFrame = new Label("Timeframe:");
	private static ListBox timeBox = new ListBox();
	private static Button showButton = new Button("Show");
	
	private static VerticalPanel transactionPanel = new VerticalPanel();
	private static FlexTable transactionTable = new FlexTable();
	
	//private static ArrayList<Transaction> transList = new ArrayList<Transaction>();
	
	public static TabPanel init(){
	    
		panel.add(typePanel);
		panel.add(timeframePanel);
		panel.add(buttonPanel);
		panel.add(transactionPanel);
		
		typePanel.add(lType);
		typePanel.add(typeBox);
		timeframePanel.add(lTimeFrame);
		timeframePanel.add(timeBox);
		buttonPanel.add(showButton);
		
		transactionPanel.add(transactionTable);
				
	    transactionTable.setBorderWidth(1);
	    transactionTable.setCellPadding(5);	
	    transactionTable.setText(0, 0, "text");
	    
		journalTabs.add(panel, "Transaction journal");
		journalTabs.add(panel, "Transactions:");  
		
		showButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {				
				
				//refresh data
				
			}
		});
		
		journalTabs.selectTab(0);
	    
	    return journalTabs;
	}
	
	public static void makeTransaction(Transaction t){
		
		//DateTimeFormat.getMediumDateTimeFormat().format(new Date() //String
		
	}
	
}
