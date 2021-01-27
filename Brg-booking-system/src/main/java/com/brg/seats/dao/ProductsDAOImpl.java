package com.brg.seats.dao;

import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import com.brg.seats.entities.Products;
import com.brg.seats.entities.Properties;

@Repository
public class ProductsDAOImpl implements IProductsDAO {
	
	@PersistenceContext
	EntityManager entityManager;	

	@Override
	public void save(Products products) {		
		Set<Properties> set = products.getProperties().stream().map(p->{
		p.setProduct(products);
		return p;
		}).collect(Collectors.toSet());
		products.setProperties(set);
		entityManager.persist(products);
	}


	/*@Override
	public void findAll(int productId, int typeId, LocalDateTime time, PageRequest pageRequest) {
		
		String query = "";
		if(productId!=0 && typeId!=0 && !time.isEqual(LocalDateTime.now()))
		query= "select  Distinct Products.productId, Products.eventId,Products.seatsAvailable,Properties.typeId,Properties.label  from Products " + 
				"Inner join Properties on Products.id=Properties.productid and Products.productid=:productId and Properties.typeId=:typeId and "
				+ "Products.startd >=:time and Products.end<=:time ";
		if(productId!=0 && typeId!=0 && !time.isEqual(LocalDateTime.now()))
		
	}*/


}
