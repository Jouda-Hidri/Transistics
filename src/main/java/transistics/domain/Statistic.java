package transistics.domain;

/**
 * Entity Statistic
 * 
 * @author joudahidri
 *
 */
public class Statistic {

	private double sum;

	private double avg;

	private long count;

	public Statistic() {
		sum = 0;
		avg = 0;
		count = 0;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public double getAvg() {
		int places = 2;
	    double scale = Math.pow(10, places);
	    return Math.round(avg * scale) / scale;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
}
