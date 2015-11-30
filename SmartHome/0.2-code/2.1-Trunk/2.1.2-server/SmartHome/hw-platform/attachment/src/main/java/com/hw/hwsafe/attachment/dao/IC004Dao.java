package com.hw.hwsafe.attachment.dao;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.attachment.pojo.C004PO;

public interface IC004Dao {

	/**
	 * 插入上传附件数据
	 * 
	 * @param c004
	 * @throws Exception
	 */
	public void insertC004(C004PO c004) throws Exception;

	/**
	 * 插入上传附件临时数据，定期清除
	 * 
	 * @param c004
	 * @throws Exception
	 */
	public void insertC004TEMP(C004PO c004) throws Exception;
	
	/**
	 * 查询数据
	 * 
	 * @param id
	 * @throws Exception
	 */
	public C004PO getC004(Map map) throws Exception;
	
	/**
	 * 查询临时数据
	 * 
	 * @param id
	 * @throws Exception
	 */
	public C004PO getC004TEMP(Map map) throws Exception;

	public List<C004PO> getC004List(Map	map);
	
	/**
	 * 删除已经上传的所有附件、图片
	 * @param id
	 * @throws Exception
	 */
	public void delAllC004(String id)throws Exception;
	
	
	public void delC004(String id)throws Exception;

	public void delC004Temp(String id);
}
