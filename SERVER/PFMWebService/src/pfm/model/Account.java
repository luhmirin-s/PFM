package pfm.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;


/**
 * The persistent class for the Account database table.
 * 
 */
@Entity
@XmlRootElement
@Table(name="Account")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private User user;
	private List<MoneyAccount> moneyAccounts;

	public Account() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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


	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userId")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	//bi-directional many-to-one association to MoneyAccount
	@OneToMany(mappedBy="account")
	public List<MoneyAccount> getMoneyAccounts() {
		return this.moneyAccounts;
	}

	public void setMoneyAccounts(List<MoneyAccount> moneyAccounts) {
		this.moneyAccounts = moneyAccounts;
	}

}