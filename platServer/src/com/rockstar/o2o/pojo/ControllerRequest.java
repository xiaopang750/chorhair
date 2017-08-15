package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ControllerRequest entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "controller_request")
public class ControllerRequest implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkControllerRequest;
	private Long pkUser;
	private String userid;
	private String controllerpath;
	private String requesttime;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public ControllerRequest() {
	}

	/** full constructor */
	public ControllerRequest(Long pkUser, String userid, String controllerpath,
			String requesttime, String ts, Short dr) {
		this.pkUser = pkUser;
		this.userid = userid;
		this.controllerpath = controllerpath;
		this.requesttime = requesttime;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_controller_request", unique = true, nullable = false)
	public Long getPkControllerRequest() {
		return this.pkControllerRequest;
	}

	public void setPkControllerRequest(Long pkControllerRequest) {
		this.pkControllerRequest = pkControllerRequest;
	}

	@Column(name = "pk_user")
	public Long getPkUser() {
		return this.pkUser;
	}

	public void setPkUser(Long pkUser) {
		this.pkUser = pkUser;
	}

	@Column(name = "userid", length = 100)
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "controllerpath")
	public String getControllerpath() {
		return this.controllerpath;
	}

	public void setControllerpath(String controllerpath) {
		this.controllerpath = controllerpath;
	}

	@Column(name = "requesttime", length = 19)
	public String getRequesttime() {
		return this.requesttime;
	}

	public void setRequesttime(String requesttime) {
		this.requesttime = requesttime;
	}

	@Column(name = "ts", length = 19)
	public String getTs() {
		return this.ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	@Column(name = "dr")
	public Short getDr() {
		return this.dr;
	}

	public void setDr(Short dr) {
		this.dr = dr;
	}

}