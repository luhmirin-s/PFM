package main.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import main.client.ParseJson;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class JsonTest implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	final Button sendButton = new Button("Send");

	final Label errorLabel = new Label();

	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
	
		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element

		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);

	}

	
	//Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler {
			
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				
				/*String jsonData = "{\"source\": "+
						"[" +
						"{\"id\": \"1\",\"name\": \"Hansabanka\",\"userId\": \"1\"}," +
						"{\"id\": \"2\",\"name\": \"Wallet    \",\"userId\": \"1\"}," +
						"{\"id\": \"3\",\"name\": \"Alibabank \",\"userId\": \"1\"}" +
						"]}";
				String jsonData2 = "{\"id\": \"1\",\"name\": \"Hansabanka\",\"userId\": \"1\"}";
				
				String jsonData3 = "{\"currency\": " +
						"[" +
						"{\"code\": \"LVL\",\"id\": \"1\"}," +
						"{\"code\": \"EUR\",\"id\": \"2\"}," +
						"{\"code\": \"USD\",\"id\": \"3\"}" +
						"]}";
				
				String jsonData4 = "{\"code\": \"USD\",\"id\": \"3\"}";
				
				String jsonData5 = "{\"id\": \"1\",\"username\": \"test\",\"password\": \"test\",\"email\": \"test\"}";
				
				ArrayList<User> accs = new ArrayList();
				String line = "";
				
				accs = ParseJson.parseSource(jsonData2);
				
				for (Source c : accs) {
					line += c.getId() + " "+ c.getName()+ " " + c.getUserId()+"\n";
				}
				
				ArrayList<Currency> accs = new ArrayList();
				accs = ParseJson.parseCurrency(jsonData4);
				
				for (Currency c : accs) {
					line += c.getId() + " "+ c.getCode()+"\n";
				}
				
				User acc = ParseJson.parseUser(jsonData5);
				line += acc.getId()+" "+acc.getUsername()+" "+acc.getPassword()+" "+acc.getEmail();						
						
				Window.alert(line);*/
				
				Window.alert(CreateJson.toJsonCreateUser("strashnij", "volk", "sabaka@babaka.lv"));
				

		}

	}
}
