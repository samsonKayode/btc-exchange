package com.btc.exchange.restcontroller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btc.exchange.entity.ExchangeRate;
import com.btc.exchange.service.IExchangeRateService;

@RestController()
@RequestMapping("/v1/exchange-rate")
public class IExchangeRateRestController {
	
	@Autowired
	IExchangeRateService exchangeRateService;
	
	@GetMapping("/latest")
	public ExchangeRate getLatest() {
		return exchangeRateService.findTopByOrderByTimestampDesc();
	}
	
	@GetMapping("/list")
	public List<ExchangeRate> getByDates(@RequestParam(value="startDate") final Date startDate, @RequestParam(value="endDate") final Date endDate){
		
		return exchangeRateService.findByDateBetweenOrderByDateDesc(startDate, endDate);
	}

}
