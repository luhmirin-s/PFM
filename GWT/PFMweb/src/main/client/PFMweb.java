package main.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import main.client.balance.Balance;
import main.client.journal.Journal;
import main.client.manager.Manager;
import main.client.transactions.Transactions;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
//import com.google.gwt.sample.stockwatcher.client.StockData;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.Random;

public class PFMweb implements EntryPoint {

	
private static TabPanel mainTabs = new TabPanel();
/*
private static VerticalPanel transPanel = new VerticalPanel();
private static VerticalPanel balancePanel = new VerticalPanel();
private static VerticalPanel journalPanel = new VerticalPanel();
private static VerticalPanel managerPanel = new VerticalPanel();
*/  
  //private HorizontalPanel toolbar = new HorizontalPanel();
  private Timer refreshTimer;
  private Label errorMsgLabel = new Label();
  
  //private Label lastUpdatedLabel = new Label(); 
  private static final int REFRESH_INTERVAL = 1000; //ms
  private static final String JSON_URL = GWT.getModuleBaseURL() + "stockPrices?q=";
  public static final String dataURL = "http://10.0.1.59:8080/PFMWebService/jaxrs/source";

  /**
   * Entry point method.
   */
  public void onModuleLoad() {
	  /*
	  Balance.init(walletPanel);
	  ExpenseTransactions.init(makerPanel);
	  Journal.init(transPanel);
	  Manager.init(editPanel);
	  */  
	  mainTabs.add(Transactions.init(), "Transactions");
	  mainTabs.add(Balance.init(), "Balance");
	  mainTabs.add(Journal.init(), "Journal");
	  mainTabs.add(Manager.init(), "Manage");
	  //mainTabs.add(walletPanel, "Wallets");
	  //mainTabs.add(transPanel, "Transactions");
	  //mainTabs.add(editPanel, "Manage");	  
	  
	  RootPanel.get("mainTabsView").add(mainTabs);
	  mainTabs.selectTab(0);
	  /*
	  RootPanel.get("walletsView").add(walletPanel);
	  RootPanel.get("makeView").add(makerPanel);
	  RootPanel.get("transactionsView").add(transPanel);
	  RootPanel.get("editView").add(editPanel);
	  */
	  //TransactionMaker.focus();
	  
	  //TestDBData.initData();
	  
	  refreshTimer = new Timer() {
	        @Override
	        public void run() {
	        	//refreshDataFromServer();
	        }
	      };
      refreshTimer.scheduleRepeating(REFRESH_INTERVAL);
	  
  }
 
  	private String prepareURL(){
  		String url = JSON_URL;

	    // Append watch list stock symbols to query URL.
	    /*
	    Iterator iter; = stocks.iterator();
	    while (iter.hasNext()) {
	      url += iter.next();
	      if (iter.hasNext()) {
	        url += "+";
	      }
	    }
	    */

	    url = URL.encode(url);
		
  		return url;
  	}
  
  	private void refreshDataFromServer(){
	    String url = prepareURL();

	 // Send request to server and catch any errors.	    
	    RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);

	    try {
	      Request request = builder.sendRequest(null, new RequestCallback() {
	        public void onError(Request request, Throwable exception) {
	          displayError("Couldn't retrieve JSON");
	        }

	        public void onResponseReceived(Request request, Response response) {
	          if (200 == response.getStatusCode()) {
	            //updateTable(asArrayOfStockData(response.getText()));
	          } else {
	            displayError("Couldn't retrieve JSON (" + response.getStatusText()
	                + ")");
	          }
	        }
	      });
	    } catch (RequestException e) {
	      displayError("Couldn't retrieve JSON");
	    }
  	}
    /**
     * Convert the string of JSON into JavaScript object.
     */
    /*
  	private final native JsArray<StockData> asArrayOfStockData(String json) /*-{
      return eval(json);
    }-*/;
    
    /**
     * If can't get JSON, display error message.
     * @param error
     */
    private void displayError(String error) {
      errorMsgLabel.setText("Error: " + error);
      errorMsgLabel.setVisible(true);
    }
  
}
  /*
    
    Timer refreshTimer = new Timer(){
    	@Override
    	public void run(){
    		refreshWatchList();
    	}
    };
    refreshTimer.scheduleRepeating(REFRESH_INTERVAL);
    
    // Listen for keyboard events in the input box.
    newSymbolTextBox.addKeyPressHandler(new KeyPressHandler() {
      public void onKeyPress(KeyPressEvent event) {
        if (event.getCharCode() == KeyCodes.KEY_ENTER) {
          addStock();
        }
      }
    });
    
    
    
  }


  private void addStock() {
	  final String symbol = newSymbolTextBox.getText().toUpperCase().trim();
	    newSymbolTextBox.setFocus(true);

	    // Stock code must be between 1 and 10 chars that are numbers, letters, or dots.
	    if (!symbol.matches("^[0-9A-Z\\.]{1,10}$")) {
	      Window.alert("'" + symbol + "' is not a valid symbol.");
	      newSymbolTextBox.selectAll();
	      return;
	    }

	    newSymbolTextBox.setText("");

	    // TODO Don't add the stock if it's already in the table.
	    if(stocks.contains(symbol))
	    	return;
	    // TODO Add the stock to the table.
	    int row = stocksFlexTable.getRowCount();
	    stocks.add(symbol);
	    stocksFlexTable.setText(row, 0, symbol);
	    // TODO Add a button to remove this stock from the table.
	    Button removeStockButton = new Button("remove");
	    removeStockButton.addClickHandler(new ClickHandler(){
	    	public void onClick(ClickEvent event){
	    		int removedIndex = stocks.indexOf(symbol);
	    		stocks.remove(removedIndex);
	    		stocksFlexTable.removeRow(removedIndex + 1);
	    	}
	    });
	    stocksFlexTable.setWidget(row, 3, removeStockButton);
	    // TODO Get the stock price.
	    refreshWatchList();

  }
  

  private void refreshWatchList() {
    final double MAX_PRICE = 100.0; // $100.00
    final double MAX_PRICE_CHANGE = 0.02; // +/- 2%

    StockPrice[] prices = new StockPrice[stocks.size()];
    for (int i = 0; i < stocks.size(); i++) {
      double price = Random.nextDouble() * MAX_PRICE;
      double change = price * MAX_PRICE_CHANGE
          * (Random.nextDouble() * 2.0 - 1.0);

      prices[i] = new StockPrice(stocks.get(i), price, change);
    }

    updateTable(prices);
 }
  
@SuppressWarnings("deprecation")
private void updateTable(StockPrice[] prices) {
	for (int i = 0; i < prices.length; i++) {
	      updateTable(prices[i]);
	    }	
  // Display timestamp showing last refresh.
  lastUpdatedLabel.setText("Last update : "
      + DateTimeFormat.getMediumDateTimeFormat().format(new Date()));
}


private void updateTable(StockPrice price) {
  // Make sure the stock is still in the stock table.
  if (!stocks.contains(price.getSymbol())) {
    return;
  }

  int row = stocks.indexOf(price.getSymbol()) + 1;

  // Format the data in the Price and Change fields.
  String priceText = NumberFormat.getFormat("#,##0.00").format(
      price.getPrice());
  NumberFormat changeFormat = NumberFormat.getFormat("+#,##0.00;-#,##0.00");
  String changeText = changeFormat.format(price.getChange());
  String changePercentText = changeFormat.format(price.getChangePercent());

  // Populate the Price and Change fields with new data.
  stocksFlexTable.setText(row, 1, priceText);
  stocksFlexTable.setText(row, 2, changeText + " (" + changePercentText
      + "%)");
  
}

private void clearStocks(){
	  if(stocks.isEmpty()) 
	  	return;
	  while(!stocks.isEmpty()){
		  stocks.remove(stocks.size()-1);
		  stocksFlexTable.removeRow(stocksFlexTable.getRowCount() - 1);
	  }
  }

}
  
  */