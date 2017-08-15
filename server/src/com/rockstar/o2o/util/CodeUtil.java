package com.rockstar.o2o.util;

import java.util.Random;

/**
 * 生成随机验证码
 * @author xc
 * 生成验证码 (getRandomStr) 
 */
public class CodeUtil { 
     //随机字符数组  
	private static char[] charSequence = "0123456789".toCharArray();  
	private static Random random = new Random();  
	public CodeUtil(){
		
	}
	
	   // 随机生成一个字符    
	    private static String getRandomChar() {    
	        int index = random.nextInt(charSequence.length);  
	        return String.valueOf(charSequence[index]);  
	    } 

	
	   /** 获取随机的字符    */   	
	   public static String getRandomStr(int CODE_NUM){
		  StringBuilder sRand = new StringBuilder(CODE_NUM);    
		    for (int i = 0; i < CODE_NUM; i++) {    
		        // 取得一个随机字符    
		        String tmp = getRandomChar();    
		        sRand.append(tmp);  
               }    
             return sRand.toString();
    	}
	
}
