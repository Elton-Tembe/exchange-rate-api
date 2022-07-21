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
		Map<String, Object> mapFinal = new HashMap<>();
	    Map<String, BigDecimal> mapRates = new HashMap<>();	    

		List<Rate> rates = service.findAllByCurrency(fromCurrency);			
		if(rates.isEmpty()) {
			mapFinal.put("result","error");
			mapFinal.put("error-type",String.format("unsupported-code %s", fromCurrency));
			return mapFinal;
		}
		
	    mapRates.put(fromCurrency, BigDecimal.valueOf(1));
		for(Rate rate : rates) {
		    mapRates.put(rate.getToCurrency(), rate.getExchangeRate());
		}	
		
		mapFinal.put("result", "success");
		mapFinal.put("baseCode", fromCurrency);
		mapFinal.put("rates", mapRates);
		
	    return mapFinal;
	}
	

	
}
