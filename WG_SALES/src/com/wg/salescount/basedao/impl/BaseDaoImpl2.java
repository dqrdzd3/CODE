package com.wg.salescount.basedao.impl;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.wg.salescount.basedao.BaseDAO;
import com.wg.salescount.util.Pager;

public class BaseDaoImpl2<T> extends JdbcDaoSupport implements BaseDAO<T> {

	private Class clazz;
	public BaseDaoImpl2(Class clazz){
		this.clazz = clazz;
	}
	
	/**
	 * 分页
	 * 
	 * @author 王珂
	 * @param pager
	 * @param condition
	 * @return
	 * @time 2015年7月22日 下午4:01:49
	 */
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

	//protected abstract String queryCondition(String sql, WeeklySales po);

	
	//protected  String queryCondition(String sql, WeeklySales po){};
	
	protected static String validate(String param) {
		param = param.replace("'", "");
		param = param.replace("=", "");
		param = param.replace("(", "");
		param = param.replace(")", "");
		return param;
	}

	@Override
	public Pager query(Pager pager, T po) {
		return null;
	}

	@Override
	public List<T> queryNoPager(T po) {
		return null;
	}

	@Override
	public int delete(T po) {
		return 0;
	}

	@Override
	public T save(T po) {
		return null;
	}

	@Override
	public T update(T po) {
		return null;
	}

	@Override
	public T detail(T po) {
		return null;
	}

	@Override
	public T queryById(String id) {
		return null;
	}

	//protected abstract Object[] getUpdateArgs(WeeklySales po);

	//protected abstract Object[] getInsertArgs(WeeklySales po);

}
