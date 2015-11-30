/**
 * 文件名：IB002Service.java
 *
 * 版本信息：1.0
 * 日期：2012-6-12
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hw.hwsafe.corpinfo.pojo.B002PO;
import com.hw.hwsafe.platform.service.IBaseService;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：IB002Service
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-12 上午10:20:11
 * 修改人：
 * 修改时间：2012-6-12 上午10:20:11
 * 修改备注：
 * @version 
 * 
 */
public interface IB002Service extends IBaseService {

	
	/**
	 * 
	 * @Title: retrieveB002ByID
	 * @Description: 通过主键查询机构信息
	 * 作者：刁海港
	 * @param @param b002ID
	 * @param @return
	 * @param @throws Exception
	 * @return B002PO
	 * @throws
	 */
	public B002PO retrieveB002ByID(String b002ID) throws Exception;
	
	public List<B002PO> retrieveB002ByCorp(Map map) throws Exception;
	/**
	 * 
	 * @Title: retrieveB002ByPO
	 * @Description: 根据条件查询机构信息
	 * 作者：刁海港
	 * @param @param b002PO
	 * @param @return
	 * @param @throws Exception
	 * @return List<B002PO>
	 * @throws
	 */
	public List<B002PO> retrieveB002ByPO(B002PO b002PO) throws Exception;
	/**
	 * 
	 * @Title: insertB002
	 * @Description: 添加一条机构信息
	 * 作者：刁海港
	 * @param @param b002PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void insertB002(B002PO b002PO) throws Exception;
	/**
	 * 
	 * @Title: updateB002
	 * @Description: 修改机构信息
	 * 作者：刁海港
	 * @param @param b002PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void updateB002(B002PO b002PO) throws Exception;
	/**
	 * 查询机构名称是否重复
	 * @param b002PO
	 * @throws Exception
	 */
	
	public boolean checkCorpName(B002PO b002PO) throws Exception;
	/**
	 * 操作前查看记录是否存在
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean isExist(String id)throws Exception;
	
	/**
	 * 
	 * @Title: updateB002
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b002PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */

	public void updateB002SBSJ(HashMap<String, Object> map) throws Exception;
}
