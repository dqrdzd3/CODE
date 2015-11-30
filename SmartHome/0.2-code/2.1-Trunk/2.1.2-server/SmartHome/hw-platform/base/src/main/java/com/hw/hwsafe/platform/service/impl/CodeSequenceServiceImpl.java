/**
 * 文件名：CodeSequenceServiceImpl.java
 *
 * 版本信息：1.0
 * 日期：2012-6-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.dao.ICodeSequenceDao;
import com.hw.hwsafe.platform.service.ICodeSequenceService;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：CodeSequenceServiceImpl
 * 类描述：CodeSequenceServiceImpl实现类
 * 创建人：马宁
 * 创建时间：2012-11-12 下午2:40:22
 * 修改人：马宁
 * 修改时间：2012-11-12 下午2:40:22
 * 修改备注：
 * @version 
 *
 */
public class CodeSequenceServiceImpl implements ICodeSequenceService {

	@Autowired
	private ICodeSequenceDao codeSequenceDao;
	
	@Override
	public String retrieveSequence(String code, int digits)
			throws Exception {
		
		String sequence = getSequenceAndProcessingService(code);
		
		if(sequence.length() > digits){
			throw new RuntimeException("指定序列号位数过短");
		}
		
		return convertSequence(sequence, digits);
	}
	
	// ---------- private methods ------------
	
	/**
	 * 
	 * 函 数 名：getSequenceAndProcessingService
	 * 功能描述：获取sequence并且处理业务
	 * @param code 代码 
	 * 创建人：马宁
	 * 创建时间：2012-11-12 下午5:06:00
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	private String getSequenceAndProcessingService(String code) throws Exception{
		Integer sequence = codeSequenceDao.retrieveSequenceByCode(code);
		
		if(sequence == null){
			codeSequenceDao.addNewSequence(code);
			sequence = 1;
		}else{
			codeSequenceDao.increaseSequence(code);
			sequence++;
		}
		return sequence.toString();
	}
	
	/**
	 * 
	 * 函 数 名：convertSequence
	 * 功能描述：转换sequence
	 * @param sequence 数据库获取的sequence字符串
	 * @param digits 序列号位数
	 * @return 转换过后的sequence字符串 
	 * 创建人：马宁
	 * 创建时间：2012-11-12 下午3:16:18
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	private String convertSequence(String sequence, int digits){
		int fillZeroCount = digits - sequence.length();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < fillZeroCount; i++){
			sb.append(0);
		}
		sb.append(sequence);
		return sb.toString();
	}

}
