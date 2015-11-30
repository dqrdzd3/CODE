package com.hw.hwsafe.platform.pojo;

import java.io.Serializable;

/**
 * 
 * 
 * 项目名称：hwsafe
 * 类名称：CodeValuePO
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-6-7 上午10:34:55
 * 修改人：李玉梅
 * 修改时间：2012-6-7 上午10:34:55
 * 修改备注：
 * @version 
 *
 */
public class CodeValuePO implements Serializable{

	private static final long serialVersionUID = -3007263065863214797L;
	
	/**
	 * 类别
	 */
	private String type;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 值
	 */
	private String value;
	/**
	 * 父
	 */
	private String parent;
	/**
	 * 排序
	 */
	private String findex;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getFindex() {
		return findex;
	}

	public void setFindex(String findex) {
		this.findex = findex;
	}

}
