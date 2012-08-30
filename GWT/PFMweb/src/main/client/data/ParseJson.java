package main.client.data;

import java.util.ArrayList;

import main.client.SystemPanel;

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
		// udaljaem lishnij tekst, ctob izbezatj oshibok
		jsonData = jsonData.replace("\"account\":", "");
		if (jsonData.contains("[")) {
			// neslojko objektov v spiske
			try {
				
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
				SystemPanel.out(e.toString());
			}
		} else {
			// odin objekt v spiske
			try {
				jsonData=jsonData.substring(1, jsonData.length()-1);
				AccountJS jsobj = parseJsonAccount(jsonData);
				// vremennij fail
				Account temp = new Account();
				temp.setId(Integer.parseInt(jsobj.getId()));
				temp.setUserId(Integer.parseInt(jsobj.getUserId()));
				temp.setName(jsobj.getName());
				accs.add(temp);
			} catch (Exception e) {
				SystemPanel.out(e.toString());
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
		// udaljaem lishnij tekst, ctob izbezatj oshibok
		jsonData = jsonData.replace("\"currency\":", "");
		if (jsonData.contains("[")) {
			// neslojko objektov v spiske
			try {
				JsArray<CurrencyJS> jsobj = parseJsonCurrencies(jsonData);
				for (int i = 0; i < jsobj.length(); i++) {
					// vremennij fail
					Currency temp = new Currency();
					temp.setId(Integer.parseInt(((CurrencyJS) jsobj.get(i)).getId()));
					temp.setCode(((CurrencyJS) jsobj.get(i)).getCode());
					curr.add(temp);
				}
			} catch (Exception e) {
				SystemPanel.out(e.toString());
			}
		} else {
			// odin objekt v spiske
			try {
				jsonData=jsonData.substring(1, jsonData.length()-1);
				CurrencyJS jsobj = parseJsonCurrency(jsonData);
				// vremennij fail
				Currency temp = new Currency();
				temp.setId(Integer.parseInt(jsobj.getId()));
				temp.setCode(jsobj.getCode());
				curr.add(temp);
			} catch (Exception e) {
				SystemPanel.out(e.toString());
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
		// udaljaem lishnij tekst, ctob izbezatj oshibok
		jsonData = jsonData.replace("\"category\":", "");
		if (jsonData.contains("[")) {
			// neslojko objektov v spiske
			try {
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
				SystemPanel.out(e.toString());
			}
		} else {
			// odin objekt v spiske
			try {
				jsonData=jsonData.substring(1, jsonData.length()-1);
				CategoryJS jsobj = parseJsonCategory(jsonData);
				// vremennij fail
				Category temp = new Category();
				temp.setId(Integer.parseInt(jsobj.getId()));
				temp.setUserId(Integer.parseInt(jsobj.getUserId()));
				temp.setName(jsobj.getName());
				accs.add(temp);
			} catch (Exception e) {
				SystemPanel.out(e.toString());
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
		// udaljaem lishnij tekst, ctob izbezatj oshibok
		jsonData = jsonData.replace("\"source\":", "");
		if (jsonData.contains("[")) {
			// neslojko objektov v spiske
			try {
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
				SystemPanel.out(e.toString());
			}
		} else {
			// odin objekt v spiske
			try {
				jsonData=jsonData.substring(1, jsonData.length()-1);
				SourceJS jsobj = parseJsonSource(jsonData);
				// vremennij fail
				Source temp = new Source();
				temp.setId(Integer.parseInt(jsobj.getId()));
				temp.setUserId(Integer.parseInt(jsobj.getUserId()));
				temp.setName(jsobj.getName());
				accs.add(temp);
			} catch (Exception e) {
				SystemPanel.out(e.toString());
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
				SystemPanel.out(e.toString());
			}
		return acc;
	}
	
	/**
	 * Deserializes JSON String to list of Expense objects 
	 * 
	 * @param jsonData
	 * @return list of Expense objects
	 */
	public static ArrayList<Expense> parseExpense(String jsonData) {
		ArrayList<Expense> accs = new ArrayList<Expense>();
		// udaljaem lishnij tekst, ctob izbezatj oshibok
		jsonData = jsonData.replace("\"expense\":", "");
		if (jsonData.contains("[")) {
			// neslojko objektov v spiske
			try {
				JsArray<ExpenseJS> jsobj = parseJsonExpenses(jsonData);
				for (int i = 0; i < jsobj.length(); i++) {
					// vremennij fail
					Expense temp = new Expense();
					temp.setAmount(Integer.parseInt(((ExpenseJS) jsobj.get(i)).getAmount()));
					temp.setAccountId(Integer.parseInt(((ExpenseJS) jsobj.get(i)).getAccountId()));
					temp.setCategoryId(Integer.parseInt(((ExpenseJS) jsobj.get(i)).getCategoryId()));
					temp.setCurrencyId(Integer.parseInt(((ExpenseJS) jsobj.get(i)).getCurrencyId()));
					accs.add(temp);
				}
			} catch (Exception e) {
				SystemPanel.out(e.toString());
			}
		} else {
			// odin objekt v spiske
			try {
				jsonData=jsonData.substring(1, jsonData.length()-1);
				ExpenseJS jsobj = parseJsonExpense(jsonData);
				// vremennij fail
				Expense temp = new Expense();
				temp.setAmount(Integer.parseInt(((ExpenseJS) jsobj).getAmount()));
				temp.setAccountId(Integer.parseInt(((ExpenseJS) jsobj).getAccountId()));
				temp.setCategoryId(Integer.parseInt(((ExpenseJS) jsobj).getCategoryId()));
				temp.setCurrencyId(Integer.parseInt(((ExpenseJS) jsobj).getCurrencyId()));
				accs.add(temp);
			} catch (Exception e) {
				SystemPanel.out(e.toString());
			}
		}
		return accs;
	}
	
	/**
	 * Deserializes JSON String to list of Income objects 
	 * 
	 * @param jsonData
	 * @return list of Income objects
	 */
	public static ArrayList<Income> parseIncome(String jsonData) {
		ArrayList<Income> accs = new ArrayList<Income>();
		// udaljaem lishnij tekst, ctob izbezatj oshibok
		jsonData = jsonData.replace("\"income\":", "");
		if (jsonData.contains("[")) {
			// neslojko objektov v spiske
			try {
				JsArray<IncomeJS> jsobj = parseJsonIncomes(jsonData);
				for (int i = 0; i < jsobj.length(); i++) {
					// vremennij fail
					Income temp = new Income();
					temp.setAmount(Integer.parseInt(((IncomeJS) jsobj.get(i)).getAmount()));
					temp.setAccountId(Integer.parseInt(((IncomeJS) jsobj.get(i)).getAccountId()));
					temp.setSourceId(Integer.parseInt(((IncomeJS) jsobj.get(i)).getSourceId()));
					temp.setCurrencyId(Integer.parseInt(((IncomeJS) jsobj.get(i)).getCurrencyId()));
					accs.add(temp);
				}
			} catch (Exception e) {
				SystemPanel.out(e.toString());
			}
		} else {
			// odin objekt v spiske
			try {
				jsonData=jsonData.substring(1, jsonData.length()-1);
				IncomeJS jsobj = parseJsonIncome(jsonData);
				// vremennij fail
				Income temp = new Income();
				temp.setAmount(Integer.parseInt(((IncomeJS) jsobj).getAmount()));
				temp.setAccountId(Integer.parseInt(((IncomeJS) jsobj).getAccountId()));
				temp.setSourceId(Integer.parseInt(((IncomeJS) jsobj).getSourceId()));
				temp.setCurrencyId(Integer.parseInt(((IncomeJS) jsobj).getCurrencyId()));
				accs.add(temp);
			} catch (Exception e) {
				SystemPanel.out(e.toString());
			}
		}
		return accs;
	}
	
	/**
	 * Deserializes JSON String to list of Transfer objects 
	 * 
	 * @param jsonData
	 * @return list of Transfer objects
	 */
	public static ArrayList<Transfer> parseTransfer(String jsonData) {
		ArrayList<Transfer> accs = new ArrayList<Transfer>();
		// udaljaem lishnij tekst, ctob izbezatj oshibok
		jsonData = jsonData.replace("\"transfer\":", "");
		if (jsonData.contains("[")) {
			// neslojko objektov v spiske
			try {
				
				JsArray<TransferJS> jsobj = parseJsonTransfers(jsonData);
				for (int i = 0; i < jsobj.length(); i++) {
					// vremennij fail
					Transfer temp = new Transfer();
					temp.setAmount(Integer.parseInt(((TransferJS) jsobj.get(i)).getAmount()));
					temp.setToAccountId(Integer.parseInt(((TransferJS) jsobj.get(i)).getToAccountId()));
					temp.setFromAccountId(Integer.parseInt(((TransferJS) jsobj.get(i)).getFromAccountId()));
					temp.setCurrencyId(Integer.parseInt(((TransferJS) jsobj.get(i)).getCurrencyId()));
					accs.add(temp);
				}
			} catch (Exception e) {
				SystemPanel.out(e.toString());
			}
		} else {
			// odin objekt v spiske
			try {
				jsonData=jsonData.substring(1, jsonData.length()-1);
				TransferJS jsobj = parseJsonTransfer(jsonData);
				// vremennij fail
				Transfer temp = new Transfer();
				temp.setAmount(Integer.parseInt(((TransferJS) jsobj).getAmount()));
				temp.setToAccountId(Integer.parseInt(((TransferJS) jsobj).getToAccountId()));
				temp.setFromAccountId(Integer.parseInt(((TransferJS) jsobj).getFromAccountId()));
				temp.setCurrencyId(Integer.parseInt(((TransferJS) jsobj).getCurrencyId()));
				accs.add(temp);
			} catch (Exception e) {
				SystemPanel.out(e.toString());
			}
		}
		return accs;
	}
	
	/**
	 * Deserializes JSON String to list of Balance objects 
	 * 
	 * @param jsonData - JSON String
	 * @return list of Balance objects
	 */
	public static ArrayList<Balance> parseBalance(String jsonData) {
		ArrayList<Balance> accs = new ArrayList<Balance>();
		// udaljaem lishnij tekst, ctob izbezatj oshibok
		jsonData = jsonData.replace("\"balance\":", "");
		if (jsonData.contains("[")) {
			// neslojko objektov v spiske
			try {
				JsArray<BalanceJS> jsobj = parseJsonBalances(jsonData);
				for (int i = 0; i < jsobj.length(); i++) {
					// vremennij fail
					Balance temp = new Balance();
					temp.setCurrencyId(Integer.parseInt(((BalanceJS) jsobj.get(i))
							.getCurrencyId()));
					temp.setSum(Double.parseDouble(((BalanceJS) jsobj.get(i))
							.getSum()));
					accs.add(temp);
				}
			} catch (Exception e) {
				SystemPanel.out(e.toString());
			}
		} else {
			// odin objekt v spiske
			try {
				jsonData=jsonData.substring(1, jsonData.length()-1);
				BalanceJS jsobj = parseJsonBalance(jsonData);
				// vremennij fail
				Balance temp = new Balance();
				temp.setCurrencyId(Integer.parseInt(((BalanceJS) jsobj).getCurrencyId()));
				temp.setSum(Double.parseDouble(((BalanceJS) jsobj).getSum()));
				accs.add(temp);
			} catch (Exception e) {
				SystemPanel.out(e.toString());
			}
		}
		return accs;
	}
	
	/**
	 * Deserializes JSON String to list of Balance objects 
	 * 
	 * @param jsonData - JSON String
	 * @return list of Balance objects
	 */
	public static ArrayList<JournalEntry> parseJournal(String jsonData) {
		ArrayList<JournalEntry> accs = new ArrayList<JournalEntry>();
		// udaljaem lishnij tekst, ctob izbezatj oshibok
		jsonData = jsonData.replace("\"journalEntry\":", "");
		if (jsonData.contains("[")) {
			// neslojko objektov v spiske
			try {
				JsArray<JournalEntryJS> jsobj = parseJsonJournals(jsonData);
				for (int i = 0; i < jsobj.length(); i++) {
					// vremennij fail
					JournalEntry temp = new JournalEntry();
					
					temp.setAccountName(((JournalEntryJS) jsobj.get(i)).getAccountName());
					temp.setAmount(((JournalEntryJS) jsobj.get(i)).getAmount());
					temp.setDate(((JournalEntryJS) jsobj.get(i)).getDate());
					temp.setText(((JournalEntryJS) jsobj.get(i)).getText());
					temp.setTransactionId(Integer.parseInt(((JournalEntryJS) jsobj.get(i)).getTransactionId()));
					temp.setType(Integer.parseInt(((JournalEntryJS) jsobj.get(i)).getType()));
					
					accs.add(temp);
				}
			} catch (Exception e) {
				SystemPanel.out(e.toString());
			}
		} else {
			// odin objekt v spiske
			try {
				jsonData=jsonData.substring(1, jsonData.length()-1);
				JournalEntryJS jsobj = parseJsonJournal(jsonData);
				// vremennij fail
				JournalEntry temp = new JournalEntry();
				
				temp.setAccountName(((JournalEntryJS) jsobj).getAccountName());
				temp.setAmount(((JournalEntryJS) jsobj).getAmount());
				temp.setDate(((JournalEntryJS) jsobj).getDate());
				temp.setText(((JournalEntryJS) jsobj).getText());
				temp.setTransactionId(Integer.parseInt(((JournalEntryJS) jsobj).getTransactionId()));
				temp.setType(Integer.parseInt(((JournalEntryJS) jsobj).getType()));
				
				accs.add(temp);
			} catch (Exception e) {
				SystemPanel.out(e.toString());
			}
		}
		return accs;
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
	
	private final native static JsArray<ExpenseJS> parseJsonExpenses(String json) /*-{
	return eval(json);
	}-*/;
	
	private final native static ExpenseJS parseJsonExpense(String json) /*-{
	eval('var res = ' + json);
	return res;
	}-*/;
	
	private final native static JsArray<IncomeJS> parseJsonIncomes(String json) /*-{
	return eval(json);
	}-*/;
	
	private final native static IncomeJS parseJsonIncome(String json) /*-{
	eval('var res = ' + json);
	return res;
	}-*/;
	
	private final native static JsArray<TransferJS> parseJsonTransfers(String json) /*-{
	return eval(json);
	}-*/;
	
	private final native static TransferJS parseJsonTransfer(String json) /*-{
	eval('var res = ' + json);
	return res;
	}-*/;
	
	private final native static JsArray<BalanceJS> parseJsonBalances(String json) /*-{
	return eval(json);
	}-*/;
	
	private final native static BalanceJS parseJsonBalance(String json) /*-{
	eval('var res = ' + json);
	return res;
	}-*/;
	
	private final native static JsArray<JournalEntryJS> parseJsonJournals(String json) /*-{
	return eval(json);
	}-*/;
	
	private final native static JournalEntryJS parseJsonJournal(String json) /*-{
	eval('var res = ' + json);
	return res;
	}-*/;
}
