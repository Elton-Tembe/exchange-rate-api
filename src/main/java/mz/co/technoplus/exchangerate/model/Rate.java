package mz.co.technoplus.exchangerate.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Rate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fromCurrency;
	private String toCurrency;
	private BigDecimal exchangeRate;
	
	@CreationTimestamp
	@JsonIgnore
	private LocalDateTime created;
	
	@UpdateTimestamp
	@JsonIgnore
	private LocalDateTime updated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	@Override
	public String toString() {
		return "Rate [id=" + id + ", fromCurrency=" + fromCurrency + ", toCurrency=" + toCurrency + ", exchangeRate="
				+ exchangeRate + ", created=" + created + ", updated=" + updated + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(created, exchangeRate, fromCurrency, id, toCurrency, updated);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rate other = (Rate) obj;
		return Objects.equals(created, other.created) && Objects.equals(exchangeRate, other.exchangeRate)
				&& Objects.equals(fromCurrency, other.fromCurrency) && Objects.equals(id, other.id)
				&& Objects.equals(toCurrency, other.toCurrency) && Objects.equals(updated, other.updated);
	}
	
	
}
