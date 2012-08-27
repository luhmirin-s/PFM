package main.client;

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
	 /* for development purposes only */
	 private static TextArea console = new TextArea();
	
	 public static DockPanel init(){
		 
		 console.setCharacterWidth(80);
		 console.setVisibleLines(3);
		 
		 //lLoggedAs.setSize("300px", "100%");
		 sysPanel.setHorizontalAlignment(DockPanel.ALIGN_RIGHT);
		 sysPanel.setSpacing(32);
		 sysPanel.add(console, DockPanel.WEST);
		 sysPanel.add(lLoggedAs, DockPanel.CENTER);
		 sysPanel.add(signOut, DockPanel.EAST);
		 
		 initHandlers();
		 
		 return sysPanel;
	 }
	 
	 public static void doLogin(String username){
		 if(username.isEmpty()) username="Demo user";
		 lLoggedAs.setText("Logged in as "+username);
	 }
	 
	 private static void initHandlers(){
			signOut.addClickHandler(new ClickHandler() {				
				@Override
				public void onClick(ClickEvent event) {									
					//check login data from server
					PFMweb.toggleView("sysPanelView", false);
					PFMweb.toggleView("mainTabsView", false);
					//clear data in mainTabsView
					LoginForm.reloadForm();
					PFMweb.toggleView("loginView", true);
				}
			});
	 }
	 
	 public static void out(String msg){
		 TestingPanel.out(msg);
	 }
}
