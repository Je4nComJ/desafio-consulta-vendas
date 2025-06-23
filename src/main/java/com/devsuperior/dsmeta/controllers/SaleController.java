package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SellerSumDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<?> getReport() {
		// TODO
		return null;
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SellerSumDTO>> getSummary(@RequestParam(defaultValue = "") String minDate,
														 @RequestParam(defaultValue = "") String maxDate) {
		List<SellerSumDTO> dto = service.sellersSales(minDate,maxDate);
		return ResponseEntity.ok(dto);
	}
}
