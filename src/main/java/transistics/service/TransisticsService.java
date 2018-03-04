package transistics.service;

import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import transistics.domain.Statistic;
import transistics.domain.Transaction;
import transistics.domain.TransisticsStore;

/**
 * Service to manage the transactions and calculate the statistics
 */
@Service
public class TransisticsService {

	/** Maximum age of a transaction */
	final long TIME_SPAN = 60000;

	/** Lock to synchronize concurrent access to statistics */
	private final Object lock = new Object();

	@Autowired
	TransisticsStore transisticsStore;


	/**
	 * Gets the statistics from the store
	 * 
	 * @return statistic
	 */
	public Statistic getStatistic() {
		return transisticsStore.getStatistic();
	}

	/**
	 * Checks the age of the given transaction, sets its life duration and
	 * updates the statistics
	 * 
	 * @param transaction
	 * 
	 * @return the transaction if recent, null otherwise
	 */
	public Transaction addTransaction(Transaction transaction) {

		long transactionAge = System.currentTimeMillis() - transaction.getTimestamp();

		if (transactionAge > TIME_SPAN) {
			return null;
		}

		setTransactionTimer(transaction, TIME_SPAN - transactionAge);

		synchronized (lock) {

			Statistic statistic = transisticsStore.getStatistic();
			Statistic statistic2 = addAmount(statistic, transaction.getAmount());
			transisticsStore.setStatistic(statistic2);

		}

		return transaction;
	}

	/**
	 * Adds given amount to statistics
	 * 
	 * @param old statistic
	 * @param transaction amount
	 * 
	 * @return new statistic
	 */
	public Statistic addAmount(Statistic statistic, double amount) {

		long count = statistic.getCount() + 1;
		statistic.setCount(count);

		double sum = statistic.getSum() + amount;
		statistic.setSum(sum);

		double avg = sum / count;
		statistic.setAvg(avg);

		return statistic;
	}

	/**
	 * Creates a TimerTask to update the statistics when the life duration of the
	 * given transaction is over and then terminates the Timer
	 * 
	 * @param transaction
	 * @param time
	 */
	void setTransactionTimer(Transaction transaction, long time) {
		
		Timer timer = new java.util.Timer();
		
		timer.schedule(new java.util.TimerTask() {
			
			@Override
			public void run() {
				
				synchronized (lock) {
					
					Statistic statistic = transisticsStore.getStatistic();
					Statistic statistic2 = removeAmount(statistic, transaction.getAmount());
					transisticsStore.setStatistic(statistic2);
					
					timer.cancel();
					
				}
				
			}
			
		}, time);
	}

	/**
	 * Removes given amount to statistics
	 * 
	 * @param old statistic
	 * @param transaction amount
	 * 
	 * @return new statistic
	 */
	public Statistic removeAmount(Statistic statistic, double amount) {

		long count = statistic.getCount() - 1;
		statistic.setCount(count);

		double sum = statistic.getSum() - amount;
		statistic.setSum(sum);

		statistic.setAvg(count > 0 ? sum / count : 0);

		return statistic;
	}
}