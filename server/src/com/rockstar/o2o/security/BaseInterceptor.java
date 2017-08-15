package com.rockstar.o2o.security;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.rockstar.o2o.basicservice.BasicService;
import com.rockstar.o2o.pojo.ControllerRequest;
import com.rockstar.o2o.pojo.ShopUser;
import com.rockstar.o2o.service.ShopUserService;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.RequestUtil;
import com.rockstar.o2o.util.ReturnPojo;
import com.rockstar.o2o.util.des.EncodeUtil;


@Component
public class BaseInterceptor extends HandlerInterceptorAdapter
{

	 private ReturnPojo pojo=new ReturnPojo();
	 
	 @Resource
	 private ShopUserService shopuserservice;
	 
	 @Resource
	 private BasicService basicservice;
	 
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
    	
      try {
      String method = request.getMethod();
      String url = request.getRequestURI();
        if (method.equalsIgnoreCase("get")) {
        	 outputstr("", "非法请求", false, null);
        	 output(response, pojo);
             return false;
        } else if (method.equalsIgnoreCase("post")){
        	 JSONObject obj=RequestUtil.getOldPostString(request);
        	 String openid=(String)obj.get("openid");
        	 String pkUser=obj.get("pkUser")==null?"":obj.getString("pkUser");
        	 String pkShop=obj.get("pkShop")==null?"":obj.getString("pkShop");
        	 if(openid!=null&&!openid.equals("")){
        		 System.out.println("微信请求,暂时不拦截");
        	 }else{
             if(!url.contains("shopuser/login")&&!url.contains("content/querybykey")&&!url.contains("content/queryhot")){        	        	
             if(pkUser.equals("")){
            	 outputstr("", "无用户信息", false, null);
            	 output(response, pojo);
            	 return false;
             }else{
            	//解密后的pkUser
            	 String[] str=EncodeUtil.decode(pkUser).split(":");
            	 if(str[0].equals("shop")||str[0].equals("plat")){
            	       	pkUser=str[1].toString();
                    	ShopUser user=shopuserservice.getUserById(Long.parseLong(pkUser));
                    	if(pkShop.equals("")){
                    		 outputstr("", "无具体组织信息", false, null);
                        	 output(response, pojo);
                        	 return false;
                    	}else{
                    	if(!user.getPkShop().equals(Long.parseLong(pkShop))){
                    		 outputstr("", "用户没有权限", false, null);
                        	 output(response, pojo);
                        	 return false;
                    	}
                    	
                  	  if(url.contains("/customeraward/")
                			  ||url.contains("/book/plateditnum.php")
                			  ||url.contains("/book/approve.php")
                			  ||url.contains("/book/platquery.php")
                			  ||url.contains("/platcombo/saveaward.php")){
                         if(!user.getUsertype().equals("2")){
                        	 outputstr("", "没有相关权限", false, null);
                        	 output(response, pojo);
                        	 return false;
                         }else{                       	
                 		     saverequest((pkUser==null||pkUser.equals(""))?null:Long.parseLong(pkUser), openid, url);
                         }
                	  }else{
                		     saverequest((pkUser==null||pkUser.equals(""))?null:Long.parseLong(pkUser), openid, url);
                	  }
                  }
            	 }else if(str[0].equals("wxcustomer")||str[0].equals("appcustomer")||str[0].equals("appfairer")){
            		    saverequest((pkUser==null||pkUser.equals(""))?null:Long.parseLong(pkUser), openid, url);
            		    return true;
            	 }else {
            		 outputstr("", "用户不存在", false, null);
                	 output(response, pojo);
                	 return false;
            	 }
                }
              }else{
       		       saverequest((pkUser==null||pkUser.equals(""))?null:Long.parseLong(pkUser), openid, url);  
              }
        	 }
           }
		} catch (Exception e) {
			// TODO: handle exception
			 outputstr("", "基本信息不完整", false, null);
        	 output(response, pojo);
			 return false;
		}
    return super.preHandle(request, response, handler);
  }

  @Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
		
	}
  
  /**
   * 保存控制器请求的记录
   */
  public void saverequest(Long pkUser,String userid,String url){
	  ControllerRequest request=new ControllerRequest();
	  request.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
	  request.setDr((short)0);
	  request.setRequesttime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
	  request.setControllerpath(url);
	  request.setPkUser(pkUser);
	  request.setUserid(userid);
	  basicservice.save(request);
  }
  
  
  @Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
  
	/**
	 * 输出结果到response
	 * @param response
	 * @param str
	 */
	public void output(HttpServletResponse response, ReturnPojo pojo) {
		try {
			String str=JSONObject.fromObject(pojo).toString();
			response.setCharacterEncoding("UTF-8");
		    response.getOutputStream().write(str.getBytes("UTF-8") );
		    response.getOutputStream().flush();	
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 生成pojo
	 * @param response
	 * @param str
	 */
	public ReturnPojo outputstr(String data,String msg,boolean issuccess,Integer totalcount) {
         pojo.setData(data);
         pojo.setMsg(msg);
         pojo.setIssuccess(issuccess);
         pojo.setTotalcount(totalcount);
         return pojo;
	}
	
 
}