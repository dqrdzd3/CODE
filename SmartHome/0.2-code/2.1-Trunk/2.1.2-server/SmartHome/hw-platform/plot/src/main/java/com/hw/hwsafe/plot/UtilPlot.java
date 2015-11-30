package com.hw.hwsafe.plot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class UtilPlot {
	
	private UtilPlot() {
	}

	/**
	 * 饼图纵表
	 * @param list
	 */
	public static HashMap<String, String> setColPieData(
			List<HashMap<String, Object>> list) {
		
		HashMap<String, String> jstr = new HashMap<String, String>();
		
		if (list.isEmpty()) {
			jstr.put("data", "");
		}else{
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			for (HashMap<String, Object> map : list) {
				sb.append("{\"label\":\"" + map.get("CD") + "\"," + "\"value\":\""
						+ map.get("CV") + "\"},");
			}
			jstr.put("conum", "" + list.size());
			jstr.put("data", sb.substring(0, sb.length() - 1) + "]");
		}
		
		return jstr;
	}
	
	/**
	 * 饼图横表
	 * @param map
	 */
	public static HashMap<String, String> setRowPieData(
			HashMap<String, Object> map) {
		
		HashMap<String, String> jstr = new HashMap<String, String>();
		
		if (map.isEmpty()) {
			jstr.put("data", "");
		}else{
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			Iterator itor = map.entrySet().iterator();
			int i = 0;
			while (itor.hasNext()) {
				Map.Entry e = (Map.Entry) itor.next();
				sb.append("{\"label\":\"" + e.getKey() + "\"," + "\"value\":\""
						+ e.getValue() + "\"},");
				i++;
			}
			jstr.put("conum", "" + i);
			jstr.put("data", sb.substring(0, sb.length() - 1) + "]");
		}
		
		return jstr;
	}
	
	/**
	 * 线图纵表  描述集合  数据集合.  下边setSColLineData为扩展方法  可用下边只传入一个集合按既定规则查询
	 * @param categories
	 * @param seriesList
	 */
	public static HashMap<String, String> setColLineData(
			List<String> categories, List<HashMap<String, Object>> seriesList) {
		HashMap<String, String> jstr = new HashMap<String, String>();
		// "categories":[{"category":[{"label":"2001"},{"label":"2002"},{"label":"2003"},{"label":"2004"},{"label":"2005"}]}]
		StringBuffer sb1 = new StringBuffer();
		sb1.append("[{\"category\":[");
		for (String str : categories) {
			sb1.append("{\"label\":\"" + str + "\"},");
		}
		jstr.put("category", sb1.substring(0, sb1.length() - 1) + "]}]");
		// "dataset" : [ {"seriesname" : "2006","data" : [ {"value" :
		// "27400"}]}]
		// 纵表做滤重处理再拼JSON串
		HashMap<String, LinePo> pomap = new HashMap<String, LinePo>();
		if (seriesList.size() != 0) {
			for (HashMap<String, Object> map : seriesList) {
				if (pomap.get(map.get("lid")) == null) {
					LinePo po = new LinePo();
					po.setLid((String) map.get("lid"));
					po.setSubCaption((String) map.get("S"));
					List<String> vl = new ArrayList<String>();
					vl.add((String) map.get("Y"));
					po.setVlist(vl);
					pomap.put((String) map.get("lid"), po);
				} else {
					pomap.get(map.get("lid")).getVlist()
							.add((String) map.get("Y"));
				}
			}
		}
		Iterator itor = pomap.entrySet().iterator();
		StringBuffer sb2 = new StringBuffer();
		sb2.append("[");
		while (itor.hasNext()) {
			Map.Entry e = (Entry) itor.next();
			LinePo po = (LinePo) e.getValue();
			String vstrs = "";
			sb2.append("{\"seriesname\":" + "\"" + po.getSubCaption()
					+ "\",\"data\":[");
			for (String str : po.getVlist()) {
				vstrs += "{\"value\":\"" + str + "\"},";
			}
			sb2.append(vstrs.substring(0, vstrs.length() - 1) + "]},");
		}
		jstr.put("dataset", sb2.substring(0, sb2.length() - 1) + "]");
		return jstr;
	}
	
	/**
	 * 线图纵表   上一个方法的扩展.可选择第一个方法 传两个集合  也可以按既定规则传入一个数据集合
	 * @param categories
	 * @param seriesList
	 */
	public static HashMap<String, String> setSColLineData(
			List<HashMap<String, Object>> seriesList) {
		HashMap<String, String> jstr = new HashMap<String, String>();
		// "categories":[{"category":[{"label":"2001"},{"label":"2002"},{"label":"2003"},{"label":"2004"},{"label":"2005"}]}]
		StringBuffer sb1 = new StringBuffer();
		sb1.append("[{\"category\":[");
		// "dataset" : [ {"seriesname" : "2006","data" : [ {"value" :
		// "27400"}]}]
		// 纵表做滤重处理再拼JSON串
		HashMap<String, LinePo> pomap = new HashMap<String, LinePo>();
		if (seriesList.size() != 0) {
			for (HashMap<String, Object> map : seriesList) {
				if (pomap.get(map.get("lid")) == null) {
					LinePo po = new LinePo();
					po.setLid((String) map.get("lid"));
					po.setSubCaption((String) map.get("S"));
					List<HashMap<String, String>> vl = new ArrayList<HashMap<String, String>>();
					HashMap<String, String> nmap = new HashMap<String, String>();
					nmap.put("X", (String) map.get("X"));
					nmap.put("Y", (String) map.get("Y"));
					vl.add(nmap);
					po.setMlist(vl);
					pomap.put((String) map.get("lid"), po);
				} else {
					HashMap<String, String> kmap = new HashMap<String, String>();
					kmap.put("X", (String) map.get("X"));
					kmap.put("Y", (String) map.get("Y"));
					pomap.get(map.get("lid")).getMlist().add(kmap);
				}
			}
		}
		Iterator itor = pomap.entrySet().iterator();
		StringBuffer sb2 = new StringBuffer();
		sb2.append("[");
		int state = 1;
		while (itor.hasNext()) {
			Map.Entry e = (Entry) itor.next();
			LinePo po = (LinePo) e.getValue();
			String vstrs = "";
			sb2.append("{\"seriesname\":" + "\"" + po.getSubCaption()
					+ "\",\"data\":[");
			for (HashMap<String, String> lmap : po.getMlist()) {
				vstrs += "{\"value\":\"" + lmap.get("Y") + "\"},";
			}
			if (state == 1) {
				for (HashMap<String, String> lmap : po.getMlist()) {
					sb1.append("{\"label\":\"" + lmap.get("X") + "\"},");
				}
				state = 0;
			}
			sb2.append(vstrs.substring(0, vstrs.length() - 1) + "]},");
		}
		jstr.put("category", sb1.substring(0, sb1.length() - 1) + "]}]");
		jstr.put("dataset", sb2.substring(0, sb2.length() - 1) + "]");
		return jstr;
	}
	
	/**
	 * 线图横表
	 * @param list
	 */
	public static HashMap<String, String> setRowLineData(
			List<HashMap<String, Object>> list) {
		HashMap<String, String> jstr = new HashMap<String, String>();
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		sb1.append("[{\"category\":[");
		sb2.append("[");
		// 横表直接拼接
		int i = 1;
		Set set = null;
		for (HashMap<String, Object> map : list) {
			if (i == 1) {// 第一次取X轴
				set = map.keySet();
				for (Object obj : set) {
					if (!obj.equals("code")) {
						sb1.append("{\"label\":\"" + obj + "\"},");
					}
				}
				i = 0;
			}
			// 根据现有SET集合取对应顺序数据
			sb2.append("{\"seriesname\":" + "\"" + map.get("code")
					+ "\",\"data\":[");
			String vstrs = "";
			for (Object obj : set) {
				if (!obj.equals("code")) {
					vstrs += "{\"value\":\"" + map.get(obj) + "\"},";
				}
			}
			sb2.append(vstrs.substring(0, vstrs.length() - 1) + "]},");
		}
		jstr.put("category", sb1.substring(0, sb1.length() - 1) + "]}]");
		jstr.put("dataset", sb2.substring(0, sb2.length() - 1) + "]");
		return jstr;
	}
}
