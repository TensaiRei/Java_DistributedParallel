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
public class Customer {
	
	private String id;
    private String companyName;
    private transient Contact contact;
    private transient Address address;

}
