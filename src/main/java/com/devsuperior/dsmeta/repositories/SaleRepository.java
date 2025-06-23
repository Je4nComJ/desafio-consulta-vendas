package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SellerSumDTO;
import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SellerSumDTO(s.name, SUM(obj.amount)) " +
            "FROM Sale obj JOIN obj.seller s " +
            "WHERE obj.date BETWEEN :initialDate AND :finalDate " +
            "GROUP BY s.name")
    List<SellerSumDTO> searchBySummary(LocalDate initialDate, LocalDate finalDate);

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleMinDTO(obj) " +
            "FROM Sale obj JOIN obj.seller s " +
            "WHERE obj.date BETWEEN :initialDate AND :finalDate " +
            "AND UPPER(s.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<SaleMinDTO> searchByReport(LocalDate initialDate, LocalDate finalDate, String name, Pageable pageable);
}
