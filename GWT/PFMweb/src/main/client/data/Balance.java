package main.client.data;

public class Balance {

	
	private int currencyId;
	private double sum;
	
	public Balance() {}
	
	public Balance(int currencyId, double sum) {
		setSum(sum);
		setCurrencyId(currencyId);
	}

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
