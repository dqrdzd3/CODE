package com.wg.salescount.weeklysales.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.wg.salescount.weeklysales.dao.WeeklySalesDAO;
import com.wg.salescount.basedao.impl.BaseDaoImpl;
import com.wg.salescount.util.Pager;
import com.wg.salescount.util.UUIDGenerator;
import com.wg.salescount.weeklysales.po.WeeklySalesPO;

@Repository
public class WeeklySalesDAOImpl extends BaseDaoImpl<WeeklySalesPO> implements WeeklySalesDAO {
	
	private static Log log = LogFactory.getLog(WeeklySalesDAOImpl.class);

	private static final String INSERT_SQL = "INSERT INTO SC_WEEKLY_SALES(WEEKLY_SALES_ID,PRODUCT_NAME,SPECIFICATIONS_MODEL,UNIT_PRICE,QUANTITY,TOTAL_PRICE,WEEK,YEAR) VALUES(?,?,?,?,?,?,?,?)";
	private static final String UPDATE_SQL = "UPDATE SC_WEEKLY_SALES SET PRODUCT_NAME=?,SPECIFICATIONS_MODEL=?,UNIT_PRICE=?,QUANTITY=?,TOTAL_PRICE=?,WEEK=?,YEAR=? WHERE WEEKLY_SALES_ID=?";
	private static final String DELETE_SQL = "DELETE FROM SC_WEEKLY_SALES WHERE WEEKLY_SALES_ID = ?";
	private static final String QUERY_SQL = "SELECT * FROM SC_WEEKLY_SALES ";
	private static final String COUNT_SQL = "SELECT COUNT(*) FROM SC_WEEKLY_SALES ";
	
	@Autowired 
	public WeeklySalesDAOImpl(DataSource dataSource) {
	    super();
	    setDataSource(dataSource);
	} 
	
	private static final RowMapper queryMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			WeeklySalesPO weeklySales = new WeeklySalesPO();
			weeklySales.setWeeklySalesId(rs.getString(1));
			weeklySales.setProductName(rs.getString(2));
			weeklySales.setSpecificationsModel(rs.getString(3));
			weeklySales.setUnitPrice(rs.getFloat(4));
			weeklySales.setQuantity(rs.getInt(5));
			weeklySales.setTotalPrice(rs.getFloat(6));
			weeklySales.setWeek(rs.getInt(7));
			weeklySales.setYear(rs.getInt(8));
			return weeklySales;
		}
	};

	@Override
	public Pager query(Pager pager, WeeklySalesPO po) {
		String condition = this.queryCondition(QUERY_SQL, po);
		StringBuilder sql = new StringBuilder(pagerAdapter(pager, condition));
		List<WeeklySalesPO> list = this.getJdbcTemplate().query(sql.toString(), queryMapper);
		log.debug("query with page");
		pager.setList(list);
		return pager;
	}

	protected String pagerAdapter(Pager pager, String condition) {
		pager.setCurrentPage(pager.getCurrentPage());
		pager.setSize(pager.getSize());
		StringBuilder totalSql = new StringBuilder("SELECT COUNT(*) AS COUNT FROM (").append(condition).append(")");
		pager.setTotal(this.getJdbcTemplate().queryForInt(totalSql.toString()));
		int end = 0;
		int start = (pager.getCurrentPage() - 1) * pager.getSize();
		if (start == pager.getSize()) {
			start = (pager.getCurrentPage() - 1) * pager.getSize() + 1;
			end = (pager.getCurrentPage() - 1) * pager.getSize() + pager.getSize();
		} else if (start == 0) {
			end = pager.getSize();
		} else {
			end = (pager.getCurrentPage() - 1) * pager.getSize() + pager.getSize() - 1;
		}
		StringBuilder sql = new StringBuilder("SELECT * FROM (SELECT A.*, ROWNUM RN FROM ( ");
		sql.append(condition);
		sql.append(" ) A WHERE ROWNUM <= ");
		sql.append(end);
		sql.append(" ) WHERE RN >= ");
		sql.append(start);
		return sql.toString();
	}

	public int getNum(WeeklySalesPO po) {
		int result = this.getJdbcTemplate().queryForInt(this.queryCondition(COUNT_SQL, po));
		log.debug("count query");
		if (result != 0) {
			return result;
		} else {
			return 0;
		}
	}

	@Override
	public List<WeeklySalesPO> queryNoPager(WeeklySalesPO po) {
		List<WeeklySalesPO> list = this.getJdbcTemplate().query(queryCondition(QUERY_SQL, po), queryMapper);
		log.debug("query with no page");
		return list;
	}

	@Override
	public int delete(WeeklySalesPO po) {
		Object[] params = { po.getWeeklySalesId() };
		int result = this.getJdbcTemplate().update(DELETE_SQL, params);
		if (this.getJdbcTemplate().update(DELETE_SQL, params) > 0) {
			log.debug("delete success.");
		} else {
			log.debug("delete failed.");
		}
		return result;
	}

	@Override
	public WeeklySalesPO update(WeeklySalesPO po) {
		Object[] params = getUpdateArgs(po);
		if (this.getJdbcTemplate().update(UPDATE_SQL, params) > 0) {
			log.debug("update success.");
			return po;
		} else {
			log.debug("update failed.");
			return null;
		}
	}

	@Override
	public WeeklySalesPO save(WeeklySalesPO po) {
		System.out.println(po);
		Object[] params =  getInsertArgs(po);
		po.setWeeklySalesId(params[0].toString());
		if (this.getJdbcTemplate().update(INSERT_SQL, params) > 0) {
			log.debug("insert success.");
			return po;
		} else {
			log.debug("insert success.");
			return null;
		}
	}

	@Override
	public WeeklySalesPO detail(WeeklySalesPO po) {
		List<WeeklySalesPO> list = this.getJdbcTemplate().query(queryCondition(QUERY_SQL, po), queryMapper);
		return (list != null && !list.isEmpty()) ? list.get(0) : null;
	}

	protected String queryCondition(String sql, WeeklySalesPO po) {
		StringBuilder res = new StringBuilder(sql);
		StringBuilder where = new StringBuilder();
		String temp = "";
		if (po.getProductName() != null && !"".equals(po.getProductName())) {
			po.setProductName(po.getProductName().replace("'", ""));
			po.setProductName(po.getProductName().replace("=", ""));
			po.setProductName(po.getProductName().replace("(", ""));
			po.setProductName(po.getProductName().replace(")", ""));
			where.append(" AND PRODUCT_NAME LIKE'%").append(po.getProductName()).append("%'");
		}
		if (po.getSpecificationsModel() != null && !"".equals(po.getSpecificationsModel())) {
			where.append(" AND SPECIFICATIONS_MODEL ='").append(validate(po.getSpecificationsModel())).append("'");
		}
		if (po.getUnitPrice() != 0) {
			where.append(" AND UNITPRICE ='").append("'");
		}
		if (po.getQuantity() != 0) {
			where.append(" AND QUANTITY ='").append("'");
		}
		if (po.getTotalPrice() != 0) {
			where.append(" AND TOTALPRICE ='").append("'");
		}
		if (po.getWeek() != 0) {
			where.append(" AND WEEK ='").append("'");
		}
		if (po.getYear() != 0) {
			where.append(" AND YEAR ='").append("'");
		}

		if (where.length() > 0) {
			res.append(" WHERE ");
			res.append(where.toString().substring(4, where.length()));
		} else {
			res.append(where);
		}
		return res.toString();
	}

	protected Object[] getUpdateArgs(WeeklySalesPO po) {
		Object[] params = { po.getProductName(), po.getSpecificationsModel(), po.getUnitPrice(), po.getQuantity(),
				po.getTotalPrice(), po.getWeek(), po.getYear(), po.getWeeklySalesId() };
		return params;
	}

	protected Object[] getInsertArgs(WeeklySalesPO po) {
		String weeklySalesId = UUIDGenerator.getUUID();
		 Object[] params = { weeklySalesId, po.getProductName(),
		 po.getSpecificationsModel(),
		 po.getUnitPrice(), po.getQuantity(), po.getTotalPrice(),
		 po.getWeek(), po.getYear() };
	//	Object[] params = { "123", "123", "123", 1, 1, 1, 1, 1, 1 };
		System.out.println(weeklySalesId);
		return params;
	}

	@Override
	public WeeklySalesPO queryById(String id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM SC_WEEKLY_SALES WHERE WEEKLY_SALES_ID = '");
		sql.append(id);
		sql.append("'");
		List<WeeklySalesPO> list = this.getJdbcTemplate().query(sql.toString(), queryMapper);
		WeeklySalesPO weeklySalesEntity = null;
		if(!list.isEmpty()){
			weeklySalesEntity = list.get(0);
		}
		return weeklySalesEntity;
	}
}
