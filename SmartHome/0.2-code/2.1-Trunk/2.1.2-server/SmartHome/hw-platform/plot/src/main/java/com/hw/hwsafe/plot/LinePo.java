package com.hw.hwsafe.plot;

import java.util.HashMap;
import java.util.List;

public class LinePo {
	private String lid;//ID
	
	private String subCaption;//线描述 
	
	private List<String> vlist;//值
	
	private List<HashMap<String, String>> mlist;

	public String getLid() {
		return lid;
	}

	public void setLid(String lid) {
		this.lid = lid;
	}

	public String getSubCaption() {
		return subCaption;
	}

	public void setSubCaption(String subCaption) {
		this.subCaption = subCaption;
	}

	public List<String> getVlist() {
		return vlist;
	}

	public void setVlist(List<String> vlist) {
		this.vlist = vlist;
	}

	public List<HashMap<String, String>> getMlist() {
		return mlist;
	}

	public void setMlist(List<HashMap<String, String>> mlist) {
		this.mlist = mlist;
	}
	
}
