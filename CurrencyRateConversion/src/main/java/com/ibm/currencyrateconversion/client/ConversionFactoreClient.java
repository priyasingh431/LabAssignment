package com.ibm.currencyrateconversion.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ibm.currencyrateconversion.DTO.ConversionFactoreDTO;



@FeignClient(name = "Currency-Convertion-Service")
public interface ConversionFactoreClient {
	
	@GetMapping("/currencyconversion/{countryCode}")
	ConversionFactoreDTO findByContryCode(@PathVariable String countryCode);
	
}
