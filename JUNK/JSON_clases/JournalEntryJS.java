package main.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

//An overlay type
class JournalEntryJS extends JavaScriptObject {

//Overlay types always have protected, zero-arg ctors
protected JournalEntryJS() { }

//Typically, methods on overlay types are JSNI
public final native String getAccountName() /*-{ return this.accountName; }-*/;
public final native String getAmount() /*-{ return this.amount; }-*/;
public final native String getDate() /*-{ return this.date; }-*/;
public final native String getText() /*-{ return this.text; }-*/;
public final native String getTransactionId() /*-{ return this.transactionId; }-*/;
public final native String getType() /*-{ return this.type; }-*/;

}