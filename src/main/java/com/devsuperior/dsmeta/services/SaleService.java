package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SellerSumDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public List<SellerSumDTO> sellersSales(String minDate, String maxDate){
		LocalDate finalDate = maxDate.isEmpty() ? LocalDate.now(ZoneId.systemDefault()) : LocalDate.parse(maxDate);
		LocalDate initialDate = minDate.isEmpty() ? finalDate.minusYears(1) : LocalDate.parse(minDate);

		List<SellerSumDTO> result = repository.searchBySummary(initialDate, finalDate);
		return result;
	}

	public Page<SaleMinDTO> salesReport(String minDate, String maxDate, String name, Pageable pageable) {
		LocalDate finalDate = maxDate.isEmpty() ? LocalDate.now(ZoneId.systemDefault()) : LocalDate.parse(maxDate);
		LocalDate initialDate = minDate.isEmpty() ? finalDate.minusYears(1) : LocalDate.parse(minDate);

		Page<SaleMinDTO> result = repository.searchByReport(initialDate,finalDate, name, pageable);
		return result;
	}
}
