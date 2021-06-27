package com.btc.exchange.service;

import java.util.Date;
import java.util.List;
import com.btc.exchange.exceptions.InternalServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.btc.exchange.dao.IExchangeRateRepository;
import com.btc.exchange.entity.ExchangeRate;

@Repository
public class ExchangeRateServiceImpl implements IExchangeRateService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public ExchangeRateServiceImpl(IExchangeRateRepository exchangeRateRepository) {
		this.exchangeRateRepository = exchangeRateRepository;
	}

	@Autowired
	IExchangeRateRepository exchangeRateRepository;

	@Override
	public ExchangeRate findTopByOrderByTimestampDesc(){
		try{
			return exchangeRateRepository.findTopByOrderByTimestampDesc();
		}catch(Exception exception){
			LOGGER.info(exception.getLocalizedMessage());
			throw new InternalServerException();
		}
	}

	@Override
	public List<ExchangeRate> findByDateBetweenOrderByDateDesc(Date startDate, Date endDate) {
		try{
			return exchangeRateRepository.findByDateBetweenOrderByDateDesc(startDate, endDate);
		}catch(Exception exception){
			LOGGER.info(exception.getLocalizedMessage());
			throw new InternalServerException();
		}
	}

	@Override
	public ExchangeRate saveExchangeRate(ExchangeRate exchangeRate) throws InternalServerException {
		ExchangeRate result=null;
		/*
		 * Check if the timestamp already exist, if it does, the data will not be saved..
		 * This will ensure some form of data integrity..
		 */
		if(exchangeRateRepository.existsByTimestamp(exchangeRate.getTimestamp())) {
			LOGGER.info("Data already exists, will not save "+exchangeRate.getTimestamp());
			return null;
		}else {
			try{
				return exchangeRateRepository.save(exchangeRate);
			}catch(Exception exception){
				LOGGER.info(exception.getLocalizedMessage());
				throw new InternalServerException();
			}
		}
	}
}
