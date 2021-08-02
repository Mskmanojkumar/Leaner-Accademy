package com.bean;

public class StudentBean {
	private int id;
	private String Fname;
	private String Lname;
	private String Dob;
	private String Address;
	private String cname;
	
	public StudentBean() {
		super();
	}

	public StudentBean(int id, String fname, String lname, String dob, String address, String cname) {
		super();
		this.id = id;
		Fname = fname;
		Lname = lname;
		Dob = dob;
		Address = address;
		this.cname = cname;
	}

	public StudentBean(String fname, String lname, String dob, String address, String cname) {
		super();
		Fname = fname;
		Lname = lname;
		Dob = dob;
		Address = address;
		this.cname = cname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return Fname;
	}

	public void setFname(String fname) {
		Fname = fname;
	}

	public String getLname() {
		return Lname;
	}

	public void setLname(String lname) {
		Lname = lname;
	}

	public String getDob() {
		return Dob;
	}

	public void setDob(String dob) {
		Dob = dob;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

		
	
	
}
