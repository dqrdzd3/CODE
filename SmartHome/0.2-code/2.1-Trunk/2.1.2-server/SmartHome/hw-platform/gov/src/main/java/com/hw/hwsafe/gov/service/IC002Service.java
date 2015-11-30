/**
 * 文件名：IC002Service.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.gov.service;

import java.util.List;

import com.hw.hwsafe.gov.pojo.C002PO;
import com.hw.hwsafe.platform.exception.jsonmsg.JsonMsgException;
import com.hw.hwsafe.platform.message.UserMessageData;
import com.hw.hwsafe.platform.service.IBaseService;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：IC002Service
 * 类描述：政府机构Service接口
 * 创建人：马宁
 * 创建时间：2012-9-29 上午11:20:27
 * 修改人：马宁
 * 修改时间：2012-9-29 上午11:20:27
 * 修改备注：
 * @version 
 *
 */
public interface IC002Service extends IBaseService {

	/**
	 * 
	 * 函 数 名：retrieveC002ByPO
	 * 功能描述：通过PO获取C002实体集合
	 * @exception Exception
	 * 创建人：马宁
	 * 创建时间：2012-9-29 下午1:22:27
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	List<C002PO> retrieveC002ByPO(C002PO c002PO) throws Exception;

	/**
	 * 
	 * 函 数 名：retrieveC002ByMa001
	 * 功能描述：通过ma001属性获取C002实体
	 * @exception Exception
	 * 创建人：马宁
	 * 创建时间：2012-9-29 下午1:23:27
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	C002PO retrieveC002ByMa001(String ma001) throws Exception;

	/**
	 * 
	 * 函 数 名：retrieveC002ByMa002
	 * 功能描述：通过ma002属性获取C002实体
	 * @exception Exception
	 * 创建人：马宁
	 * 创建时间：2012-9-29 下午1:24:03
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	C002PO retrieveC002ByMa002(String ma002) throws Exception;

	/**
	 * 
	 * 函 数 名：retrieveAll
	 * 功能描述：获取所有C002PO实例集合
	 * 创建人：马宁
	 * 创建时间：2012-10-24 下午2:08:09
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	List<C002PO> retrieveAll() throws Exception;
	
	/**
	 * 获取所有有效的C002PO实例集合
	 * 
	 * @author 马宁
	 * 创建时间: 2013-03-07 11:14
	 */
	List<C002PO> retrieveAllValid() throws Exception;
	
	/**
	 * 
	 * 函 数 名：countC002ByMa002
	 * 功能描述：通过ma002属性获取C002实体的个数
	 * @exception Exception
	 * 创建人：马宁
	 * 创建时间：2012-9-29 下午1:25:51
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	Integer countC002ByMa002(String ma002) throws Exception;

	/**
	 * 
	 * 函 数 名：insertC002
	 * 功能描述：添加C002实体
	 * @exception JsonMsgException
	 * 创建人：马宁
	 * 创建时间：2012-9-29 下午1:26:30
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	UserMessageData insertC002(C002PO c002PO) throws JsonMsgException;

	/**
	 * 
	 * 函 数 名：updateC002
	 * 功能描述：修改C002实体
	 * @exception Exception
	 * 创建人：马宁
	 * 创建时间：2012-9-29 下午1:27:08
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	UserMessageData updateC002(C002PO c002PO) throws Exception;

	/**
	 * 
	 * 函 数 名：retrieveChildC002ListByMa002
	 * 功能描述：通过ma002属性获取子C002实体集合
	 * @exception Exception
	 * 创建人：马宁
	 * 创建时间：2012-9-29 下午1:27:33
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	List<C002PO> retrieveChildC002ListByMa002(String ma002) throws Exception;
	
	/**
	 * 
	 * 函 数 名：isUniqueName
	 * 功能描述：判断机构名称是否唯一
	 * @return boolean
	 * 创建人：马宁
	 * 创建时间：2013-08-19 13:17
	 */
	boolean isUniqueName(C002PO c002po);
	
	/**
	 * 
	 * 函 数 名：isUniqueCode
	 * 功能描述：判断机构代码是否唯一
	 * @return boolean
	 * 创建人：马宁
	 * 创建时间：2012-12-8 下午3:31:41
	 */
	boolean isUniqueCode(C002PO c002PO);
	
	/**
	 * 函 数 名：countInstanceByMa007
	 * 功能描述：统计邮箱除排除主键之外被使用的次数
	 * @param C002PO c002PO 邮箱地址和排除主键
	 * @return 统计的记录数
	 * 创建人：付强
	 * 创建时间：2012-12-19 下午12:40:11
	 */
	boolean isUniqueMa007(C002PO c002PO);
	
	/**
	 * 函 数 名：deleteC001ByMA001s
	 * 功能描述：删除主键指定的政府机构
	 * @param ma001s主键集合
	 * @return 删除记录数
	 * 创建人：付强
	 * 创建时间：2012-12-18 上午10:45:44
	 */
	public UserMessageData deleteC002ByIds(String ids) throws Exception;
	
	/**
	 * 
	 * 通过企业id查询当前企业直属的政府单位
	 * @param corpId 企业id
	 * @return C002PO 当前企业直属的政府单位的信息
	 * @throws Exception          
	 * @author 陈浙东
	 * @create_time 2013-5-15 下午2:05:39
	 */
	public C002PO getC002InfoByCorpId(String corpId) throws Exception;
	
	/**
	 * 
	 * 函 数 名：insertORG
	 * 功能描述：添加C002实体
	 * @exception Exception
	 * 创建人：杜群星
	 * 创建时间：2014-4-3 下午1:26:30
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	String insertORG(C002PO c002PO) throws Exception;

	/**
	 * 
	 * 函 数 名：updateORG
	 * 功能描述：修改C002实体
	 * @exception Exception
	 * 创建人：马宁
	 * 创建时间：2012-9-29 下午1:27:08
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	boolean updateORG(C002PO c002PO) throws Exception;
	/**
	 * 函 数 名：deleteORGByIds
	 * 功能描述：删除主键指定的政府机构
	 * @param ma001s主键集合
	 * @return 删除记录数
	 * 创建人：付强
	 * 创建时间：2012-12-18 上午10:45:44
	 */
	 boolean deleteORGByIds(String ids) throws Exception;

}
