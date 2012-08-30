package main.client;

import main.client.data.LocalData;
import main.client.transactions.ExpenseTransactions;
import main.client.users.LoginForm;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;

public class SystemPanel {

	 //private static HorizontalPanel sysPanel = new HorizontalPanel();
	 private static DockPanel sysPanel = new DockPanel();
	 private static Button signOut = new Button("Sign out");
	 private static Label lLoggedAs = new Label("Logged in as ...");
	 private static Label lStatus = new Label("No changes made yet");
	 private static String currentOp;
	 /* for development purposes only */
	 private static TextArea console = new TextArea();
	
	 public static DockPanel init(){
		 
		 console.setCharacterWidth(80);
		 console.setVisibleLines(3);
		 
		 //lLoggedAs.setSize("300px", "100%");
		 sysPanel.setHorizontalAlignment(DockPanel.ALIGN_RIGHT);
		 sysPanel.setSpacing(32);
		 currentOp=null;
		 //sysPanel.add(console, DockPanel.WEST);
		 lStatus.setText("No changes made yet");
		 sysPanel.add(lStatus, DockPanel.WEST);
		 sysPanel.add(lLoggedAs, DockPanel.CENTER);
		 sysPanel.add(signOut, DockPanel.EAST);
		 
		 initHandlers();
		 
		 return sysPanel;
	 }
	 
	 public static void doLogin(String username){
		 if(username.isEmpty()) username="Demo user";
		 lLoggedAs.setText("Logged in as "+username);
		 lStatus.setText("No changes made yet");
	 }
	 
	 private static void initHandlers(){
			signOut.addClickHandler(new ClickHandler() {				
				@Override
				public void onClick(ClickEvent event) {									
					
					PFMweb.toggleView("sysPanelView", false);
					PFMweb.toggleView("mainTabsView", false);
					LocalData.initLogout();
					LoginForm.reloadForm();
					PFMweb.globalCleanup();
					PFMweb.toggleView("loginView", true);
					
				}
			});
	 }
	 
	 /* redirect */
	 public static void out(String msg){
		 //TestingPanel.out(msg);
	 }
	 public static void status(String st){
		 lStatus.setText(st);
	 }
	 /**
	  * Set a status to be displayed after some operation.
	  * Example: "adding data".
	  * Call statusDone() or statusError() after operation completes.
	  * @param op Set to null to display status "Ready"
	  */
	 public static void statusSetOp(String op){
		 lStatus.setText("Processing...");
		 currentOp=op;
	 }
	 public static void statusDone(){
		 if(currentOp==null) clearStatus();
		 else lStatus.setText("Done "+currentOp);
	 }
	 public static void statusError(){
		 if(currentOp==null) clearStatus();
		 else lStatus.setText("Error while "+currentOp+". Try again.");
	 }
	 public static void clearStatus(){
		 lStatus.setText("Ready");
	 }
}
