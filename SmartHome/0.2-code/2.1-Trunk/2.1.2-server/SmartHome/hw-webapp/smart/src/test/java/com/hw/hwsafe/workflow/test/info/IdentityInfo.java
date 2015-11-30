/**
 * 文件名：IdentityInfo.java
 *
 * 版本信息：1.0
 * 日期：2012-10-18
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.workflow.test.info;

import java.util.UUID;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：IdentityInfo
 * 类描述：测试用身份信息
 * 创建人：马宁
 * 创建时间：2012-10-18 上午9:18:21
 * 修改人：马宁
 * 修改时间：2012-10-18 上午9:18:21
 * 修改备注：
 * @version 
 *
 */
public class IdentityInfo {
	public static final String USER_ID = UUID.randomUUID().toString();
	public static final String FIRST_NAME = "o0o0o_first";
	public static final String LAST_NAME = "o0o0o_last";
	public static final String PASSWORD = "o0o0o_password";
	public static final String EMAIL = "o0o0o_email";

	public static final String GROUP_ID = UUID.randomUUID().toString();
	public static final String GROUP_NAME = "o0o0o_group";
	public static final String GROUP_TEPE = "o0o0o_type";
}
