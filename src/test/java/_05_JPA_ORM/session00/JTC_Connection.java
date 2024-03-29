package _05_JPA_ORM.session00;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class JTC_Connection {

	private static EntityManagerFactory factory;
	private static EntityManager manager;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		factory = Persistence.createEntityManagerFactory("JPA_ORM_00 MSSQL");
		manager = factory.createEntityManager();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		manager.close();
		factory.close();
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void connection() {
		System.out.println("Factory status: " + factory.isOpen());
	}

}
