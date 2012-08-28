package main.client.manager;

import main.client.PFMweb;
import main.client.RefreshingClasses;
import main.client.SystemPanel;
import main.client.data.CreateJson;
import main.client.data.LocalData;
import main.client.data.ParseJson;

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

public class SourceManager {

	private static VerticalPanel tablePanel = new VerticalPanel();

	private static DialogBox newDialog = new DialogBox();
	private static DialogBox editDialog = new DialogBox();
	private static DialogBox deletePrompt = new DialogBox();
	private static VerticalPanel newDialogPanel = new VerticalPanel();
	private static VerticalPanel editDialogPanel = new VerticalPanel();
	private static VerticalPanel delDialogPanel = new VerticalPanel();

	private static FlexTable srcTable = new FlexTable();

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
	private static Label lEditError = new Label(
			"Please specify a valid replacement name!");
	private static HorizontalPanel editButtons = new HorizontalPanel();
	private static Button editUpdateButton = new Button("Update");
	private static Button editCancelButton = new Button("Cancel");

	private static HorizontalPanel delNamePanel = new HorizontalPanel();
	private static Label lDelPrompt = new Label(
			"Are you sure you want to delete?");
	private static HorizontalPanel delButtons = new HorizontalPanel();
	private static Button delConfirmButton = new Button("Confirm");
	private static Button delCancelButton = new Button("Cancel");

	private static Button createNewButton = new Button("Create new source");
	private static int editedRow;
	
	private static HorizontalPanel updatingPanel = new HorizontalPanel();

	public static VerticalPanel init() {

		tablePanel.add(srcTable);
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

	private static void initListeners() {

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
				if (txt.matches("^[0-9A-Za-z\\s]{1,16}$")) {
					lNewError.setText("");
					newDialog.hide();
					SystemPanel.out("Creating new source...");
					SystemPanel.statusSetOp("creating source");
					PFMweb.upload(PFMweb.dataURL, "/source", CreateJson
							.toJsonCreateSource(txt, LocalData.getUser()
									.getId()), "Content-Type",
							RequestBuilder.POST);
					PFMweb.initRefreshTimer(RefreshingClasses.SRC_MGR);					
				} else {
					lNewError.setText("Please specify a valid name!");
					SystemPanel.clearStatus();
				}
			}
		});

		editUpdateButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String txt = inputEditName.getText().trim();
				if (txt.matches("^[0-9A-Za-z\\s]{1,16}$")) {
					lEditError.setText("");
					editDialog.hide();
					SystemPanel.out("Editing source...");
					SystemPanel.statusSetOp("editing account");
					PFMweb.upload(PFMweb.dataURL, "/source", CreateJson
							.toJsonSource(
									LocalData.getSourceList().get(editedRow)
											.getId(), txt, LocalData.getUser()
											.getId()), "Content-Type",
							RequestBuilder.PUT);
					PFMweb.initRefreshTimer(RefreshingClasses.SRC_MGR);					
				} else {
					lEditError.setText("Please specify a valid replacement name!");
					SystemPanel.clearStatus();
				}
			}
		});

		delConfirmButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				deletePrompt.hide();
				SystemPanel.out("Deleting source...");
				SystemPanel.statusSetOp("deleting source");
				PFMweb.download(PFMweb.dataURL, "/source/"
						+ LocalData.getSourceList().get(editedRow).getId(),
						"Accept", RequestBuilder.DELETE);
				PFMweb.initRefreshTimer(RefreshingClasses.SRC_MGR);				
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

	private static Button makeEditButton(final int row) {
		Button b = new Button("edit");
		b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				inputEditName.setText("");
				editedRow = row;
				editDialog.center();
			}
		});
		return b;
	}

	private static Button makeDeleteButton(final int row) {
		Button b = new Button("delete");
		b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				lDelPrompt.setText("Are you sure you want to delete source '"
						+ srcTable.getText(row, 0) + "'?");
				editedRow = row;
				deletePrompt.center();
			}
		});
		return b;
	}

	public static void initRefresh() {

		PFMweb.download(PFMweb.dataURL, "/source/list/"
				+ LocalData.getUser().getId(), "Accepts", RequestBuilder.GET);
		SystemPanel.statusSetOp("refreshing");
		PFMweb.initRefreshTimer(RefreshingClasses.SRC_MGR);

	}

	public static void refresh() {

		if (PFMweb.getJSONdata() != null) {
			LocalData.setSourceList(ParseJson.parseSource(PFMweb
					.getJSONdata()));
			SystemPanel.out("parsing sources done");
			srcTable.clear();
			if (LocalData.getSourceList().size() > 0) {
				for (int i = 0; i < LocalData.getSourceList().size(); i++) {
					srcTable.setWidget(i, 0, new Label(LocalData.getSourceList().get(i).getName()));
					srcTable.setWidget(i, 1, makeEditButton(i));
					srcTable.setWidget(i, 2, makeDeleteButton(i));
					SystemPanel.statusDone();
				}
			} else {
				SystemPanel.out("Source list is empty");
				SystemPanel.statusError();
			}

		} else {
			SystemPanel.out("Error receiving JSON");
			SystemPanel.statusError();
		}

	}

}