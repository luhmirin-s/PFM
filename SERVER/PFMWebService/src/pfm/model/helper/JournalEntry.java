package pfm.model.helper;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
//@Entity
@NamedNativeQuery(
		  name="findJournalEntries",
		  query="CALL findJournalEntries(?)",
		  resultClass=JournalEntry.class
		)
public class JournalEntry {
	//@Id
	private int id;
	//@Id
	private JournalEntryType type;
	private String text;
	private String amount;
	private Date date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public JournalEntryType getType() {
		return type;
	}
	public void setType(JournalEntryType type) {
		this.type = type;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
