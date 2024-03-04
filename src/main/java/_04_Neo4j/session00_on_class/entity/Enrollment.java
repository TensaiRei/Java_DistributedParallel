package _04_Neo4j.session00_on_class.entity;

public class Enrollment {

	private Course course;
	private Student student;

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Enrollment() {
		super();
	}

	public Enrollment(Course course, Student student) {
		super();
		this.course = course;
		this.student = student;
	}

	@Override
	public String toString() {
		return "Enrollment [course=" + course + ", student=" + student + "]";
	}

}
