package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ShopInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "shop_info")
public class ShopInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkShop;
	private String shopshortcode;
	private String shopcode;
	private String shopname;
	private String shopowner;
	private String cellphone;
	private String fixphone;
	private String shopinterface;
	private String interfacephone;
	private String businessour;
	private String addr;
	private String location;
	private String ts;
	private Short dr;
	private String province;
	private String city;
	private String county;
	private String street;
	private String provincename;
	private String cityname;
	private String countyname;
	private String streetname;
	private String orderqrcodeurl;
	private String shopqrcodeurl;

	// Constructors

	/** default constructor */
	public ShopInfo() {
	}

	/** full constructor */
	public ShopInfo(String shopshortcode, String shopcode, String shopname,
			String shopowner, String cellphone, String fixphone,
			String shopinterface, String interfacephone, String businessour,
			String addr, String location, String ts, Short dr, String province,
			String city, String county, String street, String provincename, String cityname,
			String countyname, String streetname,String orderqrcodeurl,
			String shopqrcodeurl) {
		this.shopshortcode = shopshortcode;
		this.shopcode = shopcode;
		this.shopname = shopname;
		this.shopowner = shopowner;
		this.cellphone = cellphone;
		this.fixphone = fixphone;
		this.shopinterface = shopinterface;
		this.interfacephone = interfacephone;
		this.businessour = businessour;
		this.addr = addr;
		this.location = location;
		this.ts = ts;
		this.dr = dr;
		this.province = province;
		this.city = city;
		this.county = county;
		this.street = street;
		this.provincename = provincename;
		this.cityname = cityname;
		this.countyname = countyname;
		this.streetname = streetname;
		this.orderqrcodeurl = orderqrcodeurl;
		this.shopqrcodeurl = shopqrcodeurl;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_shop", unique = true, nullable = false)
	public Long getPkShop() {
		return this.pkShop;
	}

	public void setPkShop(Long pkShop) {
		this.pkShop = pkShop;
	}

	@Column(name = "shopshortcode", length = 20)
	public String getShopshortcode() {
		return this.shopshortcode;
	}

	public void setShopshortcode(String shopshortcode) {
		this.shopshortcode = shopshortcode;
	}

	@Column(name = "shopcode", length = 30)
	public String getShopcode() {
		return this.shopcode;
	}

	public void setShopcode(String shopcode) {
		this.shopcode = shopcode;
	}

	@Column(name = "shopname", length = 100)
	public String getShopname() {
		return this.shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	@Column(name = "shopowner", length = 100)
	public String getShopowner() {
		return this.shopowner;
	}

	public void setShopowner(String shopowner) {
		this.shopowner = shopowner;
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

	@Column(name = "shopinterface", length = 20)
	public String getShopinterface() {
		return this.shopinterface;
	}

	public void setShopinterface(String shopinterface) {
		this.shopinterface = shopinterface;
	}

	@Column(name = "interfacephone", length = 11)
	public String getInterfacephone() {
		return this.interfacephone;
	}

	public void setInterfacephone(String interfacephone) {
		this.interfacephone = interfacephone;
	}

	@Column(name = "businessour", length = 50)
	public String getBusinessour() {
		return this.businessour;
	}

	public void setBusinessour(String businessour) {
		this.businessour = businessour;
	}

	@Column(name = "addr", length = 200)
	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Column(name = "location")
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	@Column(name = "street", length = 30)
	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
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
	@Column(name = "streetname")
	public String getStreetname() {
		return streetname;
	}

	public void setStreetname(String streetname) {
		this.streetname = streetname;
	}

	@Column(name = "orderqrcodeurl")
	public String getOrderqrcodeurl() {
		return this.orderqrcodeurl;
	}

	public void setOrderqrcodeurl(String orderqrcodeurl) {
		this.orderqrcodeurl = orderqrcodeurl;
	}

	@Column(name = "shopqrcodeurl")
	public String getShopqrcodeurl() {
		return this.shopqrcodeurl;
	}

	public void setShopqrcodeurl(String shopqrcodeurl) {
		this.shopqrcodeurl = shopqrcodeurl;
	}

}