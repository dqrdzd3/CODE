
/**
 * @title IReportResultProcessService1.java
 * @package com.hw.hwsafe.bolster.webservice.service
 * @author 杜群星
 * @create_time 2013-11-13 下午2:59:34
 * <p>Copyright 河南汉威电子股份有限公司软件部 Corporation 2013</p>
 */
	
package com.hw.hwsafe.bolster.webservice.service;




/**
 * <p>
 * 调用API实现保存和查询操作的服务接口
 * </p>
 */

public interface ISaveAndSearch4GatherService {
	
	String receiveObjectOfGather(String condition);
	
	/**
	 * 数据采集调用的MongoDB的插入操作
	 * @param gatherString
	 * @return
	 * @throws Exception          
	 * @author Administrator
	 * @create_time 2014年7月8日上午11:53:37
	 */
	String insertObjectOfGather(String gatherString);
	
	/**
	 * 
	 * 保存数据采集的原始数据
	 * 把数据添加到 MongoDB 数据库中
	 * @param gatherString
	 * @return          
	 * @author Administrator
	 * @create_time 2014年7月14日上午8:48:12
	 */
	String insertOriginalDataOfGather(String gatherString);
}
