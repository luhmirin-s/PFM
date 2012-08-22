package main.client.manager;

import main.client.balance.Balance;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class AccountManager {
	
	private static VerticalPanel tablePanel = new VerticalPanel();
	//private static VerticalPanel newPanel = new VerticalPanel();
	//private static VerticalPanel editPanel = new VerticalPanel();
	private static DialogBox newDialog = new DialogBox();
	private static DialogBox editDialog = new DialogBox();
	private static DialogBox deletePrompt = new DialogBox();
	private static VerticalPanel newDialogPanel = new VerticalPanel();
	
	private static HorizontalPanel newNamePanel = new HorizontalPanel();
	private static HorizontalPanel newButtons = new HorizontalPanel();
	private static HorizontalPanel editNamePanel = new HorizontalPanel();
	private static HorizontalPanel editButtons = new HorizontalPanel();
	private static HorizontalPanel delButtons = new HorizontalPanel();
	
	private static FlexTable accTable = new FlexTable();
	
	private static Label lNewName = new Label("Name: ");
	private static Label lEditName = new Label("New name: ");
	private static Label lDelPrompt = new Label("Are you sure you want to delete?");
	private static Button createNewButton = new Button("Create new account");
	private static Button newSaveButton = new Button("Save");
	private static Button newCancelButton = new Button("Cancel");
	private static Button editUpdateButton = new Button("Update");
	private static Button editCancelButton = new Button("Cancel");
	private static Button delConfirmButton = new Button("Confirm");
	private static Button delCancelButton = new Button("Cancel");
	private static TextBox inputNewName = new TextBox();
	private static TextBox inputEditName = new TextBox();
	
	public static VerticalPanel init(){
		
		tablePanel.add(accTable);
		tablePanel.add(createNewButton);		
		
		newNamePanel.add(lNewName);
		newNamePanel.add(inputNewName);
		newButtons.add(newSaveButton);
		newButtons.add(newCancelButton);
		newDialogPanel.add(newNamePanel);
		newDialogPanel.add(newButtons);
		newDialog.add(newDialogPanel);
		newDialog.setGlassEnabled(true);
		
		initListeners();
		
		return tablePanel;
	}
	
	private static void initListeners(){
		
		createNewButton.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {								

				newDialog.center();	
				
			}
		});
		
		newSaveButton.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {								
				newDialog.hide();				
			}
		});
		
		newCancelButton.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {								
				newDialog.hide();				
			}
		});
	}
	
	private static Button createEditButton(){
		Button b = new Button("edit");
		return b;
	}
	
	private static Button createDeleteButton(){
		Button b = new Button("delete");
		return b;
	}
	

	
	public static void refreshData(){
		//get from server
		
		//or upload
	}	

}
