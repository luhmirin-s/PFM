package main.client;

import main.client.JSONPLoader.LoaderCallback;
import main.client.balance.Balance;
import main.client.journal.Journal;
import main.client.manager.AccountManager;
import main.client.manager.Manager;
import main.client.transactions.ExpenseTransactions;
import main.client.transactions.Transactions;
import main.client.users.LoginForm;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.json.client.JSONObject;


public class PFMweb implements EntryPoint {

	
private static TabPanel mainTabs = new TabPanel();
/*
private static VerticalPanel transPanel = new VerticalPanel();
private static VerticalPanel balancePanel = new VerticalPanel();
private static VerticalPanel journalPanel = new VerticalPanel();
private static VerticalPanel managerPanel = new VerticalPanel();
*/  
  //private HorizontalPanel toolbar = new HorizontalPanel();
  private static Timer refreshTimer;
  private Label errorMsgLabel = new Label();
  
  //private Label lastUpdatedLabel = new Label(); 
  private static final int SERVER_TIMEOUT = 500; //ms
  //http://localhost:8080/PFMWebService/jaxrs
  public static final String dataURL = getAddress();
  private static RequestBuilder rb;
  private static String jsonData;
  public static boolean uploaded;
  /**
   * Entry point method.
   */
  public void onModuleLoad() {
	  
	  mainTabs.getTabBar().getElement().setId("tabBarId");
	  //mainTabs.getDeckPanel().getElement().setId("mainTabsId");
	  //mainTabs.add(LoginForm.init(), "Login");
	  mainTabs.add(Transactions.init(), "Transactions");
	  mainTabs.add(Balance.init(), "Balance");
	  mainTabs.add(Journal.init(), "Journal");
	  mainTabs.add(Manager.init(), "Manage");	  
	  mainTabs.setSize("100%", "100%");  
	  
	  RootPanel.get("loginView").add(LoginForm.init());
	  RootPanel.get("sysPanelView").add(SystemPanel.init());
	  RootPanel.get("mainTabsView").add(mainTabs);
	  RootPanel.get("testView").add(TestingPanel.init());
	  mainTabs.selectTab(0);
	  ExpenseTransactions.focus();
	  
	  toggleView("loadingView", false);
	  toggleView("loginView", true);
	  toggleView("sysPanelView", false);
	  
	  //TestDBData.initData();
	  
	  refreshTimer = new Timer() {
	        @Override
	        public void run() {
	        	//refreshDataFromServer();
	        }
	      };
      //refreshTimer.scheduleRepeating(SERVER_TIMEOUT);
	  
  }
  
  public static String getAddress(){
	  String u = GWT.getModuleBaseURL();
	  String t = u.substring(7);
	  u=u.substring(7, 7+t.indexOf('/'));
	  return "http://"+u+"/PFMWebService/jaxrs";
  }
  
  public static void initRefreshTimer(final RefreshingClasses type){
	  refreshTimer = new Timer() {
	        @Override
	        public void run() {
	        	switch(type){
					case ACC_MGR:{AccountManager.refresh();
						break;}
					case CAT_MGR:{
						break;}
					case SRC_MGR:{
						break;}	        		
	        	}
	        }
	      };
	  refreshTimer.schedule(PFMweb.getTimeout());
  }
  
  public static void toggleView(String viewId, boolean enable){
	  if(enable)
		  DOM.getElementById(viewId).getStyle().setDisplay(Display.BLOCK);
	  else
		  DOM.getElementById(viewId).getStyle().setDisplay(Display.NONE);
  }
  
  	public static int getTimeout(){
  		return SERVER_TIMEOUT;
  	}
  	
  	public static String getJSONdata(){
  		return jsonData;
  	} 
  	
  	public static boolean isUploaded(){
  		return uploaded;
  	}
  	
  	/**
  	 * Uploads a String to the server and receives a String
  	 * @param url 	Specify PFMweb.dataURL for most cases
  	 * @param resource 	Example: /user
  	 * @param req	What to upload
  	 * @param method	POST, GET or anything else
  	 */
  	public static void uploadDownload(String url, String resource, final String req, RequestBuilder.Method method) {

		jsonData=null;
		SystemPanel.out("Initializing RequestBuilder...");
		rb = new RequestBuilder(method, url + resource);
		rb.setHeader("Accepts", "application/json");
		rb.setRequestData(req);
		SystemPanel.out("Setting callback...");
		rb.setCallback(new RequestCallback() {
			@Override
			public void onResponseReceived(Request request, Response response) {
				SystemPanel.out("Status: "+String.valueOf(response.getStatusCode()));
				if (200 == response.getStatusCode()) {
					SystemPanel.out("Received message: \n "
							+ response.getText());
					jsonData = response.getText();			
				} else {
					SystemPanel.out("Couldn't retrieve message: ("
							+ response.getStatusText() + ") code "
							+ response.getStatusCode());
				}
			}

			@Override
			public void onError(Request request, Throwable exception) {
				SystemPanel.out(exception.getMessage());
			}
		});

		try {
			SystemPanel.out("Sending request to " + url + resource + "...");
			rb.send();
		} catch (com.google.gwt.http.client.RequestException e) {
			SystemPanel.out(e.toString());
		}

		SystemPanel.out("Request sent...");
	}
  	
  	/**
  	 * Sends a request expecting a String to be returned.
  	 * Use PFMweb.getJSONdata() to get data when it returns
  	 * @param url 	Specify PFMweb.dataURL for most cases
  	 * @param resource 	Example: /user
  	 * @param header Header such as "Content-Type" or "Accepts"
  	 * @param method	POST, GET or anything else
  	 */ 	
  	public static void download(String url, String resource, String header, RequestBuilder.Method method) {

		jsonData=null;
  		SystemPanel.out("Initializing RequestBuilder...");
		rb = new RequestBuilder(method, url + resource);
		rb.setHeader(header, "application/json");
		SystemPanel.out("Setting callback...");
		rb.setCallback(new RequestCallback() {
			@Override
			public void onResponseReceived(Request request, Response response) {
				SystemPanel.out("Status: "+String.valueOf(response.getStatusCode()));
				if (200 == response.getStatusCode()) {
					SystemPanel.out("Received message: \n "
							+ response.getText());
					jsonData = response.getText();
				} else {
					SystemPanel.out("Couldn't retrieve message: ("
							+ response.getStatusText() + ") code "
							+ response.getStatusCode());
				}
			}

			@Override
			public void onError(Request request, Throwable exception) {
				SystemPanel.out(exception.getMessage());
			}
		});

		try {
			SystemPanel.out("Sending request to " + url + resource + "...");
			rb.send();
		} catch (com.google.gwt.http.client.RequestException e) {
			SystemPanel.out(e.toString());
		}

		SystemPanel.out("Request sent...");

	}
  	//Probably make uploaded int? -1 sending, 0 error, 1 uploaded
  	/**
  	 * Sends a String to the server, expects code 200
  	 * Use PFMweb.isUploaded() to check for successful upload
  	 * @param url 	Specify PFMweb.dataURL for most cases
  	 * @param resource 	Example: /user
  	 * @param req	Data to be uploaded
  	 * @param header Header such as "Content-Type" or "Accepts"
  	 * @param method	POST, GET or anything else
  	 */ 
  	public static void upload(String url, final String resource, final String req, String header, RequestBuilder.Method method) {

		uploaded=false;
		SystemPanel.out("Initializing RequestBuilder...");
		rb = new RequestBuilder(method, url + resource);
		rb.setHeader("Content-Type", "application/json");		
		rb.setRequestData(req);
		SystemPanel.out("Setting callback...");
		rb.setCallback(new RequestCallback() {
			@Override
			public void onResponseReceived(Request request, Response response) {				
				SystemPanel.out("Status: "+String.valueOf(response.getStatusCode()));
				if (200 == response.getStatusCode()) {
					SystemPanel.out("Received message: \n "
							+ response.getText());
					uploaded = true;
				} else {
					SystemPanel.out("Couldn't retrieve message: ("
							+ response.getStatusText() + ") code "
							+ response.getStatusCode());
					uploaded=false;
				}
			}

			@Override
			public void onError(Request request, Throwable exception) {
				SystemPanel.out(exception.getMessage());
			}
		});

		try {
			SystemPanel.out("Sending " + req + " to " + url + resource + "...");
			rb.send();
		} catch (com.google.gwt.http.client.RequestException e) {
			SystemPanel.out(e.toString());
		}

		SystemPanel.out("Data sent...");

	}
    
}