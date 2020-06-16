package com.ibm.currencyconversion.mapper;

import com.ibm.currencyconversion.dto.ConversionFactoreDTO;
import com.ibm.currencyconversion.model.ConversionFactor;

public class ConversionFactorMapper {
	
	public ConversionFactor conversionFactoreDTOToConversionFactore(ConversionFactoreDTO conversionFactoreDTO){
		ConversionFactor conversionFactor = new ConversionFactor();
		conversionFactor.setId(conversionFactoreDTO.getId());
		conversionFactor.setCountry(conversionFactoreDTO.getCountry());
		conversionFactor.setCountryCode(conversionFactoreDTO.getCountryCode());
		conversionFactor.setTax(conversionFactoreDTO.getTax());
		
		return conversionFactor;
	}

	public ConversionFactoreDTO conversionFactoreToConversionFactoreDTO(ConversionFactor conversionFactor){
		ConversionFactoreDTO conversionFactoreDTO = new ConversionFactoreDTO();
		conversionFactoreDTO.setId(conversionFactor.getId());
		conversionFactoreDTO.setCountry(conversionFactor.getCountry());
		conversionFactoreDTO.setCountryCode(conversionFactor.getCountryCode());
		conversionFactoreDTO.setTax(conversionFactor.getTax());
		
		return conversionFactoreDTO;
	}
}
