/**
 * @title Page.java
 * @package com.hw.hwsafe.safety.action
 * @author 陈浙东
 * @create_time 2013-5-20 下午2:54:00
 * <p>Copyright 河南汉威电子股份有限公司软件部 Corporation 2013</p>
 */
package com.hw.hwsafe.platform.util;

import java.util.List;

/**
 * <p>
 * 这是写类描述，如果有换行则用<br>
 * </p>
 */
public class Page {
	public static int NUMBER = 5;
	private String curPage;
	private String totalPage;
	private String totalNum;
	private String begin;
	private String end;
	private List result;
	private int number;

	public String getCurPage() {
		return curPage;
	}

	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}

	public String getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}

	public String getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	
	
}
