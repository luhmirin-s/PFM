package main.client.manager;

import java.util.ArrayList;

import main.client.SystemPanel;
import main.client.data.Customer;
import main.client.data.CustomerJS;
import main.client.data.Money;
import main.client.data.MoneyJS;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

																	//toDo: get url from txt/html; rb.setHeader("Content-Type", "application/json");

public class AccountManager {
	
	private static VerticalPanel tablePanel = new VerticalPanel();
	
	private static DialogBox newDialog = new DialogBox();
	private static DialogBox editDialog = new DialogBox();
	private static DialogBox deletePrompt = new DialogBox();
	private static VerticalPanel newDialogPanel = new VerticalPanel();
	private static VerticalPanel editDialogPanel = new VerticalPanel();
	private static VerticalPanel delDialogPanel = new VerticalPanel();
		
	private static FlexTable accTable = new FlexTable();
	
	private static HorizontalPanel newNamePanel = new HorizontalPanel();
	private static Label lNewName = new Label("Name: ");
	private static TextBox inputNewName = new TextBox();
	private static Label lNewError = new Label("Please specify a valid name!");
	private static HorizontalPanel newButtons = new HorizontalPanel();
	private static Button newSaveButton = new Button("Save");
	private static Button newCancelButton = new Button("Cancel");
	
	private static HorizontalPanel editNamePanel = new HorizontalPanel();
	private static Label lEditName = new Label("New name: ");
	private static TextBox inputEditName = new TextBox();
	private static Label lEditError = new Label("Please specify a valid replacement name!");
	private static HorizontalPanel editButtons = new HorizontalPanel();
	private static Button editUpdateButton = new Button("Update");
	private static Button editCancelButton = new Button("Cancel");
	
	private static HorizontalPanel delNamePanel = new HorizontalPanel();
	private static Label lDelPrompt = new Label("Are you sure you want to delete?");
	private static HorizontalPanel delButtons = new HorizontalPanel();
	private static Button delConfirmButton = new Button("Confirm");
	private static Button delCancelButton = new Button("Cancel");
	
	private static Button createNewButton = new Button("Create new account");	
	private static int editedRow;
	
	//private static Label lUpdating = new Label("Updating data from server...");
	private static HorizontalPanel updatingPanel = new HorizontalPanel();
	
	/*					For testing purposes									*/
	private static Button refreshButton = new Button("Refresh");
	private static Button setHostButton = new Button("Set server host");
	private static TextBox inputServer = new TextBox();
	
	private static ArrayList<Customer> customers;
	
	private static String jsonData;
	
	public static VerticalPanel init(){
		
		tablePanel.add(accTable);
		tablePanel.add(createNewButton);	
		//updatingPanel.add(lUpdating);
		updatingPanel.setVisible(true);
		//lUpdating.setText(("Ready to update!"));
		updatingPanel.add(inputServer);
		inputServer.setText(GWT.getModuleBaseURL()+"test?q=ABC+DEF");
		inputServer.setWidth("640px");
		updatingPanel.add(refreshButton);	
		updatingPanel.add(setHostButton);
		tablePanel.add(updatingPanel);
			
		newNamePanel.add(lNewName);
		newNamePanel.add(inputNewName);
		newNamePanel.add(lNewError);
		lNewError.setText("");
		newButtons.add(newSaveButton);
		newButtons.add(newCancelButton);
		newDialogPanel.add(newNamePanel);
		newDialogPanel.add(newButtons);
		newDialog.add(newDialogPanel);
		newDialog.setGlassEnabled(true);
		
		editNamePanel.add(lEditName);
		editNamePanel.add(inputEditName);
		editNamePanel.add(lEditError);
		lNewError.setText("");
		editButtons.add(editUpdateButton);
		editButtons.add(editCancelButton);
		editDialogPanel.add(editNamePanel);
		editDialogPanel.add(editButtons);
		editDialog.add(editDialogPanel);
		editDialog.setGlassEnabled(true);
		
		delNamePanel.add(lDelPrompt);
		delButtons.add(delConfirmButton);
		delButtons.add(delCancelButton);
		delDialogPanel.add(delNamePanel);
		delDialogPanel.add(delButtons);
		deletePrompt.add(delDialogPanel);
		deletePrompt.setGlassEnabled(true);
		
		initListeners();
		return tablePanel;
	}
	
	private static void initListeners(){
		
		createNewButton.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {				
				inputNewName.setText("");
				newDialog.center();					
			}
		});
		
		refreshButton.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {				
					refreshButton.setText("Refresh");
					refreshData(inputServer.getText());				
			}
		});	
		
		setHostButton.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {				
					inputServer.setText(GWT.getModuleBaseURL());			
			}
		});
		
		newSaveButton.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {	
				String txt = inputNewName.getText().trim();
				if(txt.matches("^[0-9A-Za-z\\s]{1,16}$")){
					lNewError.setText("");
					int rc = accTable.getRowCount();
					accTable.setWidget(rc, 0, new Label(txt));
					accTable.setWidget(rc, 1, makeEditButton(rc));
					accTable.setWidget(rc, 2, makeDeleteButton(rc));
					newDialog.hide();
				} else {
					lNewError.setText("Please specify a valid name!");
				}
			}
		});
		
		editUpdateButton.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {	
				String txt = inputEditName.getText().trim();
				if(txt.matches("^[0-9A-Za-z\\s]{1,16}$")){
					lEditError.setText("");
					accTable.clearCell(editedRow, 0);
					accTable.setWidget(editedRow, 0, new Label(txt));
					editDialog.hide();
				} else {
					lEditError.setText("Please specify a valid replacement name!");
				}
			}
		});
		
		delConfirmButton.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {	
				accTable.removeRow(editedRow);
				deletePrompt.hide();
			}
		});
		
		newCancelButton.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {								
				newDialog.hide();				
			}
		});
		
		editCancelButton.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {								
				editDialog.hide();				
			}
		});
		
		delCancelButton.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {								
				deletePrompt.hide();				
			}
		});
	}
	
	private static Button makeEditButton(final int row){
		Button b = new Button("edit");
		b.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event){
				inputEditName.setText("");
				editedRow=row;
				editDialog.center();
			}
		});
		return b;
	}
	
	private static Button makeDeleteButton(final int row){
		Button b = new Button("delete");
		b.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event){
				lDelPrompt.setText("Are you sure you want to delete account '"+accTable.getText(row, 0)+"'?");
				editedRow=row;
				deletePrompt.center();
			}
		});
		return b;
	}
	
	 private final native static JsArray<CustomerJS> parseJson(String json) /*-{
		return eval(json);
	}-*/;

	public static void loadJS() {


		//globaljnij spisok
		customers = new ArrayList<Customer>();

		try {
			//parsim JSON
			JsArray<CustomerJS> jsobj = parseJson(jsonData);

			//Kazdij osnovnoj elemnent peredelivaem v nuznij nam objekt
			for (int i = 0; i < jsobj.length(); i++) {

				//vremennie peremennie:
				ArrayList<Money> tempArr = new ArrayList<Money>();
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
			String line = "Class output: \n";
			for (Customer c : customers) {
				line += c.getFirstName() + " " + c.getLastName() + "\n#";
				for (Money m : c.getMoney()) {
					line += m.getCode() + " " + m.getValue() + "\n#";
				}
				line += "\n";
			}
			SystemPanel.out(line);

		} catch (Exception e) {
			//errorLabel.setText(e.toString());
		}


	}
	
	public static void refreshData(final String url){
		//lUpdating.setText(("Updating data from server..."));
				//GWT.getModuleBaseURL()+"test?q=ABC+DEF");
				//"http://10.0.1.59:8080/PFMWebService/jaxrs/source");
				
		RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, url);				
		rb.setHeader("Content-Type", "application/json");
	    rb.setCallback(new RequestCallback(){
			
			@Override
			public void onResponseReceived(Request request, Response response) {
				//lUpdating.setText("Connecting to "+url+"...");
				SystemPanel.out("Connecting to "+url+"...");
				if (200 == response.getStatusCode()) {
					SystemPanel.out("Received message: \n "+response.getText());
					jsonData=response.getText();
					loadJS();
            	} else {
            		SystemPanel.out("Couldn't retrieve message: (" + response.getStatusText()
        				+ ") code "+response.getStatusCode());
		          }			
			}
			
			@Override
			public void onError(Request request, Throwable exception) {
				SystemPanel.out(exception.getMessage());
			}
		});
	    rb.setRequestData("HelloFromClient!");
        
        try {
			rb.send();
		} catch (com.google.gwt.http.client.RequestException e) {
			SystemPanel.out(e.toString());
		}
		
        SystemPanel.out("Data updated successfully");
	}		    
	
	/*    
    public void executeQuery(String query, final AsyncCallback<JavaScriptObject> callback){

    	RequestBuilder builder = new RequestBuilder(RequestBuilder.GET,
	                URL.encode(PFMweb.dataURL + query));
    	
    	try{
    		builder.sendRequest(null, new RequestCallback() {
    			public void onError(Request request, Throwable caught) {
    				callback.onFailure(caught);
    			}
		
    			public void onResponseReceived(Request request, Response response) {
    				if(Response.SC_OK == response.getStatusCode()) {
						 try {send
						   callback.onSuccess(JsonUtils.safeEval(response.getText()));
						 } catch (IllegalArgumentException iax) {
						   callback.onFailure(iax);
						 }
					} else {
						// Better to use a typed exception here to indicate the specific
						// cause of the failure.
						callback.onFailure(new Exception("Bad return code."));
					}
    			}
    		});
    	} catch (RequestException e) {
    		callback.onFailure(e);
    	}
	}
	*/	
	
	public static void uploadData(){
		
	}

}
