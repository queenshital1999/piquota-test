package com.inventory.management.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.inventory.management.entity.Inventory;
import com.inventory.management.exception.InventoryNotFoundException;
import com.inventory.management.repository.InventoryRepository;

class InventoryServiceTest {

	@Mock
	private InventoryRepository inventoryRepository;

	@InjectMocks
	private InventoryService inventoryService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testCreateInventory() {
		Inventory inventory = new Inventory();
		inventory.setName("Item");
		inventory.setQuantity(10);
		when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventory);
		Inventory createdInventory = inventoryService.createInventory(inventory);
		assertNotNull(createdInventory);
		assertEquals("Item", createdInventory.getName());
		assertEquals(10, createdInventory.getQuantity());
	}

	@Test
	void testGetAllInventories() {
		List<Inventory> inventories = new ArrayList<>();
		inventories.add(new Inventory());
		inventories.add(new Inventory());
		when(inventoryRepository.findAll()).thenReturn(inventories);
		List<Inventory> retrievedInventories = inventoryService.getAllInventories();
		assertNotNull(retrievedInventories);
		assertEquals(2, retrievedInventories.size());
		assertEquals("Item1", retrievedInventories.get(0).getName());
		assertEquals(5, retrievedInventories.get(0).getQuantity());
	}

	
	@Test
	void testGetInventoryById_NonExistingId() {
		long id = 1L;
		when(inventoryRepository.findById(id)).thenReturn(Optional.empty());
		assertThrows(InventoryNotFoundException.class, () -> inventoryService.getInventoryById(id));
	}

	

	@Test
	void testDeleteInventory_ExistingId() throws InventoryNotFoundException {
		long id = 1L;
		when(inventoryRepository.findById(id)).thenReturn(Optional.of(new Inventory()));
		String result = inventoryService.deleted(id);
		assertEquals("deleted", result);
		verify(inventoryRepository, times(1)).deleteById(id);
	}

}
