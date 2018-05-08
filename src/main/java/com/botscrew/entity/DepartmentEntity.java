package com.botscrew.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="department")
public class DepartmentEntity extends BaseEntity{

	@Column(name="department_name")
	private String departmentName;
	
	@Column(name="head_of_department_name")
	private String headOfDepartmentName;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="lector_id")
	private LectorEntity lector;

	public DepartmentEntity(String departmentName, String headOfDepartmentName) {
		this.departmentName = departmentName;
		this.headOfDepartmentName = headOfDepartmentName;
	}

	public DepartmentEntity() {
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getHeadOfDepartmentName() {
		return headOfDepartmentName;
	}

	public void setHeadOfDepartmentName(String headOfDepartmentName) {
		this.headOfDepartmentName = headOfDepartmentName;
	}

	public LectorEntity getLector() {
		return lector;
	}

	public void setLector(LectorEntity lector) {
		this.lector = lector;
	}

	@Override
	public String toString() {
		return "DepartmentEntity [departmentName=" + departmentName + ", headOfDepartmentName=" + headOfDepartmentName
				+ ", lector=" + lector + "]";
	}
	
}
