package main.client;

import main.client.balance.Balance;
import main.client.journal.Journal;
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
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;

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
  //private static final String JSON_URL = GWT.getModuleBaseURL() + "stockPrices?q=";
  public static final String dataURL = "localhost:8080/PFMWebService/jaxrs/";
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
      refreshTimer.scheduleRepeating(REFRESH_INTERVAL);
	  
  }
  
  public static void toggleView(String viewId, boolean enable){
	  if(enable)
		  DOM.getElementById(viewId).getStyle().setDisplay(Display.BLOCK);
	  else
		  DOM.getElementById(viewId).getStyle().setDisplay(Display.NONE);
  }
  
  	public static String download(String url, String resource, RequestBuilder.Method method) {

		jsonData=null; //returns null before Callback
		SystemPanel.out("Initializing RequestBuilder...");
		rb = new RequestBuilder(RequestBuilder.GET, url + resource);
		rb.setHeader("Content-Type", "application/json");
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
			SystemPanel.out("Sending request to " + dataURL + resource + "...");
			rb.send();
		} catch (com.google.gwt.http.client.RequestException e) {
			SystemPanel.out(e.toString());
		}

		SystemPanel.out("Request sent...");

		return jsonData;
	}
  	
  	public static boolean upload(String url, final String resource, final String req, RequestBuilder.Method method) {

		if (req.isEmpty()){
			SystemPanel.out("Won't send empty request");
			return false;
		}
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
				}
			}

			@Override
			public void onError(Request request, Throwable exception) {
				SystemPanel.out(exception.getMessage());
			}
		});

		try {
			SystemPanel.out("Sending " + req + " to " + dataURL + resource + "...");
			rb.send();
		} catch (com.google.gwt.http.client.RequestException e) {
			SystemPanel.out(e.toString());
		}

		SystemPanel.out("Data sent...");

		return uploaded;
	}
    
}