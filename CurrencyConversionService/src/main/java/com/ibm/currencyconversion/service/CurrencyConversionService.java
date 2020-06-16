package com.ibm.currencyconversion.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.currencyconversion.dto.ConversionFactoreDTO;
import com.ibm.currencyconversion.mapper.ConversionFactorMapper;
import com.ibm.currencyconversion.model.ConversionFactor;
import com.ibm.currencyconversion.repository.CurrencyConversionRepository;

@Service
public class CurrencyConversionService {

	private static Logger logger = LoggerFactory.getLogger(CurrencyConversionService.class);
	
	@Autowired
	private CurrencyConversionRepository currencyConversionRepository;

	public ConversionFactoreDTO addCurrencyConversion(ConversionFactoreDTO conversionFactoreDTO) {
		logger.info("Inside CurrencyConversionService.addCurrencyConversion start");
		ConversionFactorMapper conversionFactorMapper = new ConversionFactorMapper();
		ConversionFactor conversionFactore = conversionFactorMapper
				.conversionFactoreDTOToConversionFactore(conversionFactoreDTO);
		logger.info("Inside CurrencyConversionService.addCurrencyConversion end");
		return conversionFactorMapper
				.conversionFactoreToConversionFactoreDTO(currencyConversionRepository.save(conversionFactore));
	}

	public ConversionFactoreDTO updateCurrencyConversion(ConversionFactoreDTO conversionFactoreDTO) {
		logger.info("Inside CurrencyConversionService.updateCurrencyConversion start");
		ConversionFactorMapper conversionFactorMapper = new ConversionFactorMapper();
		ConversionFactor conversionFactore = conversionFactorMapper
				.conversionFactoreDTOToConversionFactore(conversionFactoreDTO);
		logger.info("Inside CurrencyConversionService.updateCurrencyConversion end");
		return conversionFactorMapper
				.conversionFactoreToConversionFactoreDTO(currencyConversionRepository.save(conversionFactore));
	}

	public void deleteCurrencyConversion(Long id) {
		logger.info("Inside CurrencyConversionService.deleteCurrencyConversion start");
		currencyConversionRepository.deleteById(id);
		logger.info("Inside CurrencyConversionService.deleteCurrencyConversion end");
	}

	public ConversionFactoreDTO findByContryCode(String countryCode) {
		logger.info("Inside CurrencyConversionService.findByContryCode start");
		ConversionFactorMapper conversionFactorMapper = new ConversionFactorMapper();
		logger.info("Inside CurrencyConversionService.findByContryCode end");
		return conversionFactorMapper
				.conversionFactoreToConversionFactoreDTO(currencyConversionRepository.findByCountryCode(countryCode));

	}

	public List<ConversionFactoreDTO> findAllContryCode() {
		logger.info("Inside CurrencyConversionService.findAllContryCode start");
		List<ConversionFactor> findAll = currencyConversionRepository.findAll();
		List<ConversionFactoreDTO> conversionFactoreDTOs = new ArrayList<>();
		for (ConversionFactor conversionFactor : findAll) {
			ConversionFactorMapper conversionFactorMapper = new ConversionFactorMapper();
			conversionFactoreDTOs.add(conversionFactorMapper.conversionFactoreToConversionFactoreDTO(conversionFactor));
		}
		logger.info("Inside CurrencyConversionService.findAllContryCode end");
		return conversionFactoreDTOs;

	}
}
