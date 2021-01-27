package com.brg.seats.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.brg.seats.dao.IProductsDAO;
import com.brg.seats.dao.IProductsFetchDAO;
import com.brg.seats.entities.Products;
import com.brg.seats.exception.NoRecordsFoundException;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	IProductsDAO productsDAO;

	@Autowired
	IProductsFetchDAO productFetchDAO;

	/*@Override
	@Transactional
	public void save(Products products) {
		productsDAO.save(products);
	}*/

	@Override
	@Transactional
	public List<Products> fetchAvailableSeats(Integer productId, Integer typeId, LocalDateTime time, PageRequest pageRequest) {
		Page<Products> findAllByProductId = null;		
		if (productId != null && typeId != null && time != null) {			
			findAllByProductId = productFetchDAO.findAllByProductIdTypeIdAndTime(productId, typeId, time, pageRequest);			
		} else if (productId != null && typeId != null) {
			findAllByProductId = productFetchDAO.findAllByProductIdTypeId(productId, typeId, pageRequest);

		} else if (productId != null) {			
			findAllByProductId = productFetchDAO.findAllByProductId(productId, pageRequest);

		} else {
			findAllByProductId = productFetchDAO.findAll(pageRequest);
		}
		List<Products> list = null;		
		if(findAllByProductId.getTotalElements()!=0)
			list = findAllByProductId.stream().collect(Collectors.toList());
		else 
			throw new NoRecordsFoundException("No Records Found");
		return list;
	}

}
