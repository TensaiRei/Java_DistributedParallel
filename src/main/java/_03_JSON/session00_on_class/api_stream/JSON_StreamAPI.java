package _03_JSON.session00_on_class.api_stream;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import _03_JSON.session00_on_class.entity.Address;
import _03_JSON.session00_on_class.entity.Person;
import _03_JSON.session00_on_class.entity.PhoneNumber;
import jakarta.json.Json;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;

public class JSON_StreamAPI {

	public static void setListToFile(List<Person> people, String filePath) {

		try (JsonGenerator generator = Json.createGenerator(new FileWriter(filePath))) {

			generator.writeStartArray(); 										// 0. Start Main Array

			for (Person thisPerson : people) {

				generator.writeStartObject() 									// 1. Start Person Object
						.write("firstName", thisPerson.getFirstName())
						.write("lastName", thisPerson.getLastName())
						.write("age", thisPerson.getAge());

				Address address = thisPerson.getAddress();
				generator.writeStartObject("address") 							// 2. Start Address Object
						.write("streetAddress", address.getStreetAddress())
						.write("city", address.getCity())
						.write("state", address.getState())
						.write("postalCode", address.getPostalCode())
						.writeEnd(); 											// 2. End Address Object

				List<PhoneNumber> phoneNumbers = thisPerson.getPhoneNumbers();
				generator.writeStartArray("phoneNumbers"); 						// 3. Start PhoneNumbers Array
				for (PhoneNumber thisPhoneNumber : phoneNumbers) {
					generator.writeStartObject() 								// 4. Start PhoneNumber Object
							.write("type", thisPhoneNumber.getType())
							.write("number", thisPhoneNumber.getNumber())
							.writeEnd(); 										// 4. End PhoneNumber Object
				}
				generator.writeEnd(); 											// 3. End PhoneNumbers Array

				generator.writeEnd(); 											// 1. End Person Object
			}

			generator.writeEnd(); 												// 0. End Main Array

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<Person> getListFromFile(String filePath) {

		List<Person> people = null;

		Person person = null;
		Address address = null;
		PhoneNumber phoneNumber = null;
		List<PhoneNumber> phoneNumbers = null;

		String keyName = new String();
		Stack<String> stackObject = new Stack<String>();
		Stack<String> stackArray = new Stack<String>();

		try (JsonParser parser = Json.createParser(new FileReader(filePath))) {

			while (parser.hasNext()) {
				Event event = parser.next();

				switch (event) {
					case KEY_NAME :
						keyName = parser.getString();
						break;
					case START_OBJECT :
						if (stackObject.isEmpty()) {
							person = new Person();
							stackObject.push("person");
							break;
						}
						switch (keyName) {
							case "address" :
								address = new Address();
								stackObject.push("address");
								break;
						}
						switch (stackArray.peek()) {
							case "phoneNumbers" :
								phoneNumber = new PhoneNumber();
								stackObject.push("phoneNumber");
								break;
						}
						break;
					case END_OBJECT :
						switch (stackObject.peek()) {
							case "person" :
								people.add(person);
								stackObject.pop();
								break;
							case "address" :
								person.setAddress(address);
								stackObject.pop();
								break;
							case "phoneNumber" :
								phoneNumbers.add(phoneNumber);
								stackObject.pop();
								break;
						}
						break;
					case START_ARRAY :
						if (stackArray.isEmpty()) {
							people = new ArrayList<Person>();
							stackArray.push("people");
						}
						switch (keyName) {
							case "phoneNumbers" :
								phoneNumbers = new ArrayList<PhoneNumber>();
								stackArray.push("phoneNumbers");
								break;
						}
						break;
					case END_ARRAY :
						switch (stackArray.peek()) {
							case "people" :
								return people;
							case "phoneNumbers" :
								person.setPhoneNumbers(phoneNumbers);
								stackArray.pop();
								break;
						}
						break;
					case VALUE_STRING :
						switch (keyName) {
							case "firstName" :
								person.setFirstName(parser.getString());
								break;
							case "lastName" :
								person.setLastName(parser.getString());
								break;
							case "streetAddress" :
								address.setStreetAddress(parser.getString());
								break;
							case "city" :
								address.setCity(parser.getString());
								break;
							case "state" :
								address.setState(parser.getString());
								break;
							case "type" :
								phoneNumber.setType(parser.getString());
								break;
							case "number" :
								phoneNumber.setNumber(parser.getString());
								break;
						}
						break;
					case VALUE_NUMBER :
						switch (keyName) {
							case "age" :
								person.setAge(parser.getInt());
								break;
							case "postalCode" :
								address.setPostalCode(parser.getInt());
								break;
						}
						break;
					case VALUE_NULL :
						break;
					case VALUE_TRUE :
						break;
					case VALUE_FALSE :
						break;
					default :
						break;
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return people;
	}

}
