package pfm.model.helper;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@NamedNativeQuery(
		  name="findAccountBalance",
		  query="CALL findAccountBalance(?)",
		  resultClass=Balance.class
		)
public class Balance implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@Id
	private int currencyId;
	private double sum;

	public int getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}
	
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
}
