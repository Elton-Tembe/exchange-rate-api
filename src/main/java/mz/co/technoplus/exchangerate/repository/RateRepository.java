package mz.co.technoplus.exchangerate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mz.co.technoplus.exchangerate.model.Rate;

public interface RateRepository extends JpaRepository<Rate, Long> {
	List<Rate> findByFromCurrency(String fromCurrency);
}
