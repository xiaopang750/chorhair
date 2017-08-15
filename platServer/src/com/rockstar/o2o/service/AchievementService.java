package com.rockstar.o2o.service;

import java.util.List;

public interface AchievementService {
	/**
	 * 店面收入--柱状图1
	 * @param pkShop
	 * @param month
	 * @return
	 */
	List<Object> queryIncome(String pkShop, String month);
	/**
	 * 店面收入--柱状图2
	 * @param pkShop
	 * @param month
	 * @return
	 */

	List<Object> queryavgincome(String pkShop, String month);
	/**
	 * 店面人流情况
	 * @param pkShop
	 * @param month
	 * @return
	 */

	List<Object> queryPeopleCount(String pkShop, String month);

}
