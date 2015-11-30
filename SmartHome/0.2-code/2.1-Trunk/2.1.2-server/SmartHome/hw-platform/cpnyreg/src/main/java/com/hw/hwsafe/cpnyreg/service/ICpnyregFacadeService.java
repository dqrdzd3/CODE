/**
 * 文件名：ICpnyregFacadeService.java
 * 版本信息：
 * 日期：2013-5-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2013 
 * 版权所有
 */
package com.hw.hwsafe.cpnyreg.service;


/**
 * 项目名称：hw-cpnyreg
 * 类名称：ICpnyregFacadeService
 * 类描述：企业登记门面类
 * 创建人：杜群星
 * 创建时间：2013-5-8 下午3:52:01
 */
public interface ICpnyregFacadeService {
  /**
   * 
   * 方法名称：retrieveGovIdByEntId
   * 功能描述：   通过企业id查询本企业所属政府管辖机构Id
   * @param   	entId 企业Id
   * @return    String
   * @Exception 异常对象
   * 创建人：杜群星
   * 创建日期：2013-5-8 下午3:53:29
   */
  public String retrieveGovIdByEntId(String entId) throws Exception;
  
}
