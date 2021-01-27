package com.brg.seats.controller;

import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.brg.seats.entities.Products;
import com.brg.seats.entities.Properties;
import com.brg.seats.service.ProductServiceImpl;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
	
	@Mock
	ProductServiceImpl productsService;
	
	@InjectMocks
	ProductController productController;
	
	MockMvc mockMvc;
	
	List<Products> products;
	
	@BeforeEach
	void setUp() {
		Properties props = new Properties(1164569055,"Two Hour Hire");
		Products prod = new Products(120166,327196207,LocalDateTime.now(),LocalDateTime.now(),8,0);
		prod.getProperties().add(props);
		products = new ArrayList<>();
		products.add(prod);
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}
	
	@AfterEach
	void tearDown() {
		reset(productsService);
	}

	@DisplayName("Controller Test- Fetch By id")
	@Test
	void testFetchByProductId() throws Exception {
		given(productsService.fetchAvailableSeats(any(),any(),any(),any())).willReturn(products);
		mockMvc.perform(get("/api/v1/products?"+"120166"))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andExpect(jsonPath("$", hasSize(1)));
	}

}
