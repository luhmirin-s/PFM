package main.client.data;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

//An overlay type
class BalanceJS extends JavaScriptObject {

//Overlay types always have protected, zero-arg ctors
protected BalanceJS() { }

//Typically, methods on overlay types are JSNI
public final native String getCurrencyId() /*-{ return this.currencyId; }-*/;
public final native String getSum() /*-{ return this.sum; }-*/;

}