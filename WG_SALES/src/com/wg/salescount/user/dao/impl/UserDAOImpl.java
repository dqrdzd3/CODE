package com.wg.salescount.user.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.wg.salescount.basedao.impl.BaseDaoImpl;
import com.wg.salescount.user.dao.UserDAO;
import com.wg.salescount.user.po.UserPO;
import com.wg.salescount.util.Pager;
import com.wg.salescount.util.UUIDGenerator;

@Repository
public class UserDAOImpl extends BaseDaoImpl<UserPO> implements UserDAO{

	private static Log log = LogFactory.getLog(UserDAOImpl.class);
	private static final String INSERT_SQL = "INSERT INTO SC_USER(USER_ID,USER_NAME,NAME,PASSWORD,STATUS,COMPETENCE) VALUES(?,?,?,?,?,?)";
	private static final String UPDATE_SQL = "UPDATE SC_USER SET USER_NAME=?,NAME=?,PASSWORD=?,STATUS=?,COMPETENCE=? WHERE USER_ID=?";
	private static final String QUERY_SQL = "SELECT * FROM SC_USER WHERE USER_ID = ?";
	private static final String DELETE_SQL = "DELETE FROM SC_USER WHERE USER_ID = ?";
	
	@Autowired 
	public UserDAOImpl(DataSource dataSource) {
	    super();
	    setDataSource(dataSource);
	} 
	
	private static final RowMapper queryMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			UserPO user = new UserPO();
			user.setUserId(rs.getString(1));
			user.setUserName(rs.getString(2));
			user.setName(rs.getString(3));
			user.setPassword(rs.getString(4));
			user.setStatus(rs.getInt(5));
			user.setCompetence(rs.getInt(6));
			return user;
		}
	};
	
	@Override
	public UserPO queryByUserName(String userName) {
		StringBuilder sql = new StringBuilder("SELECT * FROM SC_USER WHERE USER_NAME = '");
		sql.append(userName);
		sql.append("'");
		List<UserPO> list = this.getJdbcTemplate().query(sql.toString(), queryMapper);
		UserPO userEntity = null;
		if(!list.isEmpty()){
			userEntity = list.get(0);
		}
		return userEntity;
	}

	@Override
	public Pager query(Pager pager, UserPO po) {
		return null;
	}

	@Override
	public List<UserPO> queryNoPager(UserPO po) {
		return null;
	}

	@Override
	public int delete(UserPO po) {
		Object[] params = { po.getUserId() };
		int result = this.getJdbcTemplate().update(DELETE_SQL, params);
		if (this.getJdbcTemplate().update(DELETE_SQL, params) > 0) {
			log.debug("delete success.");
		} else {
			log.debug("delete failed.");
		}
		return result;
	}

	@Override
	public UserPO save(UserPO po) {
		Object[] params =  getInsertArgs(po);
		po.setUserId(params[0].toString());
		if (this.getJdbcTemplate().update(INSERT_SQL, params) > 0) {
			log.debug("insert success.");
			return po;
		} else {
			log.debug("insert success.");
			return null;
		}
	}

	@Override
	public UserPO update(UserPO po) {
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
	public UserPO detail(UserPO po) {
		return null;
	}
	
	protected Object[] getUpdateArgs(UserPO po){
		Object[] params = {
				po.getUserName(),po.getName(),po.getPassword(),po.getStatus(),po.getCompetence(),po.getUserId()
		};
		return params;
	}

	protected Object[] getInsertArgs(UserPO po){
		String userId = UUIDGenerator.getUUID();
		Object[] params = {
				userId,po.getUserName(),po.getName(),po.getPassword(),po.getStatus(),po.getCompetence()
		};
		return params;
	}

	@Override
	public UserPO queryById(String id) {
		return null;
	}
	
}
