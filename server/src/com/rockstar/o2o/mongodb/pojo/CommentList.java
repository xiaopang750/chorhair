package com.rockstar.o2o.mongodb.pojo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public class CommentList implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String customername;
	private Long pkShop;
	private Long pkFairer;
	private Long pkCombo;
	private Double evaluategoal;
	private String evaluatedetail;
	private String evaluatenote;
	private String evaluatetime;
	
	
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getPkShop() {
		return pkShop;
	}
	public void setPkShop(Long pkShop) {
		this.pkShop = pkShop;
	}
	public Long getPkFairer() {
		return pkFairer;
	}
	public void setPkFairer(Long pkFairer) {
		this.pkFairer = pkFairer;
	}
	public Long getPkCombo() {
		return pkCombo;
	}
	public void setPkCombo(Long pkCombo) {
		this.pkCombo = pkCombo;
	}
	public Double getEvaluategoal() {
		return evaluategoal;
	}
	public void setEvaluategoal(Double evaluategoal) {
		this.evaluategoal = evaluategoal;
	}
	public String getEvaluatedetail() {
		return evaluatedetail;
	}
	public void setEvaluatedetail(String evaluatedetail) {
		this.evaluatedetail = evaluatedetail;
	}
	public String getEvaluatenote() {
		return evaluatenote;
	}
	public void setEvaluatenote(String evaluatenote) {
		this.evaluatenote = evaluatenote;
	}
	public String getEvaluatetime() {
		return evaluatetime;
	}
	public void setEvaluatetime(String evaluatetime) {
		this.evaluatetime = evaluatetime;
	}
	@Override
	public String toString() {
		return "CommentList [id=" + id + ", customername=" + customername
				+ ", pkShop=" + pkShop + ", pkFairer=" + pkFairer
				+ ", pkCombo=" + pkCombo + ", evaluategoal=" + evaluategoal
				+ ", evaluatedetail=" + evaluatedetail + ", evaluatenote="
				+ evaluatenote + ", evaluatetime=" + evaluatetime + "]";
	}
	
	
	
}
