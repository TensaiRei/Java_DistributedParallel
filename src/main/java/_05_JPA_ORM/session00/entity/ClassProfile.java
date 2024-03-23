package _05_JPA_ORM.session00.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "ClassProfile")
public class ClassProfile {

	@Id
	@Column(name = "profile_id")
	private String id;

	@OneToOne
	@JoinColumn(name = "class_id", unique = true, nullable = false)
	private Class_ class_;

	private LocalDate createDate;
	private String description;

}
