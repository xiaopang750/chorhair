package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FairerInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fairer_info")
public class FairerInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkFairer;
	private Long pkUser;
	private Long pkShop;
	private String headimageurl;
	private String headshorturl;
	private String fairercode;
	private String nickname;
	private String fairername;
	private String fixphone;
	private String cellphone;
	private String birthday;
	private Integer age;
	private String sex;
	private String status;
	private String isvalidate;
	private String superior;
	private String rankname;
	private Integer servicenum;
	private Double servicemoney;
	private Double accountmoney;
	private String registertime;
	private String identitycard;
	private String fairerfrom;
	private String province;
	private String city;
	private String county;
	private String provincename;
	private String cityname;
	private String countyname;
	private String addr;
	private String note;
	private String urgencypeople;
	private String nativeplace;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public FairerInfo() {
	}

	/** full constructor */
	public FairerInfo(Long pkUser, Long pkShop, String headimageurl,
			String headshorturl, String fairercode, String nickname,
			String fairername, String fixphone, String cellphone,
			String birthday, Integer age, String sex, String status,
			String isvalidate, String superior, String rankname,
			Integer servicenum, Double servicemoney, Double accountmoney,
			String registertime, String identitycard, String fairerfrom,
			String province, String city, String county,String provincename, String cityname,
			String countyname, String addr, String note,
			String urgencypeople, String nativeplace, String ts, Short dr) {
		this.pkUser = pkUser;
		this.pkShop = pkShop;
		this.headimageurl = headimageurl;
		this.headshorturl = headshorturl;
		this.fairercode = fairercode;
		this.nickname = nickname;
		this.fairername = fairername;
		this.fixphone = fixphone;
		this.cellphone = cellphone;
		this.birthday = birthday;
		this.age = age;
		this.sex = sex;
		this.status = status;
		this.isvalidate = isvalidate;
		this.superior = superior;
		this.rankname = rankname;
		this.servicenum = servicenum;
		this.servicemoney = servicemoney;
		this.accountmoney = accountmoney;
		this.registertime = registertime;
		this.identitycard = identitycard;
		this.fairerfrom = fairerfrom;
		this.province = province;
		this.city = city;
		this.county = county;
		this.provincename = provincename;
		this.cityname = cityname;
		this.countyname = countyname;
		this.addr = addr;
		this.note = note;
		this.urgencypeople = urgencypeople;
		this.nativeplace = nativeplace;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_fairer", unique = true, nullable = false)
	public Long getPkFairer() {
		return this.pkFairer;
	}

	public void setPkFairer(Long pkFairer) {
		this.pkFairer = pkFairer;
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

	@Column(name = "fairercode", length = 30)
	public String getFairercode() {
		return this.fairercode;
	}

	public void setFairercode(String fairercode) {
		this.fairercode = fairercode;
	}

	@Column(name = "nickname", length = 50)
	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "fairername", length = 50)
	public String getFairername() {
		return this.fairername;
	}

	public void setFairername(String fairername) {
		this.fairername = fairername;
	}

	@Column(name = "fixphone", length = 20)
	public String getFixphone() {
		return this.fixphone;
	}

	public void setFixphone(String fixphone) {
		this.fixphone = fixphone;
	}

	@Column(name = "cellphone", length = 11)
	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Column(name = "birthday", length = 10)
	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Column(name = "age")
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "sex", length = 10)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "status", length = 30)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "isvalidate", length = 1)
	public String getIsvalidate() {
		return this.isvalidate;
	}

	public void setIsvalidate(String isvalidate) {
		this.isvalidate = isvalidate;
	}

	@Column(name = "superior", length = 30)
	public String getSuperior() {
		return this.superior;
	}

	public void setSuperior(String superior) {
		this.superior = superior;
	}

	@Column(name = "rankname", length = 30)
	public String getRankname() {
		return this.rankname;
	}

	public void setRankname(String rankname) {
		this.rankname = rankname;
	}

	@Column(name = "servicenum")
	public Integer getServicenum() {
		return this.servicenum;
	}

	public void setServicenum(Integer servicenum) {
		this.servicenum = servicenum;
	}

	@Column(name = "servicemoney", precision = 20)
	public Double getServicemoney() {
		return this.servicemoney;
	}

	public void setServicemoney(Double servicemoney) {
		this.servicemoney = servicemoney;
	}

	@Column(name = "accountmoney", precision = 20)
	public Double getAccountmoney() {
		return this.accountmoney;
	}

	public void setAccountmoney(Double accountmoney) {
		this.accountmoney = accountmoney;
	}

	@Column(name = "registertime", length = 19)
	public String getRegistertime() {
		return this.registertime;
	}

	public void setRegistertime(String registertime) {
		this.registertime = registertime;
	}

	@Column(name = "identitycard", length = 20)
	public String getIdentitycard() {
		return this.identitycard;
	}

	public void setIdentitycard(String identitycard) {
		this.identitycard = identitycard;
	}

	@Column(name = "fairerfrom", length = 20)
	public String getFairerfrom() {
		return this.fairerfrom;
	}

	public void setFairerfrom(String fairerfrom) {
		this.fairerfrom = fairerfrom;
	}

	@Column(name = "province")
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "city")
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "county")
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

	@Column(name = "note")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "urgencypeople", length = 300)
	public String getUrgencypeople() {
		return this.urgencypeople;
	}

	public void setUrgencypeople(String urgencypeople) {
		this.urgencypeople = urgencypeople;
	}

	@Column(name = "nativeplace")
	public String getNativeplace() {
		return this.nativeplace;
	}

	public void setNativeplace(String nativeplace) {
		this.nativeplace = nativeplace;
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