package com.botscrew.entity;


public enum DegreeEnum {
	ASSIST("Assistant"),
	ASSOCPROF("Associate professor"),
	PROF("Professor");
	
	private String degree;

	private DegreeEnum(String degree) {
		this.degree = degree;
	}

	public String getDegree() {
		return degree;
	}
	
}
