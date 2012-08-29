package main.client.data;

/**
 * Methods to create JSON string from parameters
 * 
 * @author student
 *
 */
public class CreateJson {
	
	
	/**
	 * Creates "Create new user" string
	 * 
	 * @param username
	 * @param password
	 * @param email
	 * @return JSON String
	 */
	public static String toJsonCreateUser(String username,String password, String email){
		String line = "{" +
				"\"username\": \"" + username +"\"," +
				"\"password\": \"" + password +"\"," +
				"\"email\": \"" + email +"\"" +
				"}";
		return line;	
	}

	/**
	 * Creates "Create new account" string
	 * 
	 * @param id
	 * @param name
	 * @param userId
	 * @return JSON String
	 */
	public static String toJsonCreateAccount(String name,int userId){
		String line = "{" +
				"\"name\": \"" + name +"\"," +
				"\"userId\": \"" + userId +	"\"" +
				"}";
		return line;	
	}
	
	/**
	 * Creates "Update account" string
	 * 
	 * @param id
	 * @param name
	 * @param userId
	 * @return JSON String
	 */
	public static String toJsonAccount(int id,String name,int userId){
		String line = "{" +
				"\"id\": \"" + id +	"\"," +
				"\"name\": \"" + name +"\"," +
				"\"userId\": \"" + userId +	"\"" +
				"}";
		return line;	
	}
	
	/**
	 * Creates "Create new category" string
	 * 
	 * @param name
	 * @param userId
	 * @return JSON String
	 */
	public static String toJsonCreateCategory(String name,int userId){
		String line = "{" +
				"\"name\": \"" + name +	"\"," +
				"\"userId\": \"" + userId +	"\"" +
				"}";
		return line;	
	}
	
	/**
	 * Creates "Update category" string
	 * 
	 * @param id
	 * @param name
	 * @param userId
	 * @return JSON String
	 */
	public static String toJsonCategory(int id,String name,int userId){
		String line = "{" +
				"\"id\": \"" + id +	"\"," +
				"\"name\": \"" + name +	"\"," +
				"\"userId\": \"" + userId +	"\"" +
				"}";
		return line;	
	}
	
	/**
	 * Creates "Create new source" string
	 * 
	 * @param name
	 * @param userId
	 * @return JSON String
	 */
	public static String toJsonCreateSource(String name,int userId){
		String line = "{" +
				"\"name\": \"" + name +	"\"," +
				"\"userId\": \"" + userId +	"\"" +
				"}";
		return line;	
	}
	
	/**
	 * Creates "update source" string
	 * 
	 * @param id
	 * @param name
	 * @param userId
	 * @return JSON String
	 */
	public static String toJsonSource(int id,String name,int userId){
		String line = "{" +
				"\"id\": \"" + id +	"\"," +
				"\"name\": \"" + name +	"\"," +
				"\"userId\": \"" + userId +	"\"" +
				"}";
		return line;	
	}
	
	/**
	 * Creates "Create new expense" string
	 * 
	 * @param amount
	 * @param accountId
	 * @param categoryId
	 * @param currencyId
	 * @return JSON String
	 */
	public static String toJsonExpenses(int amount,int accountId,
											int categoryId,int currencyId){
		String line = "{" +
				"\"amount\": \"" + amount +	"\"," +
				"\"accountId\": \"" + accountId +	"\"," +
				"\"categoryId\": \"" + categoryId +	"\"," +
				"\"currencyId\": \"" + currencyId +	"\"" +
				"}";
		return line;	
	}
	
	/**
	 * Creates "Create new income" string
	 * 
	 * @param amount
	 * @param accountId
	 * @param sourceId
	 * @param currencyId
	 * @return JSON String
	 */
	public static String toJsonIncome(int amount,int accountId,
											int sourceId,int currencyId){
		String line = "{" +
				"\"amount\": \"" + amount +	"\"," +
				"\"accountId\": \"" + accountId +	"\"," +
				"\"sourceId\": \"" + sourceId +	"\"," +
				"\"currencyId\": \"" + currencyId +	"\"" +
				"}";
		return line;	
	}
	
	/**
	 * Creates "Create new transfer" string
	 * 
	 * @param amount
	 * @param fromAccountId
	 * @param toAccountId
	 * @param currencyId
	 * @return JSON String
	 */
	public static String toJsonTransfer(int amount,int fromAccountId,
											int toAccountId,int currencyId){
		String line = "{" +
				"\"amount\": \"" + amount +	"\"," +
				"\"fromAccountId\": \"" + fromAccountId +	"\"," +
				"\"toAccountId\": \"" + toAccountId +	"\"," +
				"\"currencyId\": \"" + currencyId +	"\"" +
				"}";
		return line;	
	}
	
}