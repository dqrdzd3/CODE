/**
 * 文件名：CpnyregFacadeServiceImpl.java
 * 版本信息：
 * 日期：2013-5-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2013 
 * 版权所有
 */
package com.hw.hwsafe.cpnyreg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import com.hw.hwsafe.cpnyreg.pojo.C001PO;
import com.hw.hwsafe.cpnyreg.service.IC001Service;
import com.hw.hwsafe.cpnyreg.service.ICpnyregFacadeService;


/**
 * 项目名称：hw-cpnyreg
 * 类名称：CpnyregFacadeServiceImpl
 * 类描述：企业登记门面实现类
 * 创建人：杜群星
 * 创建时间：2013-5-8 下午3:55:13
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 
 */
public class CpnyregFacadeServiceImpl implements ICpnyregFacadeService {
  @Autowired
  private IC001Service c001Service;
  
  @Override
  public String retrieveGovIdByEntId(String entId) throws Exception {
  
    C001PO c001PO = c001Service.retrieveC001ByMA001(entId);
    if(c001PO == null){
      return "";
    }
    return c001PO.getMA004();
  }
}
