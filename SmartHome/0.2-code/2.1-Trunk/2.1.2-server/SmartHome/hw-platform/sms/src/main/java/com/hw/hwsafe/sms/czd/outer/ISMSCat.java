package com.hw.hwsafe.sms.czd.outer;

/**
 * 项目名称：hw-sms
 * 类名称：ISMSCat
 * 类描述：短信猫对外接口
 * 创建人：陈浙东
 * 创建时间：2013-4-09
 *
 */
public interface ISMSCat {
	/**
	 * 根据配置文件读取的短信猫的信息初始化短信猫
	 * @param smsCat
	 */
	public void initCatService() throws Exception;
	
	/**
	 * 启动短信猫服务
	 */
	public void startService() throws Exception;
	
	/**
	 * 停止短信猫服务
	 */
	public void stopService() throws Exception;
	
	/**
	 * 启动线程用来读取队列中未发的短信，进行发送
	 */
	public void startThread();
}
