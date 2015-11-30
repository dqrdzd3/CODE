package com.hw.smarthome.ui.weather.xml.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

import com.hw.smarthome.ui.weather.po.EnvironmentPo;
import com.hw.smarthome.ui.weather.po.WeatherPo;

/**
 * <p>
 * 继承的DefaultHandler实现了4个SAX的Handler(包括ContentHandler)
 * </p>
 * <p>
 * 方法的顺序安装API上的出现顺序进行复写
 * </p>
 * 但是没有实现他的方法，可以选择使用到的方法来复写实现，必须加上@Override来验证。
 * 
 * @author 曾凡
 * @date 2012-3-7上午11:28:11
 */
public class XMLContentHandler extends DefaultHandler {

	private static final String TAG = XMLContentHandler.class
			.getSimpleName();
	private WeatherPo weather;
	private String preTag;
	private boolean isEnd = false;

	/**
	 * 是开始读取XML文档的时候调用的方法
	 */
	@Override
	public void startDocument() throws SAXException {
		weather = new WeatherPo();
	}

	/**
	 * <p>
	 * 开始读取元素<persons xmls:pre="www.baidu.com">
	 * </p>
	 * <p>
	 * uri 命名空间
	 * </p>
	 * <p>
	 * localName 不带命名空间前缀的标签名
	 * </p>
	 * <p>
	 * qName带命名空间前缀的标签名
	 * </p>
	 * <p>
	 * attributes 属性集合
	 * </p>
	 * 
	 * @author 曾凡
	 * @date 2012-3-7 下午3:31:52
	 */
	@Override
	public void startElement(String uri, String localName,
			String qName, Attributes atts) throws SAXException {
		preTag = localName;
		if ("environment".equals(localName)) {
			weather.setEnvironment(new EnvironmentPo());
		}
	}

	/**
	 * <p>
	 * 开始读取元素" <name>ssf</name> <age>123</age>" 注意包括空格
	 * </p>
	 * <p>
	 * char[]专门用来存放字符(回车 tab和名称等)
	 * </p>
	 * <p>
	 * start开始的char下标
	 * </p>
	 * <p>
	 * length结束的char下标
	 * </p>
	 * 
	 * @author 曾凡
	 * @date 2012-3-7 下午3:31:52
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (isEnd) {
			return;
		}
		if (weather != null) {
			String data = new String(ch, start, length);
			if ("city".equals(preTag)) {
				weather.setCity(data);
			} else if ("updatetime".equals(preTag)) {
				weather.setUpdateTime(data);
			} else if ("wendu".equals(preTag)) {
				weather.setTemp(data);
			} else if ("fengli".equals(preTag)) {
				weather.setWindDegree(data);
			} else if ("shidu".equals(preTag)) {
				weather.setHum(data);
			} else if ("fengxiang".equals(preTag)) {
				weather.setWindDerection(data);
			} else if ("sunrise_1".equals(preTag)) {
				weather.setSunRise(data);
			} else if ("sunset_1".equals(preTag)) {
				weather.setSunSet(data);
			} else if ("aqi".equals(preTag)) {
				weather.getEnvironment().setAqi(data);
			} else if ("pm25".equals(preTag)) {
				weather.getEnvironment().setPm25(data);
			} else if ("suggest".equals(preTag)) {
				weather.getEnvironment().setSuggest(data);
			} else if ("quality".equals(preTag)) {
				weather.getEnvironment().setQuality(data);
			} else if ("MajorPollutants".equals(preTag)) {
				weather.getEnvironment()
						.setMajorPollutants(data);
			} else if ("o3".equals(preTag)) {
				weather.getEnvironment().setO3(data);
			} else if ("co".equals(preTag)) {
				weather.getEnvironment().setCo(data);
			} else if ("pm10".equals(preTag)) {
				weather.getEnvironment().setPm10(data);
			} else if ("so2".equals(preTag)) {
				weather.getEnvironment().setSo2(data);
			} else if ("no2".equals(preTag)) {
				weather.getEnvironment().setNo2(data);
			} else if ("time".equals(preTag)) {
				weather.getEnvironment().setUpdateTime(data);
			}
		}
	}

	/**
	 * <p>
	 * 结束上边开始解析的元素，参数列表与startElement一致
	 * </p>
	 * <p>
	 * 这个方法遇到"反斜杠"(</XX>)的时候自动调用
	 * </p>
	 * 
	 * @author 曾凡
	 * @date 2012-3-7 下午3:58:30
	 */
	@Override
	public void endElement(String uri, String localName,
			String qName) throws SAXException {
		if ("environment".equals(localName)) {
			isEnd = true;
		}
		preTag = null;
	}

	/**
	 * <p>
	 * 可以在这里进行文件的收尾工作
	 * </p>
	 * 
	 * @author 曾凡
	 * @date 2012-3-7 下午4:17:55
	 */
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}

	public WeatherPo getWeather() {
		return weather;
	}

	public void setWeather(WeatherPo weather) {
		this.weather = weather;
	}

}
