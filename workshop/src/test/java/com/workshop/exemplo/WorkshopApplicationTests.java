package com.workshop.exemplo;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.workshop.exemplo.model.Customer;
import com.workshop.exemplo.repository.CustomerRepository;
import com.workshop.exemplo.service.CustomerService;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = WorkshopApplication.class)
@EnableTransactionManagement
public class WorkshopApplicationTests {

	@Autowired
	CustomerRepository repository;
	
	@Autowired
	CustomerService service;
	
	@Before
	public void contextLoads() {
	}

	@Test
	public void customerSaveTest() {
		Customer saved = createAndSaveTestCustomer();
		
		Long customerId = saved.getId();
		
		assertThat("Customer Id is not null", customerId, notNullValue());
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void customerIdNotAllowedToChangeTest() {
		Customer saved = createAndSaveTestCustomer();
		service.update(0L, saved);
		Assert.fail("Deve lançar uma exceção se o ID argumento for diferente do ID do Customer");
	}
	
	private Customer createAndSaveTestCustomer() {
		
		Customer newClasse = new Customer();
		newClasse.setAddress("Test Addess");
		newClasse.setEmail("testeemail@teste.com");
		newClasse.setName("Test Name");
		
		return repository.save(newClasse);
	}
}
