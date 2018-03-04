package transistics.domain;

import org.springframework.stereotype.Component;

/**
 * Component to store statistics based on the recent transactions
 * 
 * @author joudahidri
 *
 */
@Component
public class TransisticsStore {

	/** Statistics of the recent transactions */
	Statistic statistic = new Statistic();

	/**
	 * Getter of statistic
	 * 
	 * @return statistic
	 */
	public Statistic getStatistic() {
		return statistic;
	}

	/**
	 * Setter of statistic
	 * 
	 * @param statistic
	 */
	public void setStatistic(Statistic statistic) {
		this.statistic = statistic;
	}

}
