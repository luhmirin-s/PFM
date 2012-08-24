package pfm.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;


/**
 * The persistent class for the income database table.
 * 
 */
@XmlRootElement
@Entity
public class Income implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int amount;
	private Date date;
	private Source source;
	private Moneyaccount moneyaccount;

	public Income() {
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


	//bi-directional many-to-one association to Source
	@ManyToOne
	@JoinColumn(name="sourceId")
	public Source getSource() {
		return this.source;
	}

	public void setSource(Source source) {
		this.source = source;
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