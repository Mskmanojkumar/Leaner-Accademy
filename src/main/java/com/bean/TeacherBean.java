package com.bean;

public class TeacherBean {
	private int tid;
	private String fname;
	private String lname;
	private String dob;
	private String address;
	private String edueq;
	
	
	public TeacherBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TeacherBean(int tid, String fname, String lname, String dob, String address, String edueq) {
		super();
		this.tid = tid;
		this.fname = fname;
		this.lname = lname;
		this.dob = dob;
		this.address = address;
		this.edueq = edueq;
	}


	public TeacherBean(String fname, String lname, String dob, String address, String edueq) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.dob = dob;
		this.address = address;
		this.edueq = edueq;
	}


	public int getTid() {
		return tid;
	}


	public void setTid(int tid) {
		this.tid = tid;
	}


	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public String getLname() {
		return lname;
	}


	public void setLname(String lname) {
		this.lname = lname;
	}


	public String getDob() {
		return dob;
	}


	public void setDob(String dob) {
		this.dob = dob;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getEdueq() {
		return edueq;
	}


	public void setEdueq(String edueq) {
		this.edueq = edueq;
	}
	

}
