package main.client.data;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

/**
 * for testing only, will be removed
 */
//An overlay type
public class CustomerJS extends JavaScriptObject {

	// Overlay types always have protected, zero-arg ctors
	protected CustomerJS() { }
	
	// Typically, methods on overlay types are JSNI
	public final native String getFirstName() /*-{ return this.firstName; }-*/;
	public final native String getLastName()  /*-{ return this.lastName;  }-*/;
	public final native JsArray<MoneyJS> getMoney() /*-{return this.money; }-*/;
	
	// Note, though, that methods aren't required to be JSNI
	public final String getFullName() {
	 return getFirstName() + " " + getLastName();
	}
	
	
}