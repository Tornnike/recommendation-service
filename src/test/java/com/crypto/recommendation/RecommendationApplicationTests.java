package com.crypto.recommendation;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.crypto.recommendation.controller.PricesController;
import com.crypto.recommendation.repository.PriceRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PricesController.class)

class RecommendationApplicationTests {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	PriceRepository priceRepository;

	@Test
	void getNormalRange() throws Exception {
		List<String> prices = new ArrayList<>(
				Arrays.asList("BTC,0.434121","LTC,0.465184","XRP,0.517857","DOGE,0.461538","ETH,0.638381"));
		Mockito.when(priceRepository.findNormalizedRange()).thenReturn(new ArrayList<String>(prices));
		mockMvc.perform(get("/prices/normalRange/"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(5)));

	}

	@Test
	void getMonthly() throws Exception {
		List<String> prices = new ArrayList<>(
				Arrays.asList("BTC,47722.66,33276.59,2022-01-31 15:00:00.0,2022-01-01 08:00:00.0,1",
						"LTC,151.50,103.40,2022-01-31 21:00:00.0,2022-01-01 10:00:00.0,1"));
		Mockito.when(priceRepository.getMonthlyStats(1)).thenReturn(prices);
		mockMvc.perform(get("/prices/monthly/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)));
	}

	@Test
	void getByCrypto() throws Exception {
		List<String> prices = new ArrayList<>(
				Arrays.asList("BTC,47722.66,33276.59,2022-02-01 00:00:00.0,2022-01-01 08:00:00.0"));
		Mockito.when(priceRepository.getMonthlyStatsByCrypto("BTC")).thenReturn(prices);
		mockMvc.perform(get("/prices/name/BTC"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)));
	}
}