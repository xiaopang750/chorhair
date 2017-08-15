package com.rockstar.o2o.vo;

import java.math.BigInteger;

public class FairerList {

	private BigInteger pk_fairer;
	private String  fairername;
	private String  rankname;

	
     public FairerList(){
    	 
     }
     
     
	public BigInteger getPk_fairer() {
		return pk_fairer;
	}
	public void setPk_fairer(BigInteger pk_fairer) {
		this.pk_fairer = pk_fairer;
	}
	public String getFairername() {
		return fairername;
	}
	public void setFairername(String fairername) {
		this.fairername = fairername;
	}
	public String getRankname() {
		return rankname;
	}
	public void setRankname(String rankname) {
		this.rankname = rankname;
	}
	
	
	
}
