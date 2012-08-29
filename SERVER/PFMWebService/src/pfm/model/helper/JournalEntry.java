package pfm.model.helper;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement
@Entity
@NamedNativeQuery(
		  name="findJournalEntries",
		  query="CALL findJournalEntries(?, ?, ?)",
		  resultClass=JournalEntry.class
		)
public class JournalEntry implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private JournalEntryPK primaryKey;
	private String accountName;
	private String text;
	private String amount;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	

	@XmlTransient
	public JournalEntryPK getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(JournalEntryPK primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	public int getType() {
		return this.primaryKey.getType();
	}
	public void setType(int type) {
		this.primaryKey.setType(type);
	}
	
	public long getTransactionId() {
		return this.primaryKey.getTransactionId();
	}
	public void setTransactionId(long transactionId) {
		this.primaryKey.setTransactionId(transactionId);
	}
	
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	@XmlTransient
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@XmlElement(name="date")
	public String getDate2() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(this.date);
	}
	public void setDate2(String date) {
		//this.date = date;
	}
	
	
}
