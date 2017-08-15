package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ShopCombo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "shop_combo")
public class ShopCombo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkShopCombo;
	private Long pkCombo;
	private Long pkShop;
	private String fairtype;
	private String fairlength;
	private String fitgroup;
	private String combopy;
	private String combospy;
	private String combocode;
	private String comboname;
	private String combotype;
	private String comborank;
	private Integer combocount;
	private Double standardmoney;
	private Double shopmoney;
	private Double currentmoney;
	private Double fairermoney;
	private Double servicemoney;
	private Double fairerrate;
	private String israte;
	private String combonote;
	private Long pkAdditiongroup;
	private Long pkServicegroup;
	private Integer buycount;
	private Integer servicecount;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public ShopCombo() {
	}

	/** full constructor */
	public ShopCombo(Long pkCombo, Long pkShop, String fairtype,
			String fairlength, String fitgroup, String combopy,
			String combospy, String combocode, String comboname,
			String combotype, String comborank, Integer combocount,
			Double standardmoney, Double shopmoney, Double currentmoney,
			Double fairermoney, Double servicemoney, Double fairerrate,
			String israte, String combonote, Long pkAdditiongroup,
			Long pkServicegroup, Integer buycount, Integer servicecount,
			String ts, Short dr) {
		this.pkCombo = pkCombo;
		this.pkShop = pkShop;
		this.fairtype = fairtype;
		this.fairlength = fairlength;
		this.fitgroup = fitgroup;
		this.combopy = combopy;
		this.combospy = combospy;
		this.combocode = combocode;
		this.comboname = comboname;
		this.combotype = combotype;
		this.comborank = comborank;
		this.combocount = combocount;
		this.standardmoney = standardmoney;
		this.shopmoney = shopmoney;
		this.currentmoney = currentmoney;
		this.fairermoney = fairermoney;
		this.servicemoney = servicemoney;
		this.fairerrate = fairerrate;
		this.israte = israte;
		this.combonote = combonote;
		this.pkAdditiongroup = pkAdditiongroup;
		this.pkServicegroup = pkServicegroup;
		this.buycount = buycount;
		this.servicecount = servicecount;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_shop_combo", unique = true, nullable = false)
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

	@Column(name = "pk_shop")
	public Long getPkShop() {
		return this.pkShop;
	}

	public void setPkShop(Long pkShop) {
		this.pkShop = pkShop;
	}

	@Column(name = "fairtype", length = 30)
	public String getFairtype() {
		return this.fairtype;
	}

	public void setFairtype(String fairtype) {
		this.fairtype = fairtype;
	}

	@Column(name = "fairlength", length = 20)
	public String getFairlength() {
		return this.fairlength;
	}

	public void setFairlength(String fairlength) {
		this.fairlength = fairlength;
	}

	@Column(name = "fitgroup", length = 30)
	public String getFitgroup() {
		return this.fitgroup;
	}

	public void setFitgroup(String fitgroup) {
		this.fitgroup = fitgroup;
	}

	@Column(name = "combopy")
	public String getCombopy() {
		return this.combopy;
	}

	public void setCombopy(String combopy) {
		this.combopy = combopy;
	}

	@Column(name = "combospy")
	public String getCombospy() {
		return this.combospy;
	}

	public void setCombospy(String combospy) {
		this.combospy = combospy;
	}

	@Column(name = "combocode", length = 50)
	public String getCombocode() {
		return this.combocode;
	}

	public void setCombocode(String combocode) {
		this.combocode = combocode;
	}

	@Column(name = "comboname", length = 100)
	public String getComboname() {
		return this.comboname;
	}

	public void setComboname(String comboname) {
		this.comboname = comboname;
	}

	@Column(name = "combotype", length = 20)
	public String getCombotype() {
		return this.combotype;
	}

	public void setCombotype(String combotype) {
		this.combotype = combotype;
	}

	@Column(name = "comborank", length = 20)
	public String getComborank() {
		return this.comborank;
	}

	public void setComborank(String comborank) {
		this.comborank = comborank;
	}

	@Column(name = "combocount")
	public Integer getCombocount() {
		return this.combocount;
	}

	public void setCombocount(Integer combocount) {
		this.combocount = combocount;
	}

	@Column(name = "standardmoney", precision = 20)
	public Double getStandardmoney() {
		return this.standardmoney;
	}

	public void setStandardmoney(Double standardmoney) {
		this.standardmoney = standardmoney;
	}

	@Column(name = "shopmoney", precision = 20)
	public Double getShopmoney() {
		return this.shopmoney;
	}

	public void setShopmoney(Double shopmoney) {
		this.shopmoney = shopmoney;
	}

	@Column(name = "currentmoney", precision = 20)
	public Double getCurrentmoney() {
		return this.currentmoney;
	}

	public void setCurrentmoney(Double currentmoney) {
		this.currentmoney = currentmoney;
	}

	@Column(name = "fairermoney", precision = 20)
	public Double getFairermoney() {
		return this.fairermoney;
	}

	public void setFairermoney(Double fairermoney) {
		this.fairermoney = fairermoney;
	}

	@Column(name = "servicemoney", precision = 20)
	public Double getServicemoney() {
		return this.servicemoney;
	}

	public void setServicemoney(Double servicemoney) {
		this.servicemoney = servicemoney;
	}

	@Column(name = "fairerrate", precision = 20)
	public Double getFairerrate() {
		return this.fairerrate;
	}

	public void setFairerrate(Double fairerrate) {
		this.fairerrate = fairerrate;
	}

	@Column(name = "israte", length = 1)
	public String getIsrate() {
		return this.israte;
	}

	public void setIsrate(String israte) {
		this.israte = israte;
	}

	@Column(name = "combonote")
	public String getCombonote() {
		return this.combonote;
	}

	public void setCombonote(String combonote) {
		this.combonote = combonote;
	}

	@Column(name = "pk_additiongroup")
	public Long getPkAdditiongroup() {
		return this.pkAdditiongroup;
	}

	public void setPkAdditiongroup(Long pkAdditiongroup) {
		this.pkAdditiongroup = pkAdditiongroup;
	}

	@Column(name = "pk_servicegroup")
	public Long getPkServicegroup() {
		return this.pkServicegroup;
	}

	public void setPkServicegroup(Long pkServicegroup) {
		this.pkServicegroup = pkServicegroup;
	}

	@Column(name = "buycount")
	public Integer getBuycount() {
		return this.buycount;
	}

	public void setBuycount(Integer buycount) {
		this.buycount = buycount;
	}

	@Column(name = "servicecount")
	public Integer getServicecount() {
		return this.servicecount;
	}

	public void setServicecount(Integer servicecount) {
		this.servicecount = servicecount;
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