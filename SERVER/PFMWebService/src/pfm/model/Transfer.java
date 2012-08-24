package pfm.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Transfer database table.
 * 
 */
@Entity
public class Transfer implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private Date date;
	private MoneyAccount moneyAccount1;
	private MoneyAccount moneyAccount2;

	public Transfer() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Temporal(TemporalType.TIMESTAMP)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	//bi-directional many-to-one association to MoneyAccount
	@ManyToOne
	@JoinColumn(name="fromId")
	public MoneyAccount getMoneyAccount1() {
		return this.moneyAccount1;
	}

	public void setMoneyAccount1(MoneyAccount moneyAccount1) {
		this.moneyAccount1 = moneyAccount1;
	}


	//bi-directional many-to-one association to MoneyAccount
	@ManyToOne
	@JoinColumn(name="toId")
	public MoneyAccount getMoneyAccount2() {
		return this.moneyAccount2;
	}

	public void setMoneyAccount2(MoneyAccount moneyAccount2) {
		this.moneyAccount2 = moneyAccount2;
	}

}