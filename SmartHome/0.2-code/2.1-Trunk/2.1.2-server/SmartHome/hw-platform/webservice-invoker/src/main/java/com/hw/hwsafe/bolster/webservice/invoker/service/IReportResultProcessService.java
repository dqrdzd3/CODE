
/**
 * @title IReportResultProcessService1.java
 * @package com.hw.hwsafe.bolster.webservice.service
 * @author 杜群星
 * @create_time 2013-11-13 下午2:59:34
 * <p>Copyright 河南汉威电子股份有限公司软件部 Corporation 2013</p>
 */
	
package com.hw.hwsafe.bolster.webservice.invoker.service;

import java.util.List;
import java.util.Map;



/**
 * <p>
 * 对隐患上报处理后返回的结果处理的服务接口
 * </p>
 */

public interface IReportResultProcessService {
	/**
	 * 接受隐患上报处理后的返回结果
	 * @param id 隐患主键
	 * @param result 隐患处理结果
	 * <br> 提示：注意key值为小写
	 * @return {@link String}
	 * @throws Exception          
	 * @author 杜群星
	 * @create_time 2013-11-13下午3:01:33
	 */
	String receiveProcessResult(String id,String result,String user,String password) throws Exception;
	/**
	 * 接受隐患上报处理后的返回结果
	 * @param list
	 * <br> 集合中存放map，map的key为id(隐患主键)，result(隐患处理结果)
	 * <br> 提示：注意key值为小写
	 * @return {@link String}
	 * @throws Exception          
	 * @author 杜群星
	 * @create_time 2013-11-13下午3:01:33
	 */
	String receiveProcessResult(List<Map<String, String>> list,String user,String password) throws Exception;

}
