package com.btc.exchange.pojos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BtcExchangeRate {

	private boolean success;

	private String terms;

	private String privacy;

	private long timestamp;

	private Date date;

	private String target;

	@JsonProperty
	private Rates rates;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Rates getRates() {
		return rates;
	}

	public void setRates(Rates rates) {
		this.rates = rates;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "BtcExchangeRate [success=" + success + ", terms=" + terms + ", privacy=" + privacy + ", timestamp="
				+ timestamp + ", date=" + date + ", target=" + target + ", rates=" + rates + "]";
	}


}
