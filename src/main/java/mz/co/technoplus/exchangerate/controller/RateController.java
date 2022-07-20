package mz.co.technoplus.exchangerate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import mz.co.technoplus.exchangerate.model.Rate;
import mz.co.technoplus.exchangerate.service.RateService;

@RestController
@RequestMapping("/exchange-rates")
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
	public List<Rate> getaAllByCurrency(String fromCurrency){
		return service.findAllByFromCurrency(fromCurrency);
	}
}
