package com.inventory.management.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.inventory.management.entity.Inventory;
import com.inventory.management.exception.InventoryNotFoundException;
import com.inventory.management.service.InventoryService;

public class InventoryControllerTest {

	@Mock
	private InventoryService inventoryService;

	@InjectMocks
	private InventoryController inventoryController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateInventory() {
		// Create a sample inventory object
		Inventory sampleInventory = new Inventory();
		sampleInventory.setId(1);
		sampleInventory.setName("Sample Inventory");
		sampleInventory.setQuantity(100);

		// Mocking the behavior of inventoryService.createInventory()
		when(inventoryService.createInventory(sampleInventory)).thenReturn(sampleInventory);

		// Call the controller method
		ResponseEntity<Inventory> response = inventoryController.createInventory(sampleInventory);

		// Verify that inventoryService.createInventory() was called with the sample
		// inventory
		verify(inventoryService, times(1)).createInventory(sampleInventory);

		// Assert that the response status is ACCEPTED and the returned inventory
		// matches the sample inventory
		assert response.getStatusCode() == HttpStatus.ACCEPTED;
		assert response.getBody() != null;
		assert response.getBody().equals(sampleInventory);
	}

	@Test
	public void testGetAllInventories() {
		// Create sample inventory data
		Inventory inventory1 = new Inventory();
		inventory1.setId(1);
		inventory1.setName("Inventory 1");
		inventory1.setQuantity(10);

		Inventory inventory2 = new Inventory();
		inventory2.setId(2);
		inventory2.setName("Inventory 2");
		inventory2.setQuantity(20);

		List<Inventory> sampleInventories = Arrays.asList(inventory1, inventory2);

		// Mock the behavior of inventoryService.getAllInventories()
		when(inventoryService.getAllInventories()).thenReturn(sampleInventories);

		// Call the controller method
		ResponseEntity<List<Inventory>> response = inventoryController.getAllInventories();

		// Verify that inventoryService.getAllInventories() was called
		verify(inventoryService, times(1)).getAllInventories();

		// Assert that the response status is ACCEPTED and the returned inventory list
		// matches the sample inventory data
		assert response.getStatusCode() == HttpStatus.ACCEPTED;
		assert response.getBody() != null;
		assert response.getBody().size() == sampleInventories.size();
		assert response.getBody().containsAll(sampleInventories);
	}

	@Test
	public void testUpdateInventory() {
		// Create a sample inventory object with updated values
		Inventory updatedInventory = new Inventory();
		updatedInventory.setId(1L);
		updatedInventory.setName("Updated Inventory");
		updatedInventory.setQuantity(50);

		// Mock the behavior of inventoryService.updateInventory()
		when(inventoryService.updateInventory(1L, updatedInventory)).thenReturn(updatedInventory);

		// Call the controller method
		ResponseEntity<Inventory> response = inventoryController.updateInventory(1L, updatedInventory);

		// Verify that inventoryService.updateInventory() was called
		verify(inventoryService, times(1)).updateInventory(1L, updatedInventory);

		// Assert that the response status is OK and the returned inventory matches the
		// updated inventory
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(updatedInventory, response.getBody());
	}

	@Test
	public void testGetInventoryById_Success() throws InventoryNotFoundException {
		// Create sample inventory data
		Inventory sampleInventory = new Inventory();
		sampleInventory.setId(1L);
		sampleInventory.setName("Inventory 1");
		sampleInventory.setQuantity(10);

		// Mock the behavior of inventoryService.getInventoryById()
		when(inventoryService.getInventoryById(1L)).thenReturn(Optional.of(sampleInventory));

		// Call the controller method
		ResponseEntity<Inventory> response = inventoryController.getInventoryById(1L);

		// Verify that inventoryService.getInventoryById() was called
		verify(inventoryService, times(1)).getInventoryById(1L);

		// Assert that the response status is OK and the returned inventory matches the
		// sample inventory data
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(sampleInventory, response.getBody());
	}

	@Test
	public void testGetInventoryById_InventoryNotFound() {
		// Mock the behavior of inventoryService.getInventoryById() to return an empty
		// Optional
		when(inventoryService.getInventoryById(1L)).thenReturn(Optional.empty());

		// Call the controller method and expect InventoryNotFoundException
		assertThrows(InventoryNotFoundException.class, () -> inventoryController.getInventoryById(1L));

		// Verify that inventoryService.getInventoryById() was called
		verify(inventoryService, times(1)).getInventoryById(1L);
	}

	@Test
	public void testDeleteInventory_Success() throws InventoryNotFoundException {
		// Mock the behavior of inventoryService.deleteInventory()
		when(inventoryService.deleted(1L)).thenReturn("Inventory deleted successfully");

		// Call the controller method
		ResponseEntity<String> response = inventoryController.deleteInventory(1L);

		// Verify that inventoryService.deleteInventory() was called
		verify(inventoryService, times(1)).deleted(1L);

		// Assert that the response status is OK and the response message is correct
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Inventory deleted successfully", response.getBody());
	}

	@Test
	public void testDeleteInventory_InventoryNotFound() throws InventoryNotFoundException {
		// Mock the behavior of inventoryService.deleteInventory() to throw
		// InventoryNotFoundException
		doThrow(new InventoryNotFoundException("Inventory not found")).when(inventoryService).deleted(1L);

		// Call the controller method and expect InventoryNotFoundException
		assertThrows(InventoryNotFoundException.class, () -> inventoryController.deleteInventory(1L));

		// Verify that inventoryService.deleteInventory() was called
		verify(inventoryService, times(1)).deleted(1L);
	}
}
