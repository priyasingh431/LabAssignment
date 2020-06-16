package com.ibm.currencyconversion.dto;

public class ConversionFactoreDTO {
	private Long id;
	private String country;
	private String countryCode;
	private Double tax;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountry() {
		return country;
	}
	public Double getTax() {
		return tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
	}
	public void setCountry(String country) {
		this.country = country;
	}

}
