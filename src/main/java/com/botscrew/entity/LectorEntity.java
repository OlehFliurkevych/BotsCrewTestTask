package com.botscrew.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="lector")
public class LectorEntity extends BaseEntity{

	@Column(name="lector_name")
	private String lectorName;
	
	@Column(name="degree")
	private DegreeEnum degree;
	
	@Column(name="salary")
	private int salary;
	
	@ManyToOne
	@JoinColumn(name="department_id")
	private DepartmentEntity department;
	
//	@ManyToMany(mappedBy="lectors")
//	private List<DepartmentEntity> departments;
	
	

	public LectorEntity() {
	}

	
	
/*	@Override
	public String toString() {
		return "LectorEntity [lectorName=" + lectorName + ", degree=" + degree + ", salary=" + salary + ", department="
				+ department + "]";
	}*/

	@Override
	public String toString() {
		return "LectorEntity [lectorName=" + lectorName + ", degree=" + degree + ", salary=" + salary + "]";
	}



	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}

	public LectorEntity(String lectorName, DegreeEnum degree, int salary, DepartmentEntity department) {
		this.lectorName = lectorName;
		this.degree = degree;
		this.salary = salary;
		this.department = department;
	}

	public String getLectorName() {
		return lectorName;
	}

	public void setLectorName(String lectorName) {
		this.lectorName = lectorName;
	}

	public DegreeEnum getDegree() {
		return degree;
	}

	public void setDegree(DegreeEnum degree) {
		this.degree = degree;
	}

//	public List<DepartmentEntity> getDepartments() {
//		return departments;
//	}
//
//	public void setDepartments(List<DepartmentEntity> departments) {
//		this.departments = departments;
//	}
	

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

//	@Override
//	public String toString() {
//		return "LectorEntity [lectorName=" + lectorName + ", degree=" + degree + ", salary=" + salary + ", departments="
//				+ departments + "]";
//	}
	
}
