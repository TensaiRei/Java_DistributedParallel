package _03_JSON.session01;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import _03_JSON.session01.api_object_model.JSON_ObjectModelHandler;
import _03_JSON.session01.entity.Employee;

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
	void writeEmployee() {
		Employee employee_1 = new Employee(1, "John", 3000);
		Employee employee_2 = new Employee(2, "Mary", 4000);
		String filePath = "data/_03_01/Employee.json";
		JSON_ObjectModelHandler.write(List.of(employee_1, employee_2), filePath);
	}
	
	@Test
	void readEmployee() {
		List<Employee> list = JSON_ObjectModelHandler.read("data/_03_01/Employee.json");
		for(Employee thisEmployee : list) {
			System.out.println(thisEmployee);
		}
	}

}
