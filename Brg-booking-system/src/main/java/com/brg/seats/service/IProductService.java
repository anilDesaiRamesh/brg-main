package com.brg.seats.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.PageRequest;

import com.brg.seats.entities.Products;

public interface IProductService {

	//void save(Products products);
	
	List<Products> fetchAvailableSeats(Integer productId, Integer typeId, LocalDateTime time, PageRequest of);

}
