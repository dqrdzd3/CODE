package com.wg.salescount.sales.dao.impl;

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
import com.wg.salescount.sales.dao.SalesDAO;
import com.wg.salescount.sales.po.SalesPO;
import com.wg.salescount.util.DateUtil;
import com.wg.salescount.util.Pager;
import com.wg.salescount.util.UUIDGenerator;
import com.wg.salescount.weeklysales.po.WeeklySalesPO;

@Repository
public class SalesDAOImpl extends BaseDaoImpl<SalesPO> implements SalesDAO{

	private static Log log = LogFactory.getLog(SalesDAOImpl.class);
	
	private static final String INSERT_SQL = "INSERT INTO SC_SALES(SALES_ID,CUSTOMER_NAME,CONSIGNEE_NAME,RECEIVER_ADDR,"
				+ "PHONE_NO,DELIVERY_TIME,BUYERS_NICKNAME,PRODUCT_NAME,PRODUCT_ID,MODEL_TYPE,COLOR,"
				+ "UNIT_PRICE,QUANTITY,TOTAL_PRICE,MONEY_STATUS,INVOICE,INVOICE_NO,SALES_PLATFORM,"
				+ "COURIER_COMPANY,COURIER_NO,SIGN_TIME,COURIER_COST,REMARK) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_SQL = "UPDATE SC_SALES SET CUSTOMER_NAME=?,CONSIGNEE_NAME=?,RECEIVER_ADDR=?,"
				+ "PHONE_NO=?,DELIVERY_TIME=?,BUYERS_NICKNAME=?,PRODUCT_NAME=?,PRODUCT_ID=?,"
				+ "MODEL_TYPE=?,COLOR=?,UNIT_PRICE=?,QUANTITY=?,TOTAL_PRICE=?,MONEY_STATUS=?,"
				+ "INVOICE=?,INVOICE_NO=?,SALES_PLATFORM=?,COURIER_COMPANY=?,COURIER_NO=?,"
				+ "SIGN_TIME=?,COURIER_COST=?,REMARK=? WHERE SALES_ID=?";
	private static final String DELETE_SQL = "DELETE FROM SC_SALES WHERE SALES_ID = ?";
	private static final String QUERY_SQL = "SELECT * FROM SC_SALES ";
	private static final String COUNT_SQL = "SELECT COUNT(*) FROM SC_SALES ";
	
	@Autowired 
	public SalesDAOImpl(DataSource dataSource) {
	    super();
	    setDataSource(dataSource);
	}
	
	private static final RowMapper queryMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			SalesPO sales = new SalesPO();
			sales.setSalesId(rs.getString(1));
			sales.setCustomerName(rs.getString(2));
			sales.setConsigneeName(rs.getString(3));
			sales.setReceiverAddr(rs.getString(4));
			sales.setPhoneNo(rs.getString(5));
			String deliveryTimeString = rs.getString(6);
			Date deliveryTime = null;
			if(deliveryTimeString != null && !deliveryTimeString.equals("")){
				deliveryTime = DateUtil.string2Date(rs.getString(6));
			}
			System.out.println("deliveryTime="+deliveryTime);
			sales.setDeliveryTime(deliveryTime);
			sales.setBuyersNickname(rs.getString(7));
			sales.setProductName(rs.getString(8));
			sales.setProductId(rs.getString(9));
			sales.setModelType(rs.getString(10));
			sales.setColor(rs.getString(11));
			sales.setUnitPrice(rs.getFloat(12));
			sales.setQuantity(rs.getInt(13));
			sales.setTotalPrice(rs.getFloat(14));
			sales.setMoneyStatus(rs.getInt(15));
			sales.setInvoice(rs.getInt(16));
			sales.setInvoiceNo(rs.getString(17));
			sales.setSalesPlatform(rs.getInt(18));
			sales.setCourierCompany(rs.getString(19));
			sales.setCourierNo(rs.getString(20));
			String signTimeString = rs.getString(21);
			Date signTime = null;
			if(signTimeString != null && !signTimeString.equals("")){
				signTime = DateUtil.string2Date(rs.getString(21));
			}
			System.out.println("signTimeString="+signTimeString);
			sales.setSignTime(signTime);
			sales.setCourierCost(rs.getFloat(22));
			/*sales.setInsuredCost(rs.getFloat(23));
			sales.setCity(rs.getString(24));
			sales.setGender(rs.getInt(25));
			sales.setAge(rs.getInt(26));
			sales.setJobs(rs.getString(27));*/
			sales.setRemark(rs.getString(23));
			return sales;
		}
	};

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

	public int getNum(SalesPO po) {
		int result = this.getJdbcTemplate().queryForInt(this.queryCondition(COUNT_SQL, po));
		log.debug("count query");
		if (result != 0) {
			return result;
		} else {
			return 0;
		}
	}
	
	@Override
	public Pager query(Pager pager, SalesPO po) {
		String condition = this.queryCondition(QUERY_SQL, po);
		StringBuilder sql = new StringBuilder(pagerAdapter(pager, condition));
		List<SalesPO> list = this.getJdbcTemplate().query(sql.toString(), queryMapper);
		log.debug("query with page");
		pager.setList(list);
		return pager;
	}

	@Override
	public List<SalesPO> queryNoPager(SalesPO po) {
		List<SalesPO> list = this.getJdbcTemplate().query(queryCondition(QUERY_SQL, po), queryMapper);
		log.debug("query with no page");
		return list;	}

	@Override
	public int delete(SalesPO po) {
		Object[] params = { po.getSalesId() };
		int result = this.getJdbcTemplate().update(DELETE_SQL, params);
		if (this.getJdbcTemplate().update(DELETE_SQL, params) > 0) {
			log.debug("delete success.");
		} else {
			log.debug("delete failed.");
		}
		return result;
	}

	@Override
	public SalesPO save(SalesPO po) {
		System.out.println(po);
		Object[] params =  getInsertArgs(po);
		po.setSalesId(params[0].toString());
		if (this.getJdbcTemplate().update(INSERT_SQL, params) > 0) {
			log.debug("insert success.");
			return po;
		} else {
			log.debug("insert success.");
			return null;
		}
	}

	@Override
	public SalesPO update(SalesPO po) {
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
	public SalesPO detail(SalesPO po) {
		List<SalesPO> list = this.getJdbcTemplate().query(queryCondition(QUERY_SQL, po), queryMapper);
		return (list != null && !list.isEmpty()) ? list.get(0) : null;
	}
	
	protected String queryCondition(String sql, SalesPO po) {
		StringBuilder res = new StringBuilder(sql);
		StringBuilder where = new StringBuilder();
		String temp = "";
		if(po.getCustomerName() != null && !"".equals(po.getCustomerName())){
			po.setCustomerName(po.getCustomerName().replace("'", ""));
			po.setCustomerName(po.getCustomerName().replace("=", ""));
			po.setCustomerName(po.getCustomerName().replace("(", ""));
			po.setCustomerName(po.getCustomerName().replace(")", ""));
			where.append(" AND CUSTOMER_NAME LIKE'%").append(po.getCustomerName()).append("%'");
		}//客户名称
		if(po.getConsigneeName() != null && !"".equals(po.getConsigneeName())){
			po.setConsigneeName(po.getConsigneeName().replace("'", ""));
			po.setConsigneeName(po.getConsigneeName().replace("=", ""));
			po.setConsigneeName(po.getConsigneeName().replace("(", ""));
			po.setConsigneeName(po.getConsigneeName().replace(")", ""));
			where.append(" AND CONSIGNEE_NAME LIKE'%").append(po.getConsigneeName()).append("%'");
		}//收货人姓名
		if(po.getProductName() != null && !"".equals(po.getProductName())){
			po.setProductName(po.getProductName().replace("'", ""));
			po.setProductName(po.getProductName().replace("=", ""));
			po.setProductName(po.getProductName().replace("(", ""));
			po.setProductName(po.getProductName().replace(")", ""));
			where.append(" AND PRODUCT_NAME LIKE'%").append(po.getProductName()).append("%'");
		}//产品名称
		if(po.getProductId() != null && !"".equals(po.getProductId())){
			where.append(" AND PRODUCT_ID ='").append(validate(po.getProductId())).append("'");
		}//产品编号
		if(po.getMoneyStatus() != 0){
			where.append(" AND MONEY_STATUS ='").append(po.getMoneyStatus()).append("'");
		}//到款情况
		if(po.getInvoice() != 0){
			where.append(" AND INVOICE ='").append(po.getInvoice()).append("'");
		}//是否开票
		if(po.getSalesPlatform() != 0){
			where.append(" AND SALES_PLATFORM ='").append(po.getSalesPlatform()).append("'");
		}//销售平台
		if (where.length() > 0) {
			res.append(" WHERE ");
			res.append(where.toString().substring(4, where.length()));
		} else {
			res.append(where);
		}
		return res.toString();
	}
	
	protected Object[] getUpdateArgs(SalesPO po) {
		Object[] params = { po.getCustomerName(),po.getConsigneeName(),po.getReceiverAddr(),po.getPhoneNo(),
				po.getDeliveryTime(),po.getBuyersNickname(),po.getProductName(),po.getProductId(),po.getModelType(),
				po.getColor(),po.getUnitPrice(),po.getQuantity(),po.getTotalPrice(),po.getMoneyStatus(),
				po.getInvoice(),po.getInvoiceNo(),po.getSalesPlatform(),po.getCourierCompany(),po.getCourierNo(),
				po.getSignTime(),po.getCourierCost(),
				//po.getInsuredCost(),po.getCity(),po.getGender(),
				//po.getAge(),po.getJobs(),
				po.getRemark(),po.getSalesId()
				};
		return params;
	}

	protected Object[] getInsertArgs(SalesPO po) {
		String salesId = UUIDGenerator.getUUID();
		 Object[] params = { salesId, po.getCustomerName(),po.getConsigneeName(),po.getReceiverAddr(),po.getPhoneNo(),
					po.getDeliveryTime(),po.getBuyersNickname(),po.getProductName(),po.getProductId(),po.getModelType(),
					po.getColor(),po.getUnitPrice(),po.getQuantity(),po.getTotalPrice(),po.getMoneyStatus(),
					po.getInvoice(),po.getInvoiceNo(),po.getSalesPlatform(),po.getCourierCompany(),po.getCourierNo(),
					po.getSignTime(),po.getCourierCost(),
					//po.getInsuredCost(),po.getCity(),po.getGender(),
					//po.getAge(),po.getJobs(),
					po.getRemark() };
		System.out.println(salesId);
		return params;
	}

	@Override
	public SalesPO queryById(String id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM SC_SALES WHERE SALES_ID = '");
		sql.append(id);
		sql.append("'");
		List<SalesPO> list = this.getJdbcTemplate().query(sql.toString(), queryMapper);
		SalesPO salesEntity = null;
		if(!list.isEmpty()){
			salesEntity = list.get(0);
		}
		return salesEntity;
	}
	
}
