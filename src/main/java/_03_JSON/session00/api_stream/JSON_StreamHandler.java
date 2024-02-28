package _03_JSON.session00.api_stream;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import _03_JSON.session00.entity.Address;
import _03_JSON.session00.entity.Person;
import _03_JSON.session00.entity.PhoneNumber;
import jakarta.json.Json;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;

public class JSON_StreamHandler {

	public static List<Person> getListFromFile(String filePath) {

		List<Person> people = null;

		Person person = null;
		Address address = null;
		PhoneNumber phoneNumber = null;
		List<PhoneNumber> phoneNumbers = null;

		String keyName = "";
		String currentObject = "";
		String currentArray = "";
		Stack<String> stackObject = new Stack<String>();
		Stack<String> stackArray = new Stack<String>();

		try (JsonParser parser = Json.createParser(new FileReader(filePath))) {

			while (parser.hasNext()) {
				Event event = parser.next();
				System.out.println(event);

				switch (event) {
				case KEY_NAME:
					keyName = parser.getString();
					break;
				case START_OBJECT:
					if (stackObject.isEmpty()) {
						person = new Person();
						stackObject.push("person");
						currentObject = "person";
						break;
					}
					switch (keyName) {
					case "address":
						address = new Address();
						stackObject.push("address");
						currentObject = "address";
						break;
					}
					switch (currentArray) {
					case "phoneNumbers":
						phoneNumber = new PhoneNumber();
						stackObject.push("phoneNumber");
						currentObject = "phoneNumber";
						break;
					}
					break;
				case END_OBJECT:
					switch (currentObject) {
					case "person":
						people.add(person);
						stackObject.pop();
						currentObject = "";
						break;
					case "address":
						person.setAddress(address);
						stackObject.pop();
						currentObject = stackObject.peek();
						break;
					case "phoneNumber":
						phoneNumbers.add(phoneNumber);
						stackObject.pop();
						currentObject = stackObject.peek();
						break;
					}
					break;
				case START_ARRAY:
					if (stackArray.isEmpty()) {
						people = new ArrayList<Person>();
						stackArray.push("people");
						currentArray = "people";
					}
					switch (keyName) {
					case "phoneNumbers":
						phoneNumbers = new ArrayList<PhoneNumber>();
						stackArray.push("phoneNumbers");
						currentArray = "phoneNumbers";
						break;
					}
					break;
				case END_ARRAY:
					switch (currentArray) {
					case "people":
						return people;
					case "phoneNumbers":
						person.setPhoneNumbers(phoneNumbers);
						stackArray.pop();
						currentArray = stackArray.peek();
						break;
					}
					break;
				case VALUE_STRING:
					switch (keyName) {
					case "firstName":
						person.setFirstName(parser.getString());
						break;
					case "lastName":
						person.setLastName(parser.getString());
						break;
					case "streetAddress":
						address.setStreetAddress(parser.getString());
						break;
					case "city":
						address.setCity(parser.getString());
						break;	
					case "state":
						address.setState(parser.getString());
						break;
					case "type":
						phoneNumber.setType(parser.getString());
						break;
					case "number":
						phoneNumber.setNumber(parser.getString());
						break;
					}
					break;
				case VALUE_NUMBER:
					switch (keyName) {
					case "age":
						person.setAge(parser.getInt());
						break;
					case "postalCode":
						address.setPostalCode(parser.getInt());
						break;
					}
					break;
				case VALUE_NULL:
					break;
				case VALUE_TRUE:
					break;
				case VALUE_FALSE:
					break;
				default:
					break;
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return people;
	}

}
