package main.client.manager;

import com.google.gwt.user.client.ui.TabPanel;

public class Manager {

	private static TabPanel managerTabs = new TabPanel();
	
	public static TabPanel getTabPanel(){
		return managerTabs;
	}
	public static TabPanel init(){
		
		managerTabs.add(AccountManager.init(), "Accounts");		
		managerTabs.selectTab(0);
		
		return managerTabs;
	}
	/* unfinished:
	public static void initListeners(){
		managerTabs.addSelectionHandler(new SelectionHandler<Integer>() {
			
			@Override
			public void onSelection(SelectionEvent<Integer> event) {
				
				//if tab 0 selected, AccountManager.refresh()...
				
			}
		})
	}
	*/
	
	public static void refreshData(){
		//get from server
		
		//fillBox();
	}

}
