package com.wg.salescount.delivery.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wg.salescount.basedao.impl.BaseDaoImpl;
import com.wg.salescount.delivery.dao.WgInterfaceCustomerDAO;
import com.wg.salescount.delivery.po.WgInterfaceCustomerPO;
import com.wg.salescount.util.Pager;

@Repository
public class WgInterfaceCustomerDAOImpl extends BaseDaoImpl<WgInterfaceCustomerPO> implements WgInterfaceCustomerDAO{

	private static Log log = LogFactory.getLog(WgInterfaceCustomerDAOImpl.class);
	
	public WgInterfaceCustomerDAOImpl() {
	}

	@Autowired 
	public WgInterfaceCustomerDAOImpl(DataSource dataSource) {
	    super();
	    setDataSource(dataSource);
	}
	
	@Override
	public WgInterfaceCustomerPO queryById(String id) {
		return null;
	}

	@Override
	public Pager query(Pager pager, WgInterfaceCustomerPO po) {
		return null;
	}

	@Override
	public List<WgInterfaceCustomerPO> queryNoPager(WgInterfaceCustomerPO po) {
		return null;
	}

	@Override
	public int delete(WgInterfaceCustomerPO po) {
		return 0;
	}

	@Override
	public WgInterfaceCustomerPO save(WgInterfaceCustomerPO po) {
		return null;
	}

	@Override
	public WgInterfaceCustomerPO update(WgInterfaceCustomerPO po) {
		return null;
	}

	@Override
	public WgInterfaceCustomerPO detail(WgInterfaceCustomerPO po) {
		return null;
	}

}
