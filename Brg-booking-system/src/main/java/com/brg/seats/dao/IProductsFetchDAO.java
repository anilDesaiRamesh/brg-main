package com.brg.seats.dao;

import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.brg.seats.entities.Products;

public interface IProductsFetchDAO extends JpaRepository<Products, Integer> {

	@Query(value = "SELECT * FROM PRODUCTS prod "
			+ " INNER JOIN PROPERTIES props WHERE prod.ID=props.PRODUCTID AND prod.PRODUCTID=:productId "
			+ " \n-- #pageRequest\n ", 
			countQuery = "select count(*) FROM PRODUCTS prod "
					+ " INNER JOIN PROPERTIES props WHERE prod.ID=props.PRODUCTID AND prod.PRODUCTID=:productId "
					+ " \n-- #pageRequest\n ", 
			nativeQuery = true)
	Page<Products> findAllByProductId(@Param("productId") Integer productId, PageRequest pageRequest);

	@Query(value = "SELECT * FROM PRODUCTS prod "
			+ " INNER JOIN PROPERTIES props WHERE prod.ID=props.PRODUCTID AND prod.PRODUCTID=:productId AND props.TYPEID=:typeId "
			+ " \n-- #pageRequest\n ", 
			countQuery = "select count(*) FROM PRODUCTS prod "
					+ " INNER JOIN PROPERTIES props WHERE prod.ID=props.PRODUCTID AND prod.PRODUCTID=:productId AND props.TYPEID=:typeId "
					+ " \n-- #pageRequest\n ", 
			nativeQuery = true)
	Page<Products> findAllByProductIdTypeId(@Param("productId") Integer productId,@Param("typeId") Integer typeId,
			PageRequest pageRequest);

	@Query(value = "SELECT * FROM PRODUCTS prod "
			+ " INNER JOIN PROPERTIES props WHERE prod.ID=props.PRODUCTID AND prod.PRODUCTID=:productId AND props.TYPEID=:typeId AND prod.STARTD>=:time AND prod.END<=:time"
			+ " \n-- #pageRequest\n ", 
			countQuery = "select count(*) FROM PRODUCTS prod "
					+ " INNER JOIN PROPERTIES props WHERE prod.ID=props.PRODUCTID AND prod.PRODUCTID=:productId AND props.TYPEID=:typeId "
					+ " \n-- #pageRequest\n ", 
			nativeQuery = true)
	Page<Products> findAllByProductIdTypeIdAndTime(@Param("productId") Integer productId,@Param("typeId") Integer typeId,@Param("time") LocalDateTime time,
			PageRequest pageRequest);
	
	
	@Query(value = "SELECT * FROM PRODUCTS prod "
			+ " INNER JOIN PROPERTIES props WHERE prod.ID=props.PRODUCTID "
			+ " \n-- #pageRequest\n ", 
			countQuery = "select count(*) FROM PRODUCTS prod "
					+ " INNER JOIN PROPERTIES props WHERE prod.ID=props.PRODUCTID "
					+ " \n-- #pageRequest\n ", 
			nativeQuery = true)
	Page<Products> findAll(PageRequest pageRequest);

}
