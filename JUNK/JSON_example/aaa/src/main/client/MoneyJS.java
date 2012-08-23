package main.client;

import java.util.ArrayList;

import com.google.gwt.core.client.JavaScriptObject;

public class MoneyJS extends JavaScriptObject {

	// Overlay types always have protected, zero-arg ctors
	protected MoneyJS() { }

	public final native String getCode() /*-{ return this.code; }-*/;
	public final native double getValue()  /*-{ return this.value;  }-*/;
	
}
