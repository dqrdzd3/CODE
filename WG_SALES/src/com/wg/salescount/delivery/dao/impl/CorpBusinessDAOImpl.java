package com.wg.salescount.delivery.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wg.salescount.basedao.impl.BaseDaoImpl;
import com.wg.salescount.delivery.dao.CorpBusinessDAO;
import com.wg.salescount.delivery.po.CorpBusinessPO;
import com.wg.salescount.util.Pager;

@Repository
public class CorpBusinessDAOImpl extends BaseDaoImpl<CorpBusinessPO> implements CorpBusinessDAO{

	private static Log log = LogFactory.getLog(CorpBusinessDAOImpl.class);
	
	@Autowired 
	public CorpBusinessDAOImpl(DataSource dataSource) {
	    super();
	    setDataSource(dataSource);
	}
	
	public CorpBusinessDAOImpl() {
	}

	@Override
	public CorpBusinessPO queryById(String id) {
		return null;
	}

	@Override
	public Pager query(Pager pager, CorpBusinessPO po) {
		return null;
	}

	@Override
	public List<CorpBusinessPO> queryNoPager(CorpBusinessPO po) {
		return null;
	}

	@Override
	public int delete(CorpBusinessPO po) {
		return 0;
	}

	@Override
	public CorpBusinessPO save(CorpBusinessPO po) {
		return null;
	}

	@Override
	public CorpBusinessPO update(CorpBusinessPO po) {
		return null;
	}

	@Override
	public CorpBusinessPO detail(CorpBusinessPO po) {
		return null;
	}

}
