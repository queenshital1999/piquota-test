package com.inventory.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.management.entity.Inventory;
import com.inventory.management.exception.InventoryNotFoundException;
import com.inventory.management.repository.InventoryRepository;

@Service
public class InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;

	public Inventory createInventory(Inventory inventory) {
		Optional.ofNullable(inventory)
				.orElseThrow(() -> new IllegalArgumentException("Inventory object cannot be null"));
		try {
			return inventoryRepository.save(inventory);
		} catch (Exception e) {
			throw new RuntimeException("Failed to create inventory", e);
		}
	}

	public List<Inventory> getAllInventories() {
		return inventoryRepository.findAll();
	}

	public Inventory getStock(long id) throws InventoryNotFoundException {
		if (inventoryRepository.findById(id).isPresent()) {

			return inventoryRepository.findById(id).get();

		}
		throw new InventoryNotFoundException("enter valid id");
	}

	public Optional<Inventory> getInventoryById(Long id) {
		return inventoryRepository.findById(id);
	}

	public Inventory updateInventory(Long id, Inventory updatedInventory) {
		return inventoryRepository.findById(id).map(existingInventory -> {
			if (updatedInventory.getName() != null) {
				existingInventory.setName(updatedInventory.getName());
			}
			if (updatedInventory.getQuantity() != 0) {
				existingInventory.setQuantity(updatedInventory.getQuantity());
			}
			return inventoryRepository.save(existingInventory);
		}).orElse(null);
	}

	public String deleted(long id) throws InventoryNotFoundException {
		if (inventoryRepository.findById(id).isPresent()) {
			inventoryRepository.deleteById(id);
			return "deleted";

		}
		throw new InventoryNotFoundException("enter valid id");
	}

}
