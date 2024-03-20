package _04_Neo4j.session01_northwind;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;
import org.neo4j.driver.Transaction;

import _04_Neo4j.session01_northwind.connect.ConnectionNeo4j;

class JTC_Initiate {

	private static final String DB_NAME = "neo4j";
	private static Driver driver;
	private static Session session;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		driver = ConnectionNeo4j.getDriver();
		session = driver.session(SessionConfig.forDatabase(DB_NAME));
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		session.close();
		driver.close();
	}

	@Test
	void testSession() {
		System.out.println(session.isOpen());
	}
	
	@Test
	void testQuery() {
		
		Transaction transaction = session.beginTransaction();
		String query = "MATCH (n:Category) RETURN n LIMIT 25";
		Result result = transaction.run(query);
		result.stream().forEach(record -> {
			System.out.println(record.get("n").asMap());
		});
		transaction.commit();
		
	}

}
