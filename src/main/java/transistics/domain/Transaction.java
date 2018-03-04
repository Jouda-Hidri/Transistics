package transistics.domain;

/**
 * Entity Transaction
 * 
 * @author joudahidri
 *
 */
public class Transaction {
	double amount;
	long timestamp;

	public Transaction() {
		amount = 0;
		timestamp = 0;
	}

	public Transaction(long amount, long timestamp) {
		this.amount = amount;
		this.timestamp = timestamp;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
