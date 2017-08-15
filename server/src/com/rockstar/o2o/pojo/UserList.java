package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_list")
public class UserList implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Long pkUser;
	private String userid;
	private String username;
	private String userfrom;
	private String nickname;
	private String headurl;
	private String headshorturl;
	private String sex;
	private String cellphone;
	private String loginpassword;
	private String usergroup;
	private String province;
	private String city;
	private String county;
	private String addr;
	private String userstatus;
	private Integer scancount;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public UserList() {
	}

	/** full constructor */
	public UserList(String userid, String username,String userfrom, String nickname,
			String headurl, String headshorturl, String sex, String cellphone,
			String loginpassword, String usergroup, String province,
			String city, String county, String addr, String userstatus,
			Integer scancount, String ts, Short dr) {
		this.userid = userid;
		this.username = username;
		this.userfrom = userfrom;
		this.nickname = nickname;
		this.headurl = headurl;
		this.headshorturl = headshorturl;
		this.sex = sex;
		this.cellphone = cellphone;
		this.loginpassword = loginpassword;
		this.usergroup = usergroup;
		this.province = province;
		this.city = city;
		this.county = county;
		this.addr = addr;
		this.userstatus = userstatus;
		this.scancount = scancount;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_user", unique = true, nullable = false)
	public Long getPkUser() {
		return this.pkUser;
	}

	public void setPkUser(Long pkUser) {
		this.pkUser = pkUser;
	}

	@Column(name = "userid", length = 30)
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "username", length = 50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "nickname", length = 50)
	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "headurl")
	public String getHeadurl() {
		return this.headurl;
	}

	public void setHeadurl(String headurl) {
		this.headurl = headurl;
	}

	@Column(name = "headshorturl")
	public String getHeadshorturl() {
		return this.headshorturl;
	}

	public void setHeadshorturl(String headshorturl) {
		this.headshorturl = headshorturl;
	}

	@Column(name = "sex", length = 10)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "cellphone", length = 11)
	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Column(name = "loginpassword", length = 100)
	public String getLoginpassword() {
		return this.loginpassword;
	}

	public void setLoginpassword(String loginpassword) {
		this.loginpassword = loginpassword;
	}

	@Column(name = "usergroup", length = 20)
	public String getUsergroup() {
		return this.usergroup;
	}

	public void setUsergroup(String usergroup) {
		this.usergroup = usergroup;
	}

	@Column(name = "province", length = 50)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "city", length = 50)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "county", length = 50)
	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	@Column(name = "addr")
	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Column(name = "userstatus", length = 10)
	public String getUserstatus() {
		return this.userstatus;
	}

	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}

	@Column(name = "scancount")
	public Integer getScancount() {
		return this.scancount;
	}

	public void setScancount(Integer scancount) {
		this.scancount = scancount;
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

	@Column(name = "userfrom")
	public String getUserfrom() {
		return userfrom;
	}

	public void setUserfrom(String userfrom) {
		this.userfrom = userfrom;
	}

	
}