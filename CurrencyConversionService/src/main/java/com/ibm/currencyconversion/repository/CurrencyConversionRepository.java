package com.ibm.currencyconversion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.currencyconversion.model.ConversionFactor;

public interface CurrencyConversionRepository extends JpaRepository<ConversionFactor, Long>{
	ConversionFactor findByCountryCode(String countryCode);

}
