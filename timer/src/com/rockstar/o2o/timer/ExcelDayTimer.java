package com.rockstar.o2o.timer;

import java.util.ResourceBundle;

import net.sf.json.JSONObject;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.rockstar.o2o.http.IHttpClientService;
import com.rockstar.o2o.http.IHttpClientServiceImpl;

/**
 * 每天的定时任务
 * @author xucheng
 *
 */
public class ExcelDayTimer implements Job{
	
	private static ResourceBundle bundle = ResourceBundle.getBundle("url");
	private String url=System.getenv("CHORHAIR")==null?bundle.getString("local.dayexcel"):
    	(System.getenv("CHORHAIR").equals("test")?bundle.getString("test.dayexcel"):bundle.getString("build.dayexcel"));
	private IHttpClientService httpservice=new IHttpClientServiceImpl();
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("daytimer---启动");
		JSONObject newobj=new JSONObject();
		newobj.accumulate("pkCustomer", "25");
		try {			
			String json=httpservice.sendPostRequset(url,newobj.toString());
		    System.out.println(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("daytimer---完成");
	}
}
