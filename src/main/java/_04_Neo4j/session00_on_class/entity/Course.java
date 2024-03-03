package _04_Neo4j.session00_on_class.entity;

import java.util.Objects;

public class Course {

	private String courseID;
	private String name;
	private int hours;
	private Department department;
	
	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Course() {
		super();
	}

	public Course(String courseID, String name, int hours, Department department) {
		super();
		this.courseID = courseID;
		this.name = name;
		this.hours = hours;
		this.department = department;
	}

	@Override
	public String toString() {
		return "Course [courseID=" + courseID + ", name=" + name + ", hours=" + hours + ", department=" + department
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(courseID, other.courseID);
	}

}
