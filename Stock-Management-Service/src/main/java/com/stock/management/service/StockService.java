package com.stock.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.management.entity.Stock;
import com.stock.management.exception.StockNotFoundException;
import com.stock.management.repository.StockRepository;

@Service
public class StockService {

	@Autowired
	private StockRepository stockRepository;

	public Stock save(Stock stock) {
		return stockRepository.save(stock);
	}

	public List<Stock> getAllStocks() {
		return stockRepository.findAll();
	}
	

	public Stock getStock(long id) throws StockNotFoundException {
		if (stockRepository.findById(id).isPresent()) {
			return stockRepository.findById(id).get();
		}
		throw new StockNotFoundException("Enter valid id");
	}

	public Optional<Stock> getStockById(Long id) {
		return stockRepository.findById(id);
	}
	

	public Stock update(long id, Stock stock) {
		return stockRepository.findById(id).map(existingStock -> {
			if (stock.getName() != null) {
				existingStock.setName(stock.getName());
			}
			if (stock.getQuantity() != 0) {
				existingStock.setQuantity(stock.getQuantity());
			}
			return stockRepository.save(existingStock);
		}).orElse(null);
	}

	
	public String deleted(long id) throws StockNotFoundException {
		if (stockRepository.findById(id).isPresent()) {
			stockRepository.deleteById(id);
			return "deleted";

		}
		throw new StockNotFoundException("enter valid id");
	}

}