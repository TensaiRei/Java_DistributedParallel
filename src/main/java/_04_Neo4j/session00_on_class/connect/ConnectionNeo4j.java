package _04_Neo4j.session00_on_class.connect;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;

public class ConnectionNeo4j {

	private static ConnectionNeo4j instance = null;
	private Driver driver = null;
	
	public static ConnectionNeo4j getInstance() {
		if (instance == null)
			return new ConnectionNeo4j();
		return instance;
	}

	public Driver getDriver() {
		return driver;
	}

	public static void setInstance(ConnectionNeo4j instance) {
		ConnectionNeo4j.instance = instance;
	}

	private ConnectionNeo4j() {
		String uri = "neo4j://localhost:7687";
		String username = "neo4j";
		String password = "12345678";
		driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password));
	}
	
	public void close() {
		driver.close();
	}

}
