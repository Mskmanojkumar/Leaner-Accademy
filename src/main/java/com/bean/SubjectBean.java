package com.bean;

public class SubjectBean {

	private int subid;
	private String subname;
	
	public SubjectBean() {
		
	}

	public SubjectBean(int subid, String subname) {
		super();
		this.subid = subid;
		this.subname = subname;
	}

	public SubjectBean(String subname) {
		super();
		this.subname = subname;
	}

	public int getSubid() {
		return subid;
	}

	public void setSubid(int subid) {
		this.subid = subid;
	}

	public String getSubname() {
		return subname;
	}

	public void setSubname(String subname) {
		this.subname = subname;
	}
		
	
}
