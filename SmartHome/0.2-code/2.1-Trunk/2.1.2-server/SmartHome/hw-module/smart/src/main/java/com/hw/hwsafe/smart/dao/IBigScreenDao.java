package com.hw.hwsafe.smart.dao;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.dao.IBaseDao;
import com.hw.hwsafe.smart.pojo.A001PO;
import com.hw.hwsafe.smart.pojo.AdvertisePO;
import com.hw.hwsafe.smart.pojo.BigScreenAuthPO;
import com.hw.hwsafe.smart.pojo.BigScreenPO;
import com.hw.hwsafe.smart.pojo.BusinessPO;
import com.hw.hwsafe.smart.pojo.InfomationPO;
import com.hw.hwsafe.smart.pojo.SceneDispPO;



public interface IBigScreenDao extends IBaseDao {

	
	/**
	 * 通过id获取实例
	 */
	AdvertisePO retrieveAdvertiseById(String ma001) throws Exception;
	/**
	 * 通过id删除实例
	 */
	void deleteAdvertiseById(String ma001) throws Exception;
	/**
	 * 添加实例
	 */
	void insertAdvertise(AdvertisePO a001PO) throws Exception;
	/**
	 * 修改实例
	 */
	void updateAdvertise(AdvertisePO a001PO) throws Exception;
	/**
	 * 通过PO获取实例
	 */
	List<AdvertisePO> retrieveAdvertiseByPO(AdvertisePO a001PO) throws Exception;
	
	//通知栏
	/**
	 * 通过id获取实例
	 */
	InfomationPO retrieveMessageById(String ma001) throws Exception;
	/**
	 * 通过id删除实例
	 */
	void deleteMessageById(String ma001) throws Exception;
	/**
	 * 添加实例
	 */
	void insertMessage(InfomationPO a001PO) throws Exception;
	/**
	 * 通过PO获取实例
	 */
	List<InfomationPO> retrieveMessageByPO(InfomationPO a001PO) throws Exception;
	
	//展示栏
	/**
	 * 添加实例
	 */
	void insertDisp(SceneDispPO a001PO) throws Exception;
	/**
	 * 通过PO获取实例
	 */
	List<SceneDispPO> retrieveDispByPO(SceneDispPO a001PO) throws Exception;
	/**
	 * 通过id删除实例
	 */
	void deleteDispById(String ma001) throws Exception;
	/**
	 * 获得授权列表
	 */
	public List<BigScreenAuthPO>  getBigscreenAuthList(String userid);
	/**
	 * 授权
	 */
	public BigScreenAuthPO  auth(String mac);
	/**
	 * 申请授权
	 */
	public void  applyAuth(BigScreenAuthPO po);
	/**
	 * 大屏列表
	 */
	public List<BigScreenPO>  getBigscreenList(String userid);
	
}
