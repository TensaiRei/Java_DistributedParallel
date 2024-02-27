package _03_JSON.session00;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import _03_JSON.session00.api_object_model.JSON_ObjectModelHandler;
import _03_JSON.session00.entity.Person;

class JSON_ObjectModelAPI {

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
	void getPeopleFromFile() {
		List<Person> people = JSON_ObjectModelHandler.getListFromFile("data/_03_00/people.json");
		for (Person thisPerson : people) {
			System.out.println(thisPerson);
		}
	}

	@Test 
	void setPeopleToFile() {
		List<Person> people = JSON_ObjectModelHandler.getListFromFile("data/_03_00/people.json");
		JSON_ObjectModelHandler.setListToFile(people, "data/_03_00/people_new.json");
	}
}
