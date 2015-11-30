package com.hw.hwsafe.attachment.service;

import java.util.List;

import com.hw.hwsafe.attachment.pojo.C004PO;

public interface IAttachmentService {

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
	 * 下载数据
	 * 
	 * @param c004
	 * @throws Exception
	 */
	public C004PO getC004PO(String id) throws Exception;

	public List<C004PO> getC004List(String id, String MA008);

	public C004PO getC004POTEMP(String id) throws Exception;

	public void deleteC004(String id) throws Exception;

	public void deleteC004TEMP(String str);
	
	void insertC004s(String[] c004ids, String MA001) throws Exception;
	
	/**
	 * 
	 * 删除一个主表的所有的附件
	 * @param ma002
	 * @throws Exception          
	 * @author 陈浙东
	 * @create_time 2013-5-8 上午11:15:53
	 */
	public void deleteAllC004(String ma002) throws Exception;
}
