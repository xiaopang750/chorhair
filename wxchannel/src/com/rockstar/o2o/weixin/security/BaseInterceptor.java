package com.rockstar.o2o.weixin.security;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.rockstars.o2o.weixin.util.encrypt.EncryptUtil;

@Component
public class BaseInterceptor extends HandlerInterceptorAdapter
{
  private static final Logger logger = Logger.getLogger(BaseInterceptor.class);



  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
  {
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");

    String reqUrl = request.getRequestURI();

    String signature = request.getParameter("signature");
    String timestamp = request.getParameter("timestamp");
    String nonce = request.getParameter("nonce");

    String token = null;
    String method = null;

    if ((signature == null) || (timestamp == null) || (nonce == null)) {
      logger.debug("不是微信消息");
      logger.debug("reqUrl is : " + reqUrl);

      token = request.getParameter("token");

      method = request.getMethod();

      if (method.equalsIgnoreCase("get")) {
        if ((reqUrl != null) && (!reqUrl.equals(""))) {
          if (reqUrl.indexOf("/static/") != -1) {
            logger.debug("请求静态资源，可放行！");
          } else if (reqUrl.indexOf("/bind") != -1) {
            if ((reqUrl.equals("/bind")) && (
              (token == null) || (token.equals("")))) {
              logger.error("请求令牌不合法，访问授权认证失败！");
              throw new Exception("请求令牌不合法，访问授权认证失败！");
            }

          }
          else if (reqUrl.indexOf("/oauth") != -1) {
            logger.debug("请求oauth，可放行！");
          } else if (reqUrl.indexOf("/error/") != -1) {
            logger.debug("请求错误提示，可放行！");
          } else {
            request.getRequestDispatcher("error/illegal").forward(
              request, response);
            logger.debug("非法请求，拦截！");
            return false;
          }
        } else {
          logger.error("无效请求！");
          throw new Exception("无效请求！");
        }
      } else if (method.equalsIgnoreCase("post"))
        logger.debug("POST暂时放行");
    }
    else
    {
      logger.debug("是微信消息");
      if (!authenticateWeixin(signature, timestamp, nonce)) {
        logger.debug("访问授权认证失败！");
        output(response, "访问授权认证失败");
        return false;
      }

      logger.debug("访问授权认证成功！");
    }
    return super.preHandle(request, response, handler);
  }

  
	/**
	 * 输出结果到response
	 * @param response
	 * @param str
	 */
	public void output(HttpServletResponse response, String str) {
		try {
			response.setCharacterEncoding("UTF-8");
		    response.getOutputStream().write(str.getBytes("UTF-8") );
		    response.getOutputStream().flush();	
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
  /**
   * 微信消息校验
   * @param signature
   * @param timestamp
   * @param nonce
   * @return
   */
  private boolean authenticateWeixin(String signature, String timestamp, String nonce)
  {
	    ResourceBundle bundle = ResourceBundle.getBundle("wx");
		String token = bundle.getString("token") ; 
        String[] array = { token, timestamp, nonce };
        Arrays.sort(array);
        String result = "";

        for (String i : array) {
          result = result + i;
        }
        String ret = "";
        try
        {
          ret = EncryptUtil.SHAsum(result.getBytes());
          if (ret.equalsIgnoreCase(signature))
            return true;
        }
        catch (NoSuchAlgorithmException ex) {
          ex.printStackTrace();
        }
    return false;
  }

}