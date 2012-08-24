package pfm.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Expense database table.
 * 
 */
@Entity
public class Expense implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int amount;
	private Date date;
	private Category category;
	private MoneyAccount moneyAccount;

	public Expense() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}


	@Temporal(TemporalType.TIMESTAMP)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="categoryId")
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}


	//bi-directional many-to-one association to MoneyAccount
	@ManyToOne
	@JoinColumn(name="moneyAccountId")
	public MoneyAccount getMoneyAccount() {
		return this.moneyAccount;
	}

	public void setMoneyAccount(MoneyAccount moneyAccount) {
		this.moneyAccount = moneyAccount;
	}

}