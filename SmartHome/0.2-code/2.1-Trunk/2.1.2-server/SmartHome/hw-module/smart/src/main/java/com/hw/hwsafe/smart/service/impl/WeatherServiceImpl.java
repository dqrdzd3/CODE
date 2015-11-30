package com.hw.hwsafe.smart.service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import net.sf.json.JSONObject;

import com.google.gson.JsonObject;
import com.hw.hwsafe.attachment.util.AppFileUpLoad;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.service.IWeatherService;

public class WeatherServiceImpl extends BaseServiceImpl implements
		IWeatherService {

	@Override
	public String ipToCity(String ip) {
		String address = "";
		try {
			address = getAddresses("ip=" + ip, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		address = address.substring(0, address.length() - 1);
		return address;
	}

	@Override
	public String cityToId(String city) {

		String path = this.getClass().getClassLoader().getResource("/")
				.getPath()
				+ "id-city.txt";
		File filePath = new File(path);

		InputStreamReader ips = null;
		try {
			ips = new InputStreamReader(new FileInputStream(filePath), "GBK");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // gb2312
		BufferedReader in_ = new BufferedReader(ips);

		String line = null;

		try {
			while ((line = in_.readLine()) != null) {
				if (line.contains(city)) {
					break;
				} else
					continue;

			}

			line = line.trim();
			String str2 = "";
			if (line != null && !"".equals(line)) {
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) >= 48 && line.charAt(i) <= 57) {
						str2 += line.charAt(i);
					}
				}
			}
			line = str2;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return line;

	}

	@Override
	public String getWeatherContent(String cityID) {
		JSONObject json = null;

		HttpClient client = new HttpClient();
		// 设置代理服务器地址和端口
		// client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);
		// 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https
		HttpMethod method = new GetMethod("http://m.weather.com.cn/atad/"
				+ cityID + ".html");
//		HttpMethod method = new GetMethod("http://www.weather.com.cn/adat/sk/"
//				+ cityID + ".html");
		// 使用POST方法
		// HttpMethod method = new PostMethod("http://java.sun.com");

		try {
			client.executeMethod(method);
		} catch (HttpException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String Jstring = "";

		try {
			Jstring = method.getResponseBodyAsString();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		method.releaseConnection();

		System.out.println(Jstring.toString());

		return Jstring;

	}

	public String getAddresses(String content, String encodingString)
			throws UnsupportedEncodingException {
		// 这里调用pconline的接口
		String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
		// 从http://whois.pconline.com.cn取得IP所在的省市区信息
		String returnStr = this.getResult(urlStr, content, encodingString);
		if (returnStr != null) {
			// 处理返回的省市区信息
			String[] temp = returnStr.split(",");
			if (temp.length < 3) {
				return "0";// 无效IP，局域网测试
			}
			String region = (temp[5].split(":"))[1].replaceAll("\"", "");
			region = decodeUnicode(region);// 省份

			String country = "";
			String area = "";
			// String region = "";
			String city = "";
			String county = "";
			String isp = "";
			for (int i = 0; i < temp.length; i++) {
				switch (i) {
				case 1:
					country = (temp[i].split(":"))[2].replaceAll("\"", "");
					country = decodeUnicode(country);// 国家
					break;
				case 3:
					area = (temp[i].split(":"))[1].replaceAll("\"", "");
					area = decodeUnicode(area);// 地区
					break;
				case 5:
					region = (temp[i].split(":"))[1].replaceAll("\"", "");
					region = decodeUnicode(region);// 省份
					break;
				case 7:
					city = (temp[i].split(":"))[1].replaceAll("\"", "");
					city = decodeUnicode(city);// 市区
					break;
				case 9:
					county = (temp[i].split(":"))[1].replaceAll("\"", "");
					county = decodeUnicode(county);// 地区
					break;
				case 11:
					isp = (temp[i].split(":"))[1].replaceAll("\"", "");
					isp = decodeUnicode(isp); // ISP公司
					break;
				}
			}

			// System.out.println(country+"="+area+"="+region+"="+city+"="+county+"="+isp);
			return city;
		}
		return null;
	}

	/**
	 * @param urlStr
	 *            请求的地址
	 * @param content
	 *            请求的参数 格式为：name=xxx&pwd=xxx
	 * @param encoding
	 *            服务器端请求编码。如GBK,UTF-8等
	 * @return
	 */
	private String getResult(String urlStr, String content, String encoding) {
		URL url = null;
		HttpURLConnection connection = null;
		try {
			url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();// 新建连接实例
			connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
			connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
			connection.setDoOutput(true);// 是否打开输出流 true|false
			connection.setDoInput(true);// 是否打开输入流true|false
			connection.setRequestMethod("POST");// 提交方法POST|GET
			connection.setUseCaches(false);// 是否缓存true|false
			connection.connect();// 打开连接端口
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());// 打开输出流往对端服务器写数据
			out.writeBytes(content);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
			out.flush();// 刷新
			out.close();// 关闭输出流
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
			// ,以BufferedReader流来读取
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();// 关闭连接
			}
		}
		return null;
	}
	/**
	 * @param urlStr
	 *            请求的地址
	 * @param content
	 *            请求的参数 格式为：name=xxx&pwd=xxx
	 * @param encoding
	 *            服务器端请求编码。如GBK,UTF-8等
	 * @return
	 */
	private String getResult1(String urlStr, String content, String encoding) throws IOException{
		 HttpURLConnection connection = null;
			try {
				 URL url = new URL("http://api.lib360.net/open/pm2.5.json?city="+URLEncoder.encode("北京"));
				connection = (HttpURLConnection) url.openConnection();// 新建连接实例
				connection.setConnectTimeout(5000);// 设置连接超时时间，单位毫秒
				connection.setReadTimeout(5000);// 设置读取数据超时时间，单位毫秒
				connection.setDoOutput(false);// 是否打开输出流 true|false
				connection.setDoInput(true);// 是否打开输入流true|false
				connection.setRequestMethod("GET");// 提交方法POST|GET
				connection.setUseCaches(false);// 是否缓存true|false
				connection.connect();// 打开连接端口
//				DataOutputStream out = new DataOutputStream(
//						connection.getOutputStream());// 打开输出流往对端服务器写数据
//				out.writeBytes("city="+"郑州");// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
//				out.flush();// 刷新
//				out.close();// 关闭输出流
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						connection.getInputStream(), "utf-8"));// 往对端写完数据对端服务器返回数据
				// ,以BufferedReader流来读取
				StringBuffer buffer = new StringBuffer();
				String line = "";
				while ((line = reader.readLine()) != null) {
					buffer.append(line);
				}
				reader.close();
				System.out.println("输出1："+buffer.toString());
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (connection != null) {
					connection.disconnect();// 关闭连接
				}
			}
			return null;

	}
	/**
	 * unicode 转换成 中文
	 * 
	 * @author fanhui 2007-3-15
	 * @param theString
	 * @return
	 */
	public static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException(
									"Malformed      encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't') {
						aChar = '\t';
					} else if (aChar == 'r') {
						aChar = '\r';
					} else if (aChar == 'n') {
						aChar = '\n';
					} else if (aChar == 'f') {
						aChar = '\f';
					}
					outBuffer.append(aChar);
				}
			} else {
				outBuffer.append(aChar);
			}
		}
		return outBuffer.toString();
	}

	@Override
	public String getPm25(String city) {
		
		try {
			String urlStr = "http://api.lib360.net/open/pm2.5.json?city=" + URLEncoder.encode(city);
			String returnStr = getResult1(urlStr,"", "utf-8");  //"city=" + city
			System.out.println("json:"+returnStr);
			JSONObject json = JSONObject.fromObject(returnStr);
			return json.getString("pm25");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	public static void main(String[] args) throws IOException{
		

		 HttpURLConnection connection = null;
			try {
				 URL url = new URL("http://wthrcdn.etouch.cn/WeatherApi?city="+URLEncoder.encode("北京","utf-8"));
				connection = (HttpURLConnection) url.openConnection();// 新建连接实例
				connection.setConnectTimeout(5000);// 设置连接超时时间，单位毫秒
				connection.setReadTimeout(5000);// 设置读取数据超时时间，单位毫秒
				connection.setDoOutput(false);// 是否打开输出流 true|false
				connection.setDoInput(true);// 是否打开输入流true|false
				connection.setRequestMethod("GET");// 提交方法POST|GET
				connection.setUseCaches(false);// 是否缓存true|false
				connection.connect();// 打开连接端口
//				DataOutputStream out = new DataOutputStream(
//						connection.getOutputStream());// 打开输出流往对端服务器写数据
//				out.writeBytes("city="+"郑州");// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
//				out.flush();// 刷新
//				out.close();// 关闭输出流
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						connection.getInputStream(), "utf-8"));// 往对端写完数据对端服务器返回数据
				// ,以BufferedReader流来读取
				StringBuffer buffer = new StringBuffer();
				String line = "";
				while ((line = reader.readLine()) != null) {
					buffer.append(line);
				}
				reader.close();
				System.out.println("输出："+buffer.toString());
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (connection != null) {
					connection.disconnect();// 关闭连接
				}
			}
	}

}
