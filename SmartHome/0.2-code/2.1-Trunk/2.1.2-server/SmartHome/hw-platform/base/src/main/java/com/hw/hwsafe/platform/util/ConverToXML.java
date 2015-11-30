package com.hw.hwsafe.platform.util;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 项目名称：hw-base 类名称：ConverToXML 类描述：把传过来的pop、map对象转换成xml 创建人：刁海港 创建时间：2013-6-20
 * 下午5:20:02
 * 
 * @version
 * 
 */
public class ConverToXML {

	private ConverToXML() {
		// do nothing
	}

	/**
	 * 
	 * DataToXml(转化数据)
	 */
	@SuppressWarnings("rawtypes")
	public static String DataToXml(GridXmlBean xmlbean)
			throws Exception {

		StringBuffer XmlText = new StringBuffer();
		XmlText.append(GridXmlBean.reportHeard);

		if (xmlbean.getXml()!=null&& !xmlbean.getXml().isEmpty()) {
			XmlText.append("<xml>\n");
			for(Map<Object, Object> map : xmlbean.getXml()){
				XmlText.append("<row>");
				for(Entry<Object, Object> entry : map.entrySet()){
					XmlText.append("<" + entry.getKey() + ">"
							+ (entry.getValue() == null ? "" : entry.getValue())
							+ "</" + entry.getKey() + ">");
				}
				XmlText.append("<row>\n");
			}
			
			XmlText.append("</xml>\n");
		}

		if (xmlbean.getGrparam()!=null&&!xmlbean.getGrparam().isEmpty()) {
			XmlText.append("<_grparam>\n");
			for (Iterator iter = xmlbean.getGrparam().entrySet().iterator(); iter
					.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				XmlText.append("<" + entry.getKey() + ">"
						+ (entry.getValue() == null ? "" : entry.getValue())
						+ "</" + entry.getKey() + ">\n");

			}
			XmlText.append("</_grparam>\n");
		}
		XmlText.append(GridXmlBean.reportTail);
		return XmlText.toString();
	}
	
	static void WriterXml(HttpServletResponse response,
			HttpServletRequest request, StringBuffer XmlText) throws Exception {
		response.resetBuffer();
		response.setCharacterEncoding(request.getCharacterEncoding());
		response.setContentType("application/xml");

		PrintWriter pw = response.getWriter();
		pw.print(XmlText.toString());
		pw.close(); // 终止后续不必要内容输出
	}

	/**
	 * 
	 * MapToXml(把Map转化成Xml)
	 */
	@SuppressWarnings("rawtypes")
	public static void MapToXml(Map obj, HttpServletResponse response,
			HttpServletRequest request) throws Exception {

		StringBuffer XmlText = new StringBuffer();
		XmlText.append("<report>\n").append("<_grparam>\n");
		if (!obj.isEmpty()) {
			for (Iterator iter = obj.entrySet().iterator(); iter.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				XmlText.append("<" + entry.getKey() + ">"
						+ (entry.getValue() == null ? "" : entry.getValue())
						+ "</" + entry.getKey() + ">");

			}
		}
		XmlText.append("</_grparam>\n").append("</report>\n");
		WriterXml(response, request, XmlText);
	}

	/**
	 * 
	 * MapToXml(把Map转化成Xml)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static StringBuffer ListToXml(List<Map<Object, Object>> obj,
			StringBuffer XmlText) throws Exception {

		// StringBuffer XmlText = new StringBuffer();
		XmlText.append("<_grparam>\n");
		// .append("<_grparam>\n");
		if (!obj.isEmpty()) {

			for (Map<Object, Object> map : obj) {
				Iterator iter = map.entrySet().iterator();
				while (iter.hasNext()) {

					Map.Entry entry = (Map.Entry) iter.next();
					if (entry.getValue() instanceof List) {
						// ListToXml((List<HashMap<Object,Object>>)entry.getValue(),XmlText);
					} else {
						XmlText.append("<"
								+ entry.getKey()
								+ ">"
								+ (entry.getValue() == null ? "" : entry
										.getValue()) + "</" + entry.getKey()
								+ ">");
					}
				}
				// for(;iter.hasNext();){
				// Map.Entry entry = (Map.Entry)iter.next();
				// if (entry.getValue() instanceof List){
				// //
				// ListToXml((List<HashMap<Object,Object>>)entry.getValue(),XmlText);
				// }
				// else{
				// XmlText.append("<"+entry.getKey()+">"+(entry.getValue()==null?"":entry.getValue())+"</"+entry.getKey()+">");
				// }
				// }
			}
		}
		return XmlText;

	}

	/**
	 * 
	 * MapToXml(把Map转化成Xml)
	 */
	@SuppressWarnings("rawtypes")
	// public static void DataToXml(Map<Object,Object> obj,HttpServletResponse
	// response,HttpServletRequest request)throws Exception{
	//
	// StringBuffer XmlText = new StringBuffer();
	// XmlText.append("<report>\n");
	// // .append("<_grparam>\n");
	// if(!obj.isEmpty()){
	// Iterator iter = obj.entrySet().iterator();
	// while(iter.hasNext()){
	// Map.Entry entry = (Map.Entry)iter.next();
	// if(entry.getValue() instanceof List){
	// // ListToXml((List<HashMap<Object,Object>>) entry.getValue(),XmlText);
	// //
	// }else {
	// if(!XmlText.toString().contains("<_grparam>"))
	// XmlText.append("<"+entry.getKey()+">"+(entry.getValue()==null?"":entry.getValue())+"</"+entry.getKey()+">");
	// }
	//
	// }
	// // for(Iterator iter = obj.entrySet().iterator();iter.hasNext();){
	// // Map.Entry entry = (Map.Entry)iter.next();
	// // if(entry.getValue() instanceof List){
	// // ListToXml((List<HashMap<Object,Object>>) entry.getValue(),XmlText);
	// // //
	// // }else {
	// //
	// //
	// XmlText.append("<"+entry.getKey()+">"+(entry.getValue()==null?"":entry.getValue())+"</"+entry.getKey()+">");
	// // }
	// //
	// // }
	// }
	// // XmlText.append("</_grparam>\n");
	// // XmlText.append("</report>\n");
	// // WriterXml(response,request,XmlText);
	// }
	// @SuppressWarnings("rawtypes")
	// public static void main(String[] args) {
	//
	// StringBuffer XmlText = new StringBuffer();
	// HashMap<String, String> map = new LinkedHashMap<String, String>();
	// map.put("1", "diaohaigang");
	// map.put("2", "小杜");
	// map.put("3", "hahahahahaha");
	// XmlText.append("<report>\n")
	// .append("<_grparam>\n");
	// if(!map.isEmpty()){
	// for(Iterator iter = map.entrySet().iterator();iter.hasNext();){
	// Map.Entry entry = (Map.Entry)iter.next();
	// XmlText.append("<"+entry.getKey()+">"+(entry.getValue()==null?"":entry.getValue())+"</"+entry.getKey()+">");
	//
	// }
	// XmlText.append("</_grparam>\n")
	// .append("</report>\n");
	// System.out.print(XmlText);
	// }
	// }
	//
	private static final String REPORT_TAG = "XXX";
	private static final String ROW_TAG = "SSS";

	public static String List2Xml(List<Map<Object, Object>> maps) {
		StringBuffer sb = new StringBuffer();

		// 构建xml头
		sb.append("<" + REPORT_TAG + ">");
		sb.append("\n");

		sb.append(List2Tag(maps));

		// 构建xml尾
		sb.append("</" + REPORT_TAG + ">");

		return sb.toString();
	}

	private static String List2Tag(List<Map<Object, Object>> maps) {
		StringBuffer sb = new StringBuffer();
		for (Map<Object, Object> map : maps) {
			sb.append("<" + ROW_TAG + ">");
			sb.append("\n");

			sb.append(Map2Tag(map));

			sb.append("\n");
			sb.append("</" + ROW_TAG + ">");
			sb.append("\n");
		}
		return sb.toString();
	}

	private static String Map2Tag(Map<Object, Object> map) {
		StringBuffer sb = new StringBuffer();

		for (Entry<Object, Object> entry : map.entrySet()) {
			Object key = entry.getKey();
			Object value = entry.getValue();
			sb.append("<" + key + ">");
			if (value instanceof List) {
				sb.append(List2Tag((List<Map<Object, Object>>) value));
			} else {
				sb.append(value);
			}
			sb.append("</" + key + ">");
		}

		return sb.toString();
	}

	public static void main(String[] args)throws Exception {
		Map<Object, Object> map1 = new  LinkedHashMap();
		map1.put("keyA", "valueA");
		map1.put("keyB", "valueB");
		Map<Object, Object> map2 = new LinkedHashMap();
		map2.put("keyC", "valueC");
		map2.put("keyD", "valueD");
		List<Map<Object, Object>> subList = new ArrayList<Map<Object, Object>>();
		subList.add(map1);
		subList.add(map2);

		Map<Object, Object> map3 = new LinkedHashMap();
		map3.put("subKey", subList);
		map3.put("keyE", "valueE");

		Map<Object, Object> map4 = new LinkedHashMap();
		map4.put("subKey", subList);

		Map<Object, Object> map5 = new LinkedHashMap();
		map5.put("keyF", "valueF");
		map5.put("keyG", "valueG");

		List<Map<Object, Object>> mylist = new ArrayList<Map<Object, Object>>();
//		mylist.add(map3);
//		mylist.add(map4);
//		mylist.add(map5);
		mylist.add(map1);

		
		GridXmlBean bean = new GridXmlBean();
//		bean.setGrparam(map1);
		bean.setXml(mylist);
		System.out.println(DataToXml(bean));
	}

}
