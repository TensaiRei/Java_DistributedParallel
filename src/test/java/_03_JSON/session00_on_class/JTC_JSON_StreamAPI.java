package _03_JSON.session00_on_class;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import _03_JSON.session00_on_class.api_stream.JSON_StreamAPI;
import _03_JSON.session00_on_class.entity.Person;

class JTC_JSON_StreamAPI {

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
		List<Person> people = JSON_StreamAPI.getListFromFile("data/_03_00/people.json");
		people.stream().forEach(System.out::println);
	}

	@Test
	void setListToFile() {
		List<Person> people = JSON_StreamAPI.getListFromFile("data/_03_00/people.json");
		JSON_StreamAPI.setListToFile(people, "data/_03_00/people_out_stream.json");
	}

}
