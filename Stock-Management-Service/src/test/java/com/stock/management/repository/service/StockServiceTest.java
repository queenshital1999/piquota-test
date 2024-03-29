package com.stock.management.repository.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stock.management.entity.Stock;
import com.stock.management.exception.StockNotFoundException;
import com.stock.management.repository.StockRepository;
import com.stock.management.service.StockService;

public class StockServiceTest {

	@Mock
	private StockRepository stockRepository;

	@InjectMocks
	private StockService stockService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateStock() {
		Stock sampleStock = new Stock();
		sampleStock.setId(1L);
		sampleStock.setName("Sample Stock");
		sampleStock.setQuantity(100);
		when(stockRepository.save(sampleStock)).thenReturn(sampleStock);
		Stock createdStock = stockService.save(sampleStock);
		verify(stockRepository, times(1)).save(sampleStock);
		assertEquals(sampleStock, createdStock);
	}

	@Test
	public void testGetStockById() {
		Stock sampleStock = new Stock();
		sampleStock.setId(1L);
		sampleStock.setName("Sample Stock");
		sampleStock.setQuantity(100);
		when(stockRepository.findById(1L)).thenReturn(Optional.of(sampleStock));
		Optional<Stock> retrievedStock = stockService.getStockById(1L);
		verify(stockRepository, times(1)).findById(1L);
		assertEquals(sampleStock, retrievedStock);
	}

	@Test
	public void testUpdateStock() {
		Stock sampleStock = new Stock();
		sampleStock.setId(1L);
		sampleStock.setName("Sample Stock");
		sampleStock.setQuantity(100);
		when(stockRepository.save(sampleStock)).thenReturn(sampleStock);
		Stock updatedStock = stockService.update(1L, sampleStock);
		verify(stockRepository, times(1)).save(sampleStock);
		assertEquals(sampleStock, updatedStock);
	}

	@Test
	public void testDeleteStock() throws StockNotFoundException {
		doNothing().when(stockRepository).deleteById(1L);
		stockService.deleted(1L);
		verify(stockRepository, times(1)).deleteById(1L);
	}
}
