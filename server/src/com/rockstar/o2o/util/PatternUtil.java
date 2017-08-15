package com.rockstar.o2o.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rockstar.o2o.constant.Patternformat;

/**
 * 正则验证
 * @author xc
 *
 */
public class PatternUtil {

	
	/**
	 * 通过正则验证数字
	 * @param content
	 * @return true/false
	 */
   public static boolean validatenum(String str){
	Pattern p = Pattern.compile(Patternformat.ALL_NUM); 
    Matcher m = p.matcher(str); 
    boolean flag = m.matches(); 
	return flag;
}

   
	 /**
	 * 通过正则验证长度
	 * @param content
	 * @return true/false
	 */
   public static boolean validatelength(String str){
	Pattern p = Pattern.compile(Patternformat.LENGTH_LIMIT); 
    Matcher m = p.matcher(str); 
    boolean flag = m.matches(); 
	return flag;
}
  
	 /**
	 * 通过正则验证手机号
	 * @param content
	 * @return true/false
	 */
    public static boolean validatecellphone(String str){
	Pattern p = Pattern.compile(Patternformat.CELLPHONE); 
    Matcher m = p.matcher(str); 
    boolean flag = m.matches(); 
	return flag;
    }
  
	 /**
	 * 通过正则验证固定号码
	 * @param content
	 * @return true/false
	 */
    public static boolean validatefixphone(String str){
	Pattern p = Pattern.compile(Patternformat.FIXPHONE); 
    Matcher m = p.matcher(str); 
    boolean flag = m.matches(); 
	return flag;
    }
    
	 /**
	 * 通过正则验证固定号码
	 * @param content
	 * @return true/false
	 */
    public static boolean validatename(String str){
	Pattern p = Pattern.compile(Patternformat.SPECIALSTR); 
    Matcher m = p.matcher(str); 
    boolean flag = m.matches(); 
	return flag;
    }
    
}
