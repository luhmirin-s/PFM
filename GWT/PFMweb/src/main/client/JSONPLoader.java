package main.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.RootPanel;
 
public class JSONPLoader {
 
	private LoaderCallback callback;
 
	public void load(String jsonUrl, LoaderCallback  loaderCallback) {
		
		this.callback = loaderCallback;
		String callbackString = "jsonLoad_" + 
			DOM.createUniqueId().replace("-", "_") + "_callback";
		String url = jsonUrl + (jsonUrl.contains("?") ? "&" : "?") 
			+ "_callback=" + callbackString;
		publishCallbackMethod(callbackString);
		Element fr1 = DOM.createElement("script");		
		fr1.setAttribute("src", url);
		RootPanel.get().getElement().appendChild(fr1);
 
	}
 
	private native void publishCallbackMethod(String callback) /*-{
		var ptr = this;
  		$wnd[callback] = function(obj) {
  			ptr.@main.client.JSONPLoader::loadremotedata(Ljava/lang/String;Lcom/google/gwt/core/client/JavaScriptObject;)(callback, obj);
  		};
	}-*/;
 
	@SuppressWarnings("unused")
	private void loadremotedata(String cbp, JavaScriptObject obj) {
	  callback.onLoad(new JSONObject(obj));
	}
 
	public interface LoaderCallback {
		void onLoad(JSONObject object);
	}
 
}