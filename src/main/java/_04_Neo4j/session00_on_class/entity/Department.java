package _04_Neo4j.session00_on_class.entity;

import java.util.Objects;

public class Department {
	
	private String deptID;
	private String name;
	private String dean;
	private String building;
	private String room;
	
	public String getDeptID() {
		return deptID;
	}

	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDean() {
		return dean;
	}

	public void setDean(String dean) {
		this.dean = dean;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public Department() {
		super();
	}

	public Department(String deptID, String name, String dean, String building, String room) {
		super();
		this.deptID = deptID;
		this.name = name;
		this.dean = dean;
		this.building = building;
		this.room = room;
	}

	@Override
	public String toString() {
		return String.format("Department [deptID=%s, name=%s, dean=%s, building=%s, room=%s]", deptID, name, dean,
				building, room);
	}

	@Override
	public int hashCode() {
		return Objects.hash(deptID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return Objects.equals(deptID, other.deptID);
	}

}
