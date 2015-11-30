/**
 * 文件名：SessionUtil.java
 *
 * 版本信息：
 * 日期：2012-10-27
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.userinfo;

import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.pojo.UserPO;


/**
 * 项目名称：framework
 * 类名称：SessionUtil
 * 类描述：
 * 创建人：杜群星
 * 创建时间：2012-10-27 下午4:50:41
 */
public class SessionUtil {

  
  /**
   * 
   * 方法名称：getUserPO
   * 功能描述：从session中取userPO
   * @param   	
   * @return    UserPO
   * @Exception 异常对象
   * @since  CodingExample　Ver(编码范例查看) 1.1
   * 创建人：杜群星
   * 创建日期：2013-1-10 上午11:56:04
   */
  public static UserPO getUserPO() {
    HttpSession session =  ServletActionContext.getRequest().getSession();
    return ((UserSession)session.getAttribute(Constants.USER_SESSION_KEY)).getUserPO();
  }

  /**
   * 
   * 方法名称：getUserId
   * 功能描述：从sessoin中取用户Id
   * @param   	
   * @return    String
   * @Exception 异常对象
   * @since  CodingExample　Ver(编码范例查看) 1.1
   * 创建人：杜群星
   * 创建日期：2013-1-10 上午11:56:28
   */
  public static String getUserId() {
  
    return getUserPO().getId();
  }

  /**
   * 
   * 方法名称：getOrgId
   * 功能描述：从sessoin中取企业或政府Id
   * @param   	
   * @return    String
   * @Exception 异常对象
   * @since  CodingExample　Ver(编码范例查看) 1.1
   * 创建人：杜群星
   * 创建日期：2013-1-10 上午11:57:17
   */
  public static String getOrgId() {
  
    return getUserPO().getCorpid();
  }

  /**
   * 
   * 方法名称：getUserType
   * 功能描述：从sessoin中取用户类型
   * @param   	
   * @return    String
   * @Exception 异常对象
   * @since  CodingExample　Ver(编码范例查看) 1.1
   * 创建人：杜群星
   * 创建日期：2013-1-10 上午11:57:44
   */
  public static String getUserType() {
  
    return getUserPO().getUSER_TYPE();
  }

  /**
   * 
   * 方法名称：getIsAdmin
   * 功能描述：从sessoin中取用户是否是管理员
   * @param   	
   * @return    String
   * @Exception 异常对象
   * @since  CodingExample　Ver(编码范例查看) 1.1
   * 创建人：杜群星
   * 创建日期：2013-1-10 上午11:57:57
   */
  public static String getIsAdmin() {
  
    return getUserPO().getIS_ADMIN();
  }
  /**
   * 
   * 方法名称：getOrgName
   * 功能描述：从sessoin中取企业或政府名称
   * @param   	
   * @return    String
   * @Exception 异常对象
   * @since  CodingExample　Ver(编码范例查看) 1.1
   * 创建人：杜群星
   * 创建日期：2013-1-10 上午11:58:43
   */
  public static String getOrgName(){
    return getUserPO().getOrgName();
  }
  /**
   * 
   * 方法名称：getUserName
   * 功能描述：从sessoin中取当前用户的真实姓名
   * @param   	
   * @return    String
   * @Exception 异常对象
   * @since  CodingExample　Ver(编码范例查看) 1.1
   * 创建人：杜群星
   * 创建日期：2013-2-5 下午4:08:36
   */
  public static String getUserName(){
    return getUserPO().getREAL_NAME();
  }
}
