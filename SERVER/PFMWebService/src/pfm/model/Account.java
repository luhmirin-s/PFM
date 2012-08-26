package pfm.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.util.List;


/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@XmlRootElement
@Table(name = "Account")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;
	
	@Transient
	private int userId;

	@ManyToOne
	@JoinColumn(name="userId")
	private User user;

	@OneToMany(mappedBy="account")
	private List<Expense> expenses;

	@OneToMany(mappedBy="account")
	private List<Income> incomes;

	@OneToMany(mappedBy="fromAccount")
	private List<Transfer> outgoingTransfers;

	@OneToMany(mappedBy="toAccount")
	private List<Transfer> incomingTransfers;

	public Account() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@XmlTransient
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@XmlTransient
	public List<Expense> getExpenses() {
		return this.expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

	@XmlTransient
	public List<Income> getIncomes() {
		return this.incomes;
	}

	public void setIncomes(List<Income> incomes) {
		this.incomes = incomes;
	}

	@XmlTransient
	public List<Transfer> getOutgoingTransfers() {
		return this.outgoingTransfers;
	}

	public void setOutgoingTransfers(List<Transfer> outgoingTransfers) {
		this.outgoingTransfers = outgoingTransfers;
	}

	@XmlTransient
	public List<Transfer> getIncomingTransfers() {
		return this.incomingTransfers;
	}

	public void setIncomingTransfers(List<Transfer> incomingTransfers) {
		this.incomingTransfers = incomingTransfers;
	}
	
	@PostLoad
	private void setupUserId() {
		setUserId(getUser().getId());
	}
	
}