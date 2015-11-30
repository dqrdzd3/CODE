package com.hw.hwsafe.gov.service;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.gov.pojo.C002PO;

public interface IGovFacadeService {
	
	/**
	 * 函 数 名：retrieveAllORG
	 * 功能描述：获取所有机构的集合
	 * 创建人：杜群星
	 * 创建时间：2014-04-03 下午1:22:27
	 */
	List<Map<String,Object>> retrieveAllORG() throws Exception;
	/**
	 * 函 数 名：retrieveC002ByMa001
	 * 功能描述：通过主键属性获取机构实体
	 * @exception Exception
	 * 创建人：杜群星
	 * 创建时间：2014-04-03 下午1:23:27
	 */
	Map<String,Object> retrieveById(String id) throws Exception;
	/**
	 * 
	 * 函 数 名：insertC002
	 * 功能描述：添加机构
	 * @exception JsonMsgException
	 * 创建人：杜群星
	 * 创建时间：2014-04-03 下午1:26:30
	 */
	String insertORG(Map<String,Object> map) throws Exception;

	/**
	 * 函 数 名：updateC002
	 * 功能描述：修改机构
	 * @exception Exception
	 * 创建人：杜群星
	 * 创建时间：2014-04-03 下午1:27:08
	 */
	boolean updateORG(Map<String,Object> map) throws Exception;
	/**
	 * 函 数 名：isUniqueName
	 * 功能描述：判断机构名称是否唯一
	 * @return boolean
	 * 创建人：杜群星
	 * 创建时间：2014-04-03 13:17
	 */
	boolean isUniqueName(Map<String,Object> map) throws Exception;
	/**
	 * 函 数 名：isUniqueCode
	 * 功能描述：判断机构代码是否唯一
	 * @return boolean
	 * 创建人：杜群星
	 * 创建时间：2014-04-03 下午3:31:41
	 */
	boolean isUniqueCode(Map<String,Object> map) throws Exception;
	/**
	 * 函 数 名：deleteORGS
	 * 功能描述：批量删除机构
	 * @return boolean
	 * 创建人：杜群星
	 * 创建时间：2014-04-03 下午3:31:41
	 */
	
	boolean  deleteORGS(List<String> list) throws Exception;
	/**
	 * 获取所有有效的机构集合
	 * 
	 * @author 杜群星
	 * 创建时间: 2014-04-03 16:13
	 */
	List<C002PO> retrieveAllValid() throws Exception;

}
