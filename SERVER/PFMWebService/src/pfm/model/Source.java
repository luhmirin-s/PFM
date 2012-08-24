package pfm.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;


/**
 * The persistent class for the Source database table.
 * 
 */
@Entity
@XmlRootElement
public class Source implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private List<Income> incomes;
	private User user;

	public Source() {
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


	//bi-directional many-to-one association to Income
	@OneToMany(mappedBy="source")
	public List<Income> getIncomes() {
		return this.incomes;
	}

	public void setIncomes(List<Income> incomes) {
		this.incomes = incomes;
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

}