/**
 * 文件名：B103DaoImpl.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.dao.impl;
import java.util.List;
import java.util.Map;


import com.hw.hwsafe.corpinfo.dao.IB103Dao;
import com.hw.hwsafe.corpinfo.pojo.B103PO;
import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;

/**
 * B103Dao层实现类
 *
 */
public class B103DaoImpl extends BaseDaoImpl implements IB103Dao{

	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return getSqlSession().getMapper(IB103Dao.class).retrieveByPage(map);
	}

	@Override
	public B103PO retrieveB103ByID(String ma001) throws Exception{
		return getSqlSession().getMapper(IB103Dao.class).retrieveB103ByID(ma001);
	}

	@Override
	public void updateB002AqryNum(String zzjgid) throws Exception{
		getSqlSession().getMapper(IB103Dao.class).updateB002AqryNum(zzjgid);
	}

	@Override
	public void insertB103(B103PO b103PO) throws Exception{
		getSqlSession().getMapper(IB103Dao.class).insertB103(b103PO);
	}

	@Override
	public void updateB103(B103PO b103PO) throws Exception{
		getSqlSession().getMapper(IB103Dao.class).updateB103(b103PO);
	}
	@Override
	public void delb103(Map map) throws Exception{
		getSqlSession().getMapper(IB103Dao.class).delb103(map);
	}
	@Override
	public void updateB002AqryNumtj(Map map) throws Exception{
		getSqlSession().getMapper(IB103Dao.class).updateB002AqryNumtj(map);
	}
	@Override
	public int getB002Stat(String ryid) throws Exception{
		return getSqlSession().getMapper(IB103Dao.class).getB002Stat(ryid);
	}

}