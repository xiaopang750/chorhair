package com.rockstar.o2o.task;

import java.util.Date;

import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.rockstar.o2o.timer.ExcelDayTimer;

/**
 * 每天晚上一点执行查询前一天的数据
 * @author xucheng
 *
 */
public class ExcelDayTaskServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
		private static final Logger logger = Logger.getLogger(ExcelDayTaskServlet.class);
		  
		@Override
		public void init() {
			try {
				SchedulerFactory sf = new StdSchedulerFactory();
				Scheduler sched = sf.getScheduler();
				JobDetail job = new JobDetail("ExcelDayTimer", "initgroup", ExcelDayTimer.class);
			    CronTrigger trigger = new CronTrigger("ExcelDayTimer", "initgroup", "0 00 01 * * ?"); 
			    Date ft = sched.scheduleJob(job, trigger); 
			    System.out.println(job.getDescription() + " 已被安排执行于: " + ft.toString() + "，并且以如下重复规则重复执行: "+ trigger.getCronExpression());
			    logger.info(job.getDescription() + " 已被安排执行于: " + ft.toString() + "，并且以如下重复规则重复执行: "+ trigger.getCronExpression());  
			    sched.start();
			    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

}
