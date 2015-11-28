package com.wg.salescount.delivery.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.wg.salescount.basedao.impl.BaseDaoImpl;
import com.wg.salescount.delivery.dao.P001DAO;
import com.wg.salescount.delivery.po.P001PO;
import com.wg.salescount.util.Pager;

@Repository
public class P001DAOImpl extends BaseDaoImpl<P001PO> implements P001DAO{

	private static Log log = LogFactory.getLog(P001DAOImpl.class);
	
	private static final String QUERY_EXIST_SQL = "SELECT * FROM P001 T ";
	
	public P001DAOImpl() {
	}

	private static final RowMapper queryMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			P001PO p001PO = new P001PO();
			p001PO.setMA001(rs.getString(1));
			p001PO.setMA002(rs.getString(2));
			p001PO.setMA003(rs.getString(3));//interface id
			p001PO.setMA004(rs.getString(4));//corp id
			return p001PO;
		}
	};
	
	@Autowired 
	public P001DAOImpl(DataSource dataSource) {
	    super();
	    setDataSource(dataSource);
	}
	
	@Override
	public P001PO queryById(String id) {
		return null;
	}

	@Override
	public Pager query(Pager pager, P001PO po) {
		return null;
	}

	@Override
	public List<P001PO> queryNoPager(P001PO po) {
		return null;
	}

	@Override
	public int delete(P001PO po) {
		return 0;
	}

	@Override
	public P001PO save(P001PO po) {
		return null;
	}

	@Override
	public P001PO update(P001PO po) {
		return null;
	}

	@Override
	public P001PO detail(P001PO po) {
		return null;
	}

	protected String queryCondition(String sql, P001PO po) {
		StringBuilder res = new StringBuilder(sql);
		StringBuilder where = new StringBuilder();
		String temp = "";
		if(po.getMA002() != null && !"".equals(po.getMA002())){
			where.append(" AND T.MA002 ='").append(po.getMA002()).append("'");
		}
		if (where.length() > 0) {
			res.append(" WHERE ");
			res.append(where.toString().substring(4, where.length()));
		} else {
			res.append(where);
		}
		return res.toString();
	}
	
	@Override
	public List<P001PO> queryExist(P001PO po){
		log.debug("enter p001dao");
		//System.out.println("enter p001dao");
		String condition = this.queryCondition(QUERY_EXIST_SQL, po);
		List<P001PO> p001POList = this.getJdbcTemplate().query(condition, queryMapper);
		List<P001PO> p001POListForReturn = new ArrayList<P001PO>();
		 if(!p001POList.isEmpty()){
			 for(P001PO p001POReturn : p001POList){
				 String interfaceId = p001POReturn.getMA003();
				 String corpId = p001POReturn.getMA004();
				 if(interfaceId != null || corpId != null){
					 p001POListForReturn.add(p001POReturn);
					 //return true;//表示已经有绑定
				 }
			 }
		 }
		 return p001POListForReturn;
		 //return false;//表示无绑定
	}
	
	
}
