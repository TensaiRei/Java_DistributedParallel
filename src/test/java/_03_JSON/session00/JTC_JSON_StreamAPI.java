package _03_JSON.session00;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import _03_JSON.session00.api_stream.JSON_StreamHandler;
import _03_JSON.session00.entity.Person;

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
		List<Person> people = JSON_StreamHandler
				.getListFromFile("data/_03_00/people.json");
		System.out.println("Size: " + people.size());
		for (Person thisPerson : people) {
			System.out.println(thisPerson);
		}
	}

}
