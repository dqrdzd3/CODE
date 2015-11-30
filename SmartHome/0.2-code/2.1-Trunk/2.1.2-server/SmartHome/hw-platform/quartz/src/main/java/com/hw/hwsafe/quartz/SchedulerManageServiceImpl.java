package com.hw.hwsafe.quartz;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import com.hw.hwsafe.utils.UUIDGenerater;

public class SchedulerManageServiceImpl implements ISchedulerManageService{
	
	private static Scheduler scheduler;

	static {
		getScheduler();
	}
	
	public static Scheduler getScheduler() {
		if (scheduler == null) {
			StdSchedulerFactory ssf = new StdSchedulerFactory();
			try {
				scheduler = ssf.getScheduler();
			} catch (SchedulerException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return scheduler;
	}
	
	/**
	 * 添加job
	 * addJob
	 * @param   
	 * @return    void
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void addJob(Map map,Class classz) throws RuntimeException{
//		StdSchedulerFactory ssf = new StdSchedulerFactory();
		
//		Scheduler scheduler = ssf.getScheduler();//取任务调度器
		try {
			//创建job
			String job = map.get("job") == null ? "" :map.get("job").toString();
			String jobGroup = map.get("jobGroup") == null ? "" :map.get("jobGroup").toString();
	//		String classz = map.get("classz").toString();
	//		Class classzz = (Class) Class.forName(classz);
			String trigger = map.get("trigger") == null ? "" : map.get("trigger").toString();
			String triggerGroup = map.get("triggeGroup") == null ? "" : map.get("triggeGroup").toString();
			String cronExpression = map.get("cronExpression") == null ? getCronExpression() : map.get("cronExpression").toString();
			
			String[] exitJobGroups = scheduler.getJobGroupNames();
			String[] exitTriggerGroups = scheduler.getTriggerGroupNames();
			for (int i = 0; i < exitJobGroups.length; i++) {
				String exitJobGroupName = exitJobGroups[i];
				//jobgroup存在
				if(exitJobGroupName.equals(jobGroup)){
					String[] exitJobs = scheduler.getJobNames(exitJobGroupName);
					for (int j = 0; j < exitJobs.length; j++) {
						String exitJob = exitJobs[j];
						if(exitJob.equals(job)){
							job += UUIDGenerater.getUUID();
						}
					}
				}
			}
			for (int m = 0; m < exitTriggerGroups.length; m++) {
				String exitTriggerGroupName = exitTriggerGroups[m];
				if(exitTriggerGroupName.equals(triggerGroup)){
					String[] exitTriggers = scheduler.getTriggerNames(exitTriggerGroupName);
					for (int n = 0; n < exitTriggers.length; n++) {
						String exitTrigger = exitTriggers[n];
						if(exitTrigger.equals(trigger)){
							trigger += UUIDGenerater.getUUID();
						}
					}
				}
			}
			
			JobDataMap jobDataMap = new JobDataMap(map);
			
			JobDetail jobDetail = new JobDetail();
			jobDetail.setName(job);//设置job名
			jobDetail.setGroup(jobGroup);//设置group名
			jobDetail.setJobClass(classz);//设置工作任务类
			jobDetail.setJobDataMap(jobDataMap);//设置参数
			
			//创建trigger
			CronTrigger cronTrigger = new CronTrigger();
			cronTrigger.setName(trigger);//触发器名称
			cronTrigger.setGroup(triggerGroup);//触发器群组
			cronTrigger.setJobName(job);//工作类名称
			cronTrigger.setJobGroup(jobGroup);//工作群组
			cronTrigger.setCronExpression(cronExpression);//时间表达式
			
			scheduler.addJob(jobDetail, true);
			scheduler.scheduleJob(cronTrigger);
			scheduler.start();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}	
	}
	
	/**
	 * 删除job
	 * deleteJob
	 * @param   
	 * @return    void
	 * @SystemException 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void deleteJob(Map map) throws RuntimeException {
		String job = map.get("job") == null ? "" : map.get("job").toString();
		String jobGroup = map.get("jobGroup") == null 
				? "" 
				: map.get("jobGroup").toString();
		
		try {
			scheduler.pauseJob(job, jobGroup);
			scheduler.deleteJob(job, jobGroup);// 移除job
		} catch (SchedulerException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * 暂停job
	 * pauseJob
	 * @param   
	 * @return    void
	 * @SystemException 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void pauseJob(Map map) throws RuntimeException {
		String job = map.get("job") == null ? "" : map.get("job").toString();
		String jobGroup = map.get("jobGroup") == null 
				? "" 
				: map.get("jobGroup").toString();
		
		try {
			scheduler.pauseJob(job, jobGroup);// 暂停job
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * 恢复job
	
	 * resumeJob
	 * @param   
	 * @return    void
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void resumeJob(Map map) throws RuntimeException {
		String job = map.get("job") == null 
				? "" 
				: map.get("job").toString();
		
		String jobGroup = map.get("jobGroup") == null ? "" : map
				.get("jobGroup").toString();
		try {
			scheduler.resumeJob(job, jobGroup);// 恢复job
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * 暂停trigger
	 * pauseTrigger
	 * @param   
	 * @return    void
	 * @SystemException 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void pauseTrigger(Map map) throws RuntimeException {
		String trigger = map.get("trigger") == null 
				? "" 
				: map.get("trigger").toString();
		
		String triggeGroup = map.get("triggeGroup") == null 
				? "" 
				: map.get("triggeGroup").toString();
		
		try {
			scheduler.pauseTrigger(trigger, triggeGroup);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * 恢复trigger
	 * resumeTrigger
	 * @param   
	 * @return    void
	 * @SystemException 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void resumeTrigger(Map map) throws RuntimeException {
		String trigger = map.get("trigger") == null 
				? "" 
				: map.get("trigger").toString();
		
		String triggeGroup = map.get("triggeGroup") == null 
				? "" 
				: map.get("triggeGroup").toString();
		
		try {
			scheduler.resumeTrigger(trigger, triggeGroup);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * 恢复trigger
	 * deleteTrigger
	 * @param   
	 * @return    void
	 * @SystemException 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void deleteTrigger(Map map) throws RuntimeException {
		String trigger = map.get("trigger") == null 
				? "" 
				: map.get("trigger").toString();
		
		String triggeGroup = map.get("triggeGroup") == null 
				? "" 
				: map.get("triggeGroup").toString();
		
		try {
			scheduler.pauseTrigger(trigger, triggeGroup);// 暂停触发器
			scheduler.unscheduleJob(trigger, triggeGroup);// 移除触发器
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public String getCronExpression(Date date) {
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}

		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day_month = calendar.get(Calendar.DAY_OF_MONTH);
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		// int day_week = calendar.get(Calendar.DAY_OF_WEEK);

		String cronStr = second + " " + minute + " " + hours + " " + day_month + " "
				+ month + " ? " + year;

		return cronStr;
	}
	
	public String getCronExpression() {
		return getCronExpression(null);
	}
}
