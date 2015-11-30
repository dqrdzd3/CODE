/**
 * 文件名：AlarmHandle.java
 *
 * 版本信息：
 * @date：2012-10-16
 * @Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.sms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * 项目名称：monitor
 * 类名称：AlarmHandle
 * 类描述：警情处理线程业务操作类
 * @创建人：Guowm
 * 创建时间：2012-10-16 上午09:14:09
 * @修改人：Guowm
 * 修改时间：2012-10-16 上午09:14:09
 * 修改备注：
 * @version 
 * 
 */
public class AlarmHandle implements Runnable{

	/**
	 *标志  
	 */
	private boolean isRun = true;
		
	/**
	 * 短信队列
	 */
	private List<SMSPO> listSMSPO = Collections.synchronizedList(new ArrayList<SMSPO>());
	
	public AlarmHandle() {
		super();
//		SMSService smsService = new SMSService();
//		smsService.initService();// 启动service服务
	}
	
	@Override
	public void run(){
		while(true){
			if(listSMSPO.size()>0){
				for(int i=listSMSPO.size()-1;i>-1;i--){
					SMSPO smspo = listSMSPO.get(i);
//					OutboundMessage msg = SMSUtils.sendMsg(smspo.getTelNum(),smspo.getTelContent());
					SMSUtils.sendMsg(smspo.getTelNum(),smspo.getTelContent());
					listSMSPO.remove(i);
				}
			}else if(listSMSPO.size()==0){
				wai();	
			}
		}
	}
	
	/**
	 * 
	 * 函数名：addAlarmInfo
	 * 功能描述：插入报警信息
	 * @param info 
	 * 异常： 
	 * @创建人：Guowm
	 * 创建时间：2012-10-16 下午07:55:43
	 * @修改人：Guowm
	 * 修改时间：2012-10-16 下午07:55:43
	 * 修改原因描述：
	 */
	public void addAlarmInfo(SMSPO smspo){
		listSMSPO.add(smspo);
	}
	
	/**
	 * 
	 * 函数名：awaken
	 * 功能描述：唤醒线程
	 * 异常： 
	 * @创建人：Guowm
	 * 创建时间：2012-10-16 下午07:55:26
	 * @修改人：Guowm
	 * 修改时间：2012-10-16 下午07:55:26
	 * 修改原因描述：
	 */
	public synchronized void awaken(){
		notifyAll();
		isRun = true;
		System.out.println("激活所有进程……");
	}

	public synchronized void wai(){
		try {
			isRun = false;
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return isRun 
	   @type boolean
	 */
	
	public boolean isRun() {
		return isRun;
	}

	/**
	 * @param isRun @type boolean the isRun to set
	 */
	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}
	
}
