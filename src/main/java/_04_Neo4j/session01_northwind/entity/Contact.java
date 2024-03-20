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
public class Contact {
	
	private String contactName;
	private String contactTitle;
	private String phone;
	private String fax;
	
}
