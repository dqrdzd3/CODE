package com.hw.hwsafe.smart.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.smart.pojo.S002PO;
import com.hw.hwsafe.smart.pojo.U001PO;
import com.hw.hwsafe.smart.service.IS002Service;
import com.hw.hwsafe.smart.service.IU001Service;
import com.hw.hwsafe.smart.util.IosPush;
import com.hw.hwsafe.smart.util.Push;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * <pre>
 * 项目名称：smart
 * 类名称：S002Action
 * 类描述：
 * 创建人：李羽皓
 * 创建时间：2014-7-11
 * 修改人:
 * 修改时间:
 * </pre>
 */
public class S002Action extends BaseAction {

	@Autowired
	private IS002Service s002Service;
	@Autowired
	private IU001Service u001Service;
	private U001PO u001PO;
	private S002PO s002PO;
	private String ids;
	private String userTree;// 用户树

	/**
	 * 打开列表界面 消息推送 按手机号推送
	 * 
	 * @return
	 * @author liyuhao
	 * @create_time 2014年7月11日上午10:13:24
	 */
	public String doIndex() {
		List<U001PO> uList = getAllU001POList();
		this.setUserTree(doInitUserTree(uList).toString());// 加载树
		return SUCCESS;
	}

	/**
	 * 读取用户列表
	 * 
	 * @return
	 */
	private List<U001PO> getAllU001POList() {
		List<U001PO> uList = null;
		try {
			uList = s002Service.retrieveInstance(); // 获取所有用户
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uList;
	}

	/**
	 * 消息推送 按地区推送
	 * 
	 * @return
	 */
	public String doPushByArea() {
		List<U001PO> uList = getAllU001POList();
		// 如果没有值,直接返回
		if(null==uList){
			return SUCCESS;
		}
		this.setUserTree(doInitTreeByArea(uList).toString());// 加载树
		return SUCCESS;
	}

	/**
	 * 
	 * 搜索框
	 * 
	 * @return
	 * @author liyuhao
	 * @create_time 2014年7月11日下午4:45:09
	 */
	public String initUserTreeByUserName() {
		String uName = u001PO.getMa008();// 获取参数
		
		// 如果是按照 区域查询就会有值
		String byArea = u001PO.getMa020();// 获取参数
		
		List<U001PO> mU001POList = null;
		try {
			mU001POList = s002Service.retrieveInstanceByName(uName);// 查询数据库
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray jsonArray = null;
		if(null==byArea || "".equals(byArea)){
			// 按照手机搜索
			jsonArray = doInitUserTree(mU001POList);
		}else{
			// 	按照区域搜索
			jsonArray = doInitTreeByArea(mU001POList);
		}
		callbackDataPO = new CallbackDataPO("1", "查询数据成功", 0, null, jsonArray,
				"");
		return JSON_DATA;
	}

	/**
	 * 
	 * 推送按钮
	 * 
	 * @return
	 * @author liyuhao
	 * @create_time 2014年7月14日下午1:43:05
	 */
	public String doPush() {
		// 获取参数
		String name = s002PO.getMa006();
		String content = s002PO.getMa005();
		// 封装对象
		S002PO s002PO = new S002PO();
		s002PO.setMa001(UUIDGenerater.getUUID());
		s002PO.setMa002("空气电台日常播报");
		s002PO.setMa003(null);
		s002PO.setMa005(content);
		s002PO.setMa006(name);
		
		List<String> phoneList = getPhoneList(name);// 得到推送人员的电话
		try {
			
			Push.doWebservice(phoneList, "空气电台日常播报", content, "00");// 将信息推送出去
/*			List<String> tokenList = getIosTockenList(phoneList);
			IosPush.getPushSuccessed(tokenList, content, 0, null);
			callbackDataPO = new CallbackDataPO("1", "推送成功", 0, null, "S002");
			s002PO.setMa007("成功");*/
		} catch (Exception e) {
			e.printStackTrace();
			callbackDataPO = new CallbackDataPO("1", "推送失败", 0, null, "S002");
			s002PO.setMa007("失败");
		}
		try {
			//List<String> phoneList = getPhoneList(name);// 得到推送人员的电话
			
			List<String> tokenList = getIosTockenList(phoneList);
			IosPush.getPushSuccessed(tokenList, content, 0, null);
			callbackDataPO = new CallbackDataPO("1", "推送成功", 0, null, "S002");
			s002PO.setMa007("成功");
		} catch (Exception e) {
			e.printStackTrace();
			callbackDataPO = new CallbackDataPO("1", "推送失败", 0, null, "S002");
			s002PO.setMa007("失败");
		}
		insertPushMsgToS002(s002PO);// 将值插入到推送记录表:s002
		return JSON_DATA;
	}

	/**
	 * 
	 * 获取用户输入的推送人的号码
	 * 
	 * @param nameAndPhones
	 * @return
	 * @author liyuhao
	 * @create_time 2014年7月15日上午11:33:23
	 */
	private List<String> getPhoneList(String nameAndPhones) {
		List<String> phoneList = new ArrayList<String>();
		String[] split = nameAndPhones.split(",");
		for (int i = 0; i < split.length; i++) {
			String e = split[i].substring(split[i].indexOf('<') + 1,
					split[i].indexOf('>'));
			phoneList.add(e);
		}
		return phoneList;
	}
	/**
	 * 
	 * 通过号码查找user的iostoken
	 * 
	 * @param nameAndPhones
	 * @return
	 * @author liyuhao
	 * @create_time 2014年7月15日上午11:33:23
	 */
	private List<String> getIosTockenList(List<String> phones){
		List<String> iosList = new ArrayList<String>();
		for (String phone:phones) {
			U001PO u001po = new U001PO();
			u001po.setMa006(phone);
			List<U001PO> poList = null;
			try {
				poList = u001Service.retrieveInstanceByPO(u001po);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (poList!=null) {
				if (!StringUtils.isBlank(poList.get(0).getMa018())) {
					iosList.add(poList.get(0).getMa018());
				}
			}
			
		}
		return iosList;
		
	}
	/**
	 * 
	 * 将值插入到推送记录表:s002
	 * 
	 * @param name
	 * @param content
	 * @author liyuhao
	 * @create_time 2014年7月14日下午2:17:36
	 */
	private void insertPushMsgToS002(S002PO s002PO) {
		try {
			s002Service.insertInstance(s002PO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 显示推送记录
	 * 
	 * @return
	 * @author liyuhao
	 * @create_time 2014年7月15日下午2:57:35
	 */
	public String doViewPushRecord() {
		return SUCCESS;
	}

	public String add() {
		return null;
	}

	/**
	 * 
	 * 显示推送详细
	 * 
	 * @return
	 * @author liyuhao
	 * @create_time 2014年7月17日下午6:07:33
	 */
	public String doShowPushDetail() {
		try {
			s002PO = s002Service.retrieveInstanceById(s002PO.getMa001());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}

	/**
	 * 
	 * 通过id批量删除推送记录
	 * 
	 * @return
	 * @author liyuaho
	 * @create_time 2014年7月15日下午3:00:35
	 */
	public String doDelete() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("idArray", ids.split(","));
			s002Service.delBatchS002(map);
			setDelSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace();
			setDelFailedMsg();
		}
		return JSON_MSG;
	}

	/**
	 * 读取u001表中记录, 初始化用户树
	 * 
	 * @return
	 */
	private JSONArray doInitUserTree(List<U001PO> list) {
		JSONArray jsonArray = new JSONArray();
		// 根节点
		setZtreeRoot(jsonArray, "1", "联通");
		setZtreeRoot(jsonArray, "2", "移动");
		setZtreeRoot(jsonArray, "3", "电信");
		setZtreeRoot(jsonArray, "4", "其他");
		// 二级节点： 联通
		setZtreeSecond(jsonArray, "101", "1", "130");
		setZtreeSecond(jsonArray, "102", "1", "131");
		setZtreeSecond(jsonArray, "103", "1", "132");
		setZtreeSecond(jsonArray, "104", "1", "145");
		setZtreeSecond(jsonArray, "105", "1", "155");
		setZtreeSecond(jsonArray, "106", "1", "156");
		setZtreeSecond(jsonArray, "107", "1", "185");
		setZtreeSecond(jsonArray, "108", "1", "186");
		setZtreeSecond(jsonArray, "109", "1", "其他");
		// 二级节点： 移动
		setZtreeSecond(jsonArray, "201", "2", "134");
		setZtreeSecond(jsonArray, "202", "2", "135");
		setZtreeSecond(jsonArray, "203", "2", "136");
		setZtreeSecond(jsonArray, "204", "2", "137");
		setZtreeSecond(jsonArray, "205", "2", "138");
		setZtreeSecond(jsonArray, "206", "2", "139");
		setZtreeSecond(jsonArray, "207", "2", "150");
		setZtreeSecond(jsonArray, "208", "2", "147");
		setZtreeSecond(jsonArray, "209", "2", "151");
		setZtreeSecond(jsonArray, "210", "2", "152");
		setZtreeSecond(jsonArray, "211", "2", "157");
		setZtreeSecond(jsonArray, "212", "2", "158");
		setZtreeSecond(jsonArray, "213", "2", "159");
		setZtreeSecond(jsonArray, "214", "2", "182");
		setZtreeSecond(jsonArray, "215", "2", "183");
		setZtreeSecond(jsonArray, "216", "2", "187");
		setZtreeSecond(jsonArray, "217", "2", "188");
		setZtreeSecond(jsonArray, "218", "2", "其他");
		// 二级节点：电信
		setZtreeSecond(jsonArray, "301", "3", "133");
		setZtreeSecond(jsonArray, "302", "3", "153");
		setZtreeSecond(jsonArray, "303", "3", "180");
		setZtreeSecond(jsonArray, "304", "3", "189");
		setZtreeSecond(jsonArray, "305", "3", "其他");

		U001PO u001po2 = null;
		for (int i = 0; i < list.size(); i++) {
			u001po2 = list.get(i);
			// 三级节点：联通
			setZtreeThird(jsonArray, u001po2, "1001", "101", "130");
			setZtreeThird(jsonArray, u001po2, "1002", "102", "131");
			setZtreeThird(jsonArray, u001po2, "1003", "103", "132");
			setZtreeThird(jsonArray, u001po2, "1004", "104", "145");
			setZtreeThird(jsonArray, u001po2, "1005", "105", "155");
			setZtreeThird(jsonArray, u001po2, "1006", "106", "156");
			setZtreeThird(jsonArray, u001po2, "1007", "107", "185");
			setZtreeThird(jsonArray, u001po2, "1008", "108", "186");
			setZtreeThird(jsonArray, u001po2, "1009", "109", "其他");
			// 三级节点：移动
			setZtreeThird(jsonArray, u001po2, "2001", "201", "134");
			setZtreeThird(jsonArray, u001po2, "2002", "202", "135");
			setZtreeThird(jsonArray, u001po2, "2003", "203", "136");
			setZtreeThird(jsonArray, u001po2, "2004", "204", "137");
			setZtreeThird(jsonArray, u001po2, "2005", "205", "138");
			setZtreeThird(jsonArray, u001po2, "2006", "206", "139");
			setZtreeThird(jsonArray, u001po2, "2007", "208", "147");
			setZtreeThird(jsonArray, u001po2, "2008", "207", "150");
			setZtreeThird(jsonArray, u001po2, "2009", "209", "151");
			setZtreeThird(jsonArray, u001po2, "2010", "210", "152");
			setZtreeThird(jsonArray, u001po2, "2011", "211", "157");
			setZtreeThird(jsonArray, u001po2, "2012", "212", "158");
			setZtreeThird(jsonArray, u001po2, "2013", "213", "159");
			setZtreeThird(jsonArray, u001po2, "2014", "214", "182");
			setZtreeThird(jsonArray, u001po2, "2015", "215", "183");
			setZtreeThird(jsonArray, u001po2, "2016", "216", "187");
			setZtreeThird(jsonArray, u001po2, "2017", "217", "188");
			setZtreeThird(jsonArray, u001po2, "2018", "218", "其他");
			// 三级节点：电信
			setZtreeThird(jsonArray, u001po2, "3001", "301", "133");
			setZtreeThird(jsonArray, u001po2, "3002", "302", "153");
			setZtreeThird(jsonArray, u001po2, "3003", "303", "180");
			setZtreeThird(jsonArray, u001po2, "3004", "304", "189");
			setZtreeThird(jsonArray, u001po2, "3005", "305", "其他");
		}

		return jsonArray;
	}

	public void setZtreeThird(JSONArray jsonArray, U001PO u001po2, String id,
			String pId, String tel) {
		String nodeName = "";
		if (u001po2.getMa006().startsWith(tel)) {
			System.out.println(u001po2.getMa019());
			if(u001po2.getMa019()!=null && u001po2.getMa019().startsWith("ios")){
				nodeName = u001po2.getMa008() + "---" + u001po2.getMa006() + "(ios)";
			}else
				nodeName = u001po2.getMa008() + "---" + u001po2.getMa006() + "(android)";
			setZtreeThird(jsonArray, id, pId, nodeName);
		}
	}

	// 三级节点
	public void setZtreeThird(JSONArray jsonArray, String id, String pId,
			String name) {
		JSONObject node = new JSONObject();
		node.put("id", id);
		node.put("pId", pId);
		node.put("name", name);
		node.put("isParent", false);
		jsonArray.add(node);
	}

	// 根节点，一级节点
	public void setZtreeRoot(JSONArray jsonArray, String id, String name) {
		JSONObject node = new JSONObject();
		node.put("id", id);
		node.put("pId", 0);
		node.put("name", name);
		node.put("isParent", true);
		jsonArray.add(node);
	}

	// 二级节点
	public void setZtreeSecond(JSONArray jsonArray, String id, String pId,
			String name) {
		JSONObject node = new JSONObject();
		node.put("id", id);
		node.put("pId", pId);
		node.put("name", name);
		node.put("isParent", true);
		jsonArray.add(node);
	}

	/**
	 * 给要作为父类节点的PO生成id
	 * 
	 * @param oldList
	 * @return
	 */
	private List<U001PO> setTreeId(List<U001PO> oldList) {
		List<U001PO> returnList = new ArrayList<U001PO>();
		int listSize = oldList.size();
		for (int i = 0; i < listSize; i++) {
			U001PO tempPO = oldList.get(i);
			tempPO.setMa001(UUIDGenerater.getUUID());
			returnList.add(tempPO);
		}
		return returnList;
	}

	/**
	 * 读取u001表中记录, 初始化用户树
	 * 
	 * 按照 区域初始化
	 * 
	 * @return
	 */
	private JSONArray doInitTreeByArea(List<U001PO> u001List) {

		// 返回的树形结构数组
		JSONArray treeArray = new JSONArray();

		List<U001PO> provinceList = null;
		List<U001PO> cityList = null;
		List<U001PO> areaList = null;

		try {
			// 临时list
			List<U001PO> tempU001List = new ArrayList<U001PO>();
			provinceList = s002Service.retrieveProvinceList(); // 获取省份
			cityList = s002Service.retrieveCityList(); // 获取市
			areaList = s002Service.retrieveAreaList(); // 获取区
			// 生成省份的id
			if (null != provinceList) {
				provinceList = setTreeId(provinceList);
				int allU001ListSize = u001List.size();
				for (int i = 0; i < allU001ListSize; i++) {
					U001PO tempU001 = u001List.get(i);
					int provinceListSize = provinceList.size();
					for (int j = 0; j < provinceListSize; j++) {
						U001PO proU001 = provinceList.get(j);
						String proID = proU001.getMa001();
						String proName = proU001.getMa002();
						if (proName.equals(tempU001.getMa002())) {
							tempU001.setMa018(proID);
							continue;
						}
					}
					tempU001List.add(tempU001);
				}
			}
			// 还原List
			u001List = tempU001List;
			tempU001List = new ArrayList<U001PO>();
			// 生成市份的id
			if (null != cityList) {
				cityList = setTreeId(cityList);
				int allU001ListSize = u001List.size();
				for (int i = 0; i < allU001ListSize; i++) {
					U001PO tempU001 = u001List.get(i);
					int cityListSize = cityList.size();
					for (int j = 0; j < cityListSize; j++) {
						U001PO cityU001 = cityList.get(j);
						String cityID = cityU001.getMa001();
						String cityName = cityU001.getMa003();
						if (cityName.equals(tempU001.getMa003())) {
							tempU001.setMa019(cityID);
							continue;
						}
					}
					tempU001List.add(tempU001);
				}
			}
			// 还原List
			u001List = tempU001List;
			tempU001List = new ArrayList<U001PO>();
			// 生成区域的id
			if (null != areaList) {
				areaList = setTreeId(areaList);
				int allU001ListSize = u001List.size();
				for (int i = 0; i < allU001ListSize; i++) {
					U001PO tempU001 = u001List.get(i);

					int areaListSize = areaList.size();
					for (int j = 0; j < areaListSize; j++) {
						U001PO areaU001 = areaList.get(j);
						String areaID = areaU001.getMa001();
						String areaName = areaU001.getMa004();
						if (areaName.equals(tempU001.getMa004())) {
							tempU001.setMa020(areaID);
							continue;
						}
					}
					tempU001List.add(tempU001);
				}

			}

			// 还原List
			u001List = tempU001List;
			tempU001List = new ArrayList<U001PO>();

			// 1.
			// ------------------------------------------------------------------------
			// 生成 省份节点
			// 省份的其他,即 省份字段为空的节点的父类节点
			JSONObject noProvinceNode = new JSONObject();
			noProvinceNode.put("id", "noProvince");
			noProvinceNode.put("pId", "");
			noProvinceNode.put("name", "其他");
			noProvinceNode.put("isParent", true);
			if (null != provinceList) {
				int proListSize = provinceList.size();
				for (int i = 0; i < proListSize; i++) {
					U001PO pro = provinceList.get(i);
					JSONObject proNode = new JSONObject();
					proNode.put("id", pro.getMa001());
					proNode.put("pId", "");
					proNode.put("name", pro.getMa002());
					proNode.put("isParent", true);
					treeArray.add(proNode);
				}
				int allU001ListSize = u001List.size();
				for (int i = 0; i < allU001ListSize; i++) {
					U001PO tempPO = u001List.get(i);
					if (null != tempPO.getMa002()) {
						continue;
					}
					JSONObject noProNode = new JSONObject();
					noProNode.put("id", tempPO.getMa001());
					noProNode.put("pId", "noProvince");
					noProNode.put("name",
							tempPO.getMa008() + "---" + tempPO.getMa006());
					noProNode.put("isParent", false);
					treeArray.add(noProNode);
				}
			}
			treeArray.add(noProvinceNode);

			// 2.
			// ------------------------------------------------------------------------
			// 把市节点换成 详细信息PO
			if (null != cityList) {
				List<U001PO> newCityList = new ArrayList<U001PO>();
				int cityListSize = cityList.size();
				for (int i = 0; i < cityListSize; i++) {
					U001PO cityPO = cityList.get(i);
					int allU001ListSize = u001List.size();
					for (int j = 0; j < allU001ListSize; j++) {
						U001PO tempPO = u001List.get(j);
						if (null != tempPO.getMa003()
								&& null != tempPO.getMa002()
								&& cityPO.getMa003().equals(tempPO.getMa003())) {
							newCityList.add(tempPO);
							break;
						}
					}
				}
				// 3. ------------------------------------------
				// 生成城市节点
				// cityList = newCityList;
				int newCitySize =newCityList.size();
				for (int i = 0; i < newCitySize; i++) {
					U001PO city = newCityList.get(i);
					JSONObject cityNode = new JSONObject();
					cityNode.put("id", city.getMa019());
					cityNode.put("pId", city.getMa018());
					cityNode.put("name", city.getMa003());
					cityNode.put("isParent", true);
					treeArray.add(cityNode);
				}

				int allU001ListSize = u001List.size();
				for (int i = 0; i < allU001ListSize; i++) {
					U001PO tempPO = u001List.get(i);
					if (null == tempPO.getMa003() && null != tempPO.getMa002()) {
						JSONObject noCityNode = new JSONObject();
						noCityNode.put("id", tempPO.getMa001());
						noCityNode.put("pId", tempPO.getMa018());
						noCityNode.put("name", tempPO.getMa008() + "---"
								+ tempPO.getMa006());
						noCityNode.put("isParent", false);
						treeArray.add(noCityNode);
					}
				}
			}

			// 4.
			// ------------------------------------------------------------------------
			// 把市节点换成 详细信息PO
			if (null != areaList) {
				List<U001PO> newAreaList = new ArrayList<U001PO>();
				int areaListSize = areaList.size();
				for (int i = 0; i < areaListSize; i++) {
					U001PO areaPO = areaList.get(i);
					int allU001ListSize = u001List.size();
					for (int j = 0; j < allU001ListSize; j++) {
						U001PO tempPO = u001List.get(j);
						if (null != tempPO.getMa004()
								&& null != tempPO.getMa003()
								&& null != tempPO.getMa002()
								&& areaPO.getMa004().equals(tempPO.getMa004())) {							
							newAreaList.add(tempPO);
							break;
						}
					}
				}
				
				// 5. ------------------------------------------
				// 生成区域节点
				// areaList = newAreaList;
				// int newAreaSize = newAreaList.size();
				for (int i = 0; i < newAreaList.size(); i++) {
					U001PO area = newAreaList.get(i);
					JSONObject areaNode = new JSONObject();
					areaNode.put("id", area.getMa020());
					areaNode.put("pId", area.getMa019());
					areaNode.put("name", area.getMa004());
					areaNode.put("isParent", true);
					treeArray.add(areaNode);
				}
				
				int allU001ListSize = u001List.size();
				for (int i = 0; i < allU001ListSize; i++) {
					U001PO tempPO = u001List.get(i);
					if (null == tempPO.getMa004() && null != tempPO.getMa002() && null != tempPO.getMa003()) {
						JSONObject noAreaNode = new JSONObject();
						noAreaNode.put("id", tempPO.getMa001());
						noAreaNode.put("pId", tempPO.getMa019());
						noAreaNode.put("name", tempPO.getMa008() + "---" + tempPO.getMa006());
						noAreaNode.put("isParent", false);
						treeArray.add(noAreaNode);
					}
				}

			}
			
			
			// 6. ------------------------------------------
			// 生成人员(手机)节点
			int allU001ListSize = u001List.size();
			for (int i = 0; i < allU001ListSize; i++) {
				U001PO tempPO = u001List.get(i);
				if (null != tempPO.getMa004() && null != tempPO.getMa002() && null != tempPO.getMa003()) {
					JSONObject phoneNode = new JSONObject();
					phoneNode.put("id", tempPO.getMa001());
					phoneNode.put("pId", tempPO.getMa020());
					String tempName;
					tempName = tempPO.getMa008() + "---" + tempPO.getMa006() ;
					/*if(tempPO.getMa019()!=null && tempPO.getMa019().startsWith("ios")){
						tempName = tempPO.getMa008() + "---" + tempPO.getMa006() + "(ios)";
					}else
						tempName = tempPO.getMa008() + "---" + tempPO.getMa006() + "(android)";*/
					phoneNode.put("name", tempName);
					phoneNode.put("isParent", false);
					treeArray.add(phoneNode);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return treeArray;
	}

	/*
	 * private JSONArray doInitTreeByArea(List<U001PO> u001list) {
	 * 
	 * // 先给U001List生成两个id,用于生成树时的主键id List<U001PO> haveAllIDOfU001List =
	 * setAllIDToU001List(u001list);
	 * 
	 * 
	 * // 返回的树形结构数组 JSONArray treeArray = new JSONArray();
	 * 
	 * List<U001PO> provinceList = null; List<U001PO> cityList = null;
	 * List<U001PO> areaList = null;
	 * 
	 * try { provinceList = s002Service.retrieveProvinceList(); // 获取省份 cityList
	 * = s002Service.retrieveCityList(); // 获取市 areaList =
	 * s002Service.retrieveAreaList(); // 获取区 // 生成作为父节点的id
	 * if(null!=provinceList){ provinceList = setTreeId(provinceList); } //
	 * 生成作为父节点的id if(null!=cityList){ cityList = setTreeId(cityList); } //
	 * 生成作为父节点的id if(null!=areaList){ areaList = setTreeId(areaList); } //
	 * ------------------------------------------------------------------------
	 * // 1. 去掉没有省份的U001PO List<U001PO> haveProvinceList = new
	 * ArrayList<U001PO>(); // 省份的其他 JSONObject noProvinceNode = new
	 * JSONObject(); noProvinceNode.put("id","noProvince");
	 * noProvinceNode.put("pId",""); noProvinceNode.put("name","其他");
	 * noProvinceNode.put("isParent", true);
	 * 
	 * int allU001ListSize = haveAllIDOfU001List.size(); for(int
	 * i=0;i<allU001ListSize;i++){ U001PO tempPO = haveAllIDOfU001List.get(i);
	 * String tempProName = tempPO.getMa002(); // 省份为空,则把他放到省份其他节点下
	 * if(null==tempProName||"".equals(tempProName)){ JSONObject noProNameNode =
	 * new JSONObject(); noProNameNode.put("id",tempPO.getMa001());
	 * noProNameNode.put("pId","noProvince");
	 * noProNameNode.put("name",tempPO.getMa008() + "---" + tempPO.getMa006());
	 * noProNameNode.put("isParent", false); treeArray.add(noProNameNode);
	 * continue; }
	 * 
	 * // 有省份的po保存起来 haveProvinceList.add(tempPO); }
	 * 
	 * if(haveProvinceList.size()==0){ haveProvinceList = haveAllIDOfU001List; }
	 * 
	 * //
	 * ------------------------------------------------------------------------
	 * // 2. 取得有省份的节点 if(null != provinceList){
	 * 
	 * int provinceListSize = provinceList.size(); for(int
	 * i=0;i<provinceListSize;i++){
	 * 
	 * U001PO proPO = provinceList.get(i); // 省份名称 String proName =
	 * proPO.getMa002(); JSONObject provinceNode = new JSONObject();
	 * provinceNode.put("id",proPO.getMa001()); provinceNode.put("pId","");
	 * provinceNode.put("name",proName); provinceNode.put("isParent", true);
	 * treeArray.add(provinceNode);
	 * 
	 * int haveProvinceListSize = haveProvinceList.size(); for(int
	 * j=0;j<haveProvinceListSize;j++){ U001PO tempPO = haveProvinceList.get(i);
	 * 
	 * String tempProName = tempPO.getMa002(); // 省份为空,则把他放到省份其他节点下
	 * if(null==tempProName||"".equals(tempProName)){ JSONObject noProNameNode =
	 * new JSONObject(); noProNameNode.put("id",tempPO.getMa001());
	 * noProNameNode.put("pId","noProvince");
	 * noProNameNode.put("name",tempPO.getMa008() + "---" + tempPO.getMa006());
	 * noProNameNode.put("isParent", false); treeArray.add(noProNameNode);
	 * continue; } }
	 * 
	 * } // 加入没有省份的JSON treeArray.add(noProvinceNode);
	 * ///------------------------------------------------------ // 有省份,有市的节点 //
	 * int provinceListSize = provinceList.size(); for(int
	 * i=0;i<provinceListSize;i++){ U001PO proPO = provinceList.get(i); // 省份名称
	 * String proName = proPO.getMa002(); // ------------------- // 有市的节点
	 * if(null !=cityList){ int cityListSzie = cityList.size(); for(int
	 * j=0;j<cityListSzie;j++){ U001PO cityPO = cityList.get(i); String cityPID
	 * = cityPO.getMa001();
	 * 
	 * int noHaveCitySize = haveProvinceList.size(); for(int
	 * k=0;k<noHaveCitySize;k++){ U001PO tempPO = haveProvinceList.get(j);
	 * if(proName.equals(tempPO.getMa002()) && null!= tempPO.getMa003()){
	 * JSONObject otherProvinceNode = new JSONObject();
	 * otherProvinceNode.put("id",tempPO.getMa019());
	 * otherProvinceNode.put("pId",proPO.getMa001());
	 * otherProvinceNode.put("name",tempPO.getMa003());
	 * otherProvinceNode.put("isParent", true);
	 * treeArray.add(otherProvinceNode); } } } }
	 * 
	 * 
	 * } ///------------------------------------------------------ // 有省份,没有市的节点
	 * // int provinceListSize = provinceList.size(); for(int
	 * i=0;i<provinceListSize;i++){ U001PO proPO = provinceList.get(i); // 省份名称
	 * String proName = proPO.getMa002(); int noHaveCitySize =
	 * haveProvinceList.size(); for(int j=0;j<noHaveCitySize;j++){ U001PO tempPO
	 * = haveProvinceList.get(j); if(proName.equals(tempPO.getMa002()) &&
	 * (null== tempPO.getMa003()||"".equals(tempPO.getMa003()))){ JSONObject
	 * otherProvinceNode = new JSONObject();
	 * otherProvinceNode.put("id",tempPO.getMa019());
	 * otherProvinceNode.put("pId",proPO.getMa001());
	 * otherProvinceNode.put("name",tempPO.getMa008() + "---" +
	 * tempPO.getMa006()); otherProvinceNode.put("isParent", false);
	 * treeArray.add(otherProvinceNode); } } }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return treeArray; }
	 */

	/**
	 * 给人员表list 生成 两个id,用于树形结构的主键
	 * 
	 * @param u001list
	 * @return
	 */
	private List<U001PO> setAllIDToU001List(List<U001PO> u001List) {

		List<U001PO> returnU001List = new ArrayList<U001PO>();
		int listSize = u001List.size();
		for (int i = 0; i < listSize; i++) {
			U001PO u001PO = u001List.get(i);
			u001PO.setMa019(UUIDGenerater.getUUID());
			u001PO.setMa020(UUIDGenerater.getUUID());
			returnU001List.add(u001PO);
		}
		return returnU001List;
	}

	/**
	 * 读取u001表中记录, 找出 省
	 * 
	 * 找出不同的省份时,只进行了一次比较,
	 * 
	 * 因此要求查询语句是排好序的
	 * 
	 * @return
	 */
	private Map<String, List<U001PO>> getProvinceMap(List<U001PO> u001list) {

		int u001ListSize = u001list.size();
		List<U001PO> provinceList = new ArrayList<U001PO>();
		List<U001PO> provinceNotNameList = new ArrayList<U001PO>();
		String beforeProvinceName = "";
		for (int i = 0; i < u001ListSize; i++) {
			U001PO tempPO = u001list.get(i);
			// 当前省份名称
			String currProvinceName = tempPO.getMa002();
			if (null == currProvinceName || "".equals(currProvinceName)) {
				// 其他
				provinceNotNameList.add(tempPO);
				continue;
			}

			if (beforeProvinceName == currProvinceName) {
				continue;
			}
			beforeProvinceName = currProvinceName;
			provinceList.add(tempPO);

		}
		Map<String, List<U001PO>> provinceMap = new HashMap<String, List<U001PO>>();
		provinceMap.put("provinceList", provinceList);
		provinceMap.put("provinceNoNameList", provinceNotNameList);

		return provinceMap;
	}

	/**
	 * 读取u001表中记录, 找出 市
	 * 
	 * 找出不同的市时,只进行了一次比较,
	 * 
	 * 因此要求查询语句是排好序的
	 * 
	 * @return
	 */
	private Map<String, Object> getCityMap(List<U001PO> u001list,
			List<U001PO> provinceList) {

		Map<String, List<U001PO>> cityMap = new HashMap<String, List<U001PO>>();
		Map<String, List<U001PO>> haveProNoCityMap = new HashMap<String, List<U001PO>>();

		int provinceListSize = provinceList.size();
		for (int i = 0; i < provinceListSize; i++) {
			U001PO provincePO = u001list.get(i);
			// 当前省份
			String currProvinceName = provincePO.getMa002();
			// 有省份,没有市名称的List
			List<U001PO> noCityNameList = new ArrayList<U001PO>();
			// 有省份,有市名称的List
			List<U001PO> cityNameList = new ArrayList<U001PO>();
			int u001ListSize = u001list.size();
			for (int j = 0; j < u001ListSize; j++) {
				U001PO tempPO = u001list.get(i);
				String tempProvinceName = tempPO.getMa002();
				if (null == tempProvinceName || "".equals(tempProvinceName)) {
					continue;
				}
				// 有省份,但是没有 市名称
				if (null == tempPO.getMa003() || "".equals(tempPO.getMa003())) {
					// 作为城市的id
					tempPO.setMa020(UUIDGenerater.getUUID());
					noCityNameList.add(tempPO);
					continue;
				}

				// 找到省份
				if (currProvinceName.equals(tempProvinceName)) {
					// 作为城市的id
					tempPO.setMa020(UUIDGenerater.getUUID());
					cityNameList.add(tempPO);
				}
			}
			// 有省份,没有市名称的map
			haveProNoCityMap.put("otherCity", noCityNameList);
			// 有省份,有市名称的map
			cityMap.put(currProvinceName, cityNameList);
		}

		Map<String, Object> returnmap = new HashMap<String, Object>();

		// 省
		returnmap.put("provinceList", provinceList);
		// 市
		returnmap.put("nomalCityMap", cityMap);
		// 无市
		returnmap.put("noCityMap", haveProNoCityMap);

		return returnmap;
	}

	// ---------------------- setter and getter
	// method--------------------------------------
	public String getUserTree() {
		return userTree;
	}

	public void setUserTree(String userTree) {
		this.userTree = userTree;
	}

	public IS002Service getS002Service() {
		return s002Service;
	}

	public void setS002Service(IS002Service s002Service) {
		this.s002Service = s002Service;
	}

	public U001PO getU001PO() {
		return u001PO;
	}

	public void setU001PO(U001PO u001po) {
		u001PO = u001po;
	}

	public S002PO getS002PO() {
		return s002PO;
	}

	public void setS002PO(S002PO s002po) {
		s002PO = s002po;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
