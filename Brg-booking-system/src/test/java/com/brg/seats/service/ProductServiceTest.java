package com.brg.seats.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.reset;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.mockito.BDDMockito.*;
import com.brg.seats.dao.IProductsFetchDAO;
import com.brg.seats.entities.Products;
import com.brg.seats.entities.Properties;
import com.brg.seats.exception.NoRecordsFoundException;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

	@Mock
	IProductsFetchDAO productsFetchDAO;

	@InjectMocks
	ProductServiceImpl productService;

	List<Products> products;

	Page<Products> page;
	
	Page<Products> pageNoRecs;

	PageRequest pageRequest;

	@Captor
	ArgumentCaptor<Integer> captor;

	@Captor
	ArgumentCaptor<Integer> typeCaptor;

	@Captor
	ArgumentCaptor<LocalDateTime> timeCaptor;

	@Captor
	ArgumentCaptor<PageRequest> pageRequestCaptor;

	@BeforeEach
	void setUp() {
		// given
		Properties props = new Properties(1164569055, "Two Hour Hire");
		Products prod = new Products(120166, 327196207, LocalDateTime.now(), LocalDateTime.now(), 8, 0);
		prod.getProperties().add(props);
		products = new ArrayList<>();
		products.add(prod);
		pageRequest = PageRequest.of(1, 1, Sort.by("productid"));
		page = new PageImpl<>(products);		
		pageNoRecs= new PageImpl<>(new ArrayList<>());
	}

	@AfterEach
	void tearDown() {
		reset(productsFetchDAO);
	}

	@Test
	@DisplayName("Service Test -1 - Find All")
	void testFindAll() {
		// given
		given(productsFetchDAO.findAll(pageRequest)).willReturn(page);
		// when
		List<Products> seats = productService.fetchAvailableSeats(null, null, null, pageRequest);
		// then
		then(productsFetchDAO).should().findAll(pageRequest);
		assertThat(seats).isNotNull();
		assertThat(seats).size().isEqualTo(1);
	}

	@Test
	@DisplayName("Service Test - 2 - Find By Product Id")
	void testFindByProductId() {
		// given
		given(productsFetchDAO.findAllByProductId(captor.capture(), pageRequestCaptor.capture())).willReturn(page);
		// when
		List<Products> seats = productService.fetchAvailableSeats(120166, null, null, pageRequest);
		// then
		then(productsFetchDAO).should().findAllByProductId(120166, pageRequest);
		assertThat(seats).isNotNull();
		assertThat(120166).isEqualTo(captor.getValue());
		assertThat(seats).size().isEqualTo(1);
	}

	@Test
	@DisplayName("Service Test - 3 - Find By Product Id And Type Id")
	void testFindByProductIdAndTypeId() {
		// given
		given(productsFetchDAO.findAllByProductIdTypeId(captor.capture(), typeCaptor.capture(),
				pageRequestCaptor.capture())).willReturn(page);
		// when
		List<Products> seats = productService.fetchAvailableSeats(120166, 1164491234, null, pageRequest);
		// then
		then(productsFetchDAO).should().findAllByProductIdTypeId(120166, 1164491234, pageRequest);
		assertThat(seats).isNotNull();
		assertThat(120166).isEqualTo(captor.getValue());
		assertThat(1164491234).isEqualTo(typeCaptor.getValue());
		assertThat(seats).size().isEqualTo(1);
	}

	@Test
	@DisplayName("Service Test - 4 - Find By Product Id And Type Id And Time")
	void testFindByProductIdTypeIdAndTime() {
		// given
		given(productsFetchDAO.findAllByProductIdTypeIdAndTime(captor.capture(), typeCaptor.capture(),
				timeCaptor.capture(), pageRequestCaptor.capture())).willReturn(page);
		// when
		List<Products> seats = productService.fetchAvailableSeats(120166, 1164491234, LocalDateTime.now(), pageRequest);
		// then
		/*then(productsFetchDAO).should().findAllByProductIdTypeIdAndTime(120166, 1164491234, LocalDateTime.now(),
				pageRequest);*/
		assertThat(seats).isNotNull();
		assertThat(120166).isEqualTo(captor.getValue());
		assertThat(1164491234).isEqualTo(typeCaptor.getValue());
		assertThat(timeCaptor.getValue()).isNotNull();
		assertThat(seats).size().isEqualTo(1);
	}

	@Test
	@DisplayName("Service Test - 5 - Exception")
	void testException() {
		// given
		given(productsFetchDAO.findAllByProductId(captor.capture(), pageRequestCaptor.capture())).willAnswer(answer -> {
			Integer productid = answer.getArgument(0);
			if (productid.equals(120166)) {
				return page;
			}
			else
				return pageNoRecs;
			//throw new NoRecordsFoundException("No Records Found");
		});
		assertThrows(NoRecordsFoundException.class, ()->productService.fetchAvailableSeats(120167, null, null, pageRequest));		
	}
}
