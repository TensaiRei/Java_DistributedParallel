package _03_JSON.session00_on_class;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import _03_JSON.session00_on_class.api_object_model.JSON_ObjectModelAPI;
import _03_JSON.session00_on_class.entity.Person;

class JTC_JSON_ObjectModelAPI {

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
	void setPeopleToFile() {
		List<Person> people = JSON_ObjectModelAPI
				.getListFromFile("data/_03_JSON/session00/people.json");
		JSON_ObjectModelAPI.setListToFile(people,
				"data/_03_JSON/session00/people_out_object_model.json");
	}

	@Test
	void getPeopleFromFile() {
		List<Person> people = JSON_ObjectModelAPI
				.getListFromFile("data/_03_JSON/session00/people.json");
		people.stream().forEach(System.out::println);
	}

}
