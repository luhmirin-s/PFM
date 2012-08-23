package pfm.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the transfer database table.
 * 
 */
@Entity
public class Transfer implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private Date date;
	private Moneyaccount moneyaccount1;
	private Moneyaccount moneyaccount2;

	public Transfer() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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


	//bi-directional many-to-one association to Moneyaccount
	@ManyToOne
	@JoinColumn(name="fromId")
	public Moneyaccount getMoneyaccount1() {
		return this.moneyaccount1;
	}

	public void setMoneyaccount1(Moneyaccount moneyaccount1) {
		this.moneyaccount1 = moneyaccount1;
	}


	//bi-directional many-to-one association to Moneyaccount
	@ManyToOne
	@JoinColumn(name="toId")
	public Moneyaccount getMoneyaccount2() {
		return this.moneyaccount2;
	}

	public void setMoneyaccount2(Moneyaccount moneyaccount2) {
		this.moneyaccount2 = moneyaccount2;
	}

}