/**
 * 文件名：C006DaoImpl.java
 * 版本信息：
 * 日期：2012-12-21
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 */
package com.hw.hwsafe.corpinfo.dao.impl;

import java.util.List;
import java.util.Map;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.hw.hwsafe.corpinfo.dao.IC007Dao;
import com.hw.hwsafe.corpinfo.pojo.C007PO;
import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;

/**
 * 
 * 
 * 项目名称：hw-register
 * 类名称：C006DaoImpl
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-12-21 下午5:07:12
 * 修改人：李玉梅
 * 修改时间：2012-12-21 下午5:07:12
 * 修改备注：
 * @version 
 *
 */
public class C007DaoImpl extends BaseDaoImpl implements IC007Dao {

	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {

		return getSqlSession().getMapper(IC007Dao.class).retrieveByPage(map);
	}

	@Override
	public C007PO retrieveC007ById(String id) throws Exception {

		return getSqlSession().getMapper(IC007Dao.class).retrieveC007ById(id);
	}

	@Override
	public C007PO retrieveC007ByKey(String key) throws Exception {

		return getSqlSession().getMapper(IC007Dao.class).retrieveC007ByKey(key);
	}

	@Override
	public List<C007PO> retrieveC007ByPO(C007PO C007Po) throws Exception {

		return getSqlSession().getMapper(IC007Dao.class).retrieveC007ByPO(C007Po);
	}

	@Override
	public List<C007PO> retrieveChildC007ListByKey(String key) throws Exception {

		return getSqlSession().getMapper(IC007Dao.class).retrieveChildC007ListByKey(key);
	}

	@Override
	public Integer countC007ByKey(C007PO C007PO) throws Exception {

		return getSqlSession().getMapper(IC007Dao.class).countC007ByKey(C007PO);
	}

	@Override
	public Integer countC007ByValueUnderParentNode(C007PO C007PO)
			throws Exception {

		return getSqlSession().getMapper(IC007Dao.class).countC007ByValueUnderParentNode(C007PO);
	}

	@Override
	public Integer countC007ByOrdernum(C007PO C007PO) throws Exception {
	
		return getSqlSession().getMapper(IC007Dao.class).countC007ByOrdernum(C007PO);
	}

	@Override
	public Integer insertC007(C007PO C007PO) throws Exception {

		return getSqlSession().getMapper(IC007Dao.class).insertC007(C007PO);
	}

	@Override
	public Integer updateC007(C007PO C007PO) throws Exception {
	
		return getSqlSession().getMapper(IC007Dao.class).updateC007(C007PO);
	}

	@Override
	public Integer updateOrdernumIncrease(C007PO C007PO) throws Exception {
		return getSqlSession().getMapper(IC007Dao.class).updateOrdernumIncrease(C007PO);
	}

	@Override
	public Integer deleteC007ByIds(Map<String, Object> args) throws Exception {
		return getSqlSession().getMapper(IC007Dao.class).deleteC007ByIds(args);
	}

	@Override
	public Integer deleteChildC007ByKey(String key) throws Exception {
		return getSqlSession().getMapper(IC007Dao.class).deleteChildC007ByKey(key);
	}

	@Override
	public List getHymlList() throws Exception {
		return getSqlSession().getMapper(IC007Dao.class).getHymlList();
	}

	@Override
	public List getHydlList(String parentid) throws Exception {
		return getSqlSession().getMapper(IC007Dao.class).getHydlList(parentid);
	}

	@Override
	public List getHyzlList(String parentid) throws Exception {
		return getSqlSession().getMapper(IC007Dao.class).getHyzlList(parentid);
	}

	@Override
	public List getHyxlList(String parentid) throws Exception {
		return getSqlSession().getMapper(IC007Dao.class).getHyxlList(parentid);
	}

	@Override
	public C007PO getC007POByValue(String value) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IC007Dao.class).getC007POByValue(value);

	}

	/*
	   (non-Javadoc)
	 * @see com.hw.hwsafe.corpinfo.dao.IC007Dao#getHymlPOList()
	 */
		
	@Override
	public List<C007PO> getHymlPOList() {
		return getSqlSession().getMapper(IC007Dao.class).getHymlPOList();
	}

	@Override
	public List<C007PO> retrieveChildC007ListByParent(String partentid)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IC007Dao.class).retrieveChildC007ListByParent(partentid);
	}
}
