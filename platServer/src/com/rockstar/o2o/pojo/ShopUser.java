package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ShopUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "shop_user")
public class ShopUser implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkShopuser;
	private Long pkUser;
	private Long pkShop;
	private String usertype;
	private String cellphone;
	private String shopusercode;
	private String shopusername;
	private String loginpassword;
	private String islock;
	private String registertime;
	private String startime;
	private String endtime;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public ShopUser() {
	}

	/** full constructor */
	public ShopUser(Long pkUser, Long pkShop, String cellphone,String registertime,
			String shopusercode, String shopusername, String loginpassword,String usertype,
			String islock, String startime, String endtime, String ts, Short dr) {
		this.pkUser = pkUser;
		this.pkShop = pkShop;
		this.cellphone = cellphone;
		this.shopusercode = shopusercode;
		this.usertype = usertype;
		this.shopusername = shopusername;
		this.loginpassword = loginpassword;
		this.registertime = registertime;
		this.islock = islock;
		this.startime = startime;
		this.endtime = endtime;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_shopuser", unique = true, nullable = false)
	public Long getPkShopuser() {
		return this.pkShopuser;
	}

	public void setPkShopuser(Long pkShopuser) {
		this.pkShopuser = pkShopuser;
	}

	@Column(name = "pk_user")
	public Long getPkUser() {
		return this.pkUser;
	}

	public void setPkUser(Long pkUser) {
		this.pkUser = pkUser;
	}

	@Column(name = "pk_shop")
	public Long getPkShop() {
		return this.pkShop;
	}

	public void setPkShop(Long pkShop) {
		this.pkShop = pkShop;
	}

	@Column(name = "cellphone", length = 11)
	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Column(name = "shopusercode", length = 30)
	public String getShopusercode() {
		return this.shopusercode;
	}

	public void setShopusercode(String shopusercode) {
		this.shopusercode = shopusercode;
	}

	@Column(name = "shopusername", length = 50)
	public String getShopusername() {
		return this.shopusername;
	}

	public void setShopusername(String shopusername) {
		this.shopusername = shopusername;
	}

	@Column(name = "loginpassword", length = 100)
	public String getLoginpassword() {
		return this.loginpassword;
	}

	public void setLoginpassword(String loginpassword) {
		this.loginpassword = loginpassword;
	}
	@Column(name = "registertime", length = 19)
	public String getRegistertime() {
		return registertime;
	}

	public void setRegistertime(String registertime) {
		this.registertime = registertime;
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