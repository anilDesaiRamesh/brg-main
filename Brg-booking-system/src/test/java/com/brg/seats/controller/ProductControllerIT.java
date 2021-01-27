package com.brg.seats.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.brg.seats.entities.Products;


@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class ProductControllerIT {

	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	void testFindAll() {
		List<Products> list = restTemplate.getForObject("/api/v1/products", List.class);
		assertThat(list).hasSize(25);
	}
	
	@Test
	void testFindByProductId() {
		int productId = 120166;
		String pageSize = "40";
		String sortBy="seatsAvailable";
		String URL = "/api/v1/products?productId="
				+productId+"&pageSize="+pageSize+"&sortBy="+sortBy;
		System.out.println("URL " + URL);
		List<Products> list = restTemplate.getForObject(URL, List.class);
		assertThat(list).hasSize(40);
	}
	
	@Test
	void testFindByProductIdAndTypeId() {
		int productId = 120166;
		int typeId=1164491234;
		String pageSize = "40";
		String sortBy="seatsAvailable";
		String URL = "/api/v1/products?productId="
				+productId+"&typeId="+typeId+"&pageSize="+pageSize+"&sortBy="+sortBy;		
		List<Products> list = restTemplate.getForObject(URL, List.class);		
		assertThat(list).hasSize(1);
	}
	
	@Test
	void testException() {
		int productId = 120167;		
		String pageSize = "40";
		String sortBy="seatsAvailable";        
		String URL = "/api/v1/products?productId="
				+productId+"&pageSize="+pageSize+"&sortBy="+sortBy;		
		ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());		
	}
}
