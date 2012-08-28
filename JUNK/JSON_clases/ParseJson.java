package main.client;

import java.util.ArrayList;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.Window;

/**
 * 
 * Methods to parse different JSON data to usable lists of objects
 * 
 * @author student
 *
 */
public class ParseJson {


	/**
	 * Deserializes JSON String to list of Account objects 
	 * 
	 * @param jsonData - JSON String
	 * @return list of Account objects
	 */
	public static ArrayList<Account> parseAccount(String jsonData) {
		ArrayList<Account> accs = new ArrayList<Account>();
		if (jsonData.contains("[")) {
			// neslojko objektov v spiske
			try {
				// udaljaem lishnij tekst, ctob izbezatj oshibok
				jsonData = jsonData.replace("\"account\":", "");
				JsArray<AccountJS> jsobj = parseJsonAccounts(jsonData);
				for (int i = 0; i < jsobj.length(); i++) {
					// vremennij fail
					Account temp = new Account();
					temp.setId(Integer.parseInt(((AccountJS) jsobj.get(i))
							.getId()));
					temp.setUserId(Integer.parseInt(((AccountJS) jsobj.get(i))
							.getUserId()));
					temp.setName(((AccountJS) jsobj.get(i)).getName());
					accs.add(temp);
				}
			} catch (Exception e) {
				Window.alert(e.toString());
			}
		} else {
			// odin objekt v spiske
			try {
				AccountJS jsobj = parseJsonAccount(jsonData);
				// vremennij fail
				Account temp = new Account();
				temp.setId(Integer.parseInt(jsobj.getId()));
				temp.setUserId(Integer.parseInt(jsobj.getUserId()));
				temp.setName(jsobj.getName());
				accs.add(temp);
			} catch (Exception e) {
				Window.alert(e.toString());
			}
		}
		return accs;
	}

	/**
	 * Deserializes JSON String to list of Currency objects 
	 * 
	 * @param jsonData - JSON String
	 * @return list of Currency objects
	 */
	public static ArrayList<Currency> parseCurrency(String jsonData) {
		ArrayList<Currency> curr = new ArrayList<Currency>();
		if (jsonData.contains("[")) {
			// neslojko objektov v spiske
			try {
				// udaljaem lishnij tekst, ctob izbezatj oshibok
				jsonData = jsonData.replace("\"currency\":", "");
				JsArray<CurrencyJS> jsobj = parseJsonCurrencies(jsonData);
				for (int i = 0; i < jsobj.length(); i++) {
					// vremennij fail
					Currency temp = new Currency();
					temp.setId(Integer.parseInt(((CurrencyJS) jsobj.get(i)).getId()));
					temp.setCode(((CurrencyJS) jsobj.get(i)).getCode());
					curr.add(temp);
				}
			} catch (Exception e) {
				Window.alert(e.toString());
			}
		} else {
			// odin objekt v spiske
			try {
				CurrencyJS jsobj = parseJsonCurrency(jsonData);
				// vremennij fail
				Currency temp = new Currency();
				temp.setId(Integer.parseInt(jsobj.getId()));
				temp.setCode(jsobj.getCode());
				curr.add(temp);
			} catch (Exception e) {
				Window.alert(e.toString());
			}
		}
		return curr;
	}
	
	/**
	 * Deserializes JSON String to list of Category objects 
	 * 
	 * @param jsonData - JSON String
	 * @return list of Category objects
	 */
	public static ArrayList<Category> parseCategory(String jsonData) {
		ArrayList<Category> accs = new ArrayList<Category>();
		if (jsonData.contains("[")) {
			// neslojko objektov v spiske
			try {
				// udaljaem lishnij tekst, ctob izbezatj oshibok
				jsonData = jsonData.replace("\"category\":", "");
				JsArray<CategoryJS> jsobj = parseJsonCategories(jsonData);
				for (int i = 0; i < jsobj.length(); i++) {
					// vremennij fail
					Category temp = new Category();
					temp.setId(Integer.parseInt(((CategoryJS) jsobj.get(i))
							.getId()));
					temp.setUserId(Integer.parseInt(((CategoryJS) jsobj.get(i))
							.getUserId()));
					temp.setName(((CategoryJS) jsobj.get(i)).getName());
					accs.add(temp);
				}
			} catch (Exception e) {
				Window.alert(e.toString());
			}
		} else {
			// odin objekt v spiske
			try {
				CategoryJS jsobj = parseJsonCategory(jsonData);
				// vremennij fail
				Category temp = new Category();
				temp.setId(Integer.parseInt(jsobj.getId()));
				temp.setUserId(Integer.parseInt(jsobj.getUserId()));
				temp.setName(jsobj.getName());
				accs.add(temp);
			} catch (Exception e) {
				Window.alert(e.toString());
			}
		}
		return accs;
	}
	
	/**
	 * Deserializes JSON String to list of Source objects 
	 * 
	 * @param jsonData - JSON String
	 * @return list of Source objects
	 */
	public static ArrayList<Source> parseSource(String jsonData) {
		ArrayList<Source> accs = new ArrayList<Source>();
		if (jsonData.contains("[")) {
			// neslojko objektov v spiske
			try {
				// udaljaem lishnij tekst, ctob izbezatj oshibok
				jsonData = jsonData.replace("\"source\":", "");
				JsArray<SourceJS> jsobj = parseJsonSources(jsonData);
				for (int i = 0; i < jsobj.length(); i++) {
					// vremennij fail
					Source temp = new Source();
					temp.setId(Integer.parseInt(((SourceJS) jsobj.get(i))
							.getId()));
					temp.setUserId(Integer.parseInt(((SourceJS) jsobj.get(i))
							.getUserId()));
					temp.setName(((SourceJS) jsobj.get(i)).getName());
					accs.add(temp);
				}
			} catch (Exception e) {
				Window.alert(e.toString());
			}
		} else {
			// odin objekt v spiske
			try {
				SourceJS jsobj = parseJsonSource(jsonData);
				// vremennij fail
				Source temp = new Source();
				temp.setId(Integer.parseInt(jsobj.getId()));
				temp.setUserId(Integer.parseInt(jsobj.getUserId()));
				temp.setName(jsobj.getName());
				accs.add(temp);
			} catch (Exception e) {
				Window.alert(e.toString());
			}
		}
		return accs;
	}
	
	/**
	 * Deserializes JSON String to User object 
	 * 
	 * @param jsonData - JSON String
	 * @return User object
	 */
	public static User parseUser(String jsonData) {
		User acc = new User();
			// odin objekt v spiske
			try {
				UserJS jsobj = parseJsonUser(jsonData);
				// vremennij fail
				
				acc.setId(Integer.parseInt(jsobj.getId()));
				acc.setUsername(jsobj.getUsername());
				acc.setPassword(jsobj.getPassword());
				acc.setEmail(jsobj.getEmail());
				
			} catch (Exception e) {
				Window.alert(e.toString());
			}
		return acc;
	}
	
	/*
	 * Takes in a trusted JSON String and evals it.
	 * 
	 * @param JSON String that you trust
	 * 
	 * @return JavaScriptObject that you can cast to an Overlay Type
	 */
	private final native static AccountJS parseJsonAccount(String json) /*-{
		eval('var res = ' + json);
		return res;
	}-*/;

	private final native static JsArray<AccountJS> parseJsonAccounts(String json) /*-{
		return eval(json);
	}-*/;

	private final native static CurrencyJS parseJsonCurrency(String json) /*-{
	eval('var res = ' + json);
	return res;
	}-*/;

	private final native static JsArray<CurrencyJS> parseJsonCurrencies(String json) /*-{
	return eval(json);
	}-*/;

	private final native static CategoryJS parseJsonCategory(String json) /*-{
		eval('var res = ' + json);
		return res;
	}-*/;

	private final native static JsArray<CategoryJS> parseJsonCategories(	String json) /*-{
		return eval(json);
	}-*/;
	
	private final native static SourceJS parseJsonSource(String json) /*-{
	eval('var res = ' + json);
	return res;
	}-*/;

	private final native static JsArray<SourceJS> parseJsonSources(String json) /*-{
	return eval(json);
	}-*/;
	
	private final native static UserJS parseJsonUser(String json) /*-{
	eval('var res = ' + json);
	return res;
	}-*/;
}