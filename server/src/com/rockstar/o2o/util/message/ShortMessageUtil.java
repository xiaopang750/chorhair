package com.rockstar.o2o.util.message;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.rpc.ServiceException;

import com.rockstar.o2o.util.PubUtil;
import com.rockstar.o2o.webservice.shortmessage.WebService;
import com.rockstar.o2o.webservice.shortmessage.WebServiceLocator;
import com.rockstar.o2o.webservice.shortmessage.WebServiceSoap;

/**
 * 短信工具类
 * @author xc
 *
 */
public class ShortMessageUtil {
	/*
	 * webservice服务器定义
	 */
	private  String serviceURL =PubUtil.getServerURL("ShortMessageService");

	private  String sn = "SDK-XJG-010-00275";// 序列号
	private  String password = "818088";
	private  String pwd = "";// 密码
	
	private  WebService shortmessagewebservice = new WebServiceLocator();
	/*
	 * 构造函数
	 */
	public ShortMessageUtil()
			throws UnsupportedEncodingException {
		this.pwd = this.getMD5(sn + password);
	}

	private  WebServiceSoap getIShortmessageService() throws MalformedURLException, ServiceException
	{
		WebServiceSoap client = shortmessagewebservice.getWebServiceSoap(new URL(serviceURL));
		return client;
	}
	
	
	/*
	 * 方法名称：getMD5 
	 * 功    能：字符串MD5加密 
	 * 参    数：待转换字符串 
	 * 返 回 值：加密之后字符串
	 */
	public String getMD5(String sourceStr) throws UnsupportedEncodingException {
		String resultStr = "";
		try {
			byte[] temp = sourceStr.getBytes();
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(temp);
			// resultStr = new String(md5.digest());
			byte[] b = md5.digest();
			for (int i = 0; i < b.length; i++) {
				char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
						'9', 'A', 'B', 'C', 'D', 'E', 'F' };
				char[] ob = new char[2];
				ob[0] = digit[(b[i] >>> 4) & 0X0F];
				ob[1] = digit[b[i] & 0X0F];
				resultStr += new String(ob);
			}
			return resultStr;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * 方法名称：register 
	 * 功    能：注册 
	 * 参    数：对应参数 省份，城市，行业，企业名称，联系人，电话，手机，电子邮箱，传真，地址，邮编 
	 * 返 回 值：注册结果（String）
	 */
	public  String register(String province, String city, String trade,
			String entname, String linkman, String phone, String mobile,
			String email, String fax, String address, String postcode) {
		String result = "";
		try {
			result=getIShortmessageService().register(sn, pwd, province, city, trade, entname, linkman, phone, mobile, email, fax, address, postcode, "");
			return new String(result.getBytes(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/*
	 * 方法名称：chargeFee 
	 * 功    能：充值 
	 * 参    数：充值卡号，充值密码 
	 * 返 回 值：操作结果（String）
	 */
	public  String chargeFee(String cardno, String cardpwd) {
		String result = "";
		try {
			result=getIShortmessageService().chargUp(sn, pwd, cardno, cardpwd);
			return new String(result.getBytes(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/*
	 * 方法名称：getBalance 
	 * 功    能：获取余额 
	 * 参    数：无 
	 * 返 回 值：余额（String）
	 */
	public  String getBalance() {
		String result = "";
		try {
			result=getIShortmessageService().balance(sn, pwd);
			return new String(result.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/*
	 * 方法名称：mt 
	 * 功    能：发送短信 
	 * 参    数：mobile,content,ext,stime,rrid(手机号，内容，扩展码，定时时间，唯一标识)
	 * 返 回 值：唯一标识，如果不填写rrid将返回系统生成的
	 */
	public  String mt(String mobile, String content, String ext, String stime,
			String rrid) {
		String result = "";
		try {
		    result=getIShortmessageService().mt(sn, pwd, mobile, content, ext, stime, rrid);			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/*
	 * 方法名称：mo 
	 * 功    能：接收短信 
	 * 参    数：无 
	 * 返 回 值：接收到的信息
	 */
	public  String mo() {
		String result = "";
		try {
			result=getIShortmessageService().mo(sn, pwd);
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/*
	 * 方法名称：msgid 
	 * 功    能：获取msgid（发送成功的最后100次） 
	 * 参    数：无 
	 * 返 回 值：msgid串
	 */
	public String msgid() {
		String result = "";
		try {
			result=getIShortmessageService().msgid(sn, pwd);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}	
	
	/*
	 * 方法名称：TestCode  
	 * 功    能：获取TestCode （编解码） 
	 * 参    数：无 
	 * 返 回 值：TestCode 串
	 */
	public String TestCode (String content, String code, String codetype) {
		String result = "";
		try {
			result=getIShortmessageService().testCode(content, code, codetype);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
