package com.wg.salescount.delivery.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.wg.salescount.basedao.impl.BaseDaoImpl;
import com.wg.salescount.delivery.dao.DeliveryBySupplierDAO;
import com.wg.salescount.delivery.po.CorpBusinessPO;
import com.wg.salescount.delivery.vo.DeliveryBySupplierVO;
import com.wg.salescount.util.Pager;
import com.wg.salescount.weeklysales.po.WeeklySalesPO;

@Repository
public class DeliveryBySupplierDAOImpl extends BaseDaoImpl<DeliveryBySupplierVO> implements DeliveryBySupplierDAO{

	private static Log log = LogFactory.getLog(DeliveryBySupplierDAOImpl.class);
	
	private static final String QUERY_COMPANY_SQL = "SELECT T.MA001, T.MA002 FROM CORP_BUSINESS T";
	private static final String UPDATE_POO1_SQL = "UPDATE P001 T SET T.MA004 =? WHERE T.MA002=?";//2B_id  equip_id
	private static final String QUERY_SQL = "SELECT * FROM CORP_BUSINESS T ";
	
	
	@Autowired 
	public DeliveryBySupplierDAOImpl(DataSource dataSource) {
	    super();
	    setDataSource(dataSource);
	}
	
	private static final RowMapper queryMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			DeliveryBySupplierVO deliveryBySupplier = new DeliveryBySupplierVO();
			deliveryBySupplier.setP001PrimaryKey(rs.getString(1));
			deliveryBySupplier.setEquipmentId(rs.getString(2));
			deliveryBySupplier.setCorpBusinessId(rs.getString(3));
			deliveryBySupplier.setCompanyName(rs.getString(4));
			return deliveryBySupplier;
		}
	};
	
	private static final RowMapper queryMapper1 = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			CorpBusinessPO corpBusiness = new CorpBusinessPO();
			corpBusiness.setMA001(rs.getString(1));
			corpBusiness.setMA002(rs.getString(2));
			return corpBusiness;
		}
	};
	
	public DeliveryBySupplierDAOImpl() {
	}

	@Override
	public DeliveryBySupplierVO queryById(String id) {
		return null;
	}

	@Override
	public Pager query(Pager pager, DeliveryBySupplierVO po) {
		return null;
	}

	@Override
	public List<DeliveryBySupplierVO> queryNoPager(DeliveryBySupplierVO po) {
		return null;
	}

	@Override
	public int delete(DeliveryBySupplierVO po) {
		return 0;
	}

	@Override
	public DeliveryBySupplierVO save(DeliveryBySupplierVO po) {
		return null;
	}

	protected String queryCondition(String sql, DeliveryBySupplierVO vo) {
		StringBuilder res = new StringBuilder(sql);
		StringBuilder where = new StringBuilder();
		String temp = "";
		if (vo.getCompanyName() != null && !"".equals(vo.getCompanyName())) {
			vo.setCompanyName(vo.getCompanyName().replace("'", ""));
			vo.setCompanyName(vo.getCompanyName().replace("=", ""));
			vo.setCompanyName(vo.getCompanyName().replace("(", ""));
			vo.setCompanyName(vo.getCompanyName().replace(")", ""));
			where.append(" AND T.MA002 LIKE'%").append(vo.getCompanyName()).append("%'");
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
	public DeliveryBySupplierVO update(DeliveryBySupplierVO vo) {
		Object[] params = getUpdateArgs(vo);
		if (this.getJdbcTemplate().update(UPDATE_POO1_SQL, params) > 0) {
			log.debug("update success.");
			return vo;
		} else {
			log.debug("update failed.");
			return null;
		}
	}

	@Override
	public List<DeliveryBySupplierVO> batchUpdate(final List<DeliveryBySupplierVO> voList){
		if(voList == null || voList.size() <= 0){
			return null;
		}	
		
		int results[] = this.getJdbcTemplate().batchUpdate(UPDATE_POO1_SQL, new BatchPreparedStatementSetter(){

			@Override
			public int getBatchSize() {
				return voList.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				//for(i=0;i<this.getBatchSize();i++){
					DeliveryBySupplierVO deliveryBySupplierVO = voList.get(i);
					ps.setString(1, deliveryBySupplierVO.getCorpBusinessId());
					ps.setString(2, deliveryBySupplierVO.getEquipmentId());
				//}			
			}
			
		});	
		
		int result = Arrays.binarySearch(results, 0);//判断是否存在0,返回值>=0,则存在,因为排过序
		if(result >= 0){
			return voList;
		}else{
			return null;
		}
		
	}
	
	@Override
	public DeliveryBySupplierVO detail(DeliveryBySupplierVO po) {
		return null;
	}

	protected Object[] getUpdateArgs(DeliveryBySupplierVO vo) {
		Object[] params = {vo.getCorpBusinessId(),vo.getEquipmentId()};
		return params;
	}
	
	public DeliveryBySupplierVO queryCompanyId(DeliveryBySupplierVO vo){
		String condition = this.queryCondition(QUERY_SQL, vo);
		List<CorpBusinessPO> list = this.getJdbcTemplate().query(condition, queryMapper1);
		CorpBusinessPO corpBusiness = null;
		if(!list.isEmpty()){
			corpBusiness = list.get(0);
		}
		DeliveryBySupplierVO deliveryBySupplier = new DeliveryBySupplierVO();
		String companyName = corpBusiness.getMA002();
		String corpBusinessId = corpBusiness.getMA001();
		deliveryBySupplier.setCompanyName(companyName);
		deliveryBySupplier.setCorpBusinessId(corpBusinessId);
		return deliveryBySupplier;
	}

	@Override
	public List<String> queryCompanyName() {
		List<CorpBusinessPO> corpBusinessList = this.getJdbcTemplate().query(QUERY_COMPANY_SQL, queryMapper1);
		List<String> companyNameList = new ArrayList<String>();
		if(!corpBusinessList.isEmpty()){
			for(CorpBusinessPO corpBusiness : corpBusinessList){
				String companyName = corpBusiness.getMA002();
				companyNameList.add(companyName);
			}
		}
		return companyNameList;
	}
}
