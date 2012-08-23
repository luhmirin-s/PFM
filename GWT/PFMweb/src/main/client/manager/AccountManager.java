package main.client.manager;

import main.client.Account;
import main.client.PFMweb;
import main.client.TestDBData;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
/*
import org.fusesource.restygwt.client.JsonCallback;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;
*/

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
	
	private static Label lUpdating = new Label("Updating data from server...");
	private static HorizontalPanel updatingPanel = new HorizontalPanel();
	
	private static Button refreshButton = new Button("Refresh");
	
	public static VerticalPanel init(){
		
		tablePanel.add(accTable);
		tablePanel.add(createNewButton);	
		updatingPanel.add(lUpdating);
		updatingPanel.setVisible(true);
		lUpdating.setText(("Ready to update!"));
		updatingPanel.add(refreshButton);
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
				refreshData();
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
	
	/**
	 * Creates an Account from table data, starting with the given row (one Account per call)
	 * Table headers are name|value|currency
	 * @param row - what row to start from
	 * @return Returns a newly created account
	 */
	public static Account convertToAccount(int row){
		
		int size=row;
		while(accTable.getText(size, 0)!="") size++;
		int[] values = new int[size];
		String[] currencies = new String[size];
		for(int i=0; i<size; i++){
			values[i]=Integer.valueOf(accTable.getText(row+i, 1));
			currencies[i]=accTable.getText(row+i, 2);
		}
		
		Account acc = new Account(TestDBData.retrieveData().size(), accTable.getText(row, 0), values, currencies);
		
		return acc;
	}
	
	public static void refreshData(){
		lUpdating.setText(("Updating data from server..."));
		final String url=URL.encode(//URL.encode(PFMweb.dataURL));
				GWT.getModuleBaseURL()+"test?q=ABC+DEF");
				//"http://10.0.1.59:8080/PFMWebService/jaxrs/source");
				//"http://google.ru");
				
		RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, url);				
		rb.setHeader("Content-Type", "application/json");
		 //RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, "http://www.lenta.ru");
		 //rb.setTimeoutMillis(3000);
	    rb.setCallback(new RequestCallback(){
			
			@Override
			public void onResponseReceived(Request request, Response response) {
				lUpdating.setText("Connecting to "+url+"...");
				if (200 == response.getStatusCode()) {
					Window.alert("Received message: \n "+response.getText());
		          } else {
		            Window.alert("Couldn't retrieve message: (" + response.getStatusText()
		                + ") code "+response.getStatusCode());
		          }
				
				
				//accTable.setText(accTable.getRowCount(), 1, response.getText());
				//accTable.setText(accTable.getRowCount()-1, 0, "test: ");
			}
			
			@Override
			public void onError(Request request, Throwable exception) {
				Window.alert(exception.getMessage());
			}
		});
	    rb.setRequestData("HelloFromClient!");
        
        try {
			rb.send();
		} catch (com.google.gwt.http.client.RequestException e) {
			Window.alert(e.toString());
		}
				                          //new DateCallbackHandler());

		
		/*
		Resource resource = new Resource("http://10.0.1.59:8080/PFMWebService/jaxrs/source");

		resource.get().send(new JsonCallback() {
		    public void onSuccess(Method method, JSONValue response) {		    	
		    	Window.alert(response.toString()+" "+method.getResponse().getText());
		    	System.out.println(response);
		    }
		    public void onFailure(Method method, Throwable exception) {
		        Window.alert("Error: "+exception);
		    }
		});
		*/
		
		lUpdating.setText("Data updated successfully");
	}	
		/*
		JavaScriptObject json;
	    //JavaScriptObject executeQuery(String query) {
	        	        
	        try {
	            @SuppressWarnings("unused")
	            Request request = builder.sendRequest(null, new RequestCallback() {
	                public void onError(Request request, Throwable exception) {
	                    // violation, etc.)
	                }

	                public void onResponseReceived(Request request,
	                        Response response) {
	                    if (200 == response.getStatusCode()) {
	                        // Process the response in response.getText()
	                        json =parseJson(response.getText());
	                    } else {

	                    }
	                }
	            });
	        } catch (RequestException e) {
	            // Couldn't connect to server
	        }
	        return json;
	    //}

	    public static native JavaScriptObject parseJson(String jsonStr) /*-{
	        return eval(jsonStr );
	        ;
	    }-*/;
	    
	    
    public void executeQuery(String query, final AsyncCallback<JavaScriptObject> callback){
	
    	//String url = "http://api.domain.com?client_id=xxxx&query=";
    	RequestBuilder builder = new RequestBuilder(RequestBuilder.GET,
	                URL.encode(PFMweb.dataURL + query));
    	
    	try{
    		builder.sendRequest(null, new RequestCallback() {
    			public void onError(Request request, Throwable caught) {
    				callback.onFailure(caught);
    			}
		
    			public void onResponseReceived(Request request, Response response) {
    				if(Response.SC_OK == response.getStatusCode()) {
						 try {
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
	
	public static void uploadData(){
		
	}

}
