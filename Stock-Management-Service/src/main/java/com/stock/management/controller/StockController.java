package com.stock.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.management.entity.Stock;
import com.stock.management.exception.StockNotFoundException;
import com.stock.management.service.StockService;

@RestController
@RequestMapping("/stocks")
public class StockController {

	@Autowired
	private StockService stockService;

	@PostMapping(value = "/add")
	public ResponseEntity<Stock> insertData(@RequestBody Stock stock) {
		return new ResponseEntity<Stock>(stockService.save(stock), HttpStatus.CREATED);

	}

	@GetMapping("/all")
	public ResponseEntity<List<Stock>> getAllStocks() {
		return new ResponseEntity<List<Stock>>(stockService.getAllStocks(), HttpStatus.ACCEPTED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Stock> getStockById(@PathVariable long id) throws StockNotFoundException {
		return new ResponseEntity<Stock>(stockService.getStock(id), HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Stock> update(@PathVariable long id, @RequestBody Stock stock) {
		return new ResponseEntity<Stock>(stockService.update(id, stock), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStock(@PathVariable long id) throws StockNotFoundException {
		return new ResponseEntity<String>(stockService.deleted(id), HttpStatus.OK);

	}

}
