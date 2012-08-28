package pfm.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;


/**
 * The persistent class for the transfer database table.
 * 
 */
@Entity
@XmlRootElement
@Table(name = "Transfer")
public class Transfer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private double amount;
	
	@Transient
	private int fromAccountId;
	
	@Transient
	private int toAccountId;
	
	@Transient
	private int currencyId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@ManyToOne
	@JoinColumn(name="fromAccountId")
	private Account fromAccount;

	@ManyToOne
	@JoinColumn(name="toAccountId")
	private Account toAccount;
	
	@ManyToOne
	@JoinColumn(name="currencyId")
	private Currency currency;

	public Transfer() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(int fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public int getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(int toAccountId) {
		this.toAccountId = toAccountId;
	}

	public int getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Account getFromAccount() {
		return this.fromAccount;
	}

	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}

	public Account getToAccount() {
		return this.toAccount;
	}

	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}

	public Currency getCurrency() {
		return this.currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	@PostLoad
	private void setupUserId() {
		setFromAccountId(getFromAccount().getId());
		setToAccountId(getToAccount().getId());
		setCurrencyId(getCurrency().getId());
	}
}