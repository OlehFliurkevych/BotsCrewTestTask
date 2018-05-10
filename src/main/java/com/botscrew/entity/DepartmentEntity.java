package com.botscrew.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
@Table(name="department")
@AnalyzerDef(name = "customanalyzer",
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
  @TokenFilterDef(factory = LowerCaseFilterFactory.class),
  @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
    @Parameter(name = "language", value = "English")
  })
})
public class DepartmentEntity extends BaseEntity{

	@Column(name="department_name")
//	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Field
	@Analyzer(definition = "customanalyzer")
	private String departmentName;
	
	@Field
	@Column(name="head_of_department_name")
//	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Analyzer(definition = "customanalyzer")
	private String headOfDepartmentName;
	
//	@IndexedEmbedded
	@OneToMany(cascade=CascadeType.ALL,mappedBy="department")
	private List<LectorEntity> lectors;

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

	
	
}
