package com.btc.exchange.service;

import java.util.Date;
import java.util.List;

import com.btc.exchange.entity.ExchangeRate;

public interface IExchangeRateService {
	
	public ExchangeRate findTopByOrderByTimestampDesc();
	
	public List<ExchangeRate> findByDateBetweenOrderByDateDesc(Date startDate, Date endDate);

	public ExchangeRate saveExchangeRate(ExchangeRate exchangeRate);

}
