package com.botscrew.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="lector")
public class LectorEntity extends BaseEntity{

	@Column(name="lector_name")
	private String lectorName;
	
	@Column(name="degree")
	private DegreeEnum degree;
}
