package com.btc.exchange.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rates {

	@JsonProperty(value="BTC")
	private double btc;

	public double getBTC() {
		return btc;
	}

	public void setBTC(double BTC) {
		this.btc = BTC;
	}

	@Override
	public String toString() {
		return "Rates [BTC=" + String.valueOf(btc) + "]";
	}

}
