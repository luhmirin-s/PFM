package pfm.model.helper;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

@Embeddable
@XmlRootElement
public class JournalEntryPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int type;
	private long transactionId;

    public JournalEntryPK() {
    }

    public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public int hashCode() {
        return type + (int) transactionId;
    }

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof JournalEntryPK)) return false;
        JournalEntryPK pk = (JournalEntryPK) obj;
        return pk.transactionId == this.transactionId 
        		&& pk.type == this.type;
    }
}
