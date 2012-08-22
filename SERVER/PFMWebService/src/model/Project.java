package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Project database table.
 * 
 */
@Entity
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String description;
	private String name;
	private List<Employee_Project_Map> employeeProjectMaps;

	public Project() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to Employee_Project_Map
	@OneToMany(mappedBy="project")
	public List<Employee_Project_Map> getEmployeeProjectMaps() {
		return this.employeeProjectMaps;
	}

	public void setEmployeeProjectMaps(List<Employee_Project_Map> employeeProjectMaps) {
		this.employeeProjectMaps = employeeProjectMaps;
	}

}