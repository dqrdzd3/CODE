package com.hw.weidou.decode.base;

import com.hw.weidou.po.FrameContent;

/**
 * 解码基类，凡是集成它的都是deamon线程
 * 
 * @author 曾凡
 * @time 2014年10月11日 下午1:35:45
 */
public interface WeidouDecoder {

	/**
	 * 开始预处理
	 * 
	 * @author 曾凡
	 * @time 2014年10月11日 下午2:28:03
	 */
	public void startPreΤreatment();

	/**
	 * 发送合法的数据内容至下一个处理节点
	 * 
	 * @author 曾凡
	 * @time 2014年10月11日 下午1:46:18
	 */
	public void sent2NextProc(FrameContent content);

	/**
	 * 解码
	 * 
	 * @author 曾凡
	 * @param content
	 * @time 2014年10月11日 下午1:43:12
	 */
	public void decode(FrameContent content);

	/**
	 * 校验解码数据
	 * 
	 * @author 曾凡
	 * @param content
	 * @time 2014年10月11日 下午1:43:23
	 */
	public void validate(FrameContent content);

}
