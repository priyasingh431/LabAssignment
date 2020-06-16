package com.ibm.currencyrateconversion.controller;

import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.currencyrateconversion.DTO.ConversionFactoreDTO;
import com.ibm.currencyrateconversion.DTO.ConversionRateResponse;
import com.ibm.currencyrateconversion.DTO.ExternalSericeResponse;
import com.ibm.currencyrateconversion.client.ConversionFactoreClient;
import com.ibm.currencyrateconversion.client.ConverstionServiceClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope
@RestController
public class CurrencyRateConversionController {
	private static Logger logger = LoggerFactory.getLogger(CurrencyRateConversionController.class);
//	@Autowired
//	private CurrencyRateConversionService currencyRateConversionService;
	
	@Autowired(required=true)
	ConverstionServiceClient converstionServiceClient;
	
	@Autowired
	ConversionFactoreClient conversionFactoreClient;
	
	@Value("${msg}")
	private String message;
	
	@GetMapping("/msg")
	String getMessage() {
		return message;
	}
	
	@GetMapping("/convertrate")
	@HystrixCommand(fallbackMethod = "converstionFailedFallback")
	ConversionRateResponse getConverstionData(@RequestParam("from") String from,@RequestParam("to") String to) {
		//return currencyRateConversionService.getConvertedRate(base, symbols);
		logger.info("Inside CurrencyRateConversionController.getConverstionData start");
		ConversionRateResponse conversionRateResponse = new ConversionRateResponse();
		
		ExternalSericeResponse converstionData = converstionServiceClient.getConverstionData(from,to);
		
		ConversionFactoreDTO convertionDetails = conversionFactoreClient.findByContryCode(to);
		Double conversionFactor = convertionDetails.getTax();
		
		Map<String, Double> rates = converstionData.getRates();
		Double rate =Double.valueOf(rates.entrySet().stream().map(x->x.getValue().toString()).collect(Collectors.joining()));
		
		//Double finalRate = (conversionFactor * units) + (units*rate);
		
		conversionRateResponse.setFrom(converstionData.getBase());
		conversionRateResponse.setTo(rates);
		conversionRateResponse.setDate(converstionData.getDate());
		//conversionRateResponse.setUnits(units);
		conversionRateResponse.setTax(conversionFactor);
		//conversionRateResponse.setFinalRate(finalRate);
		logger.info("Inside CurrencyRateConversionController.getConverstionData end");
		return conversionRateResponse;
	}
	
	private ConversionRateResponse converstionFailedFallback(String from, String to) {
		return new ConversionRateResponse();
    }
	
}
