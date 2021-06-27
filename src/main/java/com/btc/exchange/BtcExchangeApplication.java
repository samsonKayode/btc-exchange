package com.btc.exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class BtcExchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BtcExchangeApplication.class, args);
	}

}
