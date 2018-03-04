package transistics.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import transistics.domain.Statistic;
import transistics.domain.Transaction;
import transistics.service.TransisticsService;

/**
 * Rest Controller to manage post transactions and get statistics requests
 * 
 * @author joudahidri
 *
 */
@RestController
public class TransisticsController {

	@Autowired
	TransisticsService transactionService;

	/**
	 * Handles transactions POST requests
	 * 
	 * @param transaction
	 * 
	 * @return HTTP response 201 for success, 204 for transactions older than 60
	 *         seconds
	 */
	@RequestMapping(value = "/transactions", method = RequestMethod.POST)
	ResponseEntity<Void> addTransaction(@RequestBody Transaction transaction) {
		Transaction acceptedTransaction = transactionService.addTransaction(transaction);

		if (null == acceptedTransaction) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	/**
	 * Handles statistics GET requests
	 * 
	 * @return statistic
	 */
	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
	Statistic getStatistics() {
		return transactionService.getStatistic();
	}

}
