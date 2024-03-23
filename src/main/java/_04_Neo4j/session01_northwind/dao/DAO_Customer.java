package _04_Neo4j.session01_northwind.dao;

import java.util.List;
import org.neo4j.driver.Driver;

import _04_Neo4j.session01_northwind.connect.ConnectionNeo4j;
import _04_Neo4j.session01_northwind.entity.Customer;

public class DAO_Customer {

	private Driver driver;

	public DAO_Customer() {
		driver = ConnectionNeo4j.getDriver();
	}

	public void close() {
		driver.close();
	}

	public List<Customer> getListOfCustomers() {
		return null;
	}

}
