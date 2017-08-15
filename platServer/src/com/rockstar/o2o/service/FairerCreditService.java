package com.rockstar.o2o.service;

import java.util.ArrayList;
import java.util.List;

import com.rockstar.o2o.pojo.FairerCredit;
public interface FairerCreditService {
	/**
	 * 添加一项收入明细
	 * @param member
	 */
	FairerCredit save(FairerCredit credit);
	
	/**
	 * 批量保存收入明细
	 * @param member
	 */
	void savelist(ArrayList<FairerCredit> credits);
	/**
	 * 根据理发师获取收入明细
	 * @param pkFairer
	 * @return
	 */
	
	List<FairerCredit> QueryByPkFairer(String pkFairer);
}
