package com.botscrew.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

@Entity
@Indexed
@Table(name="lector")
@AnalyzerDef(name = "customanalyzerLector",
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
  @TokenFilterDef(factory = LowerCaseFilterFactory.class),
  @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
    @Parameter(name = "language", value = "English")
  })
})
public class LectorEntity extends BaseEntity{

	@Field
	@Column(name="lector_name")
//	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Analyzer(definition = "customanalyzerLector")
	private String lectorName;
	
	@Field
	@Column(name="degree")
	private DegreeEnum degree;
	
	@Field
	@Column(name="salary")
	private int salary;
	
//	@IndexedEmbedded
	@ManyToOne
	@JoinColumn(name="department_id")
	private DepartmentEntity department;

	public LectorEntity() {
	}

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
	

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	
}
