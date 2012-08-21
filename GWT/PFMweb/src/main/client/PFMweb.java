package main.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.Random;

public class PFMweb implements EntryPoint {

  private static VerticalPanel walletPanel = new VerticalPanel();
  private VerticalPanel makerPanel = new VerticalPanel();
  private VerticalPanel transPanel = new VerticalPanel();
  private VerticalPanel editPanel = new VerticalPanel();
  
  private HorizontalPanel toolbar = new HorizontalPanel();
  
  //private Label lastUpdatedLabel = new Label(); 
  //private static final int REFRESH_INTERVAL = 1000; //ms

  /**
   * Entry point method.
   */
  public void onModuleLoad() {
    
	  Wallets.init(walletPanel);
	  TransactionMaker.init(makerPanel);
	  Transactions.init(transPanel);
	  WalletEditor.init(editPanel);
	  
	  RootPanel.get("walletsView").add(walletPanel);
	  RootPanel.get("makeView").add(makerPanel);
	  RootPanel.get("transactionsView").add(transPanel);
	  RootPanel.get("editView").add(editPanel);
	  
	  TransactionMaker.focus();
	  
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