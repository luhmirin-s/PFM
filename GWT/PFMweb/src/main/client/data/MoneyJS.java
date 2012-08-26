package main.client.data;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * for testing only, will be removed
 */
public class MoneyJS extends JavaScriptObject {

	// Overlay types always have protected, zero-arg ctors
	protected MoneyJS() { }

	public final native String getCode() /*-{ return this.code; }-*/;
	public final native double getValue()  /*-{ return this.value;  }-*/;

}