package com.btc.exchange.dao;

import java.util.Date;
import java.util.List;

import com.btc.exchange.entity.ExchangeRate;

public interface IExchangeRateRepository {

	public Boolean existsByTimestamp(long timestamp);

	public ExchangeRate save(ExchangeRate exchangeRate);

	public ExchangeRate findTopByOrderByTimestampDesc();

	public List<ExchangeRate> findByDateBetweenOrderByDateDesc(Date startDate, Date endDate);

}
