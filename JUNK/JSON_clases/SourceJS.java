package main.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

//An overlay type
class SourceJS extends JavaScriptObject {

//Overlay types always have protected, zero-arg ctors
protected SourceJS() { }

//Typically, methods on overlay types are JSNI
public final native String getId() /*-{ return this.id; }-*/;
public final native String getName() /*-{ return this.name; }-*/;
public final native String getUserId() /*-{ return this.userId; }-*/;

}