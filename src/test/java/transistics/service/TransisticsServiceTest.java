package transistics.service;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import transistics.domain.Statistic;
import transistics.domain.TransisticsStore;
import transistics.service.TransisticsService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransisticsServiceTest {

	@Autowired
	private TransisticsService service;

	@Mock
	TransisticsStore store;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Tests that the service method
	 * {@link transistics.service.TransisticsService#addAmount(Statistic, double)}
	 * adds the given amount to the given statistics correctly
	 */
	@Test
	public void testAddAmount() {

		Statistic dummyStatistic = new Statistic();
		dummyStatistic.setCount(2L);
		dummyStatistic.setSum(75L);
		dummyStatistic.setAvg(37.5);

		long dummyAmount = 15L;

		Statistic resultedStatisitc = service.addAmount(dummyStatistic, dummyAmount);

		double count = 3L;
		double sum = 90L;
		double avg = 30L;

		assertTrue(avg == resultedStatisitc.getAvg());
		assertTrue(count == resultedStatisitc.getCount());
		assertTrue(sum == resultedStatisitc.getSum());

	}

	/**
	 * Tests that the service method
	 * {@link transistics.service.TransisticsService#removeAmount(Statistic, double)}
	 * removes the given amount from the given statistics correctly
	 */
	@Test
	public void testRemoveAmount() {

		Statistic dummyStatistic = new Statistic();
		dummyStatistic.setCount(3L);
		dummyStatistic.setSum(90L);
		dummyStatistic.setAvg(30L);

		long dummyAmount = 30L;

		Statistic resultedStatisitc = service.removeAmount(dummyStatistic, dummyAmount);

		double count = 2L;
		double sum = 60L;
		double avg = 30L;

		assertTrue(avg == resultedStatisitc.getAvg());
		assertTrue(count == resultedStatisitc.getCount());
		assertTrue(sum == resultedStatisitc.getSum());
	}
}
