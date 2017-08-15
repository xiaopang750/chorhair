package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CustomerInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customer_info")
public class CustomerInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkCustomer;
	private Long pkShop;
	private Long pkUser;
	private String headimageurl;
	private String headshorturl;
	private String customerfrom;
	private String customercode;
	private String customername;
	private String customerstatus;
	private String nickname;
	private String sex;
	private String birthday;
	private Double accountmoney;
	private Integer integral;
	private String cellphone;
	private String fixphone;
	private String province;
	private String city;
	private String county;
	private String provincename;
	private String cityname;
	private String countyname;
	private String addr;
	private String introducer;
	private String lasttime;
	private String rank;
	private String registertime;
	private String note;
	private Long operator;
	private String operatime;
	private String  wxopenid;
	private String ts;
	private Short dr;
	private String py;
	private String shortpy;

	// Constructors

	/** default constructor */
	public CustomerInfo() {
	}

	/** full constructor */
	public CustomerInfo(Long pkShop, Long pkUser, String headimageurl,
			String headshorturl, String customerfrom, String customercode,
			String customername,String  customerstatus,String nickname, String sex, String birthday,
			Double accountmoney, Integer integral, String cellphone,
			String fixphone, String province, String city, String county,
			String provincename, String cityname,String countyname,
			String addr, String introducer, String lasttime, String rank,
			String registertime, String note, Long operator, String operatime,String wxopenid,
			String ts, Short dr, String py, String shortpy) {
		this.pkShop = pkShop;
		this.pkUser = pkUser;
		this.headimageurl = headimageurl;
		this.headshorturl = headshorturl;
		this.customerfrom = customerfrom;
		this.customercode = customercode;
		this.customername = customername;
		this.customerstatus = customerstatus;
		this.nickname = nickname;
		this.sex = sex;
		this.birthday = birthday;
		this.accountmoney = accountmoney;
		this.integral = integral;
		this.cellphone = cellphone;
		this.fixphone = fixphone;
		this.province = province;
		this.city = city;
		this.county = county;
		this.provincename = provincename;
		this.cityname = cityname;
		this.countyname = countyname;
		this.addr = addr;
		this.introducer = introducer;
		this.lasttime = lasttime;
		this.rank = rank;
		this.registertime = registertime;
		this.note = note;
		this.operator = operator;
		this.operatime = operatime;
		this.wxopenid = wxopenid;
		this.ts = ts;
		this.dr = dr;
		this.py = py;
		this.shortpy = shortpy;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_customer", unique = true, nullable = false)
	public Long getPkCustomer() {
		return this.pkCustomer;
	}

	public void setPkCustomer(Long pkCustomer) {
		this.pkCustomer = pkCustomer;
	}

	@Column(name = "pk_shop")
	public Long getPkShop() {
		return this.pkShop;
	}

	public void setPkShop(Long pkShop) {
		this.pkShop = pkShop;
	}

	@Column(name = "pk_user")
	public Long getPkUser() {
		return this.pkUser;
	}

	public void setPkUser(Long pkUser) {
		this.pkUser = pkUser;
	}

	@Column(name = "headimageurl", length = 200)
	public String getHeadimageurl() {
		return this.headimageurl;
	}

	public void setHeadimageurl(String headimageurl) {
		this.headimageurl = headimageurl;
	}

	@Column(name = "headshorturl", length = 200)
	public String getHeadshorturl() {
		return this.headshorturl;
	}

	public void setHeadshorturl(String headshorturl) {
		this.headshorturl = headshorturl;
	}

	@Column(name = "customerfrom", length = 10)
	public String getCustomerfrom() {
		return this.customerfrom;
	}

	public void setCustomerfrom(String customerfrom) {
		this.customerfrom = customerfrom;
	}

	@Column(name = "customercode", length = 30)
	public String getCustomercode() {
		return this.customercode;
	}

	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}

	@Column(name = "customername", length = 50)
	public String getCustomername() {
		return this.customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	@Column(name = "customerstatus", length = 50)
	public String getCustomerstatus() {
		return this.customerstatus;
	}

	public void setCustomerstatus(String customerstatus) {
		this.customerstatus = customerstatus;
	}

	@Column(name = "nickname", length = 50)
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "sex", length = 10)
	public String getSex() {
		return this.sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "birthday", length = 10)
	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Column(name = "accountmoney", precision = 20)
	public Double getAccountmoney() {
		return this.accountmoney;
	}

	public void setAccountmoney(Double accountmoney) {
		this.accountmoney = accountmoney;
	}

	@Column(name = "integral")
	public Integer getIntegral() {
		return this.integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	@Column(name = "cellphone", length = 11)
	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Column(name = "fixphone", length = 20)
	public String getFixphone() {
		return this.fixphone;
	}

	public void setFixphone(String fixphone) {
		this.fixphone = fixphone;
	}

	@Column(name = "province", length = 30)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "city", length = 30)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "county", length = 30)
	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}
	@Column(name = "provincename")
	public String getProvincename() {
		return provincename;
	}
	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}
	@Column(name = "cityname")
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	@Column(name = "countyname")
	public String getCountyname() {
		return countyname;
	}
	public void setCountyname(String countyname) {
		this.countyname = countyname;
	}
	@Column(name = "addr")
	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Column(name = "introducer")
	public String getIntroducer() {
		return this.introducer;
	}

	public void setIntroducer(String introducer) {
		this.introducer = introducer;
	}

	@Column(name = "lasttime", length = 19)
	public String getLasttime() {
		return this.lasttime;
	}

	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}

	@Column(name = "rank", length = 30)
	public String getRank() {
		return this.rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	@Column(name = "registertime", length = 19)
	public String getRegistertime() {
		return this.registertime;
	}

	public void setRegistertime(String registertime) {
		this.registertime = registertime;
	}

	@Column(name = "note")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "operator")
	public Long getOperator() {
		return this.operator;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
	}

	@Column(name = "operatime", length = 19)
	public String getOperatime() {
		return this.operatime;
	}

	public void setOperatime(String operatime) {
		this.operatime = operatime;
	}

	@Column(name = "wxopenid")
	public String getWxopenid() {
		return this.wxopenid;
	}

	public void setWxopenid(String wxopenid) {
		this.wxopenid = wxopenid;
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

	@Column(name = "py")
	public String getPy() {
		return this.py;
	}

	public void setPy(String py) {
		this.py = py;
	}

	@Column(name = "shortpy")
	public String getShortpy() {
		return this.shortpy;
	}

	public void setShortpy(String shortpy) {
		this.shortpy = shortpy;
	}

}