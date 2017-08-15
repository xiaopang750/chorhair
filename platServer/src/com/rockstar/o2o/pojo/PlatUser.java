package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "plat_user")
public class PlatUser implements java.io.Serializable  {
	
	private static final long serialVersionUID = 1L;
	private Long pkPlatuser;
	private Long pkUser;
	private String usertype;
	private String cellphone;
	private String platusercode;
	private String platusername;
	private String loginpassword;
	private String islock;
	private String startime;
	private String endtime;
	private String ts;
	private Short dr;	
	
	// Constructors
	/** default constructor */
	public PlatUser() {
		super();
	}
	
	/** full constructor */
	public PlatUser(Long pkUser,String usertype,
			String cellphone, String platusercode, String platusername,
			String loginpassword, String islock, String startime,
			String endtime, String ts, Short dr) {
		this.pkUser = pkUser;
		this.usertype = usertype;
		this.cellphone = cellphone;
		this.platusercode = platusercode;
		this.platusername = platusername;
		this.loginpassword = loginpassword;
		this.islock = islock;
		this.startime = startime;
		this.endtime = endtime;
		this.ts = ts;
		this.dr = dr;
	}
	
	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_platuser", unique = true, nullable = false)
	public Long getPkPlatuser() {
		return this.pkPlatuser;
	}

	public void setPkPlatuser(Long pkPlatuser) {
		this.pkPlatuser = pkPlatuser;
	}

	@Column(name = "pk_user")
	public Long getPkUser() {
		return this.pkUser;
	}

	public void setPkUser(Long pkUser) {
		this.pkUser = pkUser;
	}
	
	@Column(name = "cellphone", length = 11)
	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Column(name = "platusercode", length = 30)
	public String getPlatusercode() {
		return this.platusercode;
	}

	public void setPlatusercode(String platusercode) {
		this.platusercode = platusercode;
	}

	@Column(name = "platusername", length = 50)
	public String getPlatusername() {
		return this.platusername;
	}

	public void setPlatusername(String platusername) {
		this.platusername = platusername;
	}

	@Column(name = "loginpassword", length = 100)
	public String getLoginpassword() {
		return this.loginpassword;
	}

	public void setLoginpassword(String loginpassword) {
		this.loginpassword = loginpassword;
	}

	@Column(name = "islock", length = 1)
	public String getIslock() {
		return this.islock;
	}

	public void setIslock(String islock) {
		this.islock = islock;
	}

	@Column(name = "startime", length = 19)
	public String getStartime() {
		return this.startime;
	}

	public void setStartime(String startime) {
		this.startime = startime;
	}

	@Column(name = "endtime", length = 19)
	public String getEndtime() {
		return this.endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	@Column(name = "usertype")
	public String getUsertype() {
		return this.usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
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
