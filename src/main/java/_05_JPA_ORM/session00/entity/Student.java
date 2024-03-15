package _05_JPA_ORM.session00.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {

	@Id
	private int id;

}
