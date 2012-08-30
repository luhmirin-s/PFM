package main.client;

import main.client.balance.Balance;
import main.client.journal.Journal;
import main.client.manager.AccountManager;
import main.client.manager.CategoryManager;
import main.client.manager.Manager;
import main.client.manager.SourceManager;
import main.client.transactions.ExpenseTransactions;
import main.client.transactions.IncomeTransactions;
import main.client.transactions.Transactions;
import main.client.transactions.TransferTransactions;
import main.client.users.LoginForm;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;


public class PFMweb implements EntryPoint {

	
private static TabPanel mainTabs = new TabPanel();

  private static Timer refreshTimer;
  private Label errorMsgLabel = new Label();
  
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
	  
	  //Transactions.reselectCurrentTab();
	  mainTabs.selectTab(0);
	  
	  toggleView("loadingView", false);
	  toggleView("loginView", true);
	  toggleView("sysPanelView", false);
	  
	  initListeners();
	  
  }
  
  private static void initListeners(){
	  mainTabs.addSelectionHandler(new SelectionHandler<Integer>() {
			
			@Override
			public void onSelection(SelectionEvent<Integer> event) {		
				
				//Window.alert("tab "+event.getSelectedItem()+" clicked!");
				switch(event.getSelectedItem()){
					case 0:{
						Transactions.reselectCurrentTab();
						break;
					}	
					
					case 1:{
						Balance.reselectCurrentTab();
						break;
					}
					
					case 2:{
						SystemPanel.out("Selected tab 2");
						break;
					}
					
					case 3:{
						Manager.reselectCurrentTab();
						break;
					}

					default: SystemPanel.out("Default selection");
				}
			}
		});
  }
  
  public static String getAddress(){
	  String u = GWT.getModuleBaseURL();
	  String t = u.substring(7);
	  u=u.substring(7, 7+t.indexOf('/'));
	  return "http://"+u+"/PFMWebService/jaxrs";
  }
  
  public static TabPanel getMaintabs(){
	  return mainTabs;
  }
  
  /**
   * Call when need to refresh all data after clicking on the tab
   * @param type
   */
  public static void initRefreshTimer(final RefreshingClasses type){
	  refreshTimer = new Timer() {
	        @Override
	        public void run() {
	        	switch(type){
					case ACC_MGR:{AccountManager.refresh();
						break;}
					case CAT_MGR:{CategoryManager.refresh();
						break;}
					case SRC_MGR:{SourceManager.refresh();
						break;}	        		
	        	}
	        }
	      };
	  refreshTimer.schedule(getTimeout());
  }
  /**
   * Call when need to refresh all data after an operation (add, edit, delete)
   * EDIT: from now on, will be used also as initRefreshTimer (must specify specific type)
   * @param type
   */
  public static void requestRefresh(final RefreshingClasses type){
	  refreshTimer = new Timer() {
	        @Override
	        public void run() {
	        	SystemPanel.out("Timer called for "+type.toString());
	        	switch(type){
					case ACC_MGR:{AccountManager.initRefresh();
						break;}
					case CAT_MGR:{CategoryManager.initRefresh();
						break;}
					case SRC_MGR:{SourceManager.initRefresh();
						break;}	
					
					case TRA_EXP:{ExpenseTransactions.refreshData();
						break;}
					case TRA_EXP_ACC:{ExpenseTransactions.handleAccounts();
						break;}
					case TRA_EXP_CAT:{ExpenseTransactions.handleCategories();
						break;}	
					case TRA_EXP_CUR:{ExpenseTransactions.handleCurrencies();
						break;}
					case TRA_INC:{IncomeTransactions.refreshData();
						break;}
					case TRA_INC_ACC:{IncomeTransactions.handleAccounts();
						break;}
					case TRA_INC_CUR:{IncomeTransactions.handleCurrencies();
						break;}	
					case TRA_INC_SRC:{IncomeTransactions.handleSources();
						break;}
					case TRA_TRANSF:{TransferTransactions.refreshData();
						break;}
					case TRA_TRANSF_ACC:{TransferTransactions.handleAccounts();
						break;}
					case TRA_TRANSF_CUR:{TransferTransactions.handleCurrencies();
						break;}	
					
					case BAL_ACC:{Balance.handleAccounts();
					break;}
					case BAL_CUR:{Balance.handleCurrencies();
						break;}
					case BAL_AMNT:{Balance.retrieveBalance();
						break;}	
	        	}
	        }
	      };
	  refreshTimer.schedule(300);
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
  	 * Uploads a String to the server and receives a String.
  	 * Sets jsonData to received JSON, or "fail" if received code 
  	 * differs from 200
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
					jsonData="fail";
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