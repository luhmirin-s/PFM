package main.client.manager;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.TabPanel;

public class Manager {

	private static TabPanel managerTabs = new TabPanel();
	
	public static TabPanel getTabPanel(){
		return managerTabs;
	}
	public static TabPanel init(){
		
		managerTabs.add(AccountManager.init(), "Accounts");		
		managerTabs.add(CategoryManager.init(), "Categories");	
		managerTabs.add(SourceManager.init(), "Sources");	
		managerTabs.selectTab(0);
		initListeners();
		return managerTabs;
	}
	
	public static void reselectCurrentTab(){
		managerTabs.getTabBar().selectTab(managerTabs.getTabBar().getSelectedTab());
	}
	
	public static void initListeners(){
		managerTabs.addSelectionHandler(new SelectionHandler<Integer>() {
			
			@Override
			public void onSelection(SelectionEvent<Integer> event) {		
				
				//Window.alert("tab "+event.getSelectedItem()+" clicked!");
				switch(event.getSelectedItem()){
					case 0:{
						AccountManager.initRefresh();
						break;
					}
					case 1:{
						CategoryManager.initRefresh();
						break;
					}
					case 2:{
						SourceManager.initRefresh();
						break;
					}
					default: AccountManager.initRefresh();
				}
			}
		});
	}

}
