package _04_Neo4j.session01_northwind.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Address {
	
	private String address;
	private String city;
	private String region;
	private String postalCode;
	private String country;
	
}
