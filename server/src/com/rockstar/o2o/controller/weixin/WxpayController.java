package com.rockstar.o2o.controller.weixin;

import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rockstar.o2o.controller.BaseController;
import com.rockstar.o2o.controller.weixin.utils.GetWxOrderno;
import com.rockstar.o2o.controller.weixin.utils.RequestHandler;
import com.rockstar.o2o.controller.weixin.utils.Sha1Util;
import com.rockstar.o2o.controller.weixin.utils.TenpayUtil;
import com.rockstar.o2o.controller.weixin.utils.bean.WxPayDto;
import com.rockstar.o2o.pojo.CustomerOrder;
import com.rockstar.o2o.util.RequestUtil;
/**
 * 微信支付
 * @author xucheng
 *
 */
@Controller
@RequestMapping("/wxpay")
public class WxpayController extends BaseController{

	//微信支付商户开通后 微信会提供appid和appsecret和商户号partner
	private static String appid = "wxbef7dd5b6517baef";
	private static String appsecret = "6iTeg8ilFzKyY8moPWDPYRkQQArzXfxVhRv6q4A1TQxjXMrcV8QJGC0YiZ77OYd5LZaCTfpX9VmfsVXxv8yBOWJxiWECA3evxOV6YoJXD3u8XWXopKDwXcC2OuwI2wUh";
	private static String partner = "1236229302";
	//这个参数partnerkey是在商户后台配置的一个32位的key,微信商户平台-账户设置-安全设置-api安全
	private static String partnerkey = "89621201069855c95fba23083935312e";
	//微信支付成功后通知地址 必须要求80端口并且地址不能带参数
	private static String notifyurl = "http://rsh5.rsdataservice.com/h5/main/pay_order.html";
	
    /**
     * 扫码支付,生成支付的二维码
     * @param request
     * @param response
     * @param model
     * @throws IOException
     */
	@SuppressWarnings("static-access")
	@RequestMapping("/creatqr")
	public void createevent(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {		
		try {
		JSONObject obj=RequestUtil.getPostString(request);
		
		String pkOrder=obj.getString("pkOrder");
		
		CustomerOrder order=customerorderService.getOrderById(Long.parseLong(pkOrder));
		
	    WxPayDto tpWxPayDto = new WxPayDto();
	    tpWxPayDto.setBody(order.getShopname()+"|"+order.getComboname()+"|"+(order.getIscombo()!=null&&order.getIscombo().equals("Y")?"套餐费":"服务费"));
	    tpWxPayDto.setOrderId(getNonceStr());
	    tpWxPayDto.setSpbillCreateIp("127.0.0.1");
	    tpWxPayDto.setTotalFee(order.getOrdermoney().toString());

		// 订单号
		String orderId = order.getOrderno();
		// 附加数据 原样返回
		String attach = "";
		// 总金额以分为单位，不带小数点
		String totalFee = getMoney(order.getOrdermoney().toString());
		
		// 订单生成的机器 IP
		String spbill_create_ip = tpWxPayDto.getSpbillCreateIp();
		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String notify_url = notifyurl;
		String trade_type = "NATIVE";

		// 商户号
		String mch_id = partner;
		// 随机字符串
		String nonce_str = getNonceStr();

		// 商品描述根据情况修改
		String body = tpWxPayDto.getBody();

		// 商户订单号
		String out_trade_no = orderId;

		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("attach", attach);
		packageParams.put("out_trade_no", out_trade_no);

		// 这里写的金额为1 分到时修改
		packageParams.put("total_fee", totalFee);
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);

		packageParams.put("trade_type", trade_type);

		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(appid, appsecret, partnerkey);

		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
				+ "</nonce_str>" + "<sign>" + sign + "</sign>"
				+ "<body><![CDATA[" + body + "]]></body>" 
				+ "<out_trade_no>" + out_trade_no
				+ "</out_trade_no>" + "<attach>" + attach + "</attach>"
				+ "<total_fee>" + totalFee + "</total_fee>"
				+ "<spbill_create_ip>" + spbill_create_ip
				+ "</spbill_create_ip>" + "<notify_url>" + notify_url
				+ "</notify_url>" + "<trade_type>" + trade_type
				+ "</trade_type>" + "</xml>";
		
		String code_url = "";
		
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		
		
		code_url = new GetWxOrderno().getCodeUrl(createOrderURL, xml);
		
		JSONObject returnobj=new JSONObject();
		
		returnobj.accumulate("code_url", code_url);
		
		outputstr(returnobj.toString(), "生成付款二维码成功", true, null);
		
	    } catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		outputexceptionstr();
	   }
		output(response, pojo);
	}
	
	/**
	 * 获取请求预支付id报文
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/getpackage")
	public void getPackage(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {		
        try {
       JSONObject receiveobj=RequestUtil.getPostString(request);
       
		String openId = receiveobj.getString("openid");
		
		String pkOrder=receiveobj.getString("state");
		
		CustomerOrder order=customerorderService.getOrderById(Long.parseLong(pkOrder));
		
		// 订单号
		String orderId = order.getOrderno();
		// 附加数据 原样返回
		String attach = "";
		// 总金额以分为单位，不带小数点
		String totalFee = getMoney(order.getOrdermoney().toString());
		
		// 订单生成的机器 IP
		String spbill_create_ip = "127.0.0.1";
		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String notify_url = notifyurl;
		String trade_type = "JSAPI";

		// ---必须参数
		// 商户号
		String mch_id = partner;
		// 随机字符串
		String nonce_str = getNonceStr();

		// 商品描述根据情况修改
		String body =order.getShopname()+"|"+order.getComboname()+"|"+(order.getIscombo()!=null&&order.getIscombo().equals("Y")?"套餐费":"服务费");

		// 商户订单号
		String out_trade_no = orderId;

		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("attach", attach);
		packageParams.put("out_trade_no", out_trade_no);

		// 这里写的金额为1 分到时修改
		packageParams.put("total_fee", totalFee);
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);

		packageParams.put("trade_type", trade_type);
		packageParams.put("openid", openId);

		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(appid, appsecret, partnerkey);

		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
				+ "</nonce_str>" + "<sign>" + sign + "</sign>"
				+ "<body><![CDATA[" + body + "]]></body>" 
				+ "<out_trade_no>" + out_trade_no
				+ "</out_trade_no>" + "<attach>" + attach + "</attach>"
				+ "<total_fee>" + totalFee + "</total_fee>"
				+ "<spbill_create_ip>" + spbill_create_ip
				+ "</spbill_create_ip>" + "<notify_url>" + notify_url
				+ "</notify_url>" + "<trade_type>" + trade_type
				+ "</trade_type>" + "<openid>" + openId
				+ "</openid>" 
				+ "</xml>";
		String prepay_id = "";
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		
		
		prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);

		System.out.println("获取到的预支付ID：" + prepay_id);
		
		
		//获取prepay_id后，拼接最后请求支付所需要的package
		
		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
		String timestamp = Sha1Util.getTimeStamp();
		String packages = "prepay_id="+prepay_id;
		finalpackage.put("appId", appid);  
		finalpackage.put("timeStamp", timestamp);  
		finalpackage.put("nonceStr", nonce_str);  
		finalpackage.put("package", packages);  
		finalpackage.put("signType", "MD5");
		//要签名
		String finalsign = reqHandler.createSign(finalpackage);
		
		String finalPackage = "\"appId\":\"" + appid + "\",\"timeStamp\":\"" + timestamp
		+ "\",\"nonceStr\":\"" + nonce_str + "\",\"package\":\""
		+ packages + "\",\"signType\" : \"MD5" + "\",\"paySign\":\""
		+ finalsign + "\"";

		JSONObject returnobj=new JSONObject();
		
		returnobj.accumulate("finalPackage", finalPackage);
		
		outputstr(returnobj.toString(), "生成支付信息成功", true, null);
		
        } catch (Exception e) {
        	// TODO: handle exception
        	e.printStackTrace();
        	outputexceptionstr();
        }
        output(response, pojo);
	}
	
	
	
	/**
	 * 获取随机字符串
	 * @return
	 */
	public static String getNonceStr() {
		// 随机数
		String currTime = TenpayUtil.getCurrTime();
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		return strTime + strRandom;
	}
	
	
	/**
	 * 元转换成分
	 * @param money
	 * @return
	 */
	public static String getMoney(String amount) {
		if(amount==null){
			return "";
		}
		// 金额转化为分为单位
		String currency =  amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额  
        int index = currency.indexOf(".");  
        int length = currency.length();  
        Long amLong = 0l;  
        if(index == -1){  
            amLong = Long.valueOf(currency+"00");  
        }else if(length - index >= 3){  
            amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", ""));  
        }else if(length - index == 2){  
            amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+0);  
        }else{  
            amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"00");  
        }  
        return amLong.toString(); 
	}
	
}
