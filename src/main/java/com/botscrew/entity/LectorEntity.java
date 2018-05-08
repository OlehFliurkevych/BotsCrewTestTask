package com.botscrew.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="lector")
public class LectorEntity extends BaseEntity{

	@Column(name="lector_name")
	private String lectorName;
	
	@Column(name="degree")
	private DegreeEnum degree;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="lector")
	private List<DepartmentEntity> departments;

	public LectorEntity() {
	}

	public LectorEntity(String lectorName, DegreeEnum degree) {
		this.lectorName = lectorName;
		this.degree = degree;
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

	public List<DepartmentEntity> getDepartments() {
		return departments;
	}

	public void setDepartments(List<DepartmentEntity> departments) {
		this.departments = departments;
	}

	@Override
	public String toString() {
		return "LectorEntity [lectorName=" + lectorName + ", degree=" + degree + ", departments=" + departments + "]";
	}
	
}
