package com.ibm.currencyrateconversion.DTO;

import java.util.Map;

public class ExternalSericeResponse {
	private Map<String,Double> rates;
	private String base;
	private String date;
	
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Map<String, Double> getRates() {
		return rates;
	}
	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}
	

	
}
