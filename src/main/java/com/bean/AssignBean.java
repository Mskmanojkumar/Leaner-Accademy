package com.bean;

public class AssignBean {

	private int id;
	private String cname;
	private String subname;
	private String tname;
	public AssignBean() {
		super();
		
	}
	public AssignBean(int id, String cname, String subname, String tname) {
		super();
		this.id = id;
		this.cname = cname;
		this.subname = subname;
		this.tname = tname;
	}
	public AssignBean(String cname, String subname, String tname) {
		super();
		this.cname = cname;
		this.subname = subname;
		this.tname = tname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getSubname() {
		return subname;
	}
	public void setSubname(String subname) {
		this.subname = subname;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	
}
