package com.ibm.currencyrateconversion.DTO;

public class ConversionFactoreDTO {
	private Long Id;
	private String country;
	private String countryCode;
	private Double tax;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
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
