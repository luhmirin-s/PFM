package main.client.journal;

import java.util.ArrayList;
import java.util.Date;

import main.client.PFMweb;
import main.client.RefreshingClasses;
import main.client.SystemPanel;
import main.client.data.LocalData;
import main.client.data.ParseJson;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

public class Journal {
	
	private static TabPanel journalTabs = new TabPanel();
	
	private static VerticalPanel panel = new VerticalPanel();
	//private static HorizontalPanel typePanel = new HorizontalPanel();
	private static HorizontalPanel timeframePanel = new HorizontalPanel();
	private static HorizontalPanel buttonPanel = new HorizontalPanel();	
	//private static Label lType = new Label("Type:");
	//private static ListBox typeBox = new ListBox();
	private static Label lTimeFrame = new Label("Timeframe:");
	private static ListBox timeBox = new ListBox();
	private static Button showButton = new Button("Show");
	
	private static VerticalPanel transactionPanel = new VerticalPanel();
	private static FlexTable transactionTable = new FlexTable();
	 private static FlexCellFormatter format;
	
	//private static ArrayList<Transaction> transList = new ArrayList<Transaction>();
	
	public static TabPanel init(){
	    
		//panel.add(typePanel);
		panel.add(timeframePanel);
		panel.add(buttonPanel);
		panel.add(transactionPanel);
		
		//typePanel.add(lType);
		//typePanel.add(typeBox);
		timeframePanel.add(lTimeFrame);
		timeframePanel.add(timeBox);
		timeframePanel.add(showButton);
		//buttonPanel.add(showButton);
		
		timeBox.addItem("Today");
		timeBox.addItem("Last week");
		timeBox.addItem("Last month");
		timeBox.addItem("Last year");
		
		transactionPanel.add(transactionTable);
				
		transactionTable.setCellSpacing(0);
	    transactionTable.setBorderWidth(1);
	    transactionTable.setCellPadding(5);	
	    //transactionTable.setText(0, 0, "text");
	    
		journalTabs.add(panel, "Transaction journal");
		journalTabs.add(panel, "Transactions:");  
		
		journalTabs.selectTab(0);

		showButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {				
				
				refreshData();
				
			}
		});
		
		journalTabs.addSelectionHandler(new SelectionHandler<Integer>() {
			
			@Override
			public void onSelection(SelectionEvent<Integer> event) {		
				
				//Window.alert("tab "+event.getSelectedItem()+" clicked!");
				switch(event.getSelectedItem()){
					case 0:{
						transactionTable.removeAllRows();					
						break;
					}
					default: transactionTable.removeAllRows();	
				}
			}
		});
	    
	    return journalTabs;
	}
	
	public static void reselectCurrentTab(){
		journalTabs.getTabBar().selectTab(journalTabs.getTabBar().getSelectedTab());
	}
	
	public static void cleanup(){
		transactionTable.removeAllRows();
	}
	
	public static void refreshData(){
		transactionTable.removeAllRows();
		SystemPanel.out("Retrieving journal...");
		PFMweb.download(PFMweb.dataURL, "/journal/list?userid=" + LocalData.getUser().getId()+"&timeframe="+Integer.valueOf(timeBox.getSelectedIndex()+1), "Accepts", RequestBuilder.GET);
		SystemPanel.statusSetOp("refreshing journal");
		SystemPanel.out("Refreshing journal");
		PFMweb.requestRefresh(RefreshingClasses.JRN);
		
	}
	
	private static Button makeRemoveButton(final int row) {
		Button b = new Button("remove");
		b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				SystemPanel.out("Deleting journal entry...");
				SystemPanel.statusSetOp("deleting journal entry");
				switch(LocalData.getJournalList().get(row).getType()){
					case 1: {
						PFMweb.download(PFMweb.dataURL, "/expense/" + LocalData.getJournalList().get(row).getTransactionId(),
								"Accepts", RequestBuilder.DELETE);
						break;
					}
					case 2:{
						PFMweb.download(PFMweb.dataURL, "/income/" + LocalData.getJournalList().get(row).getTransactionId(),
								"Accepts", RequestBuilder.DELETE);
						break;
					}
					case 3:{
						PFMweb.download(PFMweb.dataURL, "/transfer/" + LocalData.getJournalList().get(row).getTransactionId(),
								"Accepts", RequestBuilder.DELETE);
						break;
				}
				}
				PFMweb.requestRefresh(RefreshingClasses.JRN_REF);
			}
		});
		return b;
	}
	
	public static void handleJournal(){
		if (PFMweb.getJSONdata() != null) {
			if(PFMweb.getJSONdata().equals("null")){
				SystemPanel.out("Journal list is empty");
				transactionTable.setText(0, 0, "No transactions for "+timeBox.getItemText(timeBox.getSelectedIndex()));
				SystemPanel.statusDone();
				return;
			}
			
			LocalData.setJournalList(ParseJson.parseJournal(PFMweb.getJSONdata()));
			if (LocalData.getJournalList().size() > 0) {
				format = transactionTable.getFlexCellFormatter();
				for (int i = 0; i < LocalData.getJournalList().size(); i++) {
					switch(LocalData.getJournalList().get(i).getType()){
					case 1:{//expense
						transactionTable.setText(i, 0, "From "+LocalData.getJournalList().get(i).getAccountName());
						transactionTable.setText(i, 1, LocalData.getJournalList().get(i).getText());
						transactionTable.setText(i, 2, LocalData.getJournalList().get(i).getAmount());
						transactionTable.setText(i, 3, LocalData.getJournalList().get(i).getDate());
						transactionTable.setWidget(i, 4, makeRemoveButton(i));
						break;
					}
					case 2:{//income
						transactionTable.setText(i, 0, "To "+LocalData.getJournalList().get(i).getAccountName());
						transactionTable.setText(i, 1, LocalData.getJournalList().get(i).getText());
						transactionTable.setText(i, 2, LocalData.getJournalList().get(i).getAmount());
						transactionTable.setText(i, 3, LocalData.getJournalList().get(i).getDate());
						transactionTable.setWidget(i, 4, makeRemoveButton(i));
						break;
					}
					case 3:{//transfer
						transactionTable.setText(i, 0, LocalData.getJournalList().get(i).getAccountName());
						format.setColSpan(i, 0, 2);
						transactionTable.setText(i, 1, LocalData.getJournalList().get(i).getAmount());
						transactionTable.setText(i, 2, LocalData.getJournalList().get(i).getDate());
						transactionTable.setWidget(i, 3, makeRemoveButton(i));
						break;
					}
					}
					
				}
				}
			SystemPanel.statusDone();			
		} else {
			SystemPanel.out("Error receiving JSON");
			SystemPanel.statusError();
			return;
		}		
		
	}
	
}
