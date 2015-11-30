package com.hw.hwsafe.utils.report;

import java.util.List;
import java.util.Map;

public class GridXmlBean {

	public static final String REPORT_HEAD = "<report>\n";
	public static final String REPORT_TAIL = "</report>\n";

	private List<Map<Object, Object>> xml;

	private Map<Object, Object> grparam;

	public List<Map<Object, Object>> getXml() {
		return xml;
	}

	public void setXml(List<Map<Object, Object>> xml) {
		this.xml = xml;
	}

	public Map<Object, Object> getGrparam() {
		return grparam;
	}

	public void setGrparam(Map<Object, Object> grparam) {
		this.grparam = grparam;
	}

}
