package main.client.data;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

//An overlay type
class CurrencyJS extends JavaScriptObject {

//Overlay types always have protected, zero-arg ctors
protected CurrencyJS() { }

//Typically, methods on overlay types are JSNI
public final native String getId() /*-{ return this.id; }-*/;
public final native String getCode() /*-{ return this.code; }-*/;

}