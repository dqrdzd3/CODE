package com.hw.hwsafe.corpinfo.service;

import java.util.List;

import com.hw.hwsafe.corpinfo.pojo.B009PO;
import com.hw.hwsafe.platform.service.IBaseService;

public interface IB009Service extends IBaseService {
	/**
	 * @Title: retrieveB009ByID
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b009ID
	 * @param @return
	 * @param @throws Exception
	 * @return B009PO
	 * @throws
	 */
	public B009PO retrieveB009ByID(String b009ID) throws Exception;
	
	/**
	 * 
	 * @Title: retrieveB009ByPO
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b009PO
	 * @param @return
	 * @param @throws Exception
	 * @return List<B009PO>
	 * @throws
	 */
	
	public List<B009PO> retrieveB009ByPO(B009PO b009PO) throws Exception;
	
	/**
	 * 
	 * @Title: insertB009
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b009PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void insertB009(B009PO b009PO) throws Exception;
	
	
	/**
	 * 
	 * @Title: updateB009
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b009PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void updateB009(B009PO b009PO) throws Exception;
	
	/**
	 * 
	 * @Title: retrieveB009BywxyID
	 * @Description: TODO 
	 * 作者：diaohaigang
	 * @param @param wxyID
	 * @param @return
	 * @param @throws Exception
	 * @return B009PO
	 * @throws
	 */
	public B009PO retrieveB009BywxyID(String wxyID) throws Exception;
	/**
	 * 
	 * isExist(记录是否存在)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public boolean isExist(String id)throws Exception;
}
