package main.client.users;

import main.client.PFMweb;
import main.client.SystemPanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoginForm {

	 private static VerticalPanel panel = new VerticalPanel();
	 private static FlexTable layout = new FlexTable();
	 private static FlexTable layoutSignup = new FlexTable();
	 private static DecoratorPanel signUpPanel = new DecoratorPanel();
	 private static DisclosurePanel signUpDisclosure = new DisclosurePanel("Sign up");
	 private static TextBox inputUsername = new TextBox();
	 private static TextBox inputPassword = new TextBox();
	 private static TextBox inputConfirmPassword = new TextBox();
	 private static TextBox inputEmail = new TextBox();
	 private static Button signIn = new Button("Sign in");
	 private static Button signUp = new Button("Sign up");
     
	
	 public static VerticalPanel init(){	
		
		//layout.setCellSpacing(8);
	 	//layout.setWidth("100%");
		panel.setWidth("35%");	
	 	layout.setText(0, 0, "Username: ");
	 	layout.setWidget(0, 1, inputUsername);
	 	layout.setText(1, 0, "Password: ");
	 	layout.setWidget(1, 1, inputPassword);
	 	layout.setWidget(2, 1, signIn);
	 	
	 	layoutSignup.setText(0, 0, "Confirm password: ");
	 	layoutSignup.setWidget(0, 1, inputConfirmPassword);
	 	layoutSignup.setText(1, 0, "E-mail: ");
	 	layoutSignup.setWidget(1, 1, inputEmail);
	 	layoutSignup.setText(2, 0, ""); //passwords do not match
	 	layoutSignup.setWidget(2, 1, signUp);

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
	 
	 private static void initHandlers(){
			signIn.addClickHandler(new ClickHandler() {				
				@Override
				public void onClick(ClickEvent event) {									
					//check login data from server
					PFMweb.toggleView("loginView", false);
					SystemPanel.doLogin(inputUsername.getText());
					PFMweb.toggleView("sysPanelView", true);
					PFMweb.toggleView("mainTabsView", true);
				}
			});
	 }
	 
}
