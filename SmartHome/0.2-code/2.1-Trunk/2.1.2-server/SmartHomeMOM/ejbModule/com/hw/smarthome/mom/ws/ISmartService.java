package com.hw.smarthome.mom.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "ISmartService", targetNamespace = "http://ws.mom.smarthome.hw.com/")
// 命名按照包名的顺序 倒着
@SOAPBinding(style = SOAPBinding.Style.RPC)

public interface ISmartService {
	// @WebMethod
	// public String getDAqPo();
	/**
	 * 返回当前信息
	 * 
	 * @author 曾凡
	 * @param gprsId
	 * @param driverType
	 * @param driverName
	 * @return
	 * @throws Exception
	 * @time 2015年1月27日 上午10:12:25
	 */
	@WebMethod
	public String retrieveCurStautsByGPRSId(String gprsId,
			String driverType, String driverName)
			throws Exception;

	/**
	 * 返回历史信息                  老的
	 * 
	 * @author 曾凡
	 * @param gprsId
	 * @param media
	 * @param pastHoursOrDays
	 * @param type
	 * @return
	 * @throws Exception
	 * @time 2015年1月27日 上午10:12:42
	 */
	@WebMethod
	public String retrieveHisStautsByGPRSId(String gprsId,
			String driverType, int pastHoursOrDays, int type)
			throws Exception;
	/**
	 * 返回历史信息 PO       新的
	 * 
	 * @author 曾凡
	 * @param gprsId
	 * @param media
	 * @param pastHoursOrDays
	 * @param type
	 * @return
	 * @throws Exception
	 * @time 2015年1月27日 上午10:12:42
	 */
	@WebMethod
	public String retrieveHisStauts(String gprsId,
			String media, int pastHoursOrDays, int type)
			throws Exception;

	/**
	 * 保存数据数据
	 * 
	 * @author 曾凡
	 * @param sensorDetail
	 * @time 2015年1月27日 上午10:12:54
	 */
	@WebMethod
	public void setRealtimeList(String sensorDetail);
}
