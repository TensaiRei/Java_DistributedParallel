package _03_JSON.session01.api_object_model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import _03_JSON.session01.entity.Employee;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import jakarta.json.JsonWriter;

public class JSON_ObjectModelHandler {

	public static void write(List<Employee> list, String filePath) {
		try (JsonWriter writer = Json.createWriter(new FileWriter(filePath))) {
			JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

			for (Employee thisEmployee : list) {
				JsonObjectBuilder jsonObjectBuilder = Json
						.createObjectBuilder();

				jsonObjectBuilder.add("id", thisEmployee.getId())
						.add("name", thisEmployee.getName())
						.add("salary", thisEmployee.getSalary());
				JsonObject jsonObject = jsonObjectBuilder.build();

				jsonArrayBuilder.add(jsonObject);
			}

			JsonArray jsonArray = jsonArrayBuilder.build();

			writer.write(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<Employee> read(String filePath) {
		List<Employee> list = new ArrayList<Employee>();

		try (JsonReader reader = Json.createReader(new FileReader(filePath))) {
			JsonArray jsonArray = reader.readArray();
			for (JsonValue thisValue : jsonArray) {
				JsonObject thisObject = (JsonObject) thisValue;
				Employee employee = new Employee(thisObject.getInt("id"),
						thisObject.getString("name"),
						thisObject.getJsonNumber("salary").doubleValue());
				list.add(employee);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return list;
	}
}
