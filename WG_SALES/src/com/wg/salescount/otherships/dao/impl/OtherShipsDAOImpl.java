package com.wg.salescount.otherships.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.wg.salescount.basedao.impl.BaseDaoImpl;
import com.wg.salescount.otherships.dao.OtherShipsDAO;
import com.wg.salescount.otherships.po.OtherShipsPO;
import com.wg.salescount.taobaosales.po.TaoBaoSalesPO;
import com.wg.salescount.util.DateUtil;
import com.wg.salescount.util.Pager;
import com.wg.salescount.util.UUIDGenerator;

@Repository
public class OtherShipsDAOImpl extends BaseDaoImpl<OtherShipsPO> implements OtherShipsDAO{
	
	private static Log log = LogFactory.getLog(OtherShipsDAOImpl.class);
	private static final String INSERT_SQL = "INSERT INTO SC_OTHER_SHIPS(OTHER_SHIPS_ID,CUSTOMER_NAME,CONSIGNEE_NAME,"
				+ "RECEIVER_ADDR,PHONE_NO,DELIVERY_TIME,PRODUCT_NAME,PRODUCT_ID,UNIT_PRICE,"
				+ "QUANTITY,TOTAL_PRICE,MONEY_STATUS,INVOICE,INVOICE_NO,SALES_PLATFORM,"
				+ "COURIER_COMPANY,COURIER_NO,SIGN_TIME,COURIER_COST,INSURED_COST,CITY,GENDER,AGE,JOBS) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_SQL = "UPDATE SC_OTHER_SHIPS SET CUSTOMER_NAME=?,CONSIGNEE_NAME=?,RECEIVER_ADDR=?,"
				+ "PHONE_NO=?,DELIVERY_TIME=?,PRODUCT_NAME=?,PRODUCT_ID=?,UNIT_PRICE=?,QUANTITY=?,"
				+ "TOTAL_PRICE=?,MONEY_STATUS=?,INVOICE=?,INVOICE_NO=?,SALES_PLATFORM=?,COURIER_COMPANY=?,"
				+ "COURIER_NO=?,SIGN_TIME=?,COURIER_COST=?,INSURED_COST=?,CITY=?,GENDER=?,AGE=?,JOBS=? WHERE OTHER_SHIPS_ID= ?";
	private static final String DELETE_SQL = "DELETE FROM SC_OTHER_SHIPS WHERE OTHER_SHIPS_ID = ?";
	private static final String QUERY_SQL = "SELECT * FROM SC_OTHER_SHIPS ";
	private static final String COUNT_SQL = "SELECT COUNT(*) FROM SC_OTHER_SHIPS ";
	
	@Autowired 
	public OtherShipsDAOImpl(DataSource dataSource) {
	    super();
	    setDataSource(dataSource);
	} 
	
	private static final RowMapper queryMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			OtherShipsPO otherShips = new OtherShipsPO();
			otherShips.setOtherShipsId(rs.getString(1));
			otherShips.setCustomerName(rs.getString(2));
			otherShips.setConsigneeName(rs.getString(3));
			otherShips.setReceiverAddr(rs.getString(4));
			otherShips.setPhoneNo(rs.getString(5));
			Date deliveryTime = null;
			String deliveryTimeString = rs.getString(6);
			if(deliveryTimeString != null && !deliveryTimeString.equals("")){
				deliveryTime = DateUtil.string2Date(deliveryTimeString);
			}
			otherShips.setDeliveryTime(deliveryTime);
			otherShips.setProductName(rs.getString(7));
			otherShips.setProductId(rs.getString(8));
			otherShips.setUnitPrice(rs.getFloat(9));
			otherShips.setQuantity(rs.getInt(10));
			otherShips.setTotalPrice(rs.getFloat(11));
			otherShips.setMoneyStatus(rs.getInt(12));
			otherShips.setInvoice(rs.getInt(13));
			otherShips.setInvoiceNo(rs.getString(14));
			otherShips.setSalesPlatform(rs.getString(15));
			otherShips.setCourierCompany(rs.getString(16));
			otherShips.setCourierNo(rs.getString(17));
			Date signTime = null;
			String signTimeString = rs.getString(18);
			if(signTimeString != null && !signTimeString.equals("")){
				signTime = DateUtil.string2Date(deliveryTimeString);
			}
			otherShips.setSignTime(signTime);
			otherShips.setCourierCost(rs.getFloat(19));
			otherShips.setInsuredCost(rs.getFloat(20));
			otherShips.setCity(rs.getString(21));
			otherShips.setGender(rs.getInt(22));
			otherShips.setAge(rs.getInt(23));
			otherShips.setJobs(rs.getString(24));
			return otherShips;
		}
	};			

	@Override
	public Pager query(Pager pager, OtherShipsPO po) {
		String condition = this.queryCondition(QUERY_SQL, po);
		StringBuilder sql = new StringBuilder(pagerAdapter(pager, condition));
		List<OtherShipsPO> list = this.getJdbcTemplate().query(sql.toString(), queryMapper);
		log.debug("query with page");
		pager.setList(list);
		return pager;
	}

	@Override
	public List<OtherShipsPO> queryNoPager(OtherShipsPO po) {
		List<OtherShipsPO> list = this.getJdbcTemplate().query(queryCondition(QUERY_SQL, po), queryMapper);
		log.debug("query with no page");
		return list;
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
	
	public int getNum(OtherShipsPO po) {
		int result = this.getJdbcTemplate().queryForInt(this.queryCondition(COUNT_SQL, po));
		log.debug("count query");
		if (result != 0) {
			return result;
		} else {
			return 0;
		}

	}
	
	protected String queryCondition(String sql, OtherShipsPO po) {
		StringBuilder res = new StringBuilder(sql);
		StringBuilder where = new StringBuilder();
		String temp = "";
		if(po.getConsigneeName() != null && !"".equals(po.getConsigneeName())){
			po.setConsigneeName(po.getConsigneeName().replace("'", ""));
			po.setConsigneeName(po.getConsigneeName().replace("=", ""));
			po.setConsigneeName(po.getConsigneeName().replace("(", ""));
			po.setConsigneeName(po.getConsigneeName().replace(")", ""));
			where.append(" AND CONSIGNEE_NAME LIKE '%").append(po.getConsigneeName()).append("%'");
		}//收货人姓名
		if (po.getProductName() != null && !"".equals(po.getProductName())) {
			po.setProductName(po.getProductName().replace("'", ""));
			po.setProductName(po.getProductName().replace("=", ""));
			po.setProductName(po.getProductName().replace("(", ""));
			po.setProductName(po.getProductName().replace(")", ""));
			where.append(" AND PRODUCT_NAME LIKE'%").append(po.getProductName()).append("%'");
		}//产品名称
		if(po.getSalesPlatform() != null && !"".equals(po.getSalesPlatform())){
			po.setSalesPlatform(po.getSalesPlatform().replace("'", ""));
			po.setSalesPlatform(po.getSalesPlatform().replace("=", ""));
			po.setSalesPlatform(po.getSalesPlatform().replace("(", ""));
			po.setSalesPlatform(po.getSalesPlatform().replace(")", ""));
			where.append(" AND SALES_PLATFORM LIKE '%").append(po.getSalesPlatform()).append("%'");
		}//销售平台
		if (po.getProductId() != null && !"".equals(po.getProductId())) {
			po.setProductId(po.getProductId().replace("'", ""));
			po.setProductId(po.getProductId().replace("=", ""));
			po.setProductId(po.getProductId().replace("(", ""));
			po.setProductId(po.getProductId().replace(")", ""));
			where.append(" AND PRODUCT_ID ='").append(validate(po.getProductId())).append("'");
		}//产品型号
		if (po.getUnitPrice() != 0) {
			where.append(" AND UNITPRICE ='").append("'");
		}
		if (po.getQuantity() != 0) {
			where.append(" AND QUANTITY ='").append("'");
		}
		if (po.getTotalPrice() != 0) {
			where.append(" AND TOTALPRICE ='").append("'");
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
	public int delete(OtherShipsPO po) {
		Object[] params = { po.getOtherShipsId() };
		int result = this.getJdbcTemplate().update(DELETE_SQL, params);
		if (this.getJdbcTemplate().update(DELETE_SQL, params) > 0) {
			log.debug("delete success.");
		} else {
			log.debug("delete failed.");
		}
		return result;
	}

	@Override
	public OtherShipsPO save(OtherShipsPO po) {
		System.out.println(po);
		Object[] params =  getInsertArgs(po);
		po.setOtherShipsId(params[0].toString());
		if (this.getJdbcTemplate().update(INSERT_SQL, params) > 0) {
			log.debug("insert success.");
			return po;
		} else {
			log.debug("insert success.");
			return null;
		}
	}

	@Override
	public OtherShipsPO update(OtherShipsPO po) {
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
	public OtherShipsPO detail(OtherShipsPO po) {
		List<OtherShipsPO> list = this.getJdbcTemplate().query(queryCondition(QUERY_SQL, po), queryMapper);
		return (list != null && !list.isEmpty()) ? list.get(0) : null;
	}

	protected Object[] getUpdateArgs(OtherShipsPO po) {
		Object[] params = { po.getCustomerName(),po.getConsigneeName(),po.getReceiverAddr()
				,po.getPhoneNo(),po.getDeliveryTime(),po.getProductName(),po.getProductId(), po.getUnitPrice(),
				po.getQuantity(),po.getTotalPrice(),po.getMoneyStatus(),po.getInvoice(),po.getInvoiceNo(),po.getSalesPlatform(),
				po.getCourierCompany(),po.getCourierNo(),po.getSignTime(),po.getCourierCost(),po.getInsuredCost(),po.getCity(),
				po.getGender(),po.getAge(),po.getJobs(),po.getOtherShipsId() };
		return params;
	}

	protected Object[] getInsertArgs(OtherShipsPO po) {
		String otherShipsId = UUIDGenerator.getUUID();
		 Object[] params = { otherShipsId, po.getCustomerName(),po.getConsigneeName(),po.getReceiverAddr()
					,po.getPhoneNo(),po.getDeliveryTime(),po.getProductName(),po.getProductId(), po.getUnitPrice(),
					po.getQuantity(),po.getTotalPrice(),po.getMoneyStatus(),po.getInvoice(),po.getInvoiceNo(),po.getSalesPlatform(),
					po.getCourierCompany(),po.getCourierNo(),po.getSignTime(),po.getCourierCost(),po.getInsuredCost(),po.getCity(),
					po.getGender(),po.getAge(),po.getJobs() };
		System.out.println(otherShipsId);
		return params;
	}

	@Override
	public OtherShipsPO queryById(String id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM SC_OTHER_SHIPS WHERE OTHER_SHIPS_ID = '");
		sql.append(id);
		sql.append("'");
		List<OtherShipsPO> list = this.getJdbcTemplate().query(sql.toString(), queryMapper);
		OtherShipsPO otherShipsEntity = null;
		if(!list.isEmpty()){
			otherShipsEntity = list.get(0);
		}
		return otherShipsEntity;
	}
}
