package com.bean;

public class ClassBean {
	
	private int cid;
	private String cname;
	
	
	public ClassBean() {
		
	}


	public ClassBean(int cid, String cname) {
		super();
		this.cid = cid;
		this.cname = cname;
	}


	public ClassBean(String cname) {
		super();
		this.cname = cname;
	}


	public int getCid() {
		return cid;
	}


	public void setCid(int cid) {
		this.cid = cid;
	}


	public String getCname() {
		return cname;
	}


	public void setCname(String cname) {
		this.cname = cname;
	}


	@Override
	public String toString() {
		return "ClassBean [cid=" + cid + ", cname=" + cname + "]";
	}
	
	
	
}
