package com.rockstar.o2o.weixin.handle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.weixin.conf.SysConf;
import com.rockstar.o2o.weixin.http.service.IHttpClientService;
import com.rockstar.o2o.weixin.pojo.WxAccessToken;
import com.rockstar.o2o.weixin.service.BasicService;
import com.rockstar.o2o.weixin.util.DateUtil;
import com.rockstar.o2o.weixin.util.RedisUtils;




import net.sf.json.JSONObject;


/**
 * 获取AccessToken
 * @author xc
 *
 */
@Component
public class AccessTokenHandle{

	@Resource
	protected  BasicService basicservice;
	
	@Resource
	protected  IHttpClientService httpservice;
	
	@Resource
	protected  SysConf wxsysConf;
	
	private static  String env = System.getenv("CHORHAIR");
	 
	static  ResourceBundle bundle = ResourceBundle.getBundle("wx");
	static  String app_id =(env==null||env.equals("test"))?bundle.getString("test_app_id") :
		bundle.getString("build_app_id");
	static  String app_secret = (env==null||env.equals("test"))?bundle.getString("test_app_secret") :
		bundle.getString("build_app_secret"); 
	static  String account_id = (env==null||env.equals("test"))?bundle.getString("test_account_id") :
		bundle.getString("build_account_id") ; 
	static  String token = (env==null||env.equals("test"))?bundle.getString("test_token") :
		bundle.getString("build_token"); 
	
	public AccessTokenHandle(){

	}
	/**
	 * 查找已经存在的AccessToken
	 * @return
	 */
	public  String GetFirstAccessToken(){
		
		boolean isredis=true;
		 String token="";
		try {
			token=RedisUtils.getObject(account_id+":"+"AccessToken")==null?null:RedisUtils.getObject(account_id+":"+"AccessToken").toString();
		} catch (Exception e) {
			// TODO: handle exception
			isredis=false;
		}
		if(!isredis||token==null||token.equals("")){
		    ArrayList<Object> list=(ArrayList<Object>) basicservice.query(WxAccessToken.class, " accountId = ? order by EXPIRES_IN DESC LIMIT 0,1", account_id);		      
	        if(list.size()>0){
			    WxAccessToken accesstoken=(WxAccessToken) list.get(0);	
			    token=accesstoken.getAccessToken();	
			    try {
			    	RedisUtils.delKey(account_id+":"+"AccessToken");
					RedisUtils.setKey(account_id+":"+"AccessToken", token);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     }
		}

        return token==null?"":token;
	}
	
	  /**
     * 申请AccessToken
     * @param accountId
     * @return
     * @throws Exception
     */
	public  String applyAccessToken()
    throws Exception
      {	      
	
           String token="";
           StringBuffer sb = new StringBuffer().append(wxsysConf.getBaseAccessTokenUrl());
    	    String uri = "grant_type=client_credential" + 
    	      "&appid=" + app_id+
    	      "&secret=" + app_secret;
    	     uri=sb.append(uri).toString().trim();
    	    String json = null;
    	    json = httpservice.sendGetRequset(uri);
    	    if ((json != null) && (!json.equals(""))) {
    	       JSONObject obj=JSONObject.fromObject(json);
    	       token = (String)obj.get("access_token");
    	    }
    	    
		    try {
		    	RedisUtils.delKey(account_id+":"+"AccessToken");
				RedisUtils.setKey(account_id+":"+"AccessToken", token);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
    	    WxAccessToken savetoken=new WxAccessToken();
			savetoken.setAccessToken(token);
			savetoken.setAccountId(account_id);
			Calendar calendar = Calendar.getInstance();
		    calendar.add(13, 7200);
		    String expireTime = DateUtil.DateToString(calendar.getTime(), DateUtil.FORMAT_ONE);
		    savetoken.setExpiresIn(expireTime);
		    savetoken.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		    savetoken.setDr((short)0);
			basicservice.save(savetoken);
    	    return token;
      }  
    
}
