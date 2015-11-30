/**
 * 文件名：MethodCacheInterceptor.java
 *
 * 版本信息：
 * 日期：2012-11-21
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.ehcache.interceptor;

import java.io.Serializable;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;


/**
 * 项目名称：framework
 * 类名称：MethodCacheInterceptor
 * 类描述：查询拦截器
 * 创建人：杜群星
 * 创建时间：2012-11-21 下午4:02:19
 */
public class MethodCacheAdviceInterceptor implements MethodInterceptor,InitializingBean{

  private static final Logger logger = Logger.getLogger(MethodCacheAdviceInterceptor.class);
  
  private Cache cache;
  
  
  public void setCache(Cache cache) {
  
    this.cache = cache;
  }
  
  public Cache getCache() {
  
    return cache;
  }
  
  public MethodCacheAdviceInterceptor(){
    super();
  }
  
  /**
   * 拦截 Service/DAO 的方法，并查找该结果是否存在，如果存在就返回 cache 中的值，
   * 否则，返回数据库查询结果，并将查询结果放入 cache
   */
  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
  
    String targetName = invocation.getThis().getClass().getName();
    
    String methodName = invocation.getMethod().getName();
    
    Object[] arguments = invocation.getArguments();
    
    Object result;
   
    
    String cacheKey =  getCacheKey(targetName, methodName, arguments);
    
    logger.info("从["+cache.getName()+"]查询key:["+cacheKey+"]的缓存]");
    
//    System.out.println("从["+cache.getName()+"]查询key:["+cacheKey+"]的缓存]");
    
    Element element = cache.get(cacheKey);
    
    if(element == null){
      
      
      result = invocation.proceed();
      
      logger.info("key:["+cacheKey+"]无缓存，创建缓存为["+result+"]");
      
//      System.out.println("key:["+cacheKey+"]无缓存，创建缓存为["+result+"]");
      
      element = new Element(cacheKey, (Serializable)result);
      
      cache.put(element);
    }
    
    return element.getObjectValue();
  }

  /**
   * cache key 　(包名+类名+方法名)
   * getCacheKey
   * @param   name
   * @return String
   */
  private String getCacheKey(String targetName, String methodName, Object[] arguments) {
    StringBuffer sb = new StringBuffer();
    
    sb.append(targetName)
      .append(".")
      .append(methodName);
    
    if(arguments != null && arguments.length > 0){
      
      for (Object object : arguments) {
        sb.append(".")
          .append(object);
      }
      
    }
    
    return sb.toString();
  }
  
  /**
   * 检查 cache 是否为空
   */
  @Override
  public void afterPropertiesSet() throws Exception {
    Assert.notNull(cache, "无缓存，请用方法： setCache(Cache) 创建缓存！");
  }
}
