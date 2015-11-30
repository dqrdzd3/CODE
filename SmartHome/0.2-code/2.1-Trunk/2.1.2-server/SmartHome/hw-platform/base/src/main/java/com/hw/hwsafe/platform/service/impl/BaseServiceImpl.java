package com.hw.hwsafe.platform.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hw.hwsafe.platform.dao.IBaseDao;
import com.hw.hwsafe.platform.dao.ICommonDao;
import com.hw.hwsafe.platform.message.UserMessageData;
import com.hw.hwsafe.platform.service.IBaseService;
import com.hw.hwsafe.platform.userinfo.SessionUtil;
import com.hw.hwsafe.platform.util.Page;

/**
 * 
 * 项目名称：framework
 * 类名称：AbstractBaseService
 * 类描述：
 * 创建人：孟繁波
 * 创建时间：2012-6-30 下午2:02:26
 * 修改人：孟繁波
 * 修改时间：2012-6-30 下午2:02:26
 * 修改备注：
 * @version 
 * 
 */
@Service
public class BaseServiceImpl implements IBaseService {
	
	@Autowired
	private ICommonDao iCommonDao;
	
	@Autowired
	private IBaseDao ibaseDao;
	
	protected UserMessageData message = new UserMessageData();
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		// 获取dao
		String daoClassName = "I" + getClass().getSimpleName().replace("ServiceImpl", "") + "Dao";
		Object dao = null;
		for (Field f : getClass().getDeclaredFields()) {
			if (f.getType().getSimpleName().equals(daoClassName)) {
				f.setAccessible(true);
				dao = f.get(this);
				f.setAccessible(false);
				break;
			}
		}
		if (dao == null) return new ArrayList();
		
		// 执行查询
		return ((IBaseDao) dao).retrieveByPage(map);
		
	}

	public int delBatch(Map map) throws Exception {
		return iCommonDao.delBatch(map);
	}
	
	public Page listByPage(Page page, Map map) throws Exception{
		// 获取dao
		String daoClassName = "I" + getClass().getSimpleName().replace("ServiceImpl", "") + "Dao";
		IBaseDao dao = null;
		for (Field f : getClass().getDeclaredFields()) {
			if (f.getType().getSimpleName().equals(daoClassName)) {
				f.setAccessible(true);
				dao = (IBaseDao)f.get(this);
				f.setAccessible(false);
				break;
			}
		}
		
		if(dao != null){
			String curPage = page.getCurPage();
			//计算当前页是从第几条到第几条
			String begin = String.valueOf((Integer.parseInt(curPage) - 1 ) * page.getNumber() + 1);
			String end = String.valueOf(Integer.parseInt(curPage) * page.getNumber());
			
			page.setBegin(begin);
			page.setEnd(end);
	
			map.put("page", page);
			if (map.get("ORGAN_UUID") == null)
				map.put("ORGAN_UUID",SessionUtil.getOrgId());
	 		//查询总条数
			
			map.put("selectCount", true);
			String totalNum = dao.getTotal(map);
			page.setTotalNum(totalNum);
			
			
			//计算总页数
			if(Integer.parseInt(totalNum) % page.getNumber() == 0){
				page.setTotalPage(String.valueOf(Integer.parseInt(totalNum)/page.getNumber()));
			}else{
				page.setTotalPage(String.valueOf(Integer.parseInt(totalNum)/page.getNumber() + 1));
			}
			map.put("selectCount", null);
			List result = dao.listByPage(map);
			page.setResult(result);
		}
		return page;
	}
	
}
