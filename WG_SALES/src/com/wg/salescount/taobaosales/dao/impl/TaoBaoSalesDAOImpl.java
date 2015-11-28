package com.wg.salescount.taobaosales.dao.impl;

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
import com.wg.salescount.taobaosales.dao.TaoBaoSalesDAO;
import com.wg.salescount.taobaosales.po.TaoBaoSalesPO;
import com.wg.salescount.util.DateUtil;
import com.wg.salescount.util.Pager;
import com.wg.salescount.util.UUIDGenerator;
import com.wg.salescount.weeklysales.po.WeeklySalesPO;

@Repository
public class TaoBaoSalesDAOImpl extends BaseDaoImpl<TaoBaoSalesPO> implements TaoBaoSalesDAO{

	private static Log log = LogFactory.getLog(TaoBaoSalesDAOImpl.class);
	
	private static final String INSERT_SQL = "INSERT INTO SC_TAOBAO_SALES(TAOBAO_SALES_ID,BUYERS_USERNAME,"
				+ "BUYERS_ALIPAY_ACCOUNT,PAYABLES,ACTUAL_PAYMENT,TOTAL_PRICE,"
				+ "ORDER_STATUS,BUYERS_MESSAGE,CONSIGNEE_NAME,RECEIVER_ADDR,"
				+ "TRANSPORT_METHODS,PHONE_NO,CELLPHONE_NO,ORDERS_CREATED_TIME,"
				+ "ORDERS_PAYED_TIME,PRODUCT_TITLE,PRODUCT_SORT,QUANTITY,LOGISTICS_NO,"
				+ "LOGISTICS_COMPANY,STORE_ID,STORE_NAME,CLOSED_REASON) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_SQL = "UPDATE SC_TAOBAO_SALES SET BUYERS_USERNAME=?,BUYERS_ALIPAY_ACCOUNT=?,PAYABLES=?,ACTUAL_PAYMENT=?,"
				+ "TOTAL_PRICE=?,ORDER_STATUS=?,BUYERS_MESSAGE=?,CONSIGNEE_NAME=?,RECEIVER_ADDR=?,"
				+ "TRANSPORT_METHODS=?,PHONE_NO=?,CELLPHONE_NO=?,ORDERS_CREATED_TIME=?,ORDERS_PAYED_TIME=?,"
				+ "PRODUCT_TITLE=?,PRODUCT_SORT=?,QUANTITY=?,LOGISTICS_NO=?,LOGISTICS_COMPANY=?,STORE_ID=?,STORE_NAME=?,CLOSED_REASON=? WHERE TAOBAO_SALES_ID=?";
	private static final String DELETE_SQL = "DELETE FROM SC_TAOBAO_SALES WHERE TAOBAO_SALES_ID = ?";
	private static final String QUERY_SQL = "SELECT * FROM SC_TAOBAO_SALES ";
	private static final String COUNT_SQL = "SELECT COUNT(*) FROM SC_TAOBAO_SALES ";
	
	@Autowired 
	public TaoBaoSalesDAOImpl(DataSource dataSource) {
	    super();
	    setDataSource(dataSource);
	}
	
	private static final RowMapper queryMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			TaoBaoSalesPO taoBaoSales = new TaoBaoSalesPO();
			taoBaoSales.setTaobaoSalesId(rs.getString(1));
			taoBaoSales.setBuyersUsername(rs.getString(2));
			taoBaoSales.setBuyersAlipayAccount(rs.getString(3));
			taoBaoSales.setPayables(rs.getFloat(4));
			taoBaoSales.setActualPayment(rs.getFloat(5));
			taoBaoSales.setTotalPrice(rs.getFloat(6));
			taoBaoSales.setOrderStatus(rs.getInt(7));
			taoBaoSales.setBuyersMessage(rs.getString(8));
			taoBaoSales.setConsigneeName(rs.getString(9));
			taoBaoSales.setReceiverAddr(rs.getString(10));
			taoBaoSales.setTransportMethods(rs.getString(11));
			taoBaoSales.setPhoneNo(rs.getString(12));
			taoBaoSales.setCellphoneNo(rs.getString(13));
			String ordersCreatedTimeString = rs.getString(14);
			Date ordersCreatedTime = null;
			if(ordersCreatedTimeString != null && !ordersCreatedTimeString.equals("")){
				ordersCreatedTime = DateUtil.string2Date(ordersCreatedTimeString);
			}
			taoBaoSales.setOrdersCreatedTime(ordersCreatedTime);
			String ordersPayedTimeString = rs.getString(15);
			Date ordersPayedTime = null;
			if(ordersPayedTimeString != null && !ordersPayedTimeString.equals("")){
				ordersPayedTime = DateUtil.string2Date(ordersPayedTimeString);
			}
			taoBaoSales.setOrdersPayedTime(ordersPayedTime);
			taoBaoSales.setProductTitle(rs.getString(16));
			taoBaoSales.setProductSort(rs.getString(17));
			taoBaoSales.setQuantity(rs.getInt(18));
			taoBaoSales.setLogisticsCompany(rs.getString(19));
			taoBaoSales.setLogisticsNo(rs.getString(20));
			taoBaoSales.setStoreId(rs.getString(21));
			taoBaoSales.setStoreName(rs.getString(22));
			taoBaoSales.setClosedReason(rs.getString(23));
			return taoBaoSales;
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

	public int getNum(TaoBaoSalesPO po) {
		int result = this.getJdbcTemplate().queryForInt(this.queryCondition(COUNT_SQL, po));
		log.debug("count query");
		if (result != 0) {
			return result;
		} else {
			return 0;
		}
	}
	
	@Override
	public TaoBaoSalesPO update(TaoBaoSalesPO po) {
		System.out.println("update");
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
	public Pager query(Pager pager, TaoBaoSalesPO po) {
		String condition = this.queryCondition(QUERY_SQL, po);
		StringBuilder sql = new StringBuilder(pagerAdapter(pager, condition));
		List<TaoBaoSalesPO> list = this.getJdbcTemplate().query(sql.toString(), queryMapper);
		log.debug("query with page");
		pager.setList(list);
		return pager;
	}

	@Override
	public List<TaoBaoSalesPO> queryNoPager(TaoBaoSalesPO po) {
		List<TaoBaoSalesPO> list = this.getJdbcTemplate().query(queryCondition(QUERY_SQL, po), queryMapper);
		log.debug("query with no page");
		return list;
	}

	@Override
	public int delete(TaoBaoSalesPO po) {
		Object[] params = { po.getTaobaoSalesId() };
		int result = this.getJdbcTemplate().update(DELETE_SQL, params);
		if (this.getJdbcTemplate().update(DELETE_SQL, params) > 0) {
			log.debug("delete success.");
		} else {
			log.debug("delete failed.");
		}
		return result;
	}

	@Override
	public TaoBaoSalesPO save(TaoBaoSalesPO po) {
		System.out.println(po);
		Object[] params =  getInsertArgs(po);
		po.setTaobaoSalesId(params[0].toString());
		if (this.getJdbcTemplate().update(INSERT_SQL, params) > 0) {
			log.debug("insert success.");
			return po;
		} else {
			log.debug("insert success.");
			return null;
		}
	}

	@Override
	public TaoBaoSalesPO detail(TaoBaoSalesPO po) {
		List<TaoBaoSalesPO> list = this.getJdbcTemplate().query(queryCondition(QUERY_SQL, po), queryMapper);
		return (list != null && !list.isEmpty()) ? list.get(0) : null;
	}
	
	protected String queryCondition(String sql, TaoBaoSalesPO po) {
		StringBuilder res = new StringBuilder(sql);
		StringBuilder where = new StringBuilder();
		String temp = "";
		if (po.getBuyersUsername() != null && !"".equals(po.getBuyersUsername())) {
			po.setBuyersUsername(po.getBuyersUsername().replace("'", ""));
			po.setBuyersUsername(po.getBuyersUsername().replace("=", ""));
			po.setBuyersUsername(po.getBuyersUsername().replace("(", ""));
			po.setBuyersUsername(po.getBuyersUsername().replace(")", ""));
			where.append(" AND BUYERS_USERNAME LIKE'%").append(po.getBuyersUsername()).append("%'");
		}//买家会员名
		if(po.getBuyersAlipayAccount() != null && !"".equals(po.getBuyersAlipayAccount())){			
			where.append(" AND BUYERS_ALIPAY_ACCOUNT ='").append(validate(po.getBuyersAlipayAccount())).append("'");
		}//买家支付宝账号
		if(po.getConsigneeName() != null && !"".equals(po.getConsigneeName())){
			po.setConsigneeName(po.getConsigneeName().replace("'", ""));
			po.setConsigneeName(po.getConsigneeName().replace("=", ""));
			po.setConsigneeName(po.getConsigneeName().replace("(", ""));
			po.setConsigneeName(po.getConsigneeName().replace(")", ""));
			where.append(" AND CONSIGNEE_NAME LIKE'%").append(po.getConsigneeName()).append("%'");
		}//收货人姓名
		if(po.getProductTitle() != null && !"".equals(po.getProductTitle())){
			po.setProductTitle(po.getProductTitle().replace("'", ""));
			po.setProductTitle(po.getProductTitle().replace("=", ""));
			po.setProductTitle(po.getProductTitle().replace("(", ""));
			po.setProductTitle(po.getProductTitle().replace(")", ""));
			where.append(" AND PRODUCT_TITLE LIKE'%").append(po.getProductTitle()).append("%'");
		}//宝贝标题
		if(po.getProductSort() != null && !"".equals(po.getProductSort())){
			po.setProductSort(po.getProductSort().replace("'", ""));
			po.setProductSort(po.getProductSort().replace("=", ""));
			po.setProductSort(po.getProductSort().replace("(", ""));
			po.setProductSort(po.getProductSort().replace(")", ""));
			where.append(" AND PRODUCT_SORT LIKE'%").append(po.getProductSort()).append("%'");
		}//宝贝种类
		if(po.getStoreId() != null && !"".equals(po.getStoreId())){
			where.append(" AND STORE_ID ='").append(validate(po.getStoreId())).append("'");
		}//店铺id
		if(po.getStoreName() != null && !"".equals(po.getStoreName())){
			po.setStoreName(po.getStoreName().replace("'", ""));
			po.setStoreName(po.getStoreName().replace("=", ""));
			po.setStoreName(po.getStoreName().replace("(", ""));
			po.setStoreName(po.getStoreName().replace(")", ""));
			where.append(" AND STORE_NAME LIKE'%").append(po.getStoreName()).append("%'");
		}//店铺名称
		if (where.length() > 0) {
			res.append(" WHERE ");
			res.append(where.toString().substring(4, where.length()));
		} else {
			res.append(where);
		}
		return res.toString();
	}

	protected Object[] getUpdateArgs(TaoBaoSalesPO po) {
		Object[] params = { po.getBuyersUsername(), po.getBuyersAlipayAccount(),po.getPayables(),po.getActualPayment(),
				po.getTotalPrice(),po.getOrderStatus(),po.getBuyersMessage(),po.getConsigneeName(),po.getReceiverAddr(),
				po.getTransportMethods(),po.getPhoneNo(),po.getCellphoneNo(),po.getOrdersCreatedTime(),po.getOrdersPayedTime(),
				po.getProductTitle(),po.getProductSort(), po.getQuantity(),po.getLogisticsNo(),po.getLogisticsCompany(),
				po.getStoreId(),po.getStoreName(),po.getClosedReason(), po.getTaobaoSalesId() };
		return params;
	}

	protected Object[] getInsertArgs(TaoBaoSalesPO po) {
		String taobaoSalesId = UUIDGenerator.getUUID();
		 Object[] params = { taobaoSalesId, po.getBuyersUsername(),po.getBuyersAlipayAccount(),po.getPayables(),po.getActualPayment(),
				 po.getTotalPrice(),po.getOrderStatus(),po.getBuyersMessage(),po.getConsigneeName(),po.getReceiverAddr(),
				 po.getTransportMethods(),po.getPhoneNo(),po.getCellphoneNo(),po.getOrdersCreatedTime(),po.getOrdersPayedTime(),
				 po.getProductTitle(),po.getProductSort(),po.getQuantity(),po.getLogisticsNo(),po.getLogisticsCompany(),po.getStoreId(),
				 po.getStoreName(),po.getClosedReason() };
	//	Object[] params = { "123", "123", "123", 1, 1, 1, 1, 1, 1 };
		System.out.println(taobaoSalesId);
		return params;
	}

	@Override
	public TaoBaoSalesPO queryById(String id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM SC_TAOBAO_SALES WHERE TAOBAO_SALES_ID = '");
		sql.append(id);
		sql.append("'");
		List<TaoBaoSalesPO> list = this.getJdbcTemplate().query(sql.toString(), queryMapper);
		TaoBaoSalesPO taoBaoSalesEntity = null;
		if(!list.isEmpty()){
			taoBaoSalesEntity = list.get(0);
		}
		return taoBaoSalesEntity;
	}
	
}
