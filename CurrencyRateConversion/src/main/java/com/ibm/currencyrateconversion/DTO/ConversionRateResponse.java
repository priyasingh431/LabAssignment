package com.ibm.currencyrateconversion.DTO;

import java.util.Map;

public class ConversionRateResponse {

	private String from;
	private Map<String,Double> to;
	private String date;
	//private int units;
	private Double tax;
	//private Double finalRate;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Double getTax() {
		return tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
	}
	public Map<String, Double> getTo() {
		return to;
	}
	public void setTo(Map<String, Double> to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	
	
	
}
