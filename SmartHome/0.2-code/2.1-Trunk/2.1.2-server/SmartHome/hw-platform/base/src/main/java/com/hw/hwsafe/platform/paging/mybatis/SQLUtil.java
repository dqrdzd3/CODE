package com.hw.hwsafe.platform.paging.mybatis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 项目名称：framework
 * 类名称：SQLUtil
 * 类描述：
 * 创建人：孟繁波
 * 创建时间：2012-6-20 下午4:38:28
 * 修改人：孟繁波
 * 修改时间：2012-6-20 下午4:38:28
 * 修改备注：
 * @version 
 * 
 */
final class SQLUtil {

	private SQLUtil(){}
	
	/**
	 * getOraclePagerSQL(拼接Oracle的分页语句)
	 * Author: 孟繁波
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	*/
	static String getOraclePagerSQL(String sql, int startRowNum, int endRowNum) {
		return "select * from (select tmp.*, rownum rn  from ("
					+ sql  
					+") tmp where rownum <= "+endRowNum+") re where rn >= " + startRowNum;
	}
	
	/**
	 * getOrderBySQL(获取排序语句)
	 * Author: 孟繁波
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	*/
	static String getOrderBySQL(String sql, String sidx, String sord) {
		if (sidx != null && !sidx.isEmpty()) {
			sord = sord == null ? "" : sord;
			Pattern ptn = Pattern.compile("order\\s+by", Pattern.CASE_INSENSITIVE);
			Matcher m = ptn.matcher(sql);
			if (m.find()) {
				sql = sql.substring(0, m.end()) + " " + sidx + " " + sord + "," + sql.substring(m.end());
			} else {
				sql = sql + " ORDER BY " + sidx + " " + sord;
			}
		}
		return sql;
	}
	
	/**
	 * getSelectCountSQL(查询条数)
	 * Author: 孟繁波
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	*/
	static String getSelectCountSQL(String sql) {
		return "select count(*) count from (" + sql + ")";
	}
}
