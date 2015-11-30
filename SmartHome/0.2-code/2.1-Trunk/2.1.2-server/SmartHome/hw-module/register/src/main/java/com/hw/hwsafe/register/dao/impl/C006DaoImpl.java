/**
 * 文件名：C006DaoImpl.java
 * 版本信息：
 * 日期：2012-12-21
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 */
package com.hw.hwsafe.register.dao.impl;

import java.util.List;
import java.util.Map;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;
import com.hw.hwsafe.register.dao.IC006Dao;
import com.hw.hwsafe.register.pojo.C006PO;

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
public class C006DaoImpl extends BaseDaoImpl implements IC006Dao {
	

	@Override
	public List<C006PO> retrieveAllC006() throws Exception {
		return getSqlSession().getMapper(IC006Dao.class).retrieveAllC006();
	}

	@Override
	public C006PO retrieveC006ByID(String c006Id) throws Exception {
		return getSqlSession().getMapper(IC006Dao.class).retrieveC006ByID(c006Id);
	}

	@Override
	public List<C006PO> retrieveC006ByPO(C006PO c006po) throws Exception {
		return getSqlSession().getMapper(IC006Dao.class).retrieveC006ByPO(c006po);
	}


	@Override
	public Integer insertC006(C006PO c006po) throws Exception {
		return getSqlSession().getMapper(IC006Dao.class).insertC006(c006po);
	}

	@Override
	public Integer updateC006(C006PO c006po) throws Exception {
		return getSqlSession().getMapper(IC006Dao.class).updateC006(c006po);
	}
	
	@Override
	public Integer countC006ByMa002(C006PO c006po) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IC006Dao.class).countC006ByMa002(c006po);
	}
	@Override
	public Integer countC006ByMa003(C006PO c006po) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IC006Dao.class).countC006ByMa003(c006po);
	}

	@Override
	public Integer countC006ByMa015(C006PO c006po) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IC006Dao.class).countC006ByMa015(c006po);
	}
	@Override
	public Integer deleteC006ByIds(String ids) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IC006Dao.class).deleteC006ByIds(ids);
	}

	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return getSqlSession().getMapper(IC006Dao.class).retrieveByPage(map);
	}

	@Override
	public Integer updateHCC006(C006PO c006po) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IC006Dao.class).updateHCC006(c006po);
	}

	@Override
	public Integer countC006ByMa010(C006PO c006po) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IC006Dao.class).countC006ByMa010(c006po);
	}

	@Override
	public Integer countC006ByMa014(C006PO c006po) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IC006Dao.class).countC006ByMa014(c006po);
	}

	@Override
	public C006PO retrieveC006ByNamePassword(C006PO c006po) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IC006Dao.class).retrieveC006ByNamePassword(c006po);
	}
	@Override
	public List getZgdwList() throws Exception {
		return getSqlSession().getMapper(IC006Dao.class).getZgdwList();
	}
	@Override
	public List getZgdwData(String pId) throws Exception {
		return getSqlSession().getMapper(IC006Dao.class).getZgdwData(pId);
	}


}
