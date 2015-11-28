package com.wg.salescount.delivery.dao;

import java.util.List;

import com.wg.salescount.basedao.BaseDAO;
import com.wg.salescount.delivery.po.P001PO;

public interface P001DAO extends BaseDAO<P001PO>{
	public List<P001PO> queryExist(P001PO po);
}
