package com.rockstar.o2o.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import com.rockstar.o2o.service.ReportExportService;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.TemplateUtil;

@Component
public class ReportExportServiceImpl extends BaseServiceImpl implements ReportExportService {
	
	@SuppressWarnings("unchecked")
	@Override
    public void exportReport(Long pkshop) {
            
	    Map<String, List<Object[]>> datas = new LinkedHashMap<String, List<Object[]>>();
	    List<Object[]> listOutData;
	   
	    for (int i = 0; i < 5; i++) {
	    	listOutData = new ArrayList<Object[]>();
	    	
	    	String sheetNameStr = "";
	    	String begintime="";
	 		String endtime="";
	 		Object[] obj =new Object[23];
	 		int m=0;
	 		
	    	if ("0".equals(String.valueOf(i))) {
	            sheetNameStr = "日统计";
	    		begintime=(DateUtil.befoDay())+" 00:00:00";
	    		endtime=DateUtil.befoDay()+" 23:59:59";
	    		obj[m]=DateUtil.DateToString(DateUtil.nextDay(new Date(), -1),"yyyy年MM月dd日");
	    		m++;
	        } else if ("1".equals(String.valueOf(i))) {
	            sheetNameStr = "周统计";
	            DateUtil d =new DateUtil();
	            if(DateUtil.getCurrDate("yyyy-MM-dd").equals(d.getMondayOFWeek2())){
	            	begintime=d.getPreviousWeekday2()+" 00:00:00";
		    		endtime=d.getPreviousWeekSunday2()+" 23:59:59";
		    		obj[m]=d.getPreviousWeek();
	            	
	            }else{
		            begintime=d.getMondayOFWeek2()+" 00:00:00";
		    		endtime=d.getCurrentWeekday2()+" 23:59:59";
		    		obj[m]=d.getThisWeek();
	            }
	    		m++;
	        } else if ("2".equals(String.valueOf(i))) {
	        	 sheetNameStr = "月统计";
	        	 DateUtil d =new DateUtil();
	        	if(DateUtil.getCurrDate("yyyy-MM-dd").equals(d.getFirstDayOfMonth())){
	        		begintime=d.getPreviousMonthFirst()+" 00:00:00";
		    		endtime=d.getPreviousMonthEnd()+" 23:59:59";
		    		obj[m]=d.getPreviousMonth();
	        	}else{
		            begintime=d.getFirstDayOfMonth()+" 00:00:00";
		    		endtime=d.getDefaultDay()+" 23:59:59";
		    		obj[m]=DateUtil.getToYear()+"年"+DateUtil.getToMonth()+"月份";
	        	}
	    		m++;
	        } else if ("3".equals(String.valueOf(i))) {
	            sheetNameStr = "季统计"; 
	            DateUtil d =new DateUtil();
	            if(DateUtil.getCurrDate("yyyy-MM-dd").equals(d.getThisSeasonFirstDay(DateUtil.getToMonth()))){
		            begintime=d.getPreviousSeasonFirstDay(DateUtil.getToMonth())+" 00:00:00";
		    		endtime=d.getPreviousSeasonEndDay(DateUtil.getToMonth())+" 23:59:59";
		    		obj[m]=d.getPreviousSeasonYear(DateUtil.getToMonth())+"年"+d.getPreviousSeason(DateUtil.getToMonth())+"季度";
	            }else{
	            	begintime=d.getThisSeasonFirstDay(DateUtil.getToMonth())+" 00:00:00";
		    		endtime=d.getThisSeasonEndDay(DateUtil.getToMonth())+" 23:59:59";
		    		obj[m]=DateUtil.getToYear()+"年"+d.getThisSeason(DateUtil.getToMonth())+"季度";
	            }
	    		m++;
	        } else {
	            sheetNameStr = "年统计";
	            DateUtil d =new DateUtil();
	            if(DateUtil.getCurrDate("yyyy-MM-dd").equals(d.getCurrentYearFirst2())){
		            begintime=d.getPreviousYearFirst2()+" 00:00:00";
		    		endtime=d.getPreviousYearEnd()+" 23:59:59";
		    		obj[m]=(DateUtil.getToYear()-1)+"年";
	            }else{
	            	begintime=d.getCurrentYearFirst2()+" 00:00:00";
		    		endtime=d.getCurrentYearEnd()+" 23:59:59";
		    		obj[m]=DateUtil.getToYear()+"年";
	            	
	            }
	    		m++;
	        }
	    	
	    	//套餐
	    	List<Object> list1=getComboReportData(pkshop,begintime,endtime);	
	    	//单次
	    	List<Object> list2=getOnetryReportData(pkshop,begintime,endtime);
	    	//服务
	    	List<Object> list3=getServiceReportData(pkshop,begintime,endtime);
	    	//增项
	    	List<Object> list4=getAdditionReportData(pkshop,begintime,endtime);
	    	//剪发
	    	List<Object> list5=getCutfairReportData(pkshop,begintime,endtime);
	    	//总计-新模型总收入
	    	//新模型总收入=新增套餐总收入+新增单次体验总收入+服务费总收入+外卖单品总收入
	    	List<Object> list6=getTotalMoneyData(pkshop,begintime,endtime);
	    	
	    	//总计
	    	List<Object> list7=getTotalReportData(pkshop,begintime,endtime);
	    	
	    	for (Object objects : list1) {
	    		List<Object> li=(List<Object>)objects;
	    		for (Object object : li) {
		    		obj[m]=String.valueOf(object);
		    		m++;
	    		}
            }
	    	for (Object objects : list2) {
	    		List<Object> li=(List<Object>)objects;
	    		for (Object object : li) {
	    			obj[m]=String.valueOf(object);
		    		m++;
	    		}
            }
	    	for (Object objects : list3) {
	    		List<Object> li=(List<Object>)objects;
	    		for (Object object : li) {
	    			obj[m]=String.valueOf(object);
		    		m++;
	    		}
            }	
	    	for (Object objects : list4) {
	    		List<Object> li=(List<Object>)objects;
	    		for (Object object : li) {
	    			obj[m]=String.valueOf(object);
		    		m++;
	    		}
            }	
	    	for (Object objects : list5) {
	    		List<Object> li=(List<Object>)objects;
	    		for (Object object : li) {
	    			obj[m]=String.valueOf(object);
		    		m++;
	    		}
            }
	    	for (Object objects : list6) {
	    		List<Object> li=(List<Object>)objects;
	    		for (Object object : li) {
	    			obj[m]=String.valueOf(object);
		    		m++;
	    		}
            }
	    	for (Object objects : list7) {
	    		List<Object> li=(List<Object>)objects;
	    		for (Object object : li) {
	    			obj[m]=String.valueOf(object);
		    		m++;
	    		}
            }	
            listOutData.add(obj);
	    	datas.put(sheetNameStr, listOutData);
	           
	    }
	    readWriter(datas);
	}	
	
	/**
     * 
     * @Title 		   	函数名称:             readWriter
     * @Description   	功能描述：	          利用模板导出excel2007
     * @param           datas:               填充的数据
     * @param           startRow:            excel中开始填充的行数
     * @return          返回值：              void  
     * @throws
     */
	public void  readWriter(Map<String, List<Object[]>> datas){
		int[] startRow={1,1,1,1,1};
		XSSFWorkbook wb=null;
		XSSFSheet sheet =null;
		FileOutputStream fout=null;
        // 获取要读取的EXCEL表格模板
        String[] allpath=TemplateUtil.class.getResource("/").getPath().split("WEB-INF");
        String tempPath=allpath[0]+"template/export/统计报表数据.xlsx";       
        File file = new File(tempPath);
        try {
        	InputStream input=new FileInputStream(file);
        	wb= new XSSFWorkbook(input);
            if (datas != null) {
                int i = 0;
                Iterator<Entry<String, List<Object[]>>> iter = datas.entrySet().iterator();
                while (iter.hasNext()) {
                    Entry<String, List<Object[]>> entry = iter.next();
                    if (null != entry.getValue()) {
                    	getXSSFSheet(entry.getKey(), entry.getValue(), sheet, wb, startRow[i]);
                        i++;
                    }
                }
            }
            //新建一输出流
             fout = new FileOutputStream(file);
            //存盘
            wb.write(fout);      
            fout.flush();           
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
          // 关闭流
          try {
              if (null != wb) {
            	  wb.close();
              }
              fout.close();
          } catch (Exception e) {
              e.printStackTrace();
          }
      }	
	}
	
	/**
     * @Title           函数名称：   getXSSFSheet
     * @Description     功能描述：    利用模板导出excel2007
     * @param           参          数：   
     * @return          返  回   值：   void  
     * @throws
     */
    private static void getXSSFSheet(String key, List<Object[]> value, XSSFSheet sheet, XSSFWorkbook wb, int y){
    	sheet = wb.getSheet(key);
    	XSSFRow row;
    	XSSFCell cell; 
        int index = 0;
        for (int i = y; i < value.get(0).length + y; i++) {
            row = sheet.getRow(i);
            cell= row.getCell(2);
            for (int j = 0; j < value.size(); j++) {        
                try {
                	// 定义单元格为字符串类型
                	cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                	cell.setCellValue((String)value.get(0)[index]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            index++;
        }
    }
    
	
//	/**
//     * 
//     * @Title 		   	函数名称:             readWriter
//     * @Description   	功能描述：	          利用模板导出excel2003
//     * @param           datas:               填充的数据
//     * @param           startRow:            excel中开始填充的行数
//     * @return          返回值：              void  
//     * @throws
//     */
//	public void  readWriter(Map<String, List<Object[]>> datas){
//		int[] startRow={1,1,1,1,1};
//        WritableWorkbook wwb = null;
//        WritableSheet wws = null;
//        FileOutputStream out = null;
//        // 获取要读取的EXCEL表格模板
//        String[] allpath=TemplateUtil.class.getResource("/").getPath().split("WEB-INF");
//        String tempPath=allpath[0]+"template/export/统计报表数据.xls";
//        File file = new File(tempPath);
//        try {
//            WorkbookSettings settings = new WorkbookSettings();
//            Workbook wb = Workbook.getWorkbook(file);
//            out = new FileOutputStream(file);
//            wwb = Workbook.createWorkbook(out, wb, settings);
//            if (datas != null) {
//                int i = 0;
//                Iterator<Entry<String, List<Object[]>>> iter = datas.entrySet().iterator();
//                while (iter.hasNext()) {
//                    Entry<String, List<Object[]>> entry = iter.next();
//                    if (null != entry.getValue()) {
//                        getSheet(entry.getKey(), entry.getValue(), wws, wwb, startRow[i]);
//                        i++;
//                    }
//                }
//            }
//            wwb.write();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // 关闭流
//            try {
//                if (null != wwb) {
//                    wwb.close();
//                }
//                out.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//	}
//	
//    /**
//     * @throws WriteException 
//     * 
//     * @Title           函数名称：   getSheet
//     * @Description     功能描述：   利用模板导出excel2003
//     * @param           参          数：   
//     * @return          返  回   值：   void  
//     * @throws
//     */
//    private static void getSheet(String key, List<Object[]> value, WritableSheet wws, WritableWorkbook wwb, int y) throws WriteException {
//        wws = wwb.getSheet(key);
//        WritableCellFormat wcf = new WritableCellFormat();
//        wcf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
//        Label label = null;
//        int index = 0;
//        for (int i = y; i < value.get(0).length + y; i++) {
//            for (int j = 0; j < value.size(); j++) {
//                label = new Label(2, i, (String) value.get(0)[index], wcf);
//                try {
//                    wws.addCell(label);
//                } catch (RowsExceededException e) {
//                    e.printStackTrace();
//                } catch (WriteException e) {
//                    e.printStackTrace();
//                }
//
//            }
//            index++;
//        }
//    }
    

	/**
	 * 套餐数据统计
	 * @param pkshop
	 * @param begintime
	 * @param endtime
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getComboReportData(Long pkshop,String begintime,String endtime) {
		List<Object> list;
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer sbf= new StringBuffer();
        sbf.append(" SELECT                                                                                        "); 
        sbf.append(" 	IFNULL(COUNT(1),0) as combocount,                                                          ");
        sbf.append(" 	IFNULL(COUNT(DISTINCT a.pk_customer),0) as customercount,                                  ");
        sbf.append(" 	IFNULL((select  COUNT(DISTINCT a.pk_customer) FROM                                         ");
        sbf.append(" 			customer_order a JOIN customer_combo b ON a.pk_customer_combo=b.pk_customer_combo  ");
        sbf.append(" 		WHERE                                                                                  ");
        sbf.append(" 			IFNULL(a.dr, 0) = 0                                                                ");
        sbf.append(" 		AND IFNULL(b.dr, 0) = 0                                                                ");
        sbf.append(" 		AND a.paystatus = '002'                                                                ");
        sbf.append(" 		AND a.pk_shop = :pkshop                                                                ");
        sbf.append(" 		AND a.iscombo ='Y'                                                                     ");
        sbf.append(" 		AND b.combotype!='2'                                                                   ");
        sbf.append(" 		AND a.paytime <= :endtime),0) as customertotalcount,                                   ");
        sbf.append(" 	IFNULL(SUM(a.ordermoney),0) combototalmoney                                                ");
        sbf.append(" FROM                                                                                          ");
        sbf.append(" 	customer_order a JOIN customer_combo b ON a.pk_customer_combo=b.pk_customer_combo          ");
        sbf.append(" WHERE                                                                                         ");
        sbf.append(" 	IFNULL(a.dr, 0) = 0                                                                        ");
        sbf.append(" AND IFNULL(b.dr, 0) = 0                                                                       ");
        sbf.append(" AND a.paystatus = '002'                                                                       ");
        sbf.append(" AND a.pk_shop = :pkshop                                                                       ");
        sbf.append(" AND a.iscombo ='Y'                                                                            ");
        sbf.append(" AND b.combotype!='2'                                                                          ");
        sbf.append(" AND a.paytime BETWEEN :begintime AND :endtime                                                 ");
        
        map.put("pkshop", pkshop);
        map.put("begintime", begintime);
        map.put("endtime", endtime);
        
        list= (List<Object>) baseDao.querySqlListByConMap2(sbf.toString(),map);
        return list;
	}
	
	/**
	 * 单次数据统计
	 * @param pkshop
	 * @param begintime
	 * @param endtime
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getOnetryReportData(Long pkshop,String begintime,String endtime) {
		List<Object> list;
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer sbf= new StringBuffer();
        sbf.append(" SELECT                                                                                          ");
        sbf.append(" 	IFNULL(count(1),0) onetrycount,                                                              ");
        sbf.append(" 	IFNULL((                                                                                     ");
        sbf.append(" 		SELECT                                                                                   ");
        sbf.append(" 			count(1)                                                                             ");
        sbf.append(" 		FROM                                                                                     ");
        sbf.append(" 			customer_order a JOIN customer_combo b ON a.pk_customer_combo=b.pk_customer_combo    ");
        sbf.append(" 		WHERE                                                                                    ");
        sbf.append(" 			IFNULL(a.dr, 0) = 0                                                                  ");
        sbf.append(" 		AND IFNULL(b.dr, 0) = 0                                                                  ");
        sbf.append(" 		AND a.paystatus = '002'                                                                  ");
        sbf.append(" 		AND a.pk_shop = :pkshop                                                                  ");
        sbf.append(" 		AND a.iscombo ='Y'                                                                       ");
        sbf.append(" 		AND b.fairtype='1'                                                                       ");
        sbf.append(" 		AND b.combotype='2'                                                                      ");
        sbf.append(" 		AND a.paytime BETWEEN :begintime AND :endtime                                            ");
        sbf.append(" 	),0) as permcount,                                                                           ");
        sbf.append(" 	IFNULL((                                                                                     ");
        sbf.append(" 		SELECT                                                                                   ");
        sbf.append(" 			count(1) hairdyecount                                                                ");
        sbf.append(" 		FROM                                                                                     ");
        sbf.append(" 			customer_order a JOIN customer_combo b ON a.pk_customer_combo=b.pk_customer_combo    ");
        sbf.append(" 		WHERE                                                                                    ");
        sbf.append(" 			IFNULL(a.dr, 0) = 0                                                                  ");
        sbf.append(" 		AND IFNULL(b.dr, 0) = 0                                                                  ");
        sbf.append(" 		AND a.paystatus = '002'                                                                  ");
        sbf.append(" 		AND a.pk_shop = :pkshop                                                                  ");
        sbf.append(" 		AND a.iscombo ='Y'                                                                       ");
        sbf.append(" 		AND b.fairtype='2'                                                                       ");
        sbf.append(" 		AND b.combotype='2'                                                                      ");
        sbf.append(" 		AND a.paytime BETWEEN :begintime AND :endtime                                            ");
        sbf.append(" 	),0) as hairdyecount,                                                                        ");
        sbf.append(" 	IFNULL((                                                                                     ");
        sbf.append(" 		SELECT                                                                                   ");
        sbf.append(" 			count(1)                                                                             ");
        sbf.append(" 		FROM                                                                                     ");
        sbf.append(" 			customer_order a JOIN customer_combo b ON a.pk_customer_combo=b.pk_customer_combo    ");
        sbf.append(" 		WHERE                                                                                    ");
        sbf.append(" 			IFNULL(a.dr, 0) = 0                                                                  ");
        sbf.append(" 		AND IFNULL(b.dr, 0) = 0                                                                  ");
        sbf.append(" 		AND a.paystatus = '002'                                                                  ");
        sbf.append(" 		AND a.pk_shop = :pkshop                                                                  ");
        sbf.append(" 		AND a.iscombo ='Y'                                                                       ");
        sbf.append(" 		AND b.fairtype='3'                                                                       ");
        sbf.append(" 		AND b.combotype='2'                                                                      ");
        sbf.append(" 		AND a.paytime BETWEEN :begintime AND :endtime                                            ");
        sbf.append("                                                                                                 ");
        sbf.append(" 	),0) as haircarecount,                                                                       ");
        sbf.append(" 	IFNULL((                                                                                     ");
        sbf.append(" 			SELECT                                                                               ");
        sbf.append(" 			count(1) haircount                                                                   ");
        sbf.append(" 		FROM                                                                                     ");
        sbf.append(" 			customer_order a JOIN customer_combo b ON a.pk_customer_combo=b.pk_customer_combo    ");
        sbf.append(" 		WHERE                                                                                    ");
        sbf.append(" 			IFNULL(a.dr, 0) = 0                                                                  ");
        sbf.append(" 		AND IFNULL(b.dr, 0) = 0                                                                  ");
        sbf.append(" 		AND a.paystatus = '002'                                                                  ");
        sbf.append(" 		AND a.pk_shop = :pkshop                                                                  ");
        sbf.append(" 		AND a.iscombo ='Y'                                                                       ");
        sbf.append(" 		AND b.fairtype='4'                                                                       ");
        sbf.append(" 		AND b.combotype='2'                                                                      ");
        sbf.append(" 		AND a.paytime BETWEEN :begintime AND :endtime                                            ");
        sbf.append(" 	),0) as haircount,                                                                           ");
        sbf.append(" 	0,                                                                                           ");
        sbf.append(" 	IFNULL(SUM(a.ordermoney),0) as onetrytotalmoney                                              ");
        sbf.append(" FROM                                                                                            ");
        sbf.append(" 	customer_order a JOIN customer_combo b ON a.pk_customer_combo=b.pk_customer_combo            ");
        sbf.append(" WHERE                                                                                           ");
        sbf.append(" 	IFNULL(a.dr, 0) = 0                                                                          ");
        sbf.append(" AND IFNULL(b.dr, 0) = 0                                                                         ");
        sbf.append(" AND a.paystatus = '002'                                                                         ");
        sbf.append(" AND a.pk_shop = :pkshop                                                                         ");
        sbf.append(" AND a.iscombo ='Y'                                                                              ");
        sbf.append(" AND b.fairtype in ('1','2','3','4')                                                             ");
        sbf.append(" AND b.combotype='2'                                                                             ");
        sbf.append(" AND a.paytime BETWEEN :begintime AND :endtime                                                   ");
        
        map.put("pkshop", pkshop);
        map.put("begintime", begintime);
        map.put("endtime", endtime);
        
        list= (List<Object>) baseDao.querySqlListByConMap2(sbf.toString(),map);
        return list;
	}
	
	
	/**
	 * 服务数据统计
	 * @param pkshop
	 * @param begintime
	 * @param endtime
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getServiceReportData(Long pkshop,String begintime,String endtime) {
		List<Object> list;
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer sbf= new StringBuffer();
        sbf.append("  SELECT                                          ");
        sbf.append("  	IFNULL(COUNT(1),0) servicecount,              ");
        sbf.append("  	IFNULL(SUM(ordermoney),0) as servicemoney     ");
        sbf.append("  FROM                                            ");
        sbf.append("  	customer_order                                ");
        sbf.append("  WHERE                                           ");
        sbf.append("  	IFNULL(dr, 0) = 0                             ");
        sbf.append("  AND paystatus = '002'                           ");
        sbf.append("  AND pk_shop = :pkshop                           ");
        sbf.append("  AND IFNULL(iscombo, 'N') = 'N'                  ");
        sbf.append("  AND paytime BETWEEN :begintime AND :endtime     ");
        
        map.put("pkshop", pkshop);
        map.put("begintime", begintime);
        map.put("endtime", endtime);
        
        list= (List<Object>) baseDao.querySqlListByConMap2(sbf.toString(),map);
        return list;
	}
	
	
	/**
	 * 外卖/增项
	 * @param pkshop
	 * @param begintime
	 * @param endtime
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getAdditionReportData(Long pkshop,String begintime,String endtime) {
		List<Object> list;
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer sbf= new StringBuffer();
        
        sbf.append("  SELECT                                                                              ");
        sbf.append("  	0,                                                                                ");
        sbf.append("  	IFNULL(SUM(b.detailmoney),0) as additionmoney,                                    ");
        sbf.append("  	IFNULL((                                                                          ");
        sbf.append("  		SELECT                                                                        ");
        sbf.append("  			SUM(b.detailmoney)                                                        ");
        sbf.append("  		FROM                                                                          ");
        sbf.append("  			customer_order a JOIN customer_order_detail b ON a.pk_order=b.pk_order    ");
        sbf.append("  		WHERE                                                                         ");
        sbf.append("  			IFNULL(a.dr, 0) = 0                                                       ");
        sbf.append("  		AND IFNULL(b.dr, 0) = 0                                                       ");
        sbf.append("  		AND a.paystatus = '002'                                                       ");
        sbf.append("  		AND a.pk_shop = :pkshop                                                       ");
        sbf.append("  		AND b.detailtype !='ADDITION'                                                 ");
        sbf.append("  		AND a.paytime BETWEEN :begintime AND :endtime                                 ");
        sbf.append("  	),0) as detailservicemoney                                                        ");
        sbf.append("  FROM                                                                                ");
        sbf.append("  	customer_order a JOIN customer_order_detail b ON a.pk_order=b.pk_order            ");
        sbf.append("  WHERE                                                                               ");
        sbf.append("  	IFNULL(a.dr, 0) = 0                                                               ");
        sbf.append("  AND IFNULL(b.dr, 0) = 0                                                             ");
        sbf.append("  AND a.paystatus = '002'                                                             ");
        sbf.append("  AND a.pk_shop = :pkshop                                                             ");
        sbf.append("  AND b.detailtype='ADDITION'                                                         ");
        sbf.append("  AND a.paytime BETWEEN :begintime AND :endtime                                       ");
        
        map.put("pkshop", pkshop);
        map.put("begintime", begintime);
        map.put("endtime", endtime);
        
        list= (List<Object>) baseDao.querySqlListByConMap2(sbf.toString(),map);
        return list;
	}
	
	
	
	/**
	 * 剪发、造型、洗头
	 * @param pkshop
	 * @param begintime
	 * @param endtime
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getCutfairReportData(Long pkshop,String begintime,String endtime) {
		List<Object> list;
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer sbf= new StringBuffer();
        
        sbf.append("  SELECT                                                                                  ");
        sbf.append("  	IFNULL(SUM(a.ordermoney),0) as cutfairtotalmoney                                      ");
        sbf.append("  FROM                                                                                    ");
        sbf.append("  	customer_order a JOIN customer_combo b ON a.pk_customer_combo=b.pk_customer_combo     ");
        sbf.append("  WHERE                                                                                   ");
        sbf.append("  	IFNULL(a.dr, 0) = 0                                                                   ");
        sbf.append("  AND IFNULL(b.dr, 0) = 0                                                                 ");
        sbf.append("  AND a.paystatus = '002'                                                                 ");
        sbf.append("  AND a.pk_shop = :pkshop                                                                 ");
        sbf.append("  AND b.fairtype in ('5','6','7','8')                                                     ");
        sbf.append("  AND a.paytime BETWEEN :begintime AND :endtime                                           ");
        
        map.put("pkshop", pkshop);
        map.put("begintime", begintime);
        map.put("endtime", endtime);
        
        list= (List<Object>) baseDao.querySqlListByConMap2(sbf.toString(),map);
        return list;
	}
	
	/**
	 * 总收入
	 * @param pkshop
	 * @param begintime
	 * @param endtime
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getTotalMoneyData(Long pkshop,String begintime,String endtime) {
		List<Object> list;
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer sbf= new StringBuffer();
        sbf.append("  SELECT                                                                                         ");
        sbf.append("  	IFNULL(SUM(a.ordermoney),0)+                                                                 ");
        sbf.append("  		IFNULL((SELECT                                                                           ");
        sbf.append("  			SUM(a.ordermoney) as onetrytotalmoney                                                ");
        sbf.append("  		FROM                                                                                     ");
        sbf.append("  			customer_order a JOIN customer_combo b ON a.pk_customer_combo=b.pk_customer_combo    ");
        sbf.append("  		WHERE                                                                                    ");
        sbf.append("  			IFNULL(a.dr, 0) = 0                                                                  ");
        sbf.append("  		AND IFNULL(b.dr, 0) = 0                                                                  ");
        sbf.append("  		AND a.paystatus = '002'                                                                  ");
        sbf.append("  		AND a.pk_shop = :pkshop                                                                  ");
        sbf.append("  		AND a.iscombo ='Y'                                                                       ");
        sbf.append("  		AND b.fairtype in ('1','2','3','4')                                                      ");
        sbf.append("  		AND b.combotype='2'                                                                      ");
        sbf.append("  		AND a.paytime BETWEEN :begintime AND :endtime),0)                                        ");
        sbf.append("  	+IFNULL((                                                                                    ");
        sbf.append("  		SELECT                                                                                   ");
        sbf.append("  			SUM(ordermoney)                                                                      ");
        sbf.append("  		FROM                                                                                     ");
        sbf.append("  			customer_order                                                                       ");
        sbf.append("  		WHERE                                                                                    ");
        sbf.append("  			IFNULL(dr, 0) = 0                                                                    ");
        sbf.append("  		AND paystatus = '002'                                                                    ");
        sbf.append("  		AND pk_shop = :pkshop                                                                    ");
        sbf.append("  		AND IFNULL(iscombo, 'N') = 'N'                                                           ");
        sbf.append("  		AND paytime BETWEEN :begintime AND :endtime                                              ");
        sbf.append("  	),0) as total                                                                                ");
        sbf.append("  FROM                                                                                           ");
        sbf.append("  	customer_order a JOIN customer_combo b ON a.pk_customer_combo=b.pk_customer_combo            ");
        sbf.append("  WHERE                                                                                          ");
        sbf.append("  	IFNULL(a.dr, 0) = 0                                                                          ");
        sbf.append("  AND IFNULL(b.dr, 0) = 0                                                                        ");
        sbf.append("  AND a.paystatus = '002'                                                                        ");
        sbf.append("  AND a.pk_shop = :pkshop                                                                        ");
        sbf.append("  AND a.iscombo ='Y'                                                                             ");
        sbf.append("  AND b.combotype!='2'                                                                           ");
        sbf.append("  AND a.paytime BETWEEN :begintime AND :endtime                                                  ");
        
        map.put("pkshop", pkshop);
        map.put("begintime", begintime);
        map.put("endtime", endtime);
        
        list= (List<Object>) baseDao.querySqlListByConMap2(sbf.toString(),map);
        return list;
	}
	
	/**
	 * 总计
	 * @param pkshop
	 * @param begintime
	 * @param endtime
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getTotalReportData(Long pkshop,String begintime,String endtime) {
		List<Object> list;
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer sbf= new StringBuffer();
        sbf.append("  select                                                                                       ");
        sbf.append("  	IFNULL((                                                                                          ");
        sbf.append("  		SELECT                                                                                 ");
        sbf.append("  			sum(ordermoney)                                                                    ");
        sbf.append("  		FROM                                                                                   ");
        sbf.append("  			customer_order                                                                     ");
        sbf.append("  		WHERE                                                                                  ");
        sbf.append("  			IFNULL(dr, 0) = 0                                                                  ");
        sbf.append("  		AND paystatus = '002'                                                                  ");
        sbf.append("  		AND pk_shop = :pkshop                                                                  ");
        sbf.append("  		and paymode !='2'                                                                      ");
        sbf.append("  		AND paytime BETWEEN :begintime AND :endtime                                            ");
        sbf.append("  	),0) as moneytotal,                                                                           ");
        sbf.append("  	IFNULL((                                                                                          ");
        sbf.append("  		select                                                                                 ");
        sbf.append("  			COUNT(DISTINCT a.pk_customer)                                                      ");
        sbf.append("  		FROM                                                                                   ");
        sbf.append("  			customer_order a JOIN customer_combo b ON a.pk_customer_combo=b.pk_customer_combo  ");
        sbf.append("  		WHERE                                                                                  ");
        sbf.append("  			IFNULL(a.dr, 0) = 0                                                                ");
        sbf.append("  		AND IFNULL(b.dr, 0) = 0                                                                ");
        sbf.append("  		AND a.paystatus = '002'                                                                ");
        sbf.append("  		AND a.pk_shop = :pkshop                                                                ");
        sbf.append("  		AND a.paytime BETWEEN :begintime AND :endtime                                          ");
        sbf.append("  	),0) as customercount,                                                                     ");
        sbf.append("  	IFNULL(COUNT(DISTINCT a.pk_customer),0) customertotalcount,                                ");
        sbf.append("  0 as vipmomey                                                                                ");
        sbf.append("  FROM                                                                                         ");
        sbf.append("  	customer_order a                                                                           ");
        sbf.append("  WHERE                                                                                        ");
        sbf.append("  	IFNULL(a.dr, 0) = 0                                                                        ");
        sbf.append("  AND a.paystatus = '002'                                                                      ");
        sbf.append("  AND a.pk_shop = :pkshop                                                                      ");
        sbf.append("  AND a.paytime BETWEEN :begintime AND :endtime                                                ");
        
        map.put("pkshop", pkshop);
        map.put("begintime", begintime);
        map.put("endtime", endtime);
        
        list= (List<Object>) baseDao.querySqlListByConMap2(sbf.toString(),map);
        return list;
	}
	
    
    
    
    
    
}
