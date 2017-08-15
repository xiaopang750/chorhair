package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * PlatformCombo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="platform_combo")

public class PlatformCombo  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkCombo;
     private String combocode;
     private String comboname;
     private String combospy;
     private String combopy;
     private String fairtype;
     private String fitgroup;
     private String fairlength;
     private String comborank;
     private String combotype;
     private Double combomoney;
     private Integer combocount;
     private String combonote;
     private String ts;
     private Short dr;


    // Constructors

    /** default constructor */
    public PlatformCombo() {
    }

    
    /** full constructor */
    public PlatformCombo(String combocode, String comboname, String combospy, String combopy, String 
fairtype, String fitgroup, String fairlength, String comborank, String combotype, Double combomoney, Integer combocount, String combonote, String ts, Short dr) {
        this.combocode = combocode;
        this.comboname = comboname;
        this.combospy = combospy;
        this.combopy = combopy;
        this.fairtype = fairtype;
        this.fitgroup = fitgroup;
        this.fairlength = fairlength;
        this.comborank = comborank;
        this.combotype = combotype;
        this.combomoney = combomoney;
        this.combocount = combocount;
        this.combonote = combonote;
        this.ts = ts;
        this.dr = dr;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="pk_combo", unique=true, nullable=false)

    public Long getPkCombo() {
        return this.pkCombo;
    }
    
    public void setPkCombo(Long pkCombo) {
        this.pkCombo = pkCombo;
    }
    
    @Column(name="combocode", length=50)

    public String getCombocode() {
        return this.combocode;
    }
    
    public void setCombocode(String combocode) {
        this.combocode = combocode;
    }
    
    @Column(name="comboname", length=100)

    public String getComboname() {
        return this.comboname;
    }
    
    public void setComboname(String comboname) {
        this.comboname = comboname;
    }
    
    @Column(name="combospy")

    public String getCombospy() {
        return this.combospy;
    }
    
    public void setCombospy(String combospy) {
        this.combospy = combospy;
    }
    
    @Column(name="combopy")

    public String getCombopy() {
        return this.combopy;
    }
    
    public void setCombopy(String combopy) {
        this.combopy = combopy;
    }
    
    @Column(name="fairtype", length=30)

    public String getfairtype() {
        return this.fairtype;
    }
    
    public void setfairtype(String fairtype) {
        this.fairtype = fairtype;
    }
    
    @Column(name="fitgroup", length=10)

    public String getFitgroup() {
        return this.fitgroup;
    }
    
    public void setFitgroup(String fitgroup) {
        this.fitgroup = fitgroup;
    }
    
    @Column(name="fairlength", length=10)

    public String getFairlength() {
        return this.fairlength;
    }
    
    public void setFairlength(String fairlength) {
        this.fairlength = fairlength;
    }
    
    @Column(name="comborank", length=20)

    public String getComborank() {
        return this.comborank;
    }
    
    public void setComborank(String comborank) {
        this.comborank = comborank;
    }
    
    @Column(name="combotype", length=30)

    public String getCombotype() {
        return this.combotype;
    }
    
    public void setCombotype(String combotype) {
        this.combotype = combotype;
    }
    
    @Column(name="combomoney", precision=20)

    public Double getCombomoney() {
        return this.combomoney;
    }
    
    public void setCombomoney(Double combomoney) {
        this.combomoney = combomoney;
    }
    
    @Column(name="combocount")

    public Integer getCombocount() {
        return this.combocount;
    }
    
    public void setCombocount(Integer combocount) {
        this.combocount = combocount;
    }
    
    @Column(name="combonote")

    public String getCombonote() {
        return this.combonote;
    }
    
    public void setCombonote(String combonote) {
        this.combonote = combonote;
    }
    
    @Column(name="ts", length=19)

    public String getTs() {
        return this.ts;
    }
    
    public void setTs(String ts) {
        this.ts = ts;
    }
    
    @Column(name="dr")

    public Short getDr() {
        return this.dr;
    }
    
    public void setDr(Short dr) {
        this.dr = dr;
    }
   








}