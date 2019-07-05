package com.journaldev.spring.controller;

public class Topic {
	
	private int id;
	public Topic(int id, String course, String desc) {
		super();
		this.id = id;
		this.course = course;
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	private String course;
	private String desc;

	

}
