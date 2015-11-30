
/**
 * @title LoginLogAction.java
 * @package com.hw.hwsafe.platform.action
 * @author 孟繁波
 * @create_time 2013-6-20 下午4:05:55
 * <p>Copyright 河南汉威电子股份有限公司软件部 Corporation 2013</p>
 */
	
package com.hw.hwsafe.platform.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.pojo.LoginLogPO;
import com.hw.hwsafe.platform.service.ILoginLogService;


/**
 * <p>
 * 这是写类描述，如果有换行则用<br>
 * </p>
 */

public class LoginLogAction extends BaseAction {
	
	@Autowired
	private ILoginLogService iLoginLogService;

	private LoginLogPO loginLogPO;
	
	private Date queryStartDate;
	
	private Date queryEndDate;
	
	public String queryData() throws Exception {
		// 分页信息
		pagerData = initPagerData();
		// 参数Map
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pagerData", pagerData);
		
		// 根据条件查询分页数据
		paramMap.put("po", loginLogPO); // 查询条件
		
		String userId = this.getSessionUserPO().getId();
		String userType = this.getSessionUserPO().getUSER_TYPE();
		String isAdmin = this.getSessionUserPO().getIS_ADMIN();
		String orgId = this.getSessionUserPO().getCorpid();
		
		paramMap.put("userId", userId);
		paramMap.put("userType", userType);
		paramMap.put("isAdmin", isAdmin);
		paramMap.put("orgId", orgId);
		paramMap.put("queryStartDate", queryStartDate);
		paramMap.put("queryEndDate", queryEndDate);
		
		
		pagerData.setRows(iLoginLogService.retrieveByPage(paramMap));
		// 根据条件查询总条数
		paramMap.put("selectCount", true);
		pagerData.setRecords(iLoginLogService.retrieveByPage(paramMap));

		return JSON_PAGER;
	}


	public LoginLogPO getLoginLogPO() {
		return loginLogPO;
	}


	public void setLoginLogPO(LoginLogPO loginLogPO) {
		this.loginLogPO = loginLogPO;
	}


	public ILoginLogService getiLoginLogService() {
		return iLoginLogService;
	}


	public void setiLoginLogService(ILoginLogService iLoginLogService) {
		this.iLoginLogService = iLoginLogService;
	}


	public Date getQueryStartDate() {
		return queryStartDate;
	}


	public void setQueryStartDate(Date queryStartDate) {
		this.queryStartDate = queryStartDate;
	}


	public Date getQueryEndDate() {
		return queryEndDate;
	}


	public void setQueryEndDate(Date queryEndDate) {
		this.queryEndDate = queryEndDate;
	}
	
}
