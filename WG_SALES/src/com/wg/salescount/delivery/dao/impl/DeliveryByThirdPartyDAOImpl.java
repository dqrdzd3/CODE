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
import com.wg.salescount.delivery.dao.DeliveryByThirdPartyDAO;
import com.wg.salescount.delivery.po.CorpBusinessPO;
import com.wg.salescount.delivery.po.WgInterfaceCustomerPO;
import com.wg.salescount.delivery.vo.DeliveryBySupplierVO;
import com.wg.salescount.delivery.vo.DeliveryByThirdPartyVO;
import com.wg.salescount.util.Pager;

@Repository
public class DeliveryByThirdPartyDAOImpl extends BaseDaoImpl<DeliveryByThirdPartyVO> implements DeliveryByThirdPartyDAO{

	private static Log log = LogFactory.getLog(DeliveryByThirdPartyDAOImpl.class);
	
	private static final String QUERY_COMPANY_SQL = "SELECT T.MA001, T.MA002 FROM WG_INTERFACE_CUSTOMER T";
	private static final String UPDATE_POO1_SQL = "UPDATE P001 T SET T.MA003 =? WHERE T.MA002=?";
	private static final String QUERY_SQL = "SELECT * FROM WG_INTERFACE_CUSTOMER T ";
	
	@Autowired 
	public DeliveryByThirdPartyDAOImpl(DataSource dataSource) {
	    super();
	    setDataSource(dataSource);
	}
	
	private static final RowMapper queryMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			DeliveryByThirdPartyVO deliveryByThirdParty = new DeliveryByThirdPartyVO();
			deliveryByThirdParty.setPo001PrimaryKey(rs.getString(1));
			deliveryByThirdParty.setEquipmentId(rs.getString(2));
			deliveryByThirdParty.setInterfaceCompanyId(rs.getString(3));
			deliveryByThirdParty.setCompanyName(rs.getString(4));
			return deliveryByThirdParty;
		}
	};
	
	private static final RowMapper queryMapper1 = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			WgInterfaceCustomerPO wgInterfaceCustomer = new WgInterfaceCustomerPO();
			wgInterfaceCustomer.setMA001(rs.getString(1));
			wgInterfaceCustomer.setMA002(rs.getString(2));
			return wgInterfaceCustomer;
		}
	};
	
	public DeliveryByThirdPartyDAOImpl() {
	}

	@Override
	public DeliveryByThirdPartyVO queryById(String id) {
		return null;
	}

	@Override
	public Pager query(Pager pager, DeliveryByThirdPartyVO po) {
		return null;
	}

	@Override
	public List<DeliveryByThirdPartyVO> queryNoPager(DeliveryByThirdPartyVO po) {
		return null;
	}

	@Override
	public int delete(DeliveryByThirdPartyVO po) {
		return 0;
	}

	@Override
	public DeliveryByThirdPartyVO save(DeliveryByThirdPartyVO po) {
		return null;
	}

	protected String queryCondition(String sql, DeliveryByThirdPartyVO vo) {
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
	public DeliveryByThirdPartyVO update(DeliveryByThirdPartyVO vo) {
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
	public List<DeliveryByThirdPartyVO> batchUpdate(final List<DeliveryByThirdPartyVO> voList){
		System.out.println("voList="+voList);
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
				DeliveryByThirdPartyVO deliveryByThirdPartyVO = voList.get(i);
					ps.setString(1, deliveryByThirdPartyVO.getInterfaceCompanyId());
					ps.setString(2, deliveryByThirdPartyVO.getEquipmentId());
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
	public DeliveryByThirdPartyVO detail(DeliveryByThirdPartyVO po) {
		return null;
	}

	protected Object[] getUpdateArgs(DeliveryByThirdPartyVO vo) {
		Object[] params = {vo.getInterfaceCompanyId(),vo.getEquipmentId()};
		return params;
	}
	
	public DeliveryByThirdPartyVO queryCompanyId(DeliveryByThirdPartyVO vo){
		String condition = this.queryCondition(QUERY_SQL, vo);
		List<WgInterfaceCustomerPO> list = this.getJdbcTemplate().query(condition, queryMapper1);
		WgInterfaceCustomerPO wgInterfaceCustomer = null;
		if(!list.isEmpty()){
			wgInterfaceCustomer = list.get(0);
		}
		DeliveryByThirdPartyVO deliveryByThirdParty = new DeliveryByThirdPartyVO();
		String companyName = wgInterfaceCustomer.getMA002();
		String interfaceCompanyId = wgInterfaceCustomer.getMA001();
		deliveryByThirdParty.setCompanyName(companyName);
		deliveryByThirdParty.setInterfaceCompanyId(interfaceCompanyId);
		return deliveryByThirdParty;
	}

	@Override
	public List<String> queryCompanyName() {
		List<WgInterfaceCustomerPO> wgInterfaceCustomerList = this.getJdbcTemplate().query(QUERY_COMPANY_SQL, queryMapper1);
		List<String> companyNameList = new ArrayList<String>();
		if(!wgInterfaceCustomerList.isEmpty()){
			for(WgInterfaceCustomerPO wgInterfaceCustomer : wgInterfaceCustomerList){
				String companyName = wgInterfaceCustomer.getMA002();
				companyNameList.add(companyName);
			}
		}
		return companyNameList;
	}
	
}
