package main.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;

public class TransactionMaker {

	 private static TextBox inputBox;
	 private Button makeTransaction = new Button("Make transaction");
	
	 public TransactionMaker(){
		 inputBox = new TextBox();
	 }
}
