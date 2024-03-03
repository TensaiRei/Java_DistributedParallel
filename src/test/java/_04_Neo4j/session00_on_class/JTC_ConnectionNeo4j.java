package _04_Neo4j.session00_on_class;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.Values;
import org.neo4j.driver.types.Node;

import _04_Neo4j.session00_on_class.connect.ConnectionNeo4j;
import _04_Neo4j.session00_on_class.entity.Course;

class JTC_ConnectionNeo4j {

	private static final String DB_NAME = "courses";

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
	void connectionNeo4j() {
		Driver driver = ConnectionNeo4j.getInstance().getDriver();
		Session session = driver.session(SessionConfig.forDatabase(DB_NAME));
		Transaction transaction = session.beginTransaction();

		String query = "MATCH (c: Course) " + "WHERE c.courseID = $id " + "RETURN c";
		String id = "CS101";

		var result = transaction.run(query, Values.parameters("id", id));
		Record record = result.single();
		Node temp = record.get("c").asNode();

		Course course = new Course();
		course.setCourseID(temp.get("courseID").asString());
		course.setName(temp.get("name").asString());
		course.setHours(temp.get("hours").asInt());

		System.out.println(course);

		transaction.commit();
		ConnectionNeo4j.getInstance().close();
	}

}
