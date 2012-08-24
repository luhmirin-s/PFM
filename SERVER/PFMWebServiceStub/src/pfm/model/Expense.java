package pfm.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the expense database table.
 * 
 */
@Entity
public class Expense implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int amount;
	private Date date;
	private Category category;
	private Moneyaccount moneyaccount;

	public Expense() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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


	//bi-directional many-to-one association to Moneyaccount
	@ManyToOne
	@JoinColumn(name="moneyAccountId")
	public Moneyaccount getMoneyaccount() {
		return this.moneyaccount;
	}

	public void setMoneyaccount(Moneyaccount moneyaccount) {
		this.moneyaccount = moneyaccount;
	}

}