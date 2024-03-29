package _03_JSON.session00_on_class.api_object_model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import _03_JSON.session00_on_class.entity.Address;
import _03_JSON.session00_on_class.entity.Person;
import _03_JSON.session00_on_class.entity.PhoneNumber;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import jakarta.json.JsonWriter;

public class JSON_ObjectModelAPI {

	public static void setListToFile(List<Person> people, String filePath) {
		try (JsonWriter writer = Json.createWriter(new FileWriter(filePath))) {

			JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

			for (Person thisPerson : people) {

				Address address = thisPerson.getAddress();
				JsonObjectBuilder jsonObjectAddressBuilder = Json.createObjectBuilder();
				jsonObjectAddressBuilder.add("streetAddress", address.getStreetAddress())
						.add("city", address.getCity())
						.add("state", address.getState())
						.add("postalCode", address.getPostalCode());
				JsonObject jsonObjectAddress = jsonObjectAddressBuilder.build();

				JsonArrayBuilder jsonArrayPhoneNumbersBuilder = Json.createArrayBuilder();
				List<PhoneNumber> phoneNumbers = thisPerson.getPhoneNumbers();
				for (PhoneNumber thisPhoneNumber : phoneNumbers) {
					JsonObjectBuilder jsonObjectPhoneNumberBuilder = Json.createObjectBuilder();
					jsonObjectPhoneNumberBuilder.add("type", thisPhoneNumber.getType())
							.add("number", thisPhoneNumber.getNumber());
					JsonObject jsonObjectPhoneNumber = jsonObjectPhoneNumberBuilder.build();

					jsonArrayPhoneNumbersBuilder.add(jsonObjectPhoneNumber);
				}
				JsonArray jsonArrayPhoneNumber = jsonArrayPhoneNumbersBuilder.build();

				JsonObjectBuilder jsonObjectPersonBuilder = Json.createObjectBuilder();
				jsonObjectPersonBuilder.add("firstName", thisPerson.getFirstName())
						.add("lastName", thisPerson.getLastName())
						.add("age", thisPerson.getAge())
						.add("address", jsonObjectAddress)
						.add("phoneNumbers", jsonArrayPhoneNumber);
				JsonObject jsonObjectPerson = jsonObjectPersonBuilder.build();
				jsonArrayBuilder.add(jsonObjectPerson);
			}
			JsonArray jsonArray = jsonArrayBuilder.build();
			writer.write(jsonArray);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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

				JsonObject jsonObjectAddress = jsonObject.getJsonObject("address");
				if (jsonObjectAddress != null) {
					address = new Address();
					address.setStreetAddress(jsonObjectAddress.getString("streetAddress"));
					address.setCity(jsonObjectAddress.getString("city"));
					address.setState(jsonObjectAddress.getString("state"));
					address.setPostalCode(jsonObjectAddress.getInt("postalCode"));

					person.setAddress(address);
				}

				JsonArray jsonArrayPhoneNumbers = jsonObject.getJsonArray("phoneNumbers");
				if (jsonArrayPhoneNumbers != null) {
					phoneNumbers = new ArrayList<PhoneNumber>();
					for (JsonValue thisJsonArrayValue : jsonArrayPhoneNumbers) {
						JsonObject jsonObjectPhoneNumber = (JsonObject) thisJsonArrayValue;
						PhoneNumber phoneNumber = new PhoneNumber(jsonObjectPhoneNumber.getString("type"),
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

}
