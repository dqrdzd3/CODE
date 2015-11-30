/**
 * 文件名：IC001Dao.java
 *
 * 版本信息：
 * 日期：2012-8-6
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.cpnyreg.dao;

import java.util.List;
import java.util.Map;
import com.hw.hwsafe.cpnyreg.pojo.C001PO;
import com.hw.hwsafe.platform.dao.IBaseDao;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：IC001Dao
 * 类描述：公司Dao
 * 创建人：付强
 * 创建时间：2012-8-6 下午1:27:40
 * 
 */
public interface IC001Dao extends IBaseDao {

	/**
	 * 函 数 名：retrieveC001ByMA001
	 * 功能描述：主键查询公司信息
	 * 输入参数：
	 * @param MA001 主键
	 * 返 回 值：
	 * @return 公司信息
	 * 异    常： Exception
	 * 创建人：付强
	 * 创建时间：2012-8-6 下午1:42:20
	 */
	public C001PO retrieveC001ByMA001(String MA001)throws Exception;
	
	/**
	 * 函 数 名：insertC001
	 * 功能描述：添加一条公司信息
	 * 输入参数：c001PO 公司信息pojo
	 * @param 
	 * 返 回 值： 
	 * @return void
	 * 异    常： Exception
	 * 创建人：付强
	 * 创建时间：2012-8-6 下午1:45:36
	 */
	public Integer insertC001(C001PO c001PO) throws Exception;
	
	/**
	 * 函 数 名：updateC001
	 * 功能描述：更新一条公司信息
	 * 输入参数：公司信息pojo
	 * @param 
	 * 返 回 值：
	 * @return void
	 * 异    常： Exception
	 * 创建人：付强
	 * 创建时间：2012-8-6 下午1:47:19
	 */
	public Integer updateC001(C001PO c001PO) throws Exception;
	
	/**
	 * 函 数 名：deleteC001ByMA001
	 * 功能描述：删除主键指定的公司信息
	 * 输入参数：
	 * @param MA001 主键集合
	 * 返 回 值： 
	 * @return void
	 * 异    常： Exception
	 * 创建人：付强
	 * 创建时间：2012-8-6 下午1:48:54
	 */
	public Integer deleteC001ByIds(Map<String,Object> args) throws Exception;
	
	/**
	 * 函 数 名：countInstanceByMa002
	 * 功能描述：依据企业名称统计企业数
	 * 创建人：马宁
	 * 创建时间：2013-08-19 15:56
	 */
	public Integer countInstanceByMa002(C001PO c001po) throws Exception;
	
	/**
	 * 函 数 名：countC001ByMa003
	 * 功能描述：依据组织机构代码统计企业数
	 * 输入参数：
	 * @param String ma003 组织机构代码
	 * 返 回 值：
	 * @return 统计记录数
	 * 创建人：付强
	 * 创建时间：2012-12-12 下午3:23:45
	 */
	public Integer countC001ByMa003(C001PO c001PO)throws Exception;
	
	/**
	 * 函 数 名：countC001ByMa003
	 * 功能描述：依据邮箱统计企业数
	 * 输入参数：
	 * @param String ma007 邮箱
	 * 返 回 值：
	 * @return 统计记录数
	 * 创建人：付强
	 * 创建时间：2012-12-12 下午3:23:52
	 */
	public Integer countC001ByMa007(C001PO c001PO)throws Exception;

	/**
	 * 
	 * 函 数 名：retrieveAll
	 * 功能描述：获取所有C001PO实例集合
	 * 创建人：马宁
	 * 创建时间：2012-10-25 15:43
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public List<C001PO> retrieveAll();
	
	/**
	 * 获取所有有效的C001PO实例集合
	 * 
	 * @author 马宁
	 * 创建时间: 2013-03-07 11:28
	 */
	List<C001PO> retrieveAllValid() throws Exception;
	
	
	/**
	 * 获取所有有效的C001PO实例集合
	 * 
	 * @author 马宁
	 * 创建时间: 2013-03-07 11:28
	 */
	List<Map<String,Object>> retrieveOrgan() throws Exception;
	
	/**
	 * 
	 * 方法名称：retrieveByCondition
	 * 功能描述：快速查询企业列表,可以根据企业名称，代码，行政区划代码，省直市汉字简称查询
	 * @param   map map中存放 KEY
	 * @return    List<C001PO>
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 * 创建人：杜群星
	 * 创建日期：2013-1-10 下午2:33:11
	 */
	public List<C001PO> retrieveByCondition(Map map) throws Exception;
	
	/**
	 * 
	 * 方法名称：retrieveCountByCondition
	 * 功能描述：快速查询企业列表的总数,可以根据企业名称，代码，行政区划代码，省直市汉字简称查询
	 * @param   	
	 * @return    Integer
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 * 创建人：杜群星
	 * 创建日期：2013-1-23 下午4:40:49
	 */
	public Integer retrieveCountByCondition(Map map) throws Exception;
	
	/**
	 * 函 数 名：queryC001POByMa003
	 * 功能描述：用组织机构代码查询企业登记信息
	 * @param ma003 组织机构代码
	 * @return C001PO 企业登记PO
	 * 异    常： Exception sql查询映射异常
	 * 创建人：付强
	 * 创建时间：2013-2-19 下午3:17:57
	 */
	public C001PO queryC001POByMa003(String ma003)throws Exception;

	/**
	 * 通过MA001获取MA009
	 * 
	 * @author 马宁
	 */
	public Integer retrieveMa009ByMa001(String ma001);

}
