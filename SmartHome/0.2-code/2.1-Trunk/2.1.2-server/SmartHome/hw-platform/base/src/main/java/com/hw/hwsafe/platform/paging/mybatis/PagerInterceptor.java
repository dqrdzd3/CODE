/**
 * 文件名：CacheInterceptor.java
 * 版本信息：
 * 日期：2013-3-4
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2013 
 * 版权所有
 */
package com.hw.hwsafe.platform.paging.mybatis;

import java.util.Map;
import java.util.Properties;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.hw.hwsafe.platform.paging.PagerData;
import com.hw.hwsafe.platform.util.Page;

/**
 * 项目名称：framework
 * 类名称：CachePagerInterceptor
 * 类描述：
 * 创建人：杜群星
 * 创建时间：2013-3-4 下午5:08:19
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 
 */
@Intercepts({@Signature(type=Executor.class,method="query",args={MappedStatement.class, Object.class, RowBounds.class,ResultHandler.class})})
public class PagerInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		Object[] args = invocation.getArgs();
		MappedStatement mappedStatement = (MappedStatement) args[0];
		Object params = args[1];
		BoundSql boundSql = mappedStatement.getBoundSql(params);
		// 原sql
		String sql = boundSql.getSql();
		// 参数
		Object paramObj = boundSql.getParameterObject();

		if (paramObj instanceof Map) {
			Map paramMap = (Map) paramObj;

			// 查询条数
			if (paramMap.get("selectCount") != null
					&& (Boolean) (paramMap.get("selectCount"))) {
				sql = SQLUtil.getSelectCountSQL(sql);
			}
			// 判断分页
			else if (paramMap.get("pagerData") != null) {
				PagerData pagerData = (PagerData) paramMap.get("pagerData");
				// 处理排序
				sql = SQLUtil.getOrderBySQL(sql, pagerData.getSidx(),
						pagerData.getSord());
				// Oracle 分页语句
				sql = SQLUtil.getOraclePagerSQL(sql,
						pagerData.getStartRowNum(), pagerData.getEndRowNum());
			}
			//陈浙东新加，新的分页使用
			else if (paramMap.get("page") != null) {
				Page page = (Page) paramMap.get("page");
				// 处理排序
				sql = SQLUtil.getOrderBySQL(sql, (String)paramMap.get("columnsort"),
						(String)paramMap.get("sort"));
				// Oracle 分页语句
				sql = SQLUtil.getOraclePagerSQL(sql,
						Integer.parseInt(page.getBegin()), Integer.parseInt(page.getEnd()));
			}

			BoundSql newBoundSql = createBoundSql(mappedStatement, boundSql,
					sql);
			Builder builder = createBuilder(mappedStatement, newBoundSql);
			MappedStatement newMappedStatement = builder.build();
			args[0] = newMappedStatement;
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// DO NOTHING
	}

	private BoundSql createBoundSql(MappedStatement mappedStatement,
			BoundSql boundSql, String sql) {
		return new BoundSql(mappedStatement.getConfiguration(), sql,
				boundSql.getParameterMappings(), boundSql.getParameterObject());
	}

	private Builder createBuilder(MappedStatement mappedStatement,
			final BoundSql boundSql) {
		Builder builder = new Builder(mappedStatement.getConfiguration(),
				mappedStatement.getId(), new SqlSource() {

					@Override
					public BoundSql getBoundSql(Object parameterObject) {
						return boundSql;
					}
				}, mappedStatement.getSqlCommandType())
				.cache(mappedStatement.getCache())
				.useCache(mappedStatement.isUseCache())
				.fetchSize(mappedStatement.getFetchSize())
				.flushCacheRequired(mappedStatement.isFlushCacheRequired())
				.keyGenerator(mappedStatement.getKeyGenerator())
				.parameterMap(mappedStatement.getParameterMap())
				.timeout(mappedStatement.getTimeout())
				.resource(mappedStatement.getResource())
				.resultMaps(mappedStatement.getResultMaps())
				.resultSetType(mappedStatement.getResultSetType())
				.statementType(mappedStatement.getStatementType());

		if (mappedStatement.getKeyProperties() != null) {
			for (String keyProperty : mappedStatement.getKeyProperties()) {
				builder.keyProperty(keyProperty);
			}
		}

		return builder;
	}
}
