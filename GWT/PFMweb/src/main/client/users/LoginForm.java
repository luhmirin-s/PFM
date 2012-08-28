package main.client.users;

import main.client.PFMweb;
import main.client.SystemPanel;
import main.client.data.CreateJson;
import main.client.data.LocalData;
import main.client.data.ParseJson;
import main.client.data.User;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoginForm {

	 private static VerticalPanel panel = new VerticalPanel();
	 private static FlexTable layout = new FlexTable();
	 private static FlexTable layoutSignup = new FlexTable();
	 private static DecoratorPanel signUpPanel = new DecoratorPanel();
	 private static DisclosurePanel signUpDisclosure = new DisclosurePanel("Sign up");
	 private static TextBox inputUsername = new TextBox();
	 private static PasswordTextBox inputPassword = new PasswordTextBox();
	 private static PasswordTextBox inputConfirmPassword = new PasswordTextBox();
	 private static TextBox inputEmail = new TextBox();
	 //private static HorizontalPanel signInHor = new HorizontalPanel();
	 //private static HorizontalPanel signUpHor = new HorizontalPanel();
	 private static Button signIn = new Button("Sign in");
	 private static Button signUp = new Button("Sign up");
	 private static Label lSignInStatus = new Label("");
	 private static Label lSignUpStatus = new Label("");
	 private static FlexCellFormatter layoutFormat;
     
	 private static Timer loginFormTimer;
	
	 public static VerticalPanel init(){	
		
		//layout.setCellSpacing(8);
	 	//layout.setWidth("100%");
		panel.setWidth("35%");	
		layoutFormat = layout.getFlexCellFormatter();
	 	layout.setText(0, 0, "Username: ");
	 	layout.setWidget(0, 1, inputUsername);
	 	layoutFormat.setColSpan(0, 1, 2);
	 	layout.setText(1, 0, "Password: ");
	 	layout.setWidget(1, 1, inputPassword);
	 	layoutFormat.setColSpan(1, 1, 2);
	 	lSignInStatus.setText("");
	 	layout.setWidget(2, 1, signIn);
	 	layout.setWidget(2, 2, lSignInStatus);
	 	
	 	layoutFormat = layoutSignup.getFlexCellFormatter();
	 	layoutSignup.setText(0, 0, "Confirm password: ");
	 	layoutSignup.setWidget(0, 1, inputConfirmPassword);
	 	layoutFormat.setColSpan(0, 1, 2);
	 	layoutSignup.setText(1, 0, "E-mail: ");
	 	layoutSignup.setWidget(1, 1, inputEmail);
	 	layoutFormat.setColSpan(1, 1, 2);
	 	//layoutSignup.setText(2, 0, ""); //passwords do not match
	 	lSignUpStatus.setText("");
	 	layoutSignup.setWidget(2, 1, signUp);
	 	layoutSignup.setWidget(2, 2, lSignUpStatus);	 	
	 	

	 	signUpDisclosure.setAnimationEnabled(true);
	 	//signUpDisclosure.ensureDebugId("cwDisclosurePanel");
	 	signUpDisclosure.setContent(layoutSignup);

	    // Wrap the contents in a DecoratorPanel	    
	    signUpPanel.setWidget(layout);
	    
	    panel.add(layout);
	    panel.add(signUpDisclosure);
	    
	    initHandlers();
	    
	 	return panel;
	 }
	 
	 public static void reloadForm(){
		 
		 inputUsername.setText("");
		 inputPassword.setText("");
		 inputConfirmPassword.setText("");
		 inputEmail.setText("");
		 signUpDisclosure.setOpen(false);
		 
	 }
	 
	 public static void stopTimer(){
		 loginFormTimer.cancel();
		 SystemPanel.out("Timer forced to stop");
	 }
	 
	 private static void initHandlers(){
		 
		signIn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
				PFMweb.uploadDownload(PFMweb.dataURL, "/user", "username="+inputUsername.getText()+"&password="+inputPassword.getText(), RequestBuilder.POST);
				SystemPanel.out("Download initiated");				
				loginFormTimer = new Timer() {
					@Override
					public void run() {
						
						if(!PFMweb.getJSONdata().isEmpty()){	
							SystemPanel.out("Object received!");							
							SystemPanel.out("parsing...");
							User u = ParseJson.parseUser(PFMweb.getJSONdata());
							LocalData.initLogin(u.getId(), u.getUsername(), u.getPassword(), u.getEmail());
							lSignInStatus.setText("");
							PFMweb.toggleView("loginView", false);
							SystemPanel.doLogin(u.getUsername());
							PFMweb.toggleView("sysPanelView", true);
							PFMweb.toggleView("mainTabsView", true);							
 
						} else {
							SystemPanel.out("Received null");
							lSignInStatus.setText("Try again");
						}
					}
				};
		      loginFormTimer.schedule(PFMweb.getTimeout());				
		      lSignInStatus.setText("Logging in...");
			}
		});
			
		signUp.addClickHandler(new ClickHandler() {				
			@Override
			public void onClick(ClickEvent event) {														

				String username=inputUsername.getText(),
				password=inputPassword.getText(),
				confPassword=inputConfirmPassword.getText(),
				email=inputEmail.getText();
				
				if(
					!username.isEmpty() && !password.isEmpty() && !email.isEmpty() &&
					//username.matches("^[0-9A-Za-z\\.]{3,32}$") &&
					//password.matches("^[0-9A-Za-z\\.]{3,32}$") &&
					password.equals(confPassword) 
					//email.matches("^[0-9A-Za-z\\.]{3,32}$")
				){
					PFMweb.upload(PFMweb.dataURL, "/user", CreateJson.toJsonCreateUser(username, password, email), "Accepts", RequestBuilder.POST);
					loginFormTimer = new Timer() {
						@Override
						public void run() {
							
							if(PFMweb.isUploaded()){	
								SystemPanel.out("User successfully created!");							
								cleanup();								 
							} else {
								SystemPanel.out("User already present or error");
								lSignUpStatus.setText("An error occured");
							}
						}
					};
					loginFormTimer.schedule(PFMweb.getTimeout()*2);
					
				} else {
					SystemPanel.out("Check input!");
					lSignUpStatus.setText("Check input!");
				}				
								 	
			}
		});
	 }
	 /**
	  * Cleans all fields of the Login form
	  */
	 private static void cleanup(){
		 inputUsername.setText("");
		 inputPassword.setText("");
		 inputConfirmPassword.setText("");
		 inputEmail.setText("");
		 lSignInStatus.setText("");
		 lSignUpStatus.setText("");
		 signUpDisclosure.setOpen(false);
	 }
	 
}