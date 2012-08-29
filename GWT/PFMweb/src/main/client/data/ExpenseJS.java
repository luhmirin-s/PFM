package main.client.data;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

//An overlay type
class ExpenseJS extends JavaScriptObject {

//Overlay types always have protected, zero-arg ctors
protected ExpenseJS() { }

//Typically, methods on overlay types are JSNI
public final native String getAmount() /*-{ return this.amount; }-*/;
public final native String getAccountId() /*-{ return this.accountID; }-*/;
public final native String getCurrencyId() /*-{ return this.currenncyId; }-*/;
public final native String getCategoryId() /*-{ return this.categoryId; }-*/;

}	