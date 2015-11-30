package com.hw.hwsafe.corpinfo.service;

import java.util.List;

import com.hw.hwsafe.corpinfo.pojo.B018PO;
import com.hw.hwsafe.platform.service.IBaseService;

public interface IB018Service extends IBaseService {
	
	/**
	 * 
	 * @Title: retrieveB018ByID
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b018ID
	 * @param @return
	 * @param @throws Exception
	 * @return B018PO
	 * @throws
	 */
	public B018PO retrieveB018ByID(String b018ID) throws Exception;
	
	/**
	 * 
	 * @Title: retrieveB018ByPO
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b018PO
	 * @param @return
	 * @param @throws Exception
	 * @return List<B018PO>
	 * @throws
	 */
	
	public List<B018PO> retrieveB018ByPO(B018PO b018PO) throws Exception;
	
	/**
	 * 
	 * @Title: insertB018
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b018PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void insertB018(B018PO b018PO) throws Exception;
	
	
	/**
	 * 
	 * @Title: updateB018
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b018PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void updateB018(B018PO b018PO) throws Exception;
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

