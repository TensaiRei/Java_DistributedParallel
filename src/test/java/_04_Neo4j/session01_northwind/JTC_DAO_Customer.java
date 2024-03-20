package _04_Neo4j.session01_northwind;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import _04_Neo4j.session01_northwind.dao.DAO_Customer;

class JTC_DAO_Customer {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void getListOfCustomers() {
		DAO_Customer dao_Customer = new DAO_Customer();
		dao_Customer.getListOfCustomers().forEach(System.out::println);
	}

}
