/**
 * 文件名：IC002Dao.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.gov.dao;

import java.util.List;
import java.util.Map;
import com.hw.hwsafe.gov.pojo.C002PO;
import com.hw.hwsafe.platform.dao.IBaseDao;

public interface IC002Dao extends IBaseDao {

	/**
	 * 
	 * 函 数 名：retrieveC002ByMa001
	 * 功能描述：通过ma001属性获取C002实体
	 * @exception Exception
	 * 创建人：马宁
	 * 创建时间：2012-9-29 下午1:15:36
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	C002PO retrieveC002ByMa001(String ma001) throws Exception;

	/**
	 * 
	 * 函 数 名：retrieveC002ByPO
	 * 功能描述：通过POJO获取实体集合
	 * @exception Exception
	 * 创建人：马宁
	 * 创建时间：2012-9-29 下午1:17:45
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	List<C002PO> retrieveC002ByPO(C002PO c002PO)
			throws Exception;

	/**
	 * 
	 * 函 数 名：retrieveC002ByMa002
	 * 功能描述：通过ma002属性获取C002实体
	 * @exception Exception
	 * 创建人：马宁
	 * 创建时间：2012-9-29 下午1:18:55
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	C002PO retrieveC002ByMa002(String ma002) throws Exception;

	/**
	 * 
	 * 函 数 名：retrieveChildC002ListByMa002
	 * 功能描述：通过ma002属性获取子C002实体集合
	 * @exception Exception
	 * 创建人：马宁
	 * 创建时间：2012-9-29 下午1:19:38
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	List<C002PO> retrieveChildC002ListByMa002(String ma002) throws Exception;

	/**
	 * 
	 * 函 数 名：retrieveAll
	 * 功能描述：获取所有C002PO实例集合
	 * 创建人：马宁
	 * 创建时间：2012-10-24 下午2:05:51
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	List<C002PO> retrieveAll() throws Exception;
	
	/**
	 * 获取所有有效的C002PO实例集合
	 * 
	 * @author 马宁
	 * 创建时间: 2013-03-07 11:16
	 */
	List<C002PO> retrieveAllValid() throws Exception;
	
	/**
	 * 
	 * 函 数 名：countC002ByMa002
	 * 功能描述：通过ma002属性统计C002实体的个数
	 * @exception Exception
	 * 创建人：马宁
	 * 创建时间：2012-9-29 下午1:20:26
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	Integer countC002ByMa002(String ma002) throws Exception;

	/**
	 * 
	 * 函 数 名：insertC002
	 * 功能描述：添加C002
	 * @exception Exception
	 * 创建人：马宁
	 * 创建时间：2012-9-29 下午1:21:19
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	Integer insertC002(C002PO c002PO) throws Exception;

	/**
	 * 
	 * 函 数 名：updateC002
	 * 功能描述：修改C002
	 * @exception Exception
	 * 创建人：马宁
	 * 创建时间：2012-9-29 下午1:21:47
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	Integer updateC002(C002PO c002PO) throws Exception;
	
	/**
	 * 
	 * 函 数 名：countInstanceByName
	 * 功能描述：通过机构名称统计实例个数
	 * @return Integer
	 * 创建人：马宁
	 * 创建时间：2013-08-19 13:22
	 */
	Integer countInstanceByMa003(C002PO c002PO);
	
	/**
	 * 
	 * 函 数 名：countInstanceByCode
	 * 功能描述：通过机构代码统计实例个数
	 * @return Integer
	 * 创建人：马宁
	 * 创建时间：2012-12-8 下午3:36:06
	 */
	Integer countInstanceByCode(C002PO c002PO);
	
	/**
	 * 函 数 名：countInstanceByMa007
	 * 功能描述：统计邮箱除排除主键之外被使用的次数
	 * @param C002PO c002PO 邮箱地址和排除主键
	 * @return 统计的记录数
	 * 创建人：付强
	 * 创建时间：2012-12-19 下午12:34:48
	 */
	Integer countInstanceByMa007(C002PO c002PO);
	
	/**
	 * 函 数 名：deleteC001ByMA001s
	 * 功能描述：删除主键指定的政府机构
	 * @param ma001s 主键集合
	 * @return 删除记录数
	 * 创建人：付强
	 * 创建时间：2012-12-18 上午10:42:41
	 */
	public Integer deleteC002ByIds(Map<String,Object> args) throws Exception;
	
	/**
	 * 函 数 名：countChildByIds
	 * 功能描述：统计主键集合下的子节点数量
	 * @param ids 主键集合
	 * @return 子节点数量
	 * 创建人：付强
	 * 创建时间：2012-12-18 下午2:36:31
	 */
	public Integer countChildByIds(Map<String,Object> args) throws Exception;

	/**
	 * 通过企业id查询当前企业直属的政府单位
	 * @param corpId 企业id
	 * @return C002PO 当前企业直属的政府单位的信息        
	 * @author 陈浙东
	 * @create_time 2013-5-15 下午2:06:24
	 */
		
	public C002PO getC002InfoByCorpId(String corpId) throws Exception;

}
