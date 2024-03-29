package com.stock.management.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.stock.management.entity.Stock;

@DataJpaTest
public class StockRepositoryTest {

	@Autowired
	private StockRepository stockRepository;

	@Test
	public void testSaveStock() {
		Stock stock = new Stock();
		stock.setName("Test Stock");
		stock.setQuantity(10);
		Stock savedStock = stockRepository.save(stock);
		Optional<Stock> retrievedStock = stockRepository.findById(savedStock.getId());
		assertTrue(retrievedStock.isPresent());
		assertEquals("Test Stock", retrievedStock.get().getName());
		assertEquals(10, retrievedStock.get().getQuantity());
	}

	@Test
	public void testFindById() {
		Optional<Stock> stockOptional = stockRepository.findById(1L);
		assertFalse(stockOptional.isPresent());
	}

	@Test
	public void testUpdateStock() {
		Optional<Stock> stockOptional = stockRepository.findById(1L);
		Stock stock = stockOptional.get();
		stock.setQuantity(20);
		stockRepository.save(stock);
		Optional<Stock> updatedStockOptional = stockRepository.findById(1L);
		assertTrue(updatedStockOptional.isPresent());
		Stock updatedStock = updatedStockOptional.get();
		assertEquals(20, updatedStock.getQuantity());
	}

	@Test
	public void testDeleteStock() {
		stockRepository.deleteById(1L);
		Optional<Stock> deletedStock = stockRepository.findById(1L);
		assertFalse(deletedStock.isPresent());
	}
}
