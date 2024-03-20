package _04_Neo4j.session01_northwind.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;

import _04_Neo4j.session01_northwind.connect.ConnectionNeo4j;
import _04_Neo4j.session01_northwind.entity.Customer;
import _04_Neo4j.session01_northwind.utillity.JsonUtility;

public class DAO_Customer {

	private Driver driver;

	public DAO_Customer() {
		driver = ConnectionNeo4j.getDriver();
	}

	public void close() {
		driver.close();
	}

	public List<Customer> getListOfCustomers() {
		// MATCH (c:Customer) RETURN c
		String query = "MATCH (c:Customer) RETURN c";
		try (Session session = driver.session()) {
			return session.executeRead(transaction -> {
				Result result = transaction.run(query);
				return result.stream()
						.collect(Collectors.toList())
						.stream()
						.map(record -> record.get("c").asNode())
						.map(node -> JsonUtility.convertNodeToPOJO(node, Customer.class))
						.collect(Collectors.toList());

			});
		}
	}

}
