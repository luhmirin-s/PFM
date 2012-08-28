package main.client.data;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

//An overlay type
class UserJS extends JavaScriptObject {

//Overlay types always have protected, zero-arg ctors
protected UserJS() { }

//Typically, methods on overlay types are JSNI
public final native String getId() /*-{ return this.id; }-*/;
public final native String getUsername() /*-{ return this.username; }-*/;
public final native String getPassword() /*-{ return this.password; }-*/;
public final native String getEmail() /*-{ return this.email; }-*/;

}