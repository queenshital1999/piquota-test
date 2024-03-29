package com.stock.management.repository.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.stock.management.controller.StockController;
import com.stock.management.entity.Stock;
import com.stock.management.service.StockService;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class StockControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private StockService stockService;

	@InjectMocks
	private StockController stockController;

	@Test
	public void testCreateStock() throws Exception {
		Stock sampleStock = new Stock();
		sampleStock.setId(1);
		sampleStock.setName("Sample Stock");
		sampleStock.setQuantity(100);
		when(stockService.save(any(Stock.class))).thenReturn(sampleStock);
		ResultActions result = mockMvc.perform(post("/stocks").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"Sample Stock\",\"quantity\":100}").accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("Sample Stock")).andExpect(jsonPath("$.quantity").value(100));
	}

	@Test
	public void testGetStock() throws Exception {
		Stock sampleStock = new Stock();
		sampleStock.setId(1);
		sampleStock.setName("Sample Stock");
		sampleStock.setQuantity(100);
		when(stockService.getStock(1)).thenReturn(sampleStock);
		ResultActions result = mockMvc.perform(get("/stocks/1").accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("Sample Stock")).andExpect(jsonPath("$.quantity").value(100));
	}

	@Test
	public void testUpdateStock() throws Exception {
		Stock sampleStock = new Stock();
		sampleStock.setId(1);
		sampleStock.setName("Sample Stock");
		sampleStock.setQuantity(100);
		when(stockService.update(anyLong(), any(Stock.class))).thenReturn(sampleStock);
		ResultActions result = mockMvc.perform(put("/stocks/1").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"Updated Stock\",\"quantity\":200}").accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("Sample Stock")).andExpect(jsonPath("$.quantity").value(100));
	}

	@Test
	public void testDeleteStock() throws Exception {
		doNothing().when(stockService).deleted(1L);
		ResultActions result = mockMvc.perform(delete("/stocks/1").accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNoContent());
	}
}
