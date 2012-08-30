package main.client.data;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

//An overlay type
class TransferJS extends JavaScriptObject {

//Overlay types always have protected, zero-arg ctors
protected TransferJS() { }

//Typically, methods on overlay types are JSNI
public final native String getAmount() /*-{ return this.amount; }-*/;
public final native String getToAccountId() /*-{ return this.toAccountID; }-*/;
public final native String getFromAccountId() /*-{ return this.fromAccountId; }-*/;
public final native String getCurrencyId() /*-{ return this.currencyId; }-*/;

}	