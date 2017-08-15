package com.rockstar.o2o.util;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 汉字转拼音
 * @author xc
 * @since 2015-02-10
 */
public class CharUtil {
	
	/**
	 * 将中文转成拼音
	 * @param source
	 * @return
	 */
  public static String chineseToPingyin(String source){
	  if(source==null||source.equals("")){
		  return "";
	  }
	  char [] sourceChar=source.toCharArray();
	  HanyuPinyinOutputFormat pyFormat=new HanyuPinyinOutputFormat();
	  pyFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
	  pyFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE );//忽略声调
	  pyFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
	  
	  String [] temp=new String[sourceChar.length];
	  StringBuffer result=new StringBuffer(20);
	  try {
		for (int i = 0; i < sourceChar.length; i++) {
			if(Character.toString(sourceChar[i]).matches("[\\u4E00-\\u9FA5]+")){
				temp=PinyinHelper.toHanyuPinyinStringArray(sourceChar[i],pyFormat);
				result.append(temp[0]);
			}else{
				result.append(Character.toString(sourceChar[i]));
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result.toString();
  }

  /**
   * 获取首字母
   * @param source
   * @return
   */
  public static String getHeadChar(String source){
	  if(source==null||source.equals("")){
		  return "";
	  }
	  HanyuPinyinOutputFormat pyFormat=new HanyuPinyinOutputFormat();
	  pyFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
	  pyFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE );//忽略声调
	  pyFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
	  
	  StringBuffer result=new StringBuffer(20);
		try {
			for (int i = 0; i < source.length(); i++) {
				if(Character.toString(source.charAt(i)).matches("[\\u4E00-\\u9FA5]+")){
					String [] pinyinarr=PinyinHelper.toHanyuPinyinStringArray(source.charAt(i),pyFormat);
					result.append(pinyinarr[0].charAt(0));
				}else{
					result.append(Character.toString(source.charAt(i)));
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return result.toString();
  }
}
