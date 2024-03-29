package com.inventory.management.controller;

import java.util.List;
import java.util.Optional;

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

import com.inventory.management.entity.Inventory;
import com.inventory.management.exception.InventoryNotFoundException;
import com.inventory.management.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;

	@PostMapping("/insert")
	public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
		return new ResponseEntity<Inventory>(inventoryService.createInventory(inventory), HttpStatus.ACCEPTED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Inventory>> getAllInventories() {
		return new ResponseEntity<List<Inventory>>(inventoryService.getAllInventories(), HttpStatus.ACCEPTED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Inventory> getInventoryById(@PathVariable Long id) throws InventoryNotFoundException {
		Optional<Inventory> inventoryOptional = inventoryService.getInventoryById(id);

		if (inventoryOptional.isPresent()) {
			return ResponseEntity.ok(inventoryOptional.get());
		} else {
			throw new InventoryNotFoundException("Inventory not found with id: " + id);

		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Inventory> updateInventory(@PathVariable Long id, @RequestBody Inventory updatedInventory) {
		return new ResponseEntity<Inventory>(inventoryService.updateInventory(id, updatedInventory), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteInventory(@PathVariable Long id) throws InventoryNotFoundException {
		return new ResponseEntity<String>(inventoryService.deleted(id), HttpStatus.OK);
	}

}
