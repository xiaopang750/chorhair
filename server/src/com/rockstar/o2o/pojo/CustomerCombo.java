package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CustomerCombo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customer_combo")
public class CustomerCombo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkCustomerCombo;
	private Long pkCustomer;
	private Long pkShopCombo;
	private String userphoto;
	private String usershortphoto;
	private String combotype;
	private String fairtype;
	private String comboname;
	private String lastusetime;
	private Long lastfairer;
	private String lastfairername;
	private Double lastmoney;
	private Double discount;
	private Integer totalcount;
	private Integer leftcount;
	private Long pkFairer;
	private String fairername;
	private String commissionpeople;
	private Double combomoney;
	private String computetimetype;
	private String combobuytime;
	private String combobegintime;
	private String comboendtime;
	private Double price;
	private String isvalid;
	private String iscomplete;
	private String combofrom;
	private String combodetail;
	private String awards;
	private Double awardvalue;
	private String ts;
	private Short dr;
	private String note;

	// Constructors

	/** default constructor */
	public CustomerCombo() {
	}

	/** full constructor */
	public CustomerCombo(Long pkCustomer, Long pkShopCombo, String userphoto,
			String usershortphoto, String combotype, String fairtype,
			String comboname, String lastusetime, Long lastfairer,
			String lastfairername, Double lastmoney, Double discount,
			Integer totalcount, Integer leftcount, Long pkFairer,
			String fairername, String commissionpeople, Double combomoney,
			String computetimetype, String combobuytime, String combobegintime,
			String comboendtime, Double price, String isvalid,
			String iscomplete, String combofrom, String combodetail,
			String awards, Double awardvalue, String ts, Short dr, String note) {
		this.pkCustomer = pkCustomer;
		this.pkShopCombo = pkShopCombo;
		this.userphoto = userphoto;
		this.usershortphoto = usershortphoto;
		this.combotype = combotype;
		this.fairtype = fairtype;
		this.comboname = comboname;
		this.lastusetime = lastusetime;
		this.lastfairer = lastfairer;
		this.lastfairername = lastfairername;
		this.lastmoney = lastmoney;
		this.discount = discount;
		this.totalcount = totalcount;
		this.leftcount = leftcount;
		this.pkFairer = pkFairer;
		this.fairername = fairername;
		this.commissionpeople = commissionpeople;
		this.combomoney = combomoney;
		this.computetimetype = computetimetype;
		this.combobuytime = combobuytime;
		this.combobegintime = combobegintime;
		this.comboendtime = comboendtime;
		this.price = price;
		this.isvalid = isvalid;
		this.iscomplete = iscomplete;
		this.combofrom = combofrom;
		this.combodetail = combodetail;
		this.awards = awards;
		this.awardvalue = awardvalue;
		this.ts = ts;
		this.dr = dr;
		this.note = note;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_customer_combo", unique = true, nullable = false)
	public Long getPkCustomerCombo() {
		return this.pkCustomerCombo;
	}

	public void setPkCustomerCombo(Long pkCustomerCombo) {
		this.pkCustomerCombo = pkCustomerCombo;
	}

	@Column(name = "pk_customer")
	public Long getPkCustomer() {
		return this.pkCustomer;
	}

	public void setPkCustomer(Long pkCustomer) {
		this.pkCustomer = pkCustomer;
	}

	@Column(name = "pk_shop_combo")
	public Long getPkShopCombo() {
		return this.pkShopCombo;
	}

	public void setPkShopCombo(Long pkShopCombo) {
		this.pkShopCombo = pkShopCombo;
	}

	@Column(name = "userphoto")
	public String getUserphoto() {
		return this.userphoto;
	}

	public void setUserphoto(String userphoto) {
		this.userphoto = userphoto;
	}

	@Column(name = "usershortphoto")
	public String getUsershortphoto() {
		return this.usershortphoto;
	}

	public void setUsershortphoto(String usershortphoto) {
		this.usershortphoto = usershortphoto;
	}

	@Column(name = "combotype", length = 30)
	public String getCombotype() {
		return this.combotype;
	}

	public void setCombotype(String combotype) {
		this.combotype = combotype;
	}

	@Column(name = "fairtype", length = 30)
	public String getFairtype() {
		return this.fairtype;
	}

	public void setFairtype(String fairtype) {
		this.fairtype = fairtype;
	}

	@Column(name = "comboname", length = 100)
	public String getComboname() {
		return this.comboname;
	}

	public void setComboname(String comboname) {
		this.comboname = comboname;
	}

	@Column(name = "lastusetime", length = 19)
	public String getLastusetime() {
		return this.lastusetime;
	}

	public void setLastusetime(String lastusetime) {
		this.lastusetime = lastusetime;
	}

	@Column(name = "lastfairer")
	public Long getLastfairer() {
		return this.lastfairer;
	}

	public void setLastfairer(Long lastfairer) {
		this.lastfairer = lastfairer;
	}

	@Column(name = "lastfairername")
	public String getLastfairername() {
		return this.lastfairername;
	}

	public void setLastfairername(String lastfairername) {
		this.lastfairername = lastfairername;
	}

	@Column(name = "lastmoney", precision = 20)
	public Double getLastmoney() {
		return this.lastmoney;
	}

	public void setLastmoney(Double lastmoney) {
		this.lastmoney = lastmoney;
	}

	@Column(name = "discount", precision = 20)
	public Double getDiscount() {
		return this.discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	@Column(name = "totalcount")
	public Integer getTotalcount() {
		return this.totalcount;
	}

	public void setTotalcount(Integer totalcount) {
		this.totalcount = totalcount;
	}

	@Column(name = "leftcount")
	public Integer getLeftcount() {
		return this.leftcount;
	}

	public void setLeftcount(Integer leftcount) {
		this.leftcount = leftcount;
	}

	@Column(name = "pk_fairer")
	public Long getPkFairer() {
		return this.pkFairer;
	}

	public void setPkFairer(Long pkFairer) {
		this.pkFairer = pkFairer;
	}

	@Column(name = "fairername")
	public String getFairername() {
		return this.fairername;
	}

	public void setFairername(String fairername) {
		this.fairername = fairername;
	}

	@Column(name = "commissionpeople")
	public String getCommissionpeople() {
		return this.commissionpeople;
	}

	public void setCommissionpeople(String commissionpeople) {
		this.commissionpeople = commissionpeople;
	}

	@Column(name = "combomoney", precision = 20)
	public Double getCombomoney() {
		return this.combomoney;
	}

	public void setCombomoney(Double combomoney) {
		this.combomoney = combomoney;
	}

	@Column(name = "computetimetype", length = 30)
	public String getComputetimetype() {
		return this.computetimetype;
	}

	public void setComputetimetype(String computetimetype) {
		this.computetimetype = computetimetype;
	}

	@Column(name = "combobuytime", length = 19)
	public String getCombobuytime() {
		return this.combobuytime;
	}

	public void setCombobuytime(String combobuytime) {
		this.combobuytime = combobuytime;
	}

	@Column(name = "combobegintime", length = 19)
	public String getCombobegintime() {
		return this.combobegintime;
	}

	public void setCombobegintime(String combobegintime) {
		this.combobegintime = combobegintime;
	}

	@Column(name = "comboendtime", length = 19)
	public String getComboendtime() {
		return this.comboendtime;
	}

	public void setComboendtime(String comboendtime) {
		this.comboendtime = comboendtime;
	}

	@Column(name = "price", precision = 20)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "isvalid", length = 1)
	public String getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}

	@Column(name = "iscomplete", length = 1)
	public String getIscomplete() {
		return this.iscomplete;
	}

	public void setIscomplete(String iscomplete) {
		this.iscomplete = iscomplete;
	}

	@Column(name = "combofrom", length = 50)
	public String getCombofrom() {
		return this.combofrom;
	}

	public void setCombofrom(String combofrom) {
		this.combofrom = combofrom;
	}

	@Column(name = "combodetail")
	public String getCombodetail() {
		return this.combodetail;
	}

	public void setCombodetail(String combodetail) {
		this.combodetail = combodetail;
	}

	@Column(name = "awards")
	public String getAwards() {
		return this.awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	@Column(name = "awardvalue", precision = 20)
	public Double getAwardvalue() {
		return this.awardvalue;
	}

	public void setAwardvalue(Double awardvalue) {
		this.awardvalue = awardvalue;
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

	@Column(name = "note")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}