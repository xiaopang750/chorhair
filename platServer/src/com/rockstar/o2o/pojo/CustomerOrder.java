package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CustomerOrder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customer_order")
public class CustomerOrder implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkOrder;
	private Long pkCustomer;
	private String orderno;
	private String ordercontent;
	private String customername;
	private String ordetime;
	private String appointtime;
	private String completetime;
	private String paytime;
	private String paystatus;
	private Double ordermoney;
	private Double combomoney;
	private String servicetype;
	private String paymode;
	private Double realmoney;
	private String iscombo;
	private String awards;
	private Double awardvalue;
	private Double discount;
	private String cellphone;
	private String orderstatus;
	private String province;
	private String city;
	private String county;
	private String provincename;
	private String cityname;
	private String countyname;
	private String addr;
	private Long pkShop;
	private String shopname;
	private Long pkFairer;
	private Long pkShopCombo;
	private Long pkCombo;
	private Long pkCustomerCombo;
	private String comboname;
	private String fairername;
	private Long pkPrice;
	private Double servicemoney;
	private Double fairercomboservicemoney;
	private Double fairerservicemoney;
	private Double faireraddmoney;
	private Double fairermoney;
	private String commissionpeople;
	private String isautoevaluate;
	private Double evaluategoal;
	private String evaluatenote;
	private String evaluatetime;
	private String introduceman;
	private String isbeauty;
	private Integer beautypraisecount;
	private Integer beautycommentcount;
	private Long operatorpk;
	private String operator;
	private String orderfrom;
	private String ts;
	private Short dr;
	private String nocontainhair;
	private String isfreeservice;
	private String cancelreason;

	// Constructors

	/** default constructor */
	public CustomerOrder() {
	}

	/** full constructor */
	public CustomerOrder(Long pkCustomer, String orderno, String ordercontent,
			String customername, String ordetime, String appointtime,
			String completetime, String paytime, String paystatus,
			Double ordermoney, Double combomoney, String servicetype,
			String paymode, Double realmoney, String iscombo, String awards,
			Double awardvalue, Double discount, String cellphone,
			String orderstatus,  String province, String city, String county,
			String provincename, String cityname,String countyname, String addr, Long pkShop,
			String shopname, Long pkFairer, Long pkShopCombo, Long pkCombo,
			Long pkCustomerCombo, String comboname, String fairername,
			Long pkPrice, Double servicemoney, Double fairercomboservicemoney,
			Double fairerservicemoney, Double faireraddmoney,
			Double fairermoney, String commissionpeople, String isautoevaluate,
			Double evaluategoal, String evaluatenote, String evaluatetime,
			String introduceman, String isbeauty, Integer beautypraisecount,
			Integer beautycommentcount, Long operatorpk, String operator,String orderfrom,
			String ts, Short dr, String nocontainhair, String isfreeservice,String cancelreason) {
		this.pkCustomer = pkCustomer;
		this.orderno = orderno;
		this.ordercontent = ordercontent;
		this.customername = customername;
		this.ordetime = ordetime;
		this.appointtime = appointtime;
		this.completetime = completetime;
		this.paytime = paytime;
		this.paystatus = paystatus;
		this.ordermoney = ordermoney;
		this.combomoney = combomoney;
		this.servicetype = servicetype;
		this.paymode = paymode;
		this.realmoney = realmoney;
		this.iscombo = iscombo;
		this.awards = awards;
		this.awardvalue = awardvalue;
		this.discount = discount;
		this.cellphone = cellphone;
		this.orderstatus = orderstatus;
		this.province = province;
		this.city = city;
		this.county = county;
		this.provincename = provincename;
		this.cityname = cityname;
		this.countyname = countyname;
		this.addr = addr;
		this.pkShop = pkShop;
		this.shopname = shopname;
		this.pkFairer = pkFairer;
		this.pkShopCombo = pkShopCombo;
		this.pkCombo = pkCombo;
		this.pkCustomerCombo = pkCustomerCombo;
		this.comboname = comboname;
		this.fairername = fairername;
		this.pkPrice = pkPrice;
		this.servicemoney = servicemoney;
		this.fairercomboservicemoney = fairercomboservicemoney;
		this.fairerservicemoney = fairerservicemoney;
		this.faireraddmoney = faireraddmoney;
		this.fairermoney = fairermoney;
		this.commissionpeople = commissionpeople;
		this.isautoevaluate = isautoevaluate;
		this.evaluategoal = evaluategoal;
		this.evaluatenote = evaluatenote;
		this.evaluatetime = evaluatetime;
		this.introduceman = introduceman;
		this.isbeauty = isbeauty;
		this.beautypraisecount = beautypraisecount;
		this.beautycommentcount = beautycommentcount;
		this.operatorpk = operatorpk;
		this.operator = operator;
		this.orderfrom = orderfrom;
		this.ts = ts;
		this.dr = dr;
		this.nocontainhair = nocontainhair;
		this.isfreeservice = isfreeservice;
		this.cancelreason = cancelreason;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_order", unique = true, nullable = false)
	public Long getPkOrder() {
		return this.pkOrder;
	}

	public void setPkOrder(Long pkOrder) {
		this.pkOrder = pkOrder;
	}

	@Column(name = "pk_customer")
	public Long getPkCustomer() {
		return this.pkCustomer;
	}

	public void setPkCustomer(Long pkCustomer) {
		this.pkCustomer = pkCustomer;
	}

	@Column(name = "orderno", length = 20)
	public String getOrderno() {
		return this.orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	@Column(name = "ordercontent")
	public String getOrdercontent() {
		return this.ordercontent;
	}

	public void setOrdercontent(String ordercontent) {
		this.ordercontent = ordercontent;
	}

	@Column(name = "customername", length = 30)
	public String getCustomername() {
		return this.customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	@Column(name = "ordetime", length = 19)
	public String getOrdetime() {
		return this.ordetime;
	}

	public void setOrdetime(String ordetime) {
		this.ordetime = ordetime;
	}

	@Column(name = "appointtime", length = 19)
	public String getAppointtime() {
		return this.appointtime;
	}

	public void setAppointtime(String appointtime) {
		this.appointtime = appointtime;
	}

	@Column(name = "completetime", length = 19)
	public String getCompletetime() {
		return this.completetime;
	}

	public void setCompletetime(String completetime) {
		this.completetime = completetime;
	}

	@Column(name = "paytime", length = 19)
	public String getPaytime() {
		return this.paytime;
	}

	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}

	@Column(name = "paystatus", length = 20)
	public String getPaystatus() {
		return this.paystatus;
	}

	public void setPaystatus(String paystatus) {
		this.paystatus = paystatus;
	}

	@Column(name = "ordermoney", precision = 20)
	public Double getOrdermoney() {
		return this.ordermoney;
	}

	public void setOrdermoney(Double ordermoney) {
		this.ordermoney = ordermoney;
	}

	@Column(name = "combomoney", precision = 20)
	public Double getCombomoney() {
		return this.combomoney;
	}

	public void setCombomoney(Double combomoney) {
		this.combomoney = combomoney;
	}

	@Column(name = "servicetype", length = 20)
	public String getServicetype() {
		return this.servicetype;
	}

	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}

	@Column(name = "paymode", length = 20)
	public String getPaymode() {
		return this.paymode;
	}

	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}

	@Column(name = "realmoney", precision = 20)
	public Double getRealmoney() {
		return this.realmoney;
	}

	public void setRealmoney(Double realmoney) {
		this.realmoney = realmoney;
	}

	@Column(name = "iscombo", length = 1)
	public String getIscombo() {
		return this.iscombo;
	}

	public void setIscombo(String iscombo) {
		this.iscombo = iscombo;
	}

	@Column(name = "awards")
	public String getAwards() {
		return this.awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	@Column(name = "awardvalue", length = 50)
	public Double getAwardvalue() {
		return this.awardvalue;
	}

	public void setAwardvalue(Double awardvalue) {
		this.awardvalue = awardvalue;
	}

	@Column(name = "discount", precision = 20)
	public Double getDiscount() {
		return this.discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	@Column(name = "cellphone", length = 11)
	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Column(name = "orderstatus", length = 20)
	public String getOrderstatus() {
		return this.orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
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

	@Column(name = "pk_shop")
	public Long getPkShop() {
		return this.pkShop;
	}

	public void setPkShop(Long pkShop) {
		this.pkShop = pkShop;
	}

	@Column(name = "shopname", length = 50)
	public String getShopname() {
		return this.shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	@Column(name = "pk_fairer")
	public Long getPkFairer() {
		return this.pkFairer;
	}

	public void setPkFairer(Long pkFairer) {
		this.pkFairer = pkFairer;
	}

	@Column(name = "pk_shop_combo")
	public Long getPkShopCombo() {
		return this.pkShopCombo;
	}

	public void setPkShopCombo(Long pkShopCombo) {
		this.pkShopCombo = pkShopCombo;
	}

	@Column(name = "pk_combo")
	public Long getPkCombo() {
		return this.pkCombo;
	}

	public void setPkCombo(Long pkCombo) {
		this.pkCombo = pkCombo;
	}

	@Column(name = "pk_customer_combo")
	public Long getPkCustomerCombo() {
		return this.pkCustomerCombo;
	}

	public void setPkCustomerCombo(Long pkCustomerCombo) {
		this.pkCustomerCombo = pkCustomerCombo;
	}

	@Column(name = "comboname", length = 100)
	public String getComboname() {
		return this.comboname;
	}

	public void setComboname(String comboname) {
		this.comboname = comboname;
	}

	@Column(name = "fairername", length = 30)
	public String getFairername() {
		return this.fairername;
	}

	public void setFairername(String fairername) {
		this.fairername = fairername;
	}

	@Column(name = "pk_price")
	public Long getPkPrice() {
		return this.pkPrice;
	}

	public void setPkPrice(Long pkPrice) {
		this.pkPrice = pkPrice;
	}

	@Column(name = "servicemoney", precision = 20)
	public Double getServicemoney() {
		return this.servicemoney;
	}

	public void setServicemoney(Double servicemoney) {
		this.servicemoney = servicemoney;
	}

	@Column(name = "fairercomboservicemoney", precision = 20)
	public Double getFairercomboservicemoney() {
		return this.fairercomboservicemoney;
	}

	public void setFairercomboservicemoney(Double fairercomboservicemoney) {
		this.fairercomboservicemoney = fairercomboservicemoney;
	}

	@Column(name = "fairerservicemoney", precision = 20)
	public Double getFairerservicemoney() {
		return this.fairerservicemoney;
	}

	public void setFairerservicemoney(Double fairerservicemoney) {
		this.fairerservicemoney = fairerservicemoney;
	}

	@Column(name = "faireraddmoney", precision = 20)
	public Double getFaireraddmoney() {
		return this.faireraddmoney;
	}

	public void setFaireraddmoney(Double faireraddmoney) {
		this.faireraddmoney = faireraddmoney;
	}

	@Column(name = "fairermoney", precision = 20)
	public Double getFairermoney() {
		return this.fairermoney;
	}

	public void setFairermoney(Double fairermoney) {
		this.fairermoney = fairermoney;
	}

	@Column(name = "commissionpeople")
	public String getCommissionpeople() {
		return this.commissionpeople;
	}

	public void setCommissionpeople(String commissionpeople) {
		this.commissionpeople = commissionpeople;
	}

	@Column(name = "isautoevaluate", length = 1)
	public String getIsautoevaluate() {
		return this.isautoevaluate;
	}

	public void setIsautoevaluate(String isautoevaluate) {
		this.isautoevaluate = isautoevaluate;
	}

	@Column(name = "evaluategoal", precision = 20, scale = 1)
	public Double getEvaluategoal() {
		return this.evaluategoal;
	}

	public void setEvaluategoal(Double evaluategoal) {
		this.evaluategoal = evaluategoal;
	}

	@Column(name = "evaluatenote")
	public String getEvaluatenote() {
		return this.evaluatenote;
	}

	public void setEvaluatenote(String evaluatenote) {
		this.evaluatenote = evaluatenote;
	}

	@Column(name = "evaluatetime", length = 19)
	public String getEvaluatetime() {
		return this.evaluatetime;
	}

	public void setEvaluatetime(String evaluatetime) {
		this.evaluatetime = evaluatetime;
	}

	@Column(name = "introduceman", length = 20)
	public String getIntroduceman() {
		return this.introduceman;
	}

	public void setIntroduceman(String introduceman) {
		this.introduceman = introduceman;
	}

	@Column(name = "isbeauty", length = 1)
	public String getIsbeauty() {
		return this.isbeauty;
	}

	public void setIsbeauty(String isbeauty) {
		this.isbeauty = isbeauty;
	}

	@Column(name = "beautypraisecount")
	public Integer getBeautypraisecount() {
		return this.beautypraisecount;
	}

	public void setBeautypraisecount(Integer beautypraisecount) {
		this.beautypraisecount = beautypraisecount;
	}

	@Column(name = "beautycommentcount")
	public Integer getBeautycommentcount() {
		return this.beautycommentcount;
	}

	public void setBeautycommentcount(Integer beautycommentcount) {
		this.beautycommentcount = beautycommentcount;
	}

	@Column(name = "operatorpk")
	public Long getOperatorpk() {
		return this.operatorpk;
	}

	public void setOperatorpk(Long operatorpk) {
		this.operatorpk = operatorpk;
	}

	@Column(name = "operator")
	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	@Column(name = "orderfrom")
	public String getOrderfrom() {
		return orderfrom;
	}

	public void setOrderfrom(String orderfrom) {
		this.orderfrom = orderfrom;
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

	@Column(name = "nocontainhair", length = 1)
	public String getNocontainhair() {
		return this.nocontainhair;
	}

	public void setNocontainhair(String nocontainhair) {
		this.nocontainhair = nocontainhair;
	}

	@Column(name = "isfreeservice", length = 1)
	public String getIsfreeservice() {
		return this.isfreeservice;
	}

	public void setIsfreeservice(String isfreeservice) {
		this.isfreeservice = isfreeservice;
	}

	@Column(name = "cancelreason")
	public String getCancelreason() {
		return cancelreason;
	}

	public void setCancelreason(String cancelreason) {
		this.cancelreason = cancelreason;
	}

	
}