package mz.co.technoplus.exchangerate.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mz.co.technoplus.exchangerate.model.Rate;
import mz.co.technoplus.exchangerate.repository.RateRepository;

@Service
public class RateService {
	
	@Autowired
	private RateRepository repository;
	
	@Transactional
	public Rate save(Rate rate) {
		Optional<Rate> saved = repository.findById(rate.getId());	
		if(saved.isPresent()) {
			throw new EntityExistsException(String.format("Rate %s  is already in use", rate.getFromCurrency()));
		}		
		return repository.save(rate);	
	}
	
	public List<Rate> findAll(){
		return repository.findAll();
	}
	
	public List<Rate> findAllByCurrency(String fromCurrency){
		List<Rate> rates = repository.findByFromCurrency(fromCurrency);
//		if(rates.isEmpty()) {
//			throw new EntityExistsException(String.format("unsupported-code %s  ", fromCurrency));
//		}
		return rates;
	}

}
