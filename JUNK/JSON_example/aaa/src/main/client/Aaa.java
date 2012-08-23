package main.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Aaa implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */

	final Button JSONButton = new Button("Load JSON");
	final Button CONNButton = new Button("Connect");
	final Label errorLabel = new Label();

	private ArrayList<Customer> customers;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		// We can add style names to widgets
		JSONButton.addStyleName("sendButton");
		CONNButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element

		JSONButton.addClickHandler(new MyHandlerJSON());
		CONNButton.addClickHandler(new MyHandlerCONN());
		
		RootPanel.get("sendButtonContainer").add(JSONButton);
		RootPanel.get("sendButtonContainer").add(CONNButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		// Create a handler for the sendButton and nameField

	}

	class MyHandlerJSON implements ClickHandler {
		/**
		 * Fired when the user clicks on the sendButton.
		 */
		public void onClick(ClickEvent event) {
			loadJS();
		}
	}
	
	class MyHandlerCONN implements ClickHandler {
		/**
		 * Fired when the user clicks on the sendButton.
		 */
		public void onClick(ClickEvent event) {
			tryConn();
		}
	}

public void tryConn(){
	
	String url = "10.0.01.59:8080/PFMWebService/jaxrs/source";	
			
	// Send request to server and catch any errors
	RequestBuilder builder = new RequestBuilder(RequestBuilder.GET,url);

	try {
		Request request = builder.sendRequest(null, new RequestCallback() {
			public void onError(Request request, Throwable exception) {
				displayError("Couldn't retrieve JSON 1");
			}

			public void onResponseReceived(Request request,Response response) {
				if (200 == response.getStatusCode()) {
					Window.alert("asd");	
				} else {
					displayError("Couldn't retrieve JSON 2 ("
							+ response.getStatusText() + " - "+ response.getStatusCode() + ")");
				}
			}
		});
		
		
	} catch (RequestException e) {
		displayError("Couldn't retrieve JSON 3");
	}

}


	/*
	 * Takes in a trusted JSON String and evals it.
	 * 
	 * @param JSON String that you trust
	 * 
	 * @return JavaScriptObject that you can cast to an Overlay Type
	 */
 private final native JsArray<CustomerJS> parseJson(String json) /*-{
	return eval(json);
}-*/;
	
	public void loadJS() {
		String jsonData = "{["
				+ " { \"firstName\" : \"Jimmy\", \"lastName\" : \"Webber\", money:[{\"value\":111,\"code\":\"LVL\"},{\"value\":222,\"code\":\"RUB\"}] },"
				+ " { \"firstName\" : \"Alan\",  \"lastName\" : \"Dayal\", money:[{\"value\":333,\"code\":\"USD\"},{\"value\":444,\"code\":\"EUR\"}] },"
				+ "]}";
		
		//globaljnij spisok
		customers = new ArrayList<Customer>();
		
		try {
			//parsim JSON
			JsArray jsobj = parseJson(jsonData);

			//Kazdij osnovnoj elemnent peredelivaem v nuznij nam objekt
			for (int i = 0; i < jsobj.length(); i++) {
				
				//vremennie peremennie:
				ArrayList<Money> tempArr = new ArrayList();
				Customer tempCust = new Customer("a", "b", tempArr);
				
				//kazdoe pole elementa peredajom vremennomu objektu
				tempCust.setFirstName(((CustomerJS) jsobj.get(i)).getFirstName());
				tempCust.setLastName(((CustomerJS) jsobj.get(i)).getLastName());

				//vremennomu massivu JS peredajom vlozennij spisok
				JsArray<MoneyJS> jsMoney = ((CustomerJS) jsobj.get(i)).getMoney();
				//kazdij element spiska perevodiv v vremennuju peremennuju 
				for (int j = 0; j < jsMoney.length(); j++) {
					//vremennaja peremennaja
					Money tempMoney = new Money("as", 43);
					//kazdoe pole spiska peredajom peremennoj
					tempMoney.setValue((int) ((MoneyJS) jsMoney.get(j)).getValue());
					tempMoney.setCode(((MoneyJS) jsMoney.get(j)).getCode());
					//dobavljaem kazduju peremennuju k vremennomu spisku
					tempArr.add(tempMoney);
				}
				
				//vremennomu objektu peredajom vremennij vlozennij spisok
				tempCust.setMoney(tempArr);
				//dobavljaem vremennuju peremennuju v globaljnij spisok
				customers.add(tempCust);
			}

			//vivodim klass:
			String line = "";
			for (Customer c : customers) {
				line += c.getFirstName() + " " + c.getLastName() + "\n#";
				for (Money m : c.getMoney()) {
					line += m.getCode() + " " + m.getValue() + "\n#";
				}
				line += "\n";
			}
			Window.alert(line);

		} catch (Exception e) {
			errorLabel.setText(e.toString());
		}
		

	}


	private void displayError(String error) {
		errorLabel.setText("Error: " + error);
		errorLabel.setVisible(true);
	}
	
	

}
