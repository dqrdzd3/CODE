package com.hw.hwsafe.platform.action;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.exception.jsonmsg.JsonMsgException;
import com.hw.hwsafe.platform.message.UserMessageData;
import com.hw.hwsafe.platform.paging.PagerData;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.platform.pojo.UserPO;
import com.hw.hwsafe.platform.service.IBaseService;
import com.hw.hwsafe.platform.service.IExceptionLogService;
import com.hw.hwsafe.platform.service.IOperLogService;
import com.hw.hwsafe.platform.userinfo.UserSession;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * 项目名称：framework
 * 类名称：BaseAction
 * 类描述：
 * 创建人：孟繁波
 * 创建时间：2012-5-12 下午2:45:32
 * 修改人：孟繁波
 * 修改时间：2012-5-12 下午2:45:32
 * 修改备注：
 * @version 
 * 
 */
public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	
	protected static final String JSON_MSG = "jsonMsg";//返回消息
	protected static final String JSON_PAGER = "jsonPager";
	protected static final String JSON_PO = "jsonPO";
	protected static final String JSON_STR="jsonstr";//自定义JSON 图表应用 也可灵活应用 
	protected static final String JSON_DATA="jsondata";//自定义JSON 图表应用 也可灵活应用 
	protected static final String NONE = null;//NONE
	protected static final String SUCCESS = "success";//SUCCESS
	protected static final String INPUT = "input";
	
	protected static final String ORGAN_UUID = "ORGAN_UUID";
	
	private Object jsonPO;

	protected Logger logger = Logger.getLogger(getClass());
	
	protected HttpServletRequest request;
	
	protected HttpServletResponse response;
	
	protected HttpSession session;
	
	protected PagerData pagerData;
	
	protected CallbackDataPO callbackDataPO;

	@SuppressWarnings("unused")
	private String actionButtons = "";
	
	/**
	 * 页面标签的编码
	 */
	private String code;
	private List<String> codes;
	
	@Autowired
	protected IBaseService iBaseService;
	
	protected UserMessageData message = new UserMessageData();
	
	protected HashMap<String, String> jstr;//add 20120914  饼图  曲线图 柱图公用json字符串
	
	@Autowired
	private IOperLogService operLogService;
	
	@Autowired
	private IExceptionLogService exceptionLogService;
	
	/**
	 * 批量删除
	 */
	public String delBatch() {
		String table = request.getParameter("table");
		String pk = request.getParameter("pk");
		String ids = request.getParameter("ids");

		try {
			// sql injection
			Pattern pat = Pattern.compile("[^\\w-]");
			Matcher mat = pat.matcher(table + pk);
			if (mat.find()) throw new Exception();
			
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("table", table);
			map.put("pk", pk);
			map.put("idArray", ids.split(","));
			
			iBaseService.delBatch(map);
			setDelSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace();
			setDelFailedMsg();
		}
		
		return JSON_MSG;
	}
	
	
	/**
	 * 初始化pagerData对象
	 */
	public PagerData initPagerData() {
		PagerData pagerData = new PagerData();
		
		// 请求参数
		int pageNum = 1, rowNum = 10;
		try {
			pageNum = Integer.parseInt(request.getParameter("page"));
			rowNum = Integer.parseInt(request.getParameter("rows"));
		} catch (Exception e) {
		}
		String sidx = request.getParameter("sidx");
		String sord = request.getParameter("sord");
		String cellIndex = request.getParameter("cellIndex");
		String pk = request.getParameter("pk");
		
		int startRowNum = (pageNum - 1) * rowNum + 1;
		int endRowNum = pageNum * rowNum;
		
		// PagerData
		pagerData.setPage(pageNum);	// 当前页
		pagerData.setPageSize(rowNum); // 每页条数
		pagerData.setStartRowNum(startRowNum);	// 开始行
		pagerData.setEndRowNum(endRowNum);		// 结束行
		pagerData.setSidx(sidx);	// 排序列
		pagerData.setSord(sord);	// 排序
		pagerData.setCellIndex(cellIndex); // 列索引
		pagerData.setPk(pk);
		
		return pagerData;
	}
	
	/**
	 * queryData 查询数据
	 * 
	 * @author 孟繁波
	 * @修改 马宁 2013-04-07 14:32 重构
	 */
	public String queryData() throws Exception {
		IBaseService iBaseService = retrieveIBaseService();
		if (iBaseService == null) {
			return NONE;
		}

		Map<String, Object> paramMap = initParamMap();

		if (!isRequestCountOnly()) {
			pagerData.setRows(iBaseService.retrieveByPage(paramMap));
		}

		// 根据条件查询总条数
		paramMap.put("selectCount", true);
		pagerData.setRecords(iBaseService.retrieveByPage(paramMap));

		return JSON_PAGER;
	}
	
	/**
	 * 获取IBaseService
	 * 
	 * @author 马宁
	 */
	protected IBaseService retrieveIBaseService()
			throws IllegalArgumentException, IllegalAccessException {
		IBaseService result = null;
		String name = getClass().getSimpleName().replace("Action", "");
		String serviceClassName = "I" + name + "Service";
		Field[] fields = getClass().getDeclaredFields();
		for (Field f : fields) {
			String fName = f.getType().getSimpleName();
			if (fName.equals(serviceClassName)) {
				f.setAccessible(true);
				result = (IBaseService) f.get(this);
				f.setAccessible(false);
				break;
			}
		}
		return result;
	}
	
	/*
	 * 初始化参数Map
	 * 
	 * @author 马宁
	 */
	private Map<String, Object> initParamMap() throws IllegalArgumentException,
			IllegalAccessException, InstantiationException {
		// 分页信息
		pagerData = initPagerData();

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pagerData", pagerData);

		Object poObj = retrievePoObj();

		// 根据条件查询分页数据
		result.put("po", poObj); // 查询条件
		
		// 用以判断政府端查看企业的相关信息
		String corpID = request.getParameter("ORGAN_UUID"); 
//		String userType = getSessionUserPO().getUSER_TYPE();
//		if (userType.equals("ENV")) {
//			
//		}
		if (corpID != null && !corpID.equals(""))
			result.put(ORGAN_UUID, corpID); // 保存企业或政府ID
		else
			result.put(ORGAN_UUID, getSessionUserPO().getORGAN_UUID()); // 保存企业或政府ID

		String govType = request.getParameter("govType");
		if (govType != null && !govType.equals(""))
			result.put("govType", govType);
				
		
		return result;
	}
	
	/**
	 * 获取PO对象
	 * 
	 * @author 马宁
	 */
	protected Object retrievePoObj() throws IllegalArgumentException,
			IllegalAccessException, InstantiationException {
		Object result = null;
		String name = getClass().getSimpleName().replace("Action", "");
		String poClassName = name + "PO";
		Field[] fields = getClass().getDeclaredFields();
		for (Field f : fields) {
			String fName = f.getType().getSimpleName();
			if (fName.equals(poClassName)) {
				f.setAccessible(true);
				result = f.get(this);
				f.setAccessible(false);
				if (result == null) {
					result = f.getType().newInstance();
				}
				break;
			}
		}
		return result;
	}
	
	/*
	 * 判断是否仅请求查询个数而不请求每条记录 
	 * 
	 * @author 马宁
	 */
	private boolean isRequestCountOnly() {
		return "true".equals(request.getParameter("qc"));
	}
	
	/**
	 * AJAX取PO
	 * @return
	 */
	public Object getJsonPO() {
		try {
			if (jsonPO == null) {
				String name = getClass().getSimpleName().replace("Action", "");
				String poClassName = name + "PO";
				Field[] fields = getClass().getDeclaredFields();
				for (Field f : fields) {
					String fName = f.getType().getSimpleName();
					if (fName.equals(poClassName)) {
						f.setAccessible(true);
						jsonPO = f.get(this);
						f.setAccessible(false);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonPO;
	}
  
	public void setCodes(String codes) {
		List<String> list = null;

		if (codes == null || codes.isEmpty()) {
			list = new ArrayList<String>();
		} else {
			if (codes.startsWith("[")) {
				codes = codes.substring(1);
			}
			if (codes.endsWith("]")) {
				codes = codes.substring(0, codes.length() - 1);
			}
			codes = codes.replace(", ", ",");
			list = Arrays.asList(codes.split(","));
		}

		this.codes = list;
	}
  
	public String execute() throws Exception {
		return SUCCESS;
	}

	public void addActionError(String msg) {

	}

	public void addActionMessage(String msg) {

	}

	public String getText(String aTextName) {
		ResourceBundle rb = ResourceBundle.getBundle("application.message.properties");
		return rb.getString(aTextName);
	}

	/**
	 * 
	 * initParamMap(打印报表时把字符串输出到前台)
	 */
	protected void gridReportPrint(String str)throws Exception {
		
		response.resetBuffer();
		response.setCharacterEncoding(request.getCharacterEncoding());
		response.setContentType("application/xml");
		PrintWriter pw = response.getWriter();
		pw.print(str);
		pw.close();  //终止后续不必要内容输出
	}
	
	// ------------------ Log --------------------
	
	/**
	 * 记录操作日志
	 * 
	 * @param content
	 *            - 日志内容
	 */
	protected void operLog(String content) {
		StackTraceElement callMethodSte = new Throwable().getStackTrace()[1];
		operLogService.log(callMethodSte.getClassName(),
				callMethodSte.getMethodName(), content);
	}
	
	/*
	 * 记录异常日志
	 */
	private String exceptionLog(Exception e){
		StringWriter out = new StringWriter();
		e.printStackTrace(new PrintWriter(out));
		
		return exceptionLogService.log(e.getMessage(), out.toString());
	}
	
	// ------------------ MSG METHODS ------------------
	
	protected void setQueryFailedMsg() {
		message.set(Constants.MSG_WARNING, Constants.QUERY_FAILED_MSG);
	}

	protected void setAddSuccessMsg() {
		message.set(Constants.MSG_OK, Constants.ADD_SUCCESS_MSG);
	}

	protected void setAddSuccessAndContinueMsg() {
		message.set(Constants.MSG_OK, Constants.ADD_SUCCESS_CONTINUE_MSG);
	}

	protected void setAddFailedMsg() {
		message.set(Constants.MSG_ERROR, Constants.ADD_FAILED_MSG);
	}

	protected void setUpdateSuccessMsg() {
		message.set(Constants.MSG_OK, Constants.UPDATE_SUCCESS_MSG);
	}

	protected void setUpdateFailedMsg() {
		message.set(Constants.MSG_ERROR, Constants.UPDATE_FAILED_MSG);
	}

	protected void setDelSuccessMsg() {
		message.set(Constants.MSG_OK, Constants.DEL_SUCCESS_MSG);
	}

	protected void setDelSuccessMsg(int rs) {
		message.set(Constants.MSG_OK, Constants.DEL_SUCCESS_MSG_PRE + rs
				+ Constants.DEL_SUCCESS_MSG_SUF);
	}

	protected void setDelFailedMsg() {
		message.set(Constants.MSG_ERROR, Constants.DEL_FAILED_MSG);
	}

	protected void setEmptyDataMsg() {
		message.set(Constants.MSG_WARNING, Constants.EMPTY_DATA_MSG);
	}

	protected void setConvertFailedMsg() {
		message.set(Constants.MSG_ERROR, Constants.CONVERT_FAILED_MSG);
	}

	protected void setIdentiryFailedMsg() {
		message.set(Constants.MSG_ERROR, Constants.IDENTIFY_FAILED_MSG);
	}

	protected void setOperateSuccessMsg() {
		message.set(Constants.MSG_OK, Constants.OPREATE_SUCCESS_MSG);
	}
	
	protected void setOperateFailedMsg() {
		message.set(Constants.MSG_ERROR, Constants.OPREATE_FAILED_MSG);
	}

	protected void setDataAbnormalMsg() {
		message.set(Constants.MSG_ERROR, Constants.DATA_ABNORMAL_MSG);
	}

	protected void setSubmitSuccessMsg() {
		message.set(Constants.MSG_OK, Constants.SUBMIT_SUCCESS_MSG);
	}

	protected void setSubmitFailedMsg() {
		message.set(Constants.MSG_ERROR, Constants.SUBMIT_FAILED_MSG);
	}

	/**
	 * 处理JsonMsgException
	 * 
	 * @param e
	 *            - service层传来的自定义异常
	 */
	protected void handleJsonMsgException(JsonMsgException e){
		logger.warn(e.getMessage(), e);
		
		String exceptionLogCode = null;
		
		if(e.isErrorMsg()){
			exceptionLogCode = exceptionLog(e);
		}
		
		String msg = generateMsg(e.getMessage(), exceptionLogCode);
		if(e.hasMsgTitle()){
			message.set(e.getMsgType(), msg, e.getMsgTitle());
		} else {
			message.set(e.getMsgType(), msg);
		}
		
	}
	
	// --------------- private methods -------------
	
	private String generateMsg(String msg, String exceptionLogCode){
		return exceptionLogCode == null 
				? msg
				: msg + "<BR>错误编号：" + exceptionLogCode;
	}
	
  // -------------- getter and setter -----------------
  
	public IBaseService getiBaseService() {
		return iBaseService;
	}

	public void setiBaseService(IBaseService iBaseService) {
		this.iBaseService = iBaseService;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public List<String> getCodes() {
		return codes;
	}

	public HttpSession getSession() {
		if (session == null) {
			session = request.getSession();
		}
		return session;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public PagerData getPagerData() {
		return pagerData;
	}

	public void setPagerData(PagerData pagerData) {
		this.pagerData = pagerData;
	}

	public void setActionButtons(String actionButtons) {
		this.actionButtons = actionButtons;
	}

	public UserPO getSessionUserPO() {
		return ((UserSession) getSession().getAttribute(
				Constants.USER_SESSION_KEY)).getUserPO();
	}

	public void setJsonPO(Object jsonPO) {
		this.jsonPO = jsonPO;
	}

	public UserMessageData getMessage() {
		return message;
	}

	public void setMessage(UserMessageData message) {
		this.message = message;
	}

	public HashMap<String, String> getJstr() {
		return jstr;
	}

	public void setJstr(HashMap<String, String> jstr) {
		this.jstr = jstr;
	}

	public void setOperLogService(IOperLogService operLogService) {
		this.operLogService = operLogService;
	}

	public void setExceptionLogService(IExceptionLogService exceptionLogService) {
		this.exceptionLogService = exceptionLogService;
	}


	public CallbackDataPO getCallbackDataPO() {
		return callbackDataPO;
	}


	public void setCallbackDataPO(CallbackDataPO callbackDataPO) {
		this.callbackDataPO = callbackDataPO;
	}
	
}
