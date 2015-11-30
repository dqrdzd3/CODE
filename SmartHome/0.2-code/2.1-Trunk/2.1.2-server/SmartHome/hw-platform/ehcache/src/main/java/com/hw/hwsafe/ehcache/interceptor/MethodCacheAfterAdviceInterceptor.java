/**
 * 文件名：MethodCacheAfterAdvice.java
 *
 * 版本信息：
 * 日期：2012-11-21
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.ehcache.interceptor;

import java.lang.reflect.Method;
import java.util.List;
import net.sf.ehcache.Cache;
import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;


/**
 * 项目名称：framework
 * 类名称：MethodCacheAfterAdvice
 * 类描述：刷新缓存拦截器
 * 创建人：杜群星
 * 创建时间：2012-11-21 下午4:38:10
 */
public class MethodCacheAfterAdviceInterceptor implements AfterReturningAdvice,InitializingBean{

  private static Logger logger = Logger.getLogger(MethodCacheAfterAdviceInterceptor.class);
  
  private Cache cache;
  
  
  public void setCache(Cache cache) {
  
    this.cache = cache;
  }
  
  
  public Cache getCache() {
  
    return cache;
  }
  
  public MethodCacheAfterAdviceInterceptor(){
    super();
  }

  /**
   * 执行完添加，修改，删除之后，移除缓存中相应的缓存，
   * 以便下次再查询时重新添加缓存
   */
  @Override
  public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
  
    String className = target.getClass().getName();
    
    List list = cache.getKeys();
    
//    System.out.println("刷新缓存!");
    
    for (int i = 0; i < list.size(); i++) {
      
      String cacheKey = String.valueOf(list.get(i));
      
      if (cacheKey.startsWith(className)) {
        
        cache.remove(cacheKey);
        
        logger.info("刷新缓存，移除key:["+cacheKey+"]的缓存");
        
//        System.out.println("移除key:["+cacheKey+"]的缓存");
        
      }
    }

    
  }
  
  @Override
  public void afterPropertiesSet() throws Exception {
    Assert.notNull(cache, "无缓存，请用方法： setCache(Cache) 创建缓存！");
  }
}
