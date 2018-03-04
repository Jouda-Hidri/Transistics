package transistics.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import transistics.api.TransisticsController;
import transistics.domain.Transaction;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransisticsControllerTest {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	@Autowired
	private TransisticsController controller;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	/**
	 * Tests that the controller method
	 * {@link transistics.api.TransisticsController#addTransaction(Transaction)}
	 * returns the correct HTTP response when the given transaction is recent
	 */
	@Test
	public void testAddTransactionsWhenTransactionIsRecent() throws Exception {
		String url = "/transactions";
		Transaction transaction = new Transaction();
		transaction.setAmount(50);
		transaction.setTimestamp(System.currentTimeMillis());
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(transaction);

		mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8).content(requestJson))
				.andExpect(status().isCreated());
	}

	/**
	 * Tests that the controller method
	 * {@link transistics.api.TransisticsController#addTransaction(Transaction)}
	 * returns the correct HTTP response when the given transaction is old
	 */
	@Test
	public void testAddTransactionsWhenTransactionIsOld() throws Exception {
		String url = "/transactions";
		Transaction transaction = new Transaction();
		transaction.setAmount(50);
		transaction.setTimestamp(0);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(transaction);

		mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8).content(requestJson))
				.andExpect(status().isNoContent());
	}

	/**
	 * Tests that the controller method
	 * {@link transistics.api.TransisticsController#getStatistics()} returns
	 * the correct HTTP response
	 */
	@Test
	public void testGetStatistics() throws Exception {
		this.mockMvc.perform(get("/statistics")).andExpect(status().isOk());
	}

}
