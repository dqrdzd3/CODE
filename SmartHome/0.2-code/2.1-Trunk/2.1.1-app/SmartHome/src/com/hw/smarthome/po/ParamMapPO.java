package com.hw.smarthome.po;

import java.io.Serializable;
import java.util.Map;
/**
 */
public class ParamMapPO implements Serializable{

	private static final long serialVersionUID = -5730386993560733395L;
	private Map<String, String> paramMap;
	public Map<String, String> getParamMap() {
		return paramMap;
	}
	public void setParamMap(Map<String, String> paramMap) {
		this.paramMap = paramMap;
	}
	

}
