package com.hw.hwsafe.platform.util;

import java.util.List;
import java.util.Map;

public class GridXmlBean {

		private List<Map<Object, Object>> xml;

		private Map<Object, Object> grparam;
		
		public static final String reportHeard = "<report>\n";
		protected static final String reportTail = "</report>\n";

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
