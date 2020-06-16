package com.ibm.currencyconversion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.currencyconversion.dto.ConversionFactoreDTO;
import com.ibm.currencyconversion.service.CurrencyConversionService;

@RestController
@RefreshScope
@RequestMapping("/currencyconversion")
public class ConversionFactorController {

	@Autowired
	private CurrencyConversionService currencyConversionService;
	
	@Value("${msg}")
	private String message;
	
	@GetMapping("/msg")
	String getMessage() {
		return message;
	}
		
	@PostMapping("/create")
	ConversionFactoreDTO create(@RequestBody ConversionFactoreDTO conversionFactoreDTO){
		return currencyConversionService.addCurrencyConversion(conversionFactoreDTO);
	}
	
	@PutMapping("/update")
	ConversionFactoreDTO update(@RequestBody ConversionFactoreDTO conversionFactoreDTO){
		return currencyConversionService.updateCurrencyConversion(conversionFactoreDTO);
	}
	
	@DeleteMapping("/delete/{id}")
	void delete(@PathVariable Long id){
		currencyConversionService.deleteCurrencyConversion(id);
	}
	
	@GetMapping("/{countryCode}")
	ConversionFactoreDTO findByContryCode(@PathVariable String countryCode){
		return currencyConversionService.findByContryCode(countryCode);
	}
	
	@GetMapping("/")
	List<ConversionFactoreDTO> findAllContryCode(){
		return currencyConversionService.findAllContryCode();
	}
	
}
