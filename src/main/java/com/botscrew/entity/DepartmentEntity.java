package com.botscrew.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="department")
public class DepartmentEntity extends BaseEntity{

	@Column(name="department_name")
	private String departmentName;
	
	@Column(name="head_of_department_name")
	private String headOfDepartmentName;
	
	
}
