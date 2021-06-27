package com.btc.exchange.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btc.exchange.entity.ExchangeRate;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Integer>, IExchangeRateRepository {
	
	public Boolean existsByTimestamp(long timestamp);
	
	public ExchangeRate findTopByOrderByTimestampDesc();
	
	public List<ExchangeRate> findByDateBetweenOrderByDateDesc(Date startDate, Date endDate);

}
