package _05_JPA_ORM.session00;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

class JTC_Connection {

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
	void connection() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Java_DistributedParallel MSSQL");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		
		try {
			transaction.begin();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			fail("Connection failed");
		} finally {
			manager.close();
			factory.close();
		}
		
		System.out.println("Connection successful!");
		System.out.println(this.getClass());
	}

}
