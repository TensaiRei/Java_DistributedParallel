package _04_Neo4j.session00_on_class.entity;

import java.util.Objects;

public class Student {

	private String studentID;
	private String name;
	private double gpa;

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public Student() {
		super();
	}

	public Student(String studentID, String name, double gpa) {
		super();
		this.studentID = studentID;
		this.name = name;
		this.gpa = gpa;
	}

	@Override
	public String toString() {
		return "Student [studentID=" + studentID + ", name=" + name + ", gpa=" + gpa + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(studentID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(studentID, other.studentID);
	}

}
