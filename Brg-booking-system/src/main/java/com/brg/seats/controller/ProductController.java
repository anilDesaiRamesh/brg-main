package com.brg.seats.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.brg.seats.entities.Products;
import com.brg.seats.service.IProductService;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

	private static final Integer DEFAULT_PAGE_NUMBER = 0;
	private static final Integer DEFAULT_PAGE_SIZE = 25;

	@Autowired
	IProductService productService;

	/*@PutMapping("/products")
	public ResponseEntity<Products> save(@RequestBody Products products) {
		productService.save(products);
		return new ResponseEntity<Products>(products, HttpStatus.OK);
	}*/

	@GetMapping("/products")
	public ResponseEntity<List<Products>> listProducts(
			@RequestParam(name = "pageNumber", value = "pageNumber", required = false) Integer pageNumber,
			@RequestParam(name = "pageSize", value = "pageSize", required = false) Integer pageSize,
			@RequestParam(name = "sortBy", defaultValue = "productid", required = false) String sortBy,
			@RequestParam(name = "productId" , required = false) Integer productId, 
			@RequestParam(name = "typeId", required = false) Integer typeId,
			@RequestParam(name = "time", required = false)
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
			LocalDateTime time) {

		if (pageNumber == null || pageNumber < 0) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		}

		if (pageSize == null || pageSize < 1) {
			pageSize = DEFAULT_PAGE_SIZE;
		}		
		
		List<Products> list = productService.fetchAvailableSeats(productId,typeId,time,
				PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)));
				
		return new ResponseEntity<List<Products>>(list,HttpStatus.OK);
	}

}
