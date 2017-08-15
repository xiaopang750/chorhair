package com.rockstar.o2o.util;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

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
	
	     public static void main(String[] args) {
	         /**
	          * 此方法产生不重复随机数
	          */
	         Scanner input =new Scanner(System.in);
	         System.out.print("请输入抽取随机数的个数:");
	         int count = input.nextInt();
	         String []str = {"张三","李四","王二","李逵","张飞"};
	         boolean []used =new boolean[str.length];//默认是false
	         String [] result = new String[count];
	         for(int i =0;i<count;i++){
	             int r;
	             do{
	                 r =(int)(Math.random()*str.length);   
	             }while(used[i]);
	             result[i]=str[r];
	             used[i]=true;
	         }
	         System.out.println(Arrays.toString(result));
	     }

	 
	public static int[] randoms(int sumNum, int num) {
	    int returnValue[] = null;
	    if (num <= sumNum) {
	        returnValue = new int[num];
	        Random r = new Random();
	 
	        int temp1, temp2;
	        // 生成范围数组
	        int send[] = new int[sumNum];
	        int len = send.length;
	        for (int i = 0; i < len; i++) {
	            send[i] = i + 1;
	        }
	 
	        for (int i = 0; i < num; i++) {
	            temp1 = Math.abs(r.nextInt()) % len;
	            returnValue[i] = send[temp1];
	            temp2 = send[temp1];
	            send[temp1] = send[len - 1];
	            send[len - 1] = temp2;
	            len--;
	        }
	    }
	    return returnValue;
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
