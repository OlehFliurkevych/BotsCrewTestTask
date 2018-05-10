package com.botscrew.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="department")
public class DepartmentEntity extends BaseEntity{

	@Column(name="department_name")
	private String departmentName;
	
	@Column(name="head_of_department_name")
	private String headOfDepartmentName;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="department")
	private List<LectorEntity> lectors;

	
//	@ManyToMany(cascade={CascadeType.ALL})
//	@JoinTable(
//			name="department_lector",
//			joinColumns={@JoinColumn(name="department_id")},
//			inverseJoinColumns={@JoinColumn(name="lector_id")})
//	private List<LectorEntity> lectors;

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
	

	public List<LectorEntity> getLectors() {
		return lectors;
	}

	public void setLectors(List<LectorEntity> lectors) {
		this.lectors = lectors;
	}

	@Override
	public String toString() {
		return "DepartmentEntity [departmentName=" + departmentName + ", headOfDepartmentName=" + headOfDepartmentName
				+ "]";
	}

/*	@Override
	public String toString() {
		return "DepartmentEntity [departmentName=" + departmentName + ", headOfDepartmentName=" + headOfDepartmentName
				+ ", lectors=" + lectors + "]";
	}*/
	
	
//	public List<LectorEntity> getLectors() {
//		return lectors;
//	}
//
//	public void setLectors(List<LectorEntity> lectors) {
//		this.lectors = lectors;
//	}
//
//	@Override
//	public String toString() {
//		return "DepartmentEntity [departmentName=" + departmentName + ", headOfDepartmentName=" + headOfDepartmentName
//				+ ", lectors=" + lectors + "]";
//	}

	
	
}
