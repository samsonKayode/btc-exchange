package com.btc.exchange.tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.btc.exchange.service.IExchangeRateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.btc.exchange.entity.ExchangeRate;
import com.btc.exchange.pojos.BtcExchangeRate;

@Component
public class RateRetrievalTask {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	IExchangeRateService exchangeRateService;

	@Async
	@Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
	public BtcExchangeRate retrieveBTCExchangeRate() {

		final String coinLayerURI = "http://api.coinlayer.com/api/live?access_key=6fd16f36639729dc879ad5df43d6d6d5&target=USD&symbols=BTC";

		RestTemplate restTemplate = new RestTemplate();

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		// Add the Jackson Message converter
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
		messageConverters.add(converter);
		restTemplate.setMessageConverters(messageConverters);

		BtcExchangeRate result = restTemplate.getForObject(coinLayerURI, BtcExchangeRate.class);

		Date date = new Date(result.getTimestamp() * 1000);
		result.setDate(date);
		
		ExchangeRate exRate = new ExchangeRate();
		
		exRate.setCurrency(result.getTarget());
		exRate.setTimestamp(result.getTimestamp());
		exRate.setDate(date);
		exRate.setRate(result.getRates().getBTC());

		exchangeRateService.saveExchangeRate(exRate);

		LOGGER.info(result.toString());
		return result;
	}
}
