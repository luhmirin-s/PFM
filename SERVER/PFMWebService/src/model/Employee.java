package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Employee database table.
 * 
 */
@Entity
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String firstName;
	private String lastName;
	private int level;
	private List<Employee_Project_Map> employeeProjectMaps;

	public Employee() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(name="firstname")
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	@Column(name="lastname")
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}


	//bi-directional many-to-one association to Employee_Project_Map
	@OneToMany(mappedBy="employee")
	public List<Employee_Project_Map> getEmployeeProjectMaps() {
		return this.employeeProjectMaps;
	}

	public void setEmployeeProjectMaps(List<Employee_Project_Map> employeeProjectMaps) {
		this.employeeProjectMaps = employeeProjectMaps;
	}

}