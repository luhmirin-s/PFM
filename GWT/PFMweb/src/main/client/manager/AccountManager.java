package main.client.manager;

import main.client.PFMweb;
import main.client.SystemPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

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
	
	//private static Label lUpdating = new Label("Updating data from server...");
	private static HorizontalPanel updatingPanel = new HorizontalPanel();
	
	private static String jsonData;
	
	public static VerticalPanel init(){
		
		tablePanel.add(accTable);
		tablePanel.add(createNewButton);	
		updatingPanel.setVisible(true);
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
	
	
	
	public static void uploadData(){
		
	}

}