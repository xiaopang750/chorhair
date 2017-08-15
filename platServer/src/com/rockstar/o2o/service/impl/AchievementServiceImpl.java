package com.rockstar.o2o.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import com.rockstar.o2o.service.AchievementService;

@Component
public class AchievementServiceImpl extends BaseServiceImpl implements AchievementService {
	/**
	 * 店面收入--柱状图1
	 * @param pkShop
	 * @param month
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> queryIncome(String pkShop, String month) {
		List<Object> list;
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer sbf= new StringBuffer();
        sbf.append("  SELECT                                                                                                  ");
        sbf.append("  	DATE_FORMAT(a.paytime, '%v') dates,                                                             ");
        sbf.append("  	SUM(a.ordermoney) as summoney,                                                                        ");
        sbf.append("  	(                                                                                                     ");
        sbf.append("  		SELECT                                                                                            ");
        sbf.append("  			SUM(aa.ordermoney)                                                                            ");
        sbf.append("  		FROM                                                                                              ");
        sbf.append("  			customer_order aa                                                                             ");
        sbf.append("  		WHERE                                                                                             ");
        sbf.append("  			IFNULL(aa.dr, 0) = 0                                                                          ");
        sbf.append("  		AND aa.paystatus = '002'                                                                          ");
        sbf.append("  		AND aa.pk_shop =:pkShop                                                                           ");
        sbf.append("  		AND aa.iscombo ='Y'                                                                               ");
        sbf.append("  		AND DATE_FORMAT (aa.paytime,'%Y-%m') =:month                                                      ");
        sbf.append("  		and DATE_FORMAT(aa.paytime, '%v')=DATE_FORMAT(a.paytime, '%v')                        ");
        sbf.append("  	)  as sumcombomoney,                                                                                  ");
        sbf.append("  	(                                                                                                     ");
        sbf.append("  		SELECT                                                                                            ");
        sbf.append("  			SUM(a5.ordermoney)                                                                            ");
        sbf.append("  		FROM                                                                                              ");
        sbf.append("  			customer_order a5 JOIN customer_combo b5 ON a5.pk_customer_combo=b5.pk_customer_combo         ");
        sbf.append("  		WHERE                                                                                             ");
        sbf.append("  			IFNULL(a5.dr, 0) = 0                                                                          ");
        sbf.append("  		AND IFNULL(b5.dr, 0) = 0                                                                          ");
        sbf.append("  		AND a5.paystatus = '002'                                                                          ");
        sbf.append("  		AND a5.pk_shop =:pkShop                                                                           ");
        sbf.append("  		AND a5.iscombo ='Y'                                                                               ");
        sbf.append("  		AND b5.combotype ='2'                                                                             ");
        sbf.append("  		AND DATE_FORMAT (a5.paytime,'%Y-%m') =:month                                                      ");
        sbf.append("  		and DATE_FORMAT(a5.paytime, '%v')=DATE_FORMAT(a.paytime, '%v')                        ");
        sbf.append("                                                                                                          ");
        sbf.append("  	) as comboonesum,                                                                                     ");
        sbf.append("  	(                                                                                                     ");
        sbf.append("  		SELECT                                                                                            ");
        sbf.append("  			SUM(a6.ordermoney)                                                                            ");
        sbf.append("  		FROM                                                                                              ");
        sbf.append("  			customer_order a6                                                                             ");
        sbf.append("  		WHERE                                                                                             ");
        sbf.append("  			IFNULL(a6.dr, 0) = 0                                                                          ");
        sbf.append("  		AND a6.paystatus = '002'                                                                          ");
        sbf.append("  		AND a6.pk_shop =:pkShop                                                                           ");
        sbf.append("  		AND IFNULL(a6.iscombo, 'N') = 'N'                                                                 ");
        sbf.append("  		AND DATE_FORMAT (a6.paytime,'%Y-%m') =:month                                                      ");
        sbf.append("  		and DATE_FORMAT(a6.paytime, '%v')=DATE_FORMAT(a.paytime, '%v')                        ");
        sbf.append("  	) as servicemoney                                                                                     ");
        sbf.append("  FROM                                                                                                    ");
        sbf.append("  	customer_order a                                                                                      ");
        sbf.append("  WHERE                                                                                                   ");
        sbf.append("  	IFNULL(a.dr, 0) = 0                                                                                   ");
        sbf.append("  AND a.paystatus = '002'                                                                                 ");
        sbf.append("  AND a.pk_shop =:pkShop                                                                                  ");
        sbf.append("  AND DATE_FORMAT (a.paytime,'%Y-%m') =:month                                                             ");
        sbf.append("  GROUP BY dates                                                                                          ");
        
        Long longshop=Long.parseLong(pkShop);
		map.put("pkShop", longshop);
        map.put("month", month);
        
        list= (List<Object>) baseDao.querySqlListByConMap(sbf.toString(),map);
        return list;
	}
	/**
	 * 店面收入--柱状图2
	 * @param pkShop
	 * @param month
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> queryavgincome(String pkShop, String month) {
		List<Object> list;
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer sbf= new StringBuffer();
		sbf.append(" SELECT                                                                                                       ");
		sbf.append(" 	DATE_FORMAT(a.paytime, '%v') dates,                                                                       ");
		sbf.append(" 	(SUM(a.ordermoney)/7) as avgmoney,                                                                        ");
		sbf.append(" 	(                                                                                                         ");
		sbf.append(" 		SELECT                                                                                                ");
		sbf.append(" 			(SUM(a3.ordermoney)/7)                                                                            ");
		sbf.append(" 		FROM                                                                                                  ");
		sbf.append(" 			customer_order a3                                                                                 ");
		sbf.append(" 		WHERE                                                                                                 ");
		sbf.append(" 			IFNULL(a3.dr, 0) = 0                                                                              ");
		sbf.append(" 		AND a3.paystatus = '002'                                                                              ");
		sbf.append(" 		AND a3.pk_shop =:pkShop                                                                               ");
		sbf.append(" 		AND a3.iscombo ='Y'                                                                                   ");
		sbf.append(" 		AND DATE_FORMAT (a3.paytime,'%Y-%m') =:month                                                          ");
		sbf.append(" 		and DATE_FORMAT(a3.paytime, '%v')=DATE_FORMAT(a.paytime, '%v')                                        ");
		sbf.append(" 	) as avgcombomoney,                                                                                       ");
		sbf.append(" 	                                                                                                          ");
		sbf.append(" 	(                                                                                                         ");
		sbf.append(" 		(                                                                                                     ");
		sbf.append(" 			SELECT SUM(a4.ordermoney) FROM	customer_order a4	WHERE	IFNULL(a4.dr, 0) = 0                  ");
		sbf.append(" 			AND a4.paystatus = '002'                                                                          ");
		sbf.append(" 			AND a4.pk_shop =:pkShop                                                                           ");
		sbf.append(" 			AND a4.iscombo = 'Y'                                                                              ");
		sbf.append(" 			AND DATE_FORMAT(a4.paytime, '%v') <= DATE_FORMAT(a.paytime, '%v')                                 ");
		sbf.append(" 		) / (                                                                                                 ");
		sbf.append(" 			(SELECT	datediff(                                                                                 ");
		sbf.append(" 				CASE WHEN DATE_ADD(DATE_SUB(DATE_FORMAT(a.paytime,'%x-%m-%d 23:59:59'),                       ");
		sbf.append(" 				INTERVAL WEEKDAY(DATE_FORMAT(a.paytime, '%x-%m-%d')) + 1 DAY),                                ");
		sbf.append(" 				INTERVAL 1 WEEK) <= DATE_FORMAT(SYSDATE(), '%x-%m-%d')                                        ");
		sbf.append(" 						 THEN DATE_ADD(DATE_SUB(DATE_FORMAT(a.paytime,'%x-%m-%d 23:59:59'),                   ");
		sbf.append(" 						 INTERVAL WEEKDAY(DATE_FORMAT(a.paytime, '%x-%m-%d')) + 1 DAY),INTERVAL 1 WEEK)       ");
		sbf.append(" 				ELSE DATE_FORMAT(SYSDATE(), '%x-%m-%d')                                                       ");
		sbf.append(" 				END,                                                                                          ");
		sbf.append(" 				(SELECT min(paytime) FROM customer_order))                                                    ");
		sbf.append(" 			) + 1                                                                                             ");
		sbf.append(" 		)                                                                                                     ");
		sbf.append(" 	) as avgallcombomoney                                                                                     ");
		sbf.append(" FROM                                                                                                         ");
		sbf.append(" 	customer_order a                                                                                          ");
		sbf.append(" WHERE                                                                                                        ");
		sbf.append(" 	IFNULL(a.dr, 0) = 0                                                                                       ");
		sbf.append(" AND a.paystatus = '002'                                                                                      ");
		sbf.append(" AND a.pk_shop =:pkShop                                                                                       ");
		sbf.append(" AND DATE_FORMAT (a.paytime,'%Y-%m') =:month                                                                  ");
		sbf.append(" GROUP BY dates                                                                                               ");
		
		Long longshop=Long.parseLong(pkShop);
		map.put("pkShop", longshop);
        map.put("month", month);
        
        list= (List<Object>) baseDao.querySqlListByConMap(sbf.toString(),map);
        return list;
	}
	/**
	 * 店面人流情况
	 * @param pkShop
	 * @param month
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> queryPeopleCount(String pkShop, String month) {
		List<Object> list;
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer sbf= new StringBuffer();
		sbf.append("  SELECT                                                                                                   ");
		sbf.append("  	DATE_FORMAT(a.paytime, '%v') dates,                                                                    ");
		sbf.append("  	COUNT(1) customercount,                                                                                ");
		sbf.append("  	(                                                                                                      ");
		sbf.append("  		select                                                                                             ");
		sbf.append("  			COUNT(DISTINCT c.pk_customer)                                                                  ");
		sbf.append("  		FROM                                                                                               ");
		sbf.append("  			(select                                                                                        ");
		sbf.append("  				DISTINCT aa.pk_customer AS pk_customer,                                                    ");
		sbf.append("  				min(aa.paytime) as minpaytime                                                              ");
		sbf.append("  				FROM                                                                                       ");
		sbf.append("  				customer_order aa JOIN customer_combo bb ON aa.pk_customer_combo=bb.pk_customer_combo      ");
		sbf.append("  				WHERE IFNULL(aa.dr, 0) = 0                                                                 ");
		sbf.append("  				AND IFNULL(bb.dr, 0) = 0                                                                   ");
		sbf.append("  				AND aa.paystatus = '002'                                                                   ");
		sbf.append("  				AND aa.pk_shop =:pkShop                                                                    ");
		sbf.append("  				AND aa.iscombo ='Y'                                                                        ");
		sbf.append("  				AND bb.combotype!='2'                                                                      ");
		sbf.append("  				AND DATE_FORMAT (aa.paytime,'%Y-%m') =:month                                               ");
		sbf.append("  				GROUP BY aa.pk_customer HAVING DATE_FORMAT (minpaytime,'%Y-%m') =:month) c                 ");
		sbf.append("  		WHERE DATE_FORMAT(c.minpaytime, '%v')=DATE_FORMAT(a.paytime, '%v')                                 ");
		sbf.append("  	) as addcustomercount,                                                                                 ");
		sbf.append("  	(                                                                                                      ");
		sbf.append("  		SELECT                                                                                             ");
		sbf.append("  		COUNT(1) count                                                                                     ");
		sbf.append("  		FROM                                                                                               ");
		sbf.append("  			customer_order aaa JOIN customer_combo bbb ON aaa.pk_customer_combo=bbb.pk_customer_combo      ");
		sbf.append("  		WHERE                                                                                              ");
		sbf.append("  			IFNULL(aaa.dr, 0) = 0                                                                          ");
		sbf.append("  		AND IFNULL(bbb.dr, 0) = 0                                                                          ");
		sbf.append("  		AND aaa.paystatus = '002'                                                                          ");
		sbf.append("  		AND aaa.pk_shop =:pkShop                                                                           ");
		sbf.append("  		AND aaa.iscombo ='Y'                                                                               ");
		sbf.append("  		AND bbb.combotype ='2'                                                                             ");
		sbf.append("  		AND DATE_FORMAT (aaa.paytime,'%Y-%m') =:month                                                      ");
		sbf.append("  		AND DATE_FORMAT(aaa.paytime, '%v')=DATE_FORMAT(a.paytime, '%v')                                    ");
		sbf.append("  	) as comboonecount,                                                                                    ");
		sbf.append("  	(                                                                                                      ");
		sbf.append("  		SELECT                                                                                             ");
		sbf.append("  			COUNT(1) count                                                                                 ");
		sbf.append("  		FROM                                                                                               ");
		sbf.append("  			customer_order aaaa  JOIN customer_combo bb4 ON aaaa.pk_customer_combo=bb4.pk_customer_combo   ");
		sbf.append("  		WHERE                                                                                              ");
		sbf.append("  			IFNULL(aaaa.dr, 0) = 0                                                                         ");
		sbf.append("  		AND IFNULL(bb4.dr, 0) = 0                                                                          ");
		sbf.append("  		AND aaaa.paystatus = '002'                                                                         ");
		sbf.append("  		AND aaaa.pk_shop =:pkShop                                                                          ");
		sbf.append("  		AND IFNULL(aaaa.iscombo, 'N') = 'N'                                                                ");
		sbf.append("  		AND bb4.combotype!='2'                                                                             ");
		sbf.append("  		AND DATE_FORMAT (aaaa.paytime,'%Y-%m') =:month                                                     ");
		sbf.append("  		AND DATE_FORMAT(aaaa.paytime, '%v')=DATE_FORMAT(a.paytime, '%v')                                   ");
		sbf.append("  	) as servicecount,                                                                                     ");
		sbf.append("  	(                                                                                                      ");
		sbf.append("  		select count(DISTINCT aa5.pk_customer)                                                             ");
		sbf.append("  		from customer_order  aa5 JOIN customer_combo bb5 ON aa5.pk_customer_combo=bb5.pk_customer_combo    ");
		sbf.append("  		where                                                                                              ");
		sbf.append("  			IFNULL(aa5.dr, 0) = 0                                                                          ");
		sbf.append("  		AND IFNULL(bb5.dr, 0) = 0                                                                          ");
		sbf.append("  		AND aa5.paystatus = '002'                                                                          ");
		sbf.append("  		AND aa5.pk_shop =:pkShop                                                                           ");
		sbf.append("  		AND aa5.iscombo ='Y'                                                                               ");
		sbf.append("  		AND bb5.combotype!='2'                                                                             ");
		sbf.append("  		and DATE_FORMAT(aa5.paytime, '%v')<=DATE_FORMAT(a.paytime, '%v')                                   ");
		sbf.append("  	)as totalcustomercount,                                                                                ");
		sbf.append("  (                                                                                                        ");
		sbf.append("  		SELECT                                                                                             ");
		sbf.append("  			COUNT(*)                                                                                       ");
		sbf.append("  		FROM                                                                                               ");
		sbf.append("  			customer_order a1 JOIN customer_combo b1 ON a1.pk_customer_combo=b1.pk_customer_combo          ");
		sbf.append("  		WHERE                                                                                              ");
		sbf.append("  			IFNULL(a1.dr, 0) = 0                                                                           ");
		sbf.append("  		AND IFNULL(b1.dr, 0) = 0                                                                           ");
		sbf.append("  		AND a1.paystatus = '002'                                                                           ");
		sbf.append("  		AND a1.pk_shop =:pkShop                                                                            ");
		sbf.append("  		AND a1.iscombo ='Y'                                                                                ");
		sbf.append("  		AND b1.combotype!='2'                                                                              ");
		sbf.append("  		AND DATE_FORMAT (a1.paytime,'%Y-%m') =:month                                                       ");
		sbf.append("  		and DATE_FORMAT(a1.paytime, '%v')=DATE_FORMAT(a.paytime, '%v')                                     ");
		sbf.append("  	) as combocount,                                                                                       ");
		sbf.append("  	(	                                                                                                   ");
		sbf.append("  		select                                                                                             ");
		sbf.append("  			count(*)                                                                                       ");
		sbf.append("  		from                                                                                               ");
		sbf.append("  			customer_order  aa JOIN customer_combo bb ON aa.pk_customer_combo=bb.pk_customer_combo         ");
		sbf.append("  		where IFNULL(aa.dr, 0) = 0                                                                         ");
		sbf.append("  		AND IFNULL(bb.dr, 0) = 0                                                                           ");
		sbf.append("  		AND aa.paystatus = '002'                                                                           ");
		sbf.append("  		AND aa.pk_shop =:pkShop                                                                            ");
		sbf.append("  		AND aa.iscombo ='Y'                                                                                ");
		sbf.append("  		AND bb.combotype!='2'                                                                              ");
		sbf.append("  		and DATE_FORMAT(aa.paytime, '%v')<=DATE_FORMAT(a.paytime, '%v')                                    ");
		sbf.append("  	) as totalcombocount                                                                                   ");
		sbf.append("  FROM                                                                                                     ");
		sbf.append("  	customer_order a                                                                                       ");
		sbf.append("  WHERE                                                                                                    ");
		sbf.append("  	IFNULL(a.dr, 0) = 0                                                                                    ");
		sbf.append("  AND a.paystatus = '002'                                                                                  ");
		sbf.append("  AND a.pk_shop =:pkShop                                                                                   ");
		sbf.append("  and DATE_FORMAT (a.paytime,'%Y-%m') =:month                                                              ");
		sbf.append("  group by dates                                                                                           ");
		

		Long longshop=Long.parseLong(pkShop);
		map.put("pkShop", longshop);
        map.put("month", month);
        
        list= (List<Object>) baseDao.querySqlListByConMap(sbf.toString(),map);
        return list;
	}

}
