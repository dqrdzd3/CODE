/**
 * 文件名：B00401Action.java
 *
 * 版本信息：1.0
 * 日期：2012-6-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.pojo.B00401PO;
import com.hw.hwsafe.corpinfo.service.IB00401Service;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.pojo.CodeValuePO;
import com.hw.hwsafe.platform.service.CodeValueService;
import com.hw.hwsafe.plot.UtilPlot;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 项目名称：hwsafe 
 * 类名称：B00401Action 
 * 类描述： 设备设施类
 * 创建人：wy 
 * 创建时间：2012-6-11 下午6:00:49 
 * 修改人：
 * 修改时间：2012-6-11 下午6:00:49 
 * 修改备注：
 * @version
 */
public class B00401Action extends BaseAction  {
	private B00401PO b00401PO;
    @Autowired
	private IB00401Service b00401Service;
	private List<B00401PO> b00401List;
	private List<HashMap<String, Object>> mapList;
	@Autowired
	private CodeValueService codevalueService;// 代码表Service
	private List<CodeValuePO> codeValueSBLX;//设备类型代码
	private List list;

	/**
	 * 索引页面
	 */
	public String doIndex() {
		return "index";
	}

	/**
	 * 展示所有的设备设施列表
	 */
	public String doList() {
		codeValueSBLX = codevalueService.retrieveCodeByType("equip_type");
		if (b00401PO == null) {
			b00401PO = new B00401PO();
		}
		String ss = request.getParameter("b00401PO.MA002");
		b00401PO.setMA002(ss == null ? this.getSessionUserPO().getCorpid() : ss);
		return SUCCESS;
	}
	/**
	 * 跳转到设备设施列表页面
	 * @return
	 */
    public String getCount(){
    	codeValueSBLX = codevalueService.retrieveCodeByType("equip_type");
    	return "count";
    }
    /**
	 * 分页方法重写 (没有PO类)
	 */
	public String queryData1() throws Exception {
		// 分页信息
		pagerData =  initPagerData();
		// 参数Map
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("pagerData", pagerData);
		// 根据条件查询分页数据
		paramMap.put("po", b00401PO); // 查询条件
		pagerData.setRows(b00401Service.retrieveByPage1(paramMap));
		
		// 根据条件查询总条数
		paramMap.put("selectCount", true);
		pagerData.setRecords(b00401Service.retrieveByPage1(paramMap));
		
		return JSON_PAGER;
	}
	/**
	 * 跳转到添加页面
	 */
	public String doAdd() {
		codeValueSBLX = codevalueService.retrieveCodeByType("equip_type");
		return "add";
	}

	/**
	 * 增加保存操作
	 */
	public String doSaveAdd() {
		try {
			b00401PO.setMA001(UUIDGenerater.getUUID());
			b00401PO.setMA002(getSessionUserPO().getCorpid());
			b00401PO.setMA003(getSessionUserPO().getOrgName());
			b00401PO.setMA017(getSessionUserPO().getId());
			b00401Service.insertB00401(b00401PO);
			setAddSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace();
			setAddFailedMsg();
		}
		return JSON_MSG;
	}

	/**
	 * 修改操作
	 */
	public String doEdit() {
		try {
			b00401PO = b00401Service.retrieveB00401ByID(b00401PO.getMA001());
			if (b00401PO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}else {
				setEmptyDataMsg();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PO;
	}
	/**
	 * 查询设备设施记录
	 * @return
	 */
	public String doView() {
		try {
			b00401PO = b00401Service.retrieveB00401ByID(b00401PO.getMA001());
			codeValueSBLX = codevalueService.retrieveCodeByType("equip_type");
			if (b00401PO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}else {
				setEmptyDataMsg();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "view";
	}
	/**
	 * 修改记录
	 */
	public String doEditB00401() {
		try {
			b00401PO = b00401Service.retrieveB00401ByID(b00401PO.getMA001());
			codeValueSBLX = codevalueService.retrieveCodeByType("equip_type");
			if (b00401PO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}else {
				setEmptyDataMsg();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "queryB00401";
	}
	
	/**
	 * 修改保存操作
	 */
	public String doSaveEdit() {
		try {
			if(b00401Service.isExist(b00401PO.getMA001())){
				b00401Service.updateB00401(b00401PO);
				setUpdateSuccessMsg();
			}else {
			    setEmptyDataMsg();
			}
		} catch (Exception e) {
			e.printStackTrace();
			setUpdateFailedMsg();
		}
		return JSON_MSG;
	}
	/**
	 * 跳转到统计页面
	 */
	public String doGetB00401Details(){
		codeValueSBLX = codevalueService.retrieveCodeByType("equip_type");
		return "tj";
	}
    /**
     * 
     * ajaxGetName(动态获取名称)
     * @param   name
     * @param  @return    设定文件
     * @return String    DOM对象
     * @Exception 异常对象
     * @since  CodingExample　Ver(编码范例查看) 1.1
     */
	public String ajaxGetName() {
		StringBuffer sb = new StringBuffer(200);
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("MA006", b00401PO.getMA006());
			map.put("MA004", b00401PO.getMA004());
			List<B00401PO> poList = b00401Service.retrieveB00401ByPinyin(map);
			B00401PO po = null;
			sb.append("[");
			if (poList != null && poList.size() > 0) {
				for (int i = 0, n = poList.size(); i < n; i++) {
					po = poList.get(i);
					sb.append("{'id':'" + i + "','name':'" + po.getMA004()
							+ "'},");
				}
				sb = new StringBuffer(sb.substring(0, sb.length() - 1));
			}
			sb.append("]");

			this.getResponse().setCharacterEncoding("utf-8");
			PrintWriter out = this.getResponse().getWriter();
			out.write(sb.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * doGetStat(统计查询)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public String doGetStat() {
		try {
			list = b00401Service.statDevide(getSessionUserPO().getCorpid());
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "stat";
	}
	
	/**
	 *  
	 * doGetstatChart(统计图)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public String doGetstatChart() {
		try {
			setJstr(UtilPlot.setColPieData(b00401Service.statDevide(getSessionUserPO().getCorpid())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_STR;
	}
	
	/**
	 * 函 数 名：doGetGroupdangerCtrl
	 * 功能描述：危险源类别统计控制跳转
	 * @return String
	 * 创建人：王贺喜
	 * 创建时间：2012-12-15 下午2:23:47
	 */
	public String doGetDangerCategoryCtrl(){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("corpid",this.getSessionUserPO().getCorpid());
		mapList = b00401Service.getGroupDangerCategory(map);
		return "dangerCategory";
	}
	
	/**
	 * 函 数 名：doGetGroupDangerCategory
	 * 功能描述：危险源类别统计 实现方法 （画图）
	 * @return String
	 * 创建人：王贺喜
	 * 创建时间：2012-12-7 下午2:24:44
	 */
	public String doGroupDangerCategory() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("corpid",this.getSessionUserPO().getCorpid());
		mapList = b00401Service.getGroupDangerCategory(map);
		setJstr(UtilPlot.setColPieData(mapList));
		return JSON_STR;
	}
	
	/**
	 * 函 数 名：doGetDangerLevel
	 * 功能描述：危险源级别统计 控制列表
	 * @return String
	 * 创建人：王贺喜
	 * 创建时间：2012-12-8 上午11:00:51
	 */
	public String doGetDangerTj() {
		//HashMap<String, Object> map = new HashMap<String, Object>();
		//map.put("corpid", this.getSessionUserPO().getCorpid());
		//mapList = b00401Service.getGroupDangerLevel(map);		
		return "dangertj";
	}
	/**
	 * 函 数 名：doGetDangerLevel
	 * 功能描述：危险源级别统计 控制列表
	 * @return String
	 * 创建人：王贺喜
	 * 创建时间：2012-12-8 上午11:00:51
	 */
	public String doGetDangerLevel() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("corpid", this.getSessionUserPO().getCorpid());
		mapList = b00401Service.getGroupDangerLevel(map);		
		return "level";
	}
	
	/**
	 * 函 数 名：doGroupLevel
	 * 功能描述：危险源级别统计实现方法（画图）
	 * @return String
	 * 创建人：王贺喜
	 * 创建时间：2012-12-7 下午2:33:30
	 */
	public String doGroupLevel() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("corpid", this.getSessionUserPO().getCorpid());
		mapList = b00401Service.getGroupDangerLevel(map);
		setJstr(UtilPlot.setColPieData(mapList));
		return JSON_STR;
	}
	
	/**
	 * 函 数 名：queryData2
	 * 功能描述：危险源等级详情 分页方法
	 * @throws Exception
	 * @return String
	 * 创建人：王贺喜
	 * 创建时间：2012-12-8 上午10:46:20
	 */
	public String queryData2() throws Exception {
		try {
			pagerData = initPagerData();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("pagerData", pagerData);
			paramMap.put(ORGAN_UUID, getSessionUserPO().getORGAN_UUID());
			if (!"true".equals(request.getParameter("qc"))) {
				pagerData.setRows(b00401Service.retrieveByPage2(paramMap));
			}
			paramMap.put("selectCount", true);
			pagerData.setRecords(b00401Service.retrieveByPage2(paramMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGER;
	}
	
	/**
	 * 函 数 名：queryData3
	 * 功能描述：危险源详情 分页方法
	 * @throws Exception
	 * @return String
	 * 创建人：王贺喜
	 * 创建时间：2012-12-8 上午10:46:20
	 */
	public String queryDate3() throws Exception {
		try {
			pagerData = initPagerData();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("pagerData", pagerData);
			paramMap.put("po", b00401PO);
			paramMap.put(ORGAN_UUID, getSessionUserPO().getORGAN_UUID());
			if (!"true".equals(request.getParameter("qc"))) {
				pagerData.setRows(b00401Service.retrieveByPage3(paramMap));
			}
			paramMap.put("selectCount", true);
			pagerData.setRecords(b00401Service.retrieveByPage3(paramMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGER;
	}
	
	/**
	 * 函 数 名：doGetDetails
	 * 功能描述：查询危险源详情
	 * @return String
	 * 创建人：王贺喜
	 * 创建时间：2012-12-7 下午2:32:11
	 */
	public String doGetDetails() {
		return "details";
	}

	public IB00401Service getB00401Service() {
		return b00401Service;
	}

	public void setB00401Service(IB00401Service b00401Service) {
		this.b00401Service = b00401Service;
	}

	public CodeValueService getCodevalueService() {
		return codevalueService;
	}

	public void setCodevalueService(CodeValueService codevalueService) {
		this.codevalueService = codevalueService;
	}
	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public List<CodeValuePO> getCodeValueSBLX() {
		return codeValueSBLX;
	}

	public void setCodeValueSBLX(List<CodeValuePO> codeValueSBLX) {
		this.codeValueSBLX = codeValueSBLX;
	}

	public void setB00401List(List<B00401PO> b00401List) {
		this.b00401List = b00401List;
	}

	public List<B00401PO> getB00401List() {
		return b00401List;
	}

	public void setB00401PO(B00401PO b00401PO) {
		this.b00401PO = b00401PO;
	}

	public B00401PO getB00401PO() {
		return b00401PO;
	}

	public List<HashMap<String, Object>> getMapList() {
		return mapList;
	}

	public void setMapList(List<HashMap<String, Object>> mapList) {
		this.mapList = mapList;
	}
	
}

