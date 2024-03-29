package com.stock.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stock.management.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

}
