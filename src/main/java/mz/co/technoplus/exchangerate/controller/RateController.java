package mz.co.technoplus.exchangerate.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import mz.co.technoplus.exchangerate.model.Rate;
import mz.co.technoplus.exchangerate.service.RateService;

@RestController
@RequestMapping("/")
@Slf4j
public class RateController {
	@Autowired
	private RateService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Rate create(@RequestBody Rate rate) {
		return this.service.save(rate);
	}
	
	@GetMapping
	public List<Rate> list(){
		return service.findAll();
	}

	@GetMapping("/{fromCurrency}")
	public Map<String, Object> listByCurrency(@PathVariable String fromCurrency){
	    Map<String, BigDecimal> mapRates = new HashMap<>();
	    mapRates.put(fromCurrency, BigDecimal.valueOf(1));
	    
		List<Rate> rates = service.findAllByCurrency(fromCurrency);		
		for(Rate rate : rates) {
		    mapRates.put(rate.getToCurrency(), rate.getExchangeRate());
		}	
		
		Map<String, Object> mapFinal = new HashMap<>();
		mapFinal.put("baseCode", fromCurrency);
		mapFinal.put("rates", mapRates);
		
	    return mapFinal;
	}
	

	
}
