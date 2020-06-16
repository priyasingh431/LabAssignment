package com.ibm.currencyrateconversion.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibm.currencyrateconversion.DTO.ExternalSericeResponse;

@FeignClient(name = "ConverstionServiceClient", url = "https://api.exchangeratesapi.io/latest")
public interface ConverstionServiceClient {

	@GetMapping
	ExternalSericeResponse getConverstionData(@RequestParam("base") String from,@RequestParam("symbols") String to);
	
}
