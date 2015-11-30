package com.hw.hwsafe.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.dao.CodeValueDao;
import com.hw.hwsafe.platform.pojo.CodeValuePO;
import com.hw.hwsafe.platform.service.CodeValueService;

/**
 * 
 * 
 * 项目名称：hwsafe
 * 类名称：CodeValueServiceImpl
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-6-7 上午10:43:38
 * 修改人：李玉梅
 * 修改时间：2012-6-7 上午10:43:38
 * 修改备注：
 * @version 
 *
 */
public class CodeValueServiceImpl extends BaseServiceImpl implements CodeValueService{
	
	@Autowired	
	private CodeValueDao codeValueDao;

	@Override
	public List<CodeValuePO> retrieveCodeByYalx() throws Exception {
		return codeValueDao.retrieveCodeByYalx();
	}

	@Override
	public List<CodeValuePO> retrieveCodeByYalxZl(String parent)
			throws Exception {
		return codeValueDao.retrieveCodeByYalxZl(parent);
	}

	@Override
	public List<CodeValuePO> retrieveCodeByYazl() throws Exception {
		return codeValueDao.retrieveCodeByYazl();
	}

	@Override
	public List<CodeValuePO> retrieveCodeByCaseType() throws Exception {
		return codeValueDao.retrieveCodeByCaseType();
	}

	@Override
	public List<CodeValuePO> retrieveCodeBySghy() throws Exception {
		return codeValueDao.retrieveCodeBySghy();
	}
	
	@Override
	public List<CodeValuePO> retrieveCodeBySslb() throws Exception {
		return codeValueDao.retrieveCodeBySslb();
	}

	@Override
	public List<CodeValuePO> retrieveCodeByType(String type){
		List<CodeValuePO> list;
		try{
			list= codeValueDao.retrieveCodeByType(type);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return list;
	}

}
