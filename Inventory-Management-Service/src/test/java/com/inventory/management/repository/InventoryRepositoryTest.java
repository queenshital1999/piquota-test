package com.inventory.management.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.inventory.management.entity.Inventory;
import com.inventory.management.service.InventoryService;

class InventoryRepositoryTest {

	@Mock
	private InventoryRepository inventoryRepository;

	@InjectMocks
	private InventoryService inventoryService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetInventoryById() {
		long id = 1L;
		Inventory inventory = new Inventory();
		when(inventoryRepository.findById(id)).thenReturn(Optional.of(inventory));
		Optional<Inventory> retrievedInventory = inventoryRepository.findById(id);
		assertTrue(retrievedInventory.isPresent());
		assertEquals("Item", retrievedInventory.get().getName());
		assertEquals(10, retrievedInventory.get().getQuantity());
	}

	@Test
	void testGetInventoryById_NotFound() {
		long id = 1L;
		when(inventoryRepository.findById(id)).thenReturn(Optional.empty());
		Optional<Inventory> retrievedInventory = inventoryRepository.findById(id);
		assertTrue(retrievedInventory.isEmpty());
	}

	@Test
	void testSaveInventory() {
		Inventory inventory = new Inventory();
		when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventory);
		Inventory savedInventory = inventoryRepository.save(inventory);
		assertNotNull(savedInventory);
		assertEquals("Item", savedInventory.getName());
		assertEquals(10, savedInventory.getQuantity());
	}

	@Test
	void testUpdateInventory() {
		long id = 1L;
		Inventory existingInventory = new Inventory();
		Inventory updatedInventory = new Inventory();
		when(inventoryRepository.findById(id)).thenReturn(Optional.of(existingInventory));
		when(inventoryRepository.save(any(Inventory.class))).thenAnswer(invocation -> invocation.getArgument(0));
		Inventory updated = inventoryRepository.save(updatedInventory);
		assertNotNull(updated);
		assertEquals("Updated Item", updated.getName());
		assertEquals(10, updated.getQuantity());
	}

	@Test
	void testDeleteInventory() {
		long id = 1L;
		inventoryRepository.deleteById(id);
		verify(inventoryRepository, times(1)).deleteById(id);
	}
}
