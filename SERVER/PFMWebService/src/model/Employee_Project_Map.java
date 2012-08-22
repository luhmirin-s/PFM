package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Employee_Project_Map database table.
 * 
 */
@Entity
public class Employee_Project_Map implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private Employee employee;
	private Project project;

	public Employee_Project_Map() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	//bi-directional many-to-one association to Employee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employeeId")
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	//bi-directional many-to-one association to Project
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="projectId")
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}