package main.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
/**
 * 
 * Class used for testing purposes (sending/getting JSON data)
 *
 */
public class TestingPanel {

	private static VerticalPanel vertPanel = new VerticalPanel();
	private static HorizontalPanel consolePanel = new HorizontalPanel();
	private static HorizontalPanel horPanel = new HorizontalPanel();
	private static HorizontalPanel buttonPanel = new HorizontalPanel();
	private static Button toggleButton = new Button("Show test stuff");
	/*					For testing purposes					*/
	private static Button getDownButton = new Button("Download(GET)");
	private static Button postDownButton = new Button("Download(POST)");
	private static Button getUpButton = new Button("Upload(GET)");
	private static Button postUpButton = new Button("Upload(POST)");
	private static Button getHostButton = new Button("Get server host");
	private static Button demoUserButton = new Button("Bypass login");
	public static TextBox inputServer = new TextBox();
	private static TextBox inputResource = new TextBox();
	private static TextBox inputRequest = new TextBox();
	private static TextArea console = new TextArea();
	private static Label lServer = new Label("Server: ");
	private static Label lResource = new Label("Resource: ");
	private static Label lRequest = new Label("Request: ");
		
	public static VerticalPanel init(){
		 
		 console.setCharacterWidth(80);
		 console.setVisibleLines(3);
		 consolePanel.add(console);
		 
		 horPanel.setSpacing(8);		
		 horPanel.add(lServer);
		 inputServer.setText("127.0.0.1:8080/PFMWebService/jaxrs/");
		 horPanel.add(inputServer);
		 horPanel.add(lResource);
		 inputResource.setText("");
		 horPanel.add(inputResource);
		 horPanel.add(lRequest);
		 inputRequest.setText("");
		 horPanel.add(inputRequest);
		 buttonPanel.add(getHostButton);
		 buttonPanel.add(getDownButton);
		 buttonPanel.add(postDownButton);
		 buttonPanel.add(getUpButton);
		 buttonPanel.add(postUpButton);
		 buttonPanel.add(demoUserButton);
		 
		 vertPanel.add(toggleButton);
		 vertPanel.add(consolePanel);
		 vertPanel.add(horPanel);
		 vertPanel.add(buttonPanel);
		 
		 consolePanel.setVisible(false);
		 horPanel.setVisible(false);
		 buttonPanel.setVisible(false);
		 toggleButton.setText("Show test stuff");
		 console.setText(PFMweb.dataURL+"\n Ready");
		 
		 initHandlers();
		 
		 return vertPanel;
	 }
	
	private static void initHandlers(){
		
		demoUserButton.addClickHandler(new ClickHandler() {				
			@Override
			public void onClick(ClickEvent event) {									
				SystemPanel.out("Logging in as demo user...");
				PFMweb.toggleView("loginView", false);
				SystemPanel.doLogin("Demo user");
				PFMweb.toggleView("sysPanelView", true);
				PFMweb.toggleView("mainTabsView", true);
			}
		});
		
		toggleButton.addClickHandler(new ClickHandler() {				
			@Override
			public void onClick(ClickEvent event) {									
				if(toggleButton.getText().equals("Show test stuff")){
					consolePanel.setVisible(true);
					horPanel.setVisible(true);
					buttonPanel.setVisible(true);
					toggleButton.setText("Hide test stuff");
				} else {
					consolePanel.setVisible(false);
					horPanel.setVisible(false);
					buttonPanel.setVisible(false);
					toggleButton.setText("Show test stuff");
				}
			}
		});
		
		getDownButton.addClickHandler(new ClickHandler() {				
			@Override
			public void onClick(ClickEvent event) {									
				//PFMweb.newDownload();
				PFMweb.download(inputServer.getText(), inputResource.getText(), "Accepts", RequestBuilder.GET);
			}
		});
		
		postDownButton.addClickHandler(new ClickHandler() {				
			@Override
			public void onClick(ClickEvent event) {									
				PFMweb.download(inputServer.getText(), inputResource.getText(), "Accepts", RequestBuilder.POST);
			}
		});
		
		getUpButton.addClickHandler(new ClickHandler() {				
			@Override
			public void onClick(ClickEvent event) {									
				PFMweb.upload(inputServer.getText(), inputResource.getText(), inputRequest.getText(), "Content-Type", RequestBuilder.GET);
			}
		});
		
		postUpButton.addClickHandler(new ClickHandler() {				
			@Override
			public void onClick(ClickEvent event) {									
				PFMweb.upload(inputServer.getText(), inputResource.getText(), inputRequest.getText(), "Content-Type", RequestBuilder.POST);
			}
		});
		
		getHostButton.addClickHandler(new ClickHandler() {				
			@Override
			public void onClick(ClickEvent event) {									
				inputServer.setText(GWT.getModuleBaseURL()+"PFMWebService/jaxrs/");
			}
		});
	}	
	
	 public static void out(String txt){
		 if(console.getText().length()>5000) 
			 console.setText(console.getText().substring(console.getText().length()-1000, console.getText().length()-1));
		 console.setText(console.getText()+"\n"+txt);
		 console.getElement().setScrollTop(console.getElement().getScrollHeight());
	 }
 
}
