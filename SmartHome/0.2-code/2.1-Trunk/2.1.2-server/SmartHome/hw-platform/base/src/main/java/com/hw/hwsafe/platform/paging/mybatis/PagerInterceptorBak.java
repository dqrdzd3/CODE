package com.hw.hwsafe.platform.paging.mybatis;

import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.DefaultParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;

import com.hw.hwsafe.platform.paging.PagerData;

/**
 * 
 * 项目名称：framework
 * 类名称：PagerInterceptor
 * 类描述：拦截修改为分页语句
 * 创建人：孟繁波
 * 创建时间：2012-6-20 下午5:25:55
 * 修改人：马宁
 * 修改时间：2013-03-05 16:12
 * 修改备注：杜群星新定义了一个拦截器,以解决使用缓存时分页报错的问题
 * @version 
 * 
 */
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class PagerInterceptorBak implements Interceptor {
	
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler);
		
		DefaultParameterHandler defaultParameterHandler = (DefaultParameterHandler) metaStatementHandler.getValue("delegate.parameterHandler");		
		Object paramObj = defaultParameterHandler.getParameterObject();
		
		if (paramObj instanceof Map) {
			Map paramMap = (Map) paramObj;
			// 原SQL
			String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
			
			// 查询条数
			if (paramMap.get("selectCount") != null && (Boolean) (paramMap.get("selectCount"))) {
				sql = SQLUtil.getSelectCountSQL(sql);
			}
			// 判断分页
			else if (paramMap.get("pagerData") != null) {
				PagerData pagerData = (PagerData) paramMap.get("pagerData");
				// 处理排序
				sql = SQLUtil.getOrderBySQL(sql, pagerData.getSidx(), pagerData.getSord());
				// Oracle 分页语句
				sql = SQLUtil.getOraclePagerSQL(sql, pagerData.getStartRowNum(), pagerData.getEndRowNum());
			}
			// 修改后SQL
			metaStatementHandler.setValue("delegate.boundSql.sql", sql);
		}
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties arg0) {
		// do nothing
	}

}
