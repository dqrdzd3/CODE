package com.hw.smarthome.ui.weather.xml.service;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;

import com.hw.smarthome.ui.weather.po.WeatherPo;
import com.hw.smarthome.ui.weather.xml.handler.XMLContentHandler;

/**
 * <p>
 * 专门用来调用XMLContentHandler类的对象
 * </p>
 * 
 * @author 曾凡
 * @date 2012-3-7下午4:28:53
 */
public class SAXPersonService {
	
	public static WeatherPo readXML(InputSource in)
			throws Exception {
		SAXParserFactory factory = SAXParserFactory
				.newInstance();
		/**
		 * 创建一个解析器
		 */
		SAXParser parse = factory.newSAXParser();
		XMLContentHandler handler = new XMLContentHandler();
		/**
		 * 将输入流传入到自定义的handler里
		 */
		parse.parse(in, handler);
		return handler.getWeather();

	}
}
