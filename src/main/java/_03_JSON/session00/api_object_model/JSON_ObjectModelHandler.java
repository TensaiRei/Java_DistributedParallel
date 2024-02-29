package _03_JSON.session00.api_object_model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import _03_JSON.session00.entity.Address;
import _03_JSON.session00.entity.Person;
import _03_JSON.session00.entity.PhoneNumber;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import jakarta.json.JsonWriter;

public class JSON_ObjectModelHandler {

	public static List<Person> getListFromFile(String filePath) {

		List<Person> people = new ArrayList<Person>();

		Person person = null;
		Address address = null;
		List<PhoneNumber> phoneNumbers = null;

		try (JsonReader reader = Json.createReader(new FileReader(filePath));) {
			JsonArray jsonArray = reader.readArray();
			if (jsonArray == null)
				return null;
			for (JsonValue thisJsonValue : jsonArray) {

				JsonObject jsonObject = (JsonObject) thisJsonValue;
				if (jsonObject == null)
					break;

				person = new Person();
				person.setFirstName(jsonObject.getString("firstName"));
				person.setLastName(jsonObject.getString("lastName"));
				person.setAge(jsonObject.getInt("age"));

				JsonObject jsonObjectAddress = jsonObject
						.getJsonObject("address");
				if (jsonObjectAddress != null) {
					address = new Address();
					address.setStreetAddress(
							jsonObjectAddress.getString("streetAddress"));
					address.setCity(jsonObjectAddress.getString("city"));
					address.setState(jsonObjectAddress.getString("state"));
					address.setPostalCode(
							jsonObjectAddress.getInt("postalCode"));

					person.setAddress(address);
				}

				JsonArray jsonArrayPhoneNumbers = jsonObject
						.getJsonArray("phoneNumbers");
				if (jsonArrayPhoneNumbers != null) {
					phoneNumbers = new ArrayList<PhoneNumber>();
					for (JsonValue thisJsonArrayValue : jsonArrayPhoneNumbers) {
						JsonObject jsonObjectPhoneNumber = (JsonObject) thisJsonArrayValue;
						PhoneNumber phoneNumber = new PhoneNumber(
								jsonObjectPhoneNumber.getString("type"),
								jsonObjectPhoneNumber.getString("number"));
						phoneNumbers.add(phoneNumber);
					}
					person.setPhoneNumbers(phoneNumbers);
				}

				people.add(person);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return people;
	}

	public static void setListToFile(List<Person> people, String filePath) {
		try (JsonWriter writer = Json.createWriter(new FileWriter(filePath))) {

			JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
			JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();

			for (Person thisPerson : people) {
				Address address = thisPerson.getAddress();
				JsonObjectBuilder jsonObjectAddressBuilder = Json
						.createObjectBuilder();
				JsonObject jsonObjectAddress = jsonObjectAddressBuilder
						.add("streetAddress", address.getStreetAddress())
						.add("city", address.getCity())
						.add("state", address.getState())
						.add("postalCode", address.getPostalCode()).build();

				JsonArrayBuilder jsonArrayPhoneNumberBuilder = Json
						.createArrayBuilder();
				List<PhoneNumber> phoneNumbers = thisPerson.getPhoneNumbers();
				for (PhoneNumber thisPhoneNumber : phoneNumbers) {
					JsonObjectBuilder jsonObjectPhoneNumberBuilder = Json
							.createObjectBuilder();
					JsonObject jsonObjectPhoneNumber = jsonObjectPhoneNumberBuilder
							.add("type", thisPhoneNumber.getType())
							.add("number", thisPhoneNumber.getNumber()).build();

					jsonArrayPhoneNumberBuilder.add(jsonObjectPhoneNumber);
				}
				JsonArray jsonArrayPhoneNumber = jsonArrayPhoneNumberBuilder
						.build();

				JsonObject jsonObjectPerson = jsonObjectBuilder
						.add("firstName", thisPerson.getFirstName())
						.add("lastName", thisPerson.getLastName())
						.add("age", thisPerson.getAge())
						.add("address", jsonObjectAddress)
						.add("phoneNumbers", jsonArrayPhoneNumber).build();
				jsonArrayBuilder.add(jsonObjectPerson);
			}
			JsonArray jsonArray = jsonArrayBuilder.build();
			writer.write(jsonArray);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
