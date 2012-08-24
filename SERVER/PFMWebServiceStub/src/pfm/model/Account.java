package pfm.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the account database table.
 * 
 */
@Entity
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private User user;
	private List<Moneyaccount> moneyaccounts;

	public Account() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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


	//bi-directional many-to-one association to Moneyaccount
	@OneToMany(mappedBy="account")
	public List<Moneyaccount> getMoneyaccounts() {
		return this.moneyaccounts;
	}

	public void setMoneyaccounts(List<Moneyaccount> moneyaccounts) {
		this.moneyaccounts = moneyaccounts;
	}

}