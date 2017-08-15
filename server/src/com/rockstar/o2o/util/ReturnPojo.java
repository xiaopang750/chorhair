package com.rockstar.o2o.util;

public class ReturnPojo {
	
	private boolean issuccess;
	private String msg;
	private String code;
	private String data;
	private Integer totalcount;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(Integer totalcount) {
		this.totalcount = totalcount;
	}
	public boolean isIssuccess() {
		return issuccess;
	}
	public void setIssuccess(boolean issuccess) {
		this.issuccess = issuccess;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}


	
}
