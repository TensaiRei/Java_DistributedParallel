package _04_Neo4j.session01_northwind.connect;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConnectionNeo4j {

	private static final String URI = "neo4j://localhost:7687";
	private static final String USERNAME = "neo4j";
	private static final String PASSWORD = "123123123";

	public static Driver getDriver() {
		return GraphDatabase.driver(URI, AuthTokens.basic(USERNAME, PASSWORD));
	}
	
}
