package com.hw.hwsafe.smart.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/*import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransport;*/
import org.xmlpull.v1.XmlPullParserException;

import com.hw.hwsafe.platform.constants.ConfConstants;

public class WebServiceUtil {
	/**
	 * 调用云接口查询设备即时信息和查询历史信息
	 */
	public static String doWebservice(String gprsId, String driverType,
			String driverName, String methodName) throws Exception {
		// String url = ConfConstants.HWCLOUD_EQUIPMENT_URL;
		// Client client = new Client(new URL(url));
		// Object[] results = client.invoke(methodName, new Object[] { gprsId,
		// driverType, driverName });
		OMElement ome = null;
		try {

			String wsdlUrl = ConfConstants.HWCLOUD_EQUIPMENT_URL;
			String namespace = ConfConstants.HWCLOUD_EQUIPMENT_NAMESPACE;

			RPCServiceClient client = new RPCServiceClient();

			// 设置webservice服务地址
			Options options = client.getOptions();
			EndpointReference targetEPR = new EndpointReference(wsdlUrl);
			options.setTo(targetEPR);

			// 设置要调用的方法名、命名空间
			QName qName = new QName(namespace, methodName);

			// 设置入参
			Object[] inParams = new Object[] { gprsId, driverType, driverName };
			ome = client.invokeBlocking(qName, inParams);
			// 开始异步调用

			// client.invokeNonBlocking(qName, inParams,
			// new AxisCallback() {
			// @Override
			// public void onComplete() {
			//
			// }
			// @Override
			// public void onError(Exception ex) {
			//
			// }
			// @Override
			// public void onFault(MessageContext arg0) {
			//
			// }
			//
			// @Override
			// public void onMessage(MessageContext msgContext) {
			//
			// }
			//
			//
			// });

		} catch (Exception e) {

		}
		return getResult(ome);
	}
	public static String doWebservice(String param, String methodName) throws Exception {
		// String url = ConfConstants.HWCLOUD_EQUIPMENT_URL;
		// Client client = new Client(new URL(url));
		// Object[] results = client.invoke(methodName, new Object[] { gprsId,
		// driverType, driverName });
		OMElement ome = null;
		try {

			String wsdlUrl = ConfConstants.HWCLOUD_EQUIPMENT_URL;
			String namespace = ConfConstants.HWCLOUD_EQUIPMENT_NAMESPACE;

			RPCServiceClient client = new RPCServiceClient();

			// 设置webservice服务地址
			Options options = client.getOptions();
			EndpointReference targetEPR = new EndpointReference(wsdlUrl);
			options.setTo(targetEPR);

			// 设置要调用的方法名、命名空间
			QName qName = new QName(namespace, methodName);

			// 设置入参
			Object[] inParams = new Object[] { param};
			ome = client.invokeBlocking(qName, inParams);
			// 开始异步调用

		

		} catch (Exception e) {

		}
		return getResult(ome);
	}
	// 历史报警 type 类型 1 为 小时 2 为 天数 pastHoursOrdays是具体数字
	public static String doHisWebservice(String gprsId, String driverType,
			int pastHoursOrDays, int type, String methodName) throws Exception {
		// String url = ConfConstants.HWCLOUD_EQUIPMENT_URL;
		// Client client = new Client(new URL(url));
		// Object[] results = client.invoke(methodName, new Object[] { gprsId,
		// driverType, Integer.valueOf(pastHoursOrDays),
		// Integer.valueOf(type)});
		// return (String) results[0];
		OMElement ome = null;
		try {

			String wsdlUrl = ConfConstants.HWCLOUD_EQUIPMENT_URL;
			String namespace = ConfConstants.HWCLOUD_EQUIPMENT_NAMESPACE;

			RPCServiceClient client = new RPCServiceClient();

			// 设置webservice服务地址
			Options options = client.getOptions();
			EndpointReference targetEPR = new EndpointReference(wsdlUrl);
			options.setTo(targetEPR);

			// 设置要调用的方法名、命名空间
			QName qName = new QName(namespace, methodName);

			// 设置入参
			Object[] inParams = new Object[] { gprsId, driverType,
					Integer.valueOf(pastHoursOrDays), Integer.valueOf(type) };
			ome = client.invokeBlocking(qName, inParams);
			// 开始异步调用
			// client.invokeNonBlocking(qName, inParams,
			// new AxisCallback() {
			// @Override
			// public void onComplete() {
			//
			// }
			// @Override
			// public void onError(Exception ex) {
			//
			// }
			// @Override
			// public void onFault(MessageContext arg0) {
			//
			// }
			//
			// @Override
			// public void onMessage(MessageContext msgContext) {
			//
			// }
			//
			//
			// });

		} catch (Exception e) {

		}
		return getResult(ome);
	}

	// 解析webservice返回的数据
	public static String getResult(OMElement element) {
		if (element == null) {
			return null;
		}
		Iterator iterator = element.getChildElements();
		Iterator innerItr;
		String omeString = "";
		OMElement result = null;
		while (iterator.hasNext()) {
			result = (OMElement) iterator.next();
			omeString = result.getText();
			// System.out.println("While1: " + result); // 新增
			// System.out.println("\t\t" + result.getLocalName() + ": "
			// + result.getText()); // 新增
			// innerItr = result.getChildElements();
			// while (innerItr.hasNext()) { // 新增
			// OMElement elem = (OMElement) innerItr.next(); // 新增
			// System.out.println("\tWhile: " + elem); // 新增
			// System.out.println("\t\t" + elem.getLocalName() + ": "
			// + elem.getText()); // 新增
			// } // 新增
		}
		return omeString;
	}

	// 解析 天气预报
	/*public static Map<String, String> getWeather(String cityName) {
		Map<String, String> result = new HashMap<String, String>();
		String NAMESPACE = "http://WebXml.com.cn/";

		String METHOD_NAME = "getWeatherbyCityName";

		String URL = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";

		String SOAP_ACTION = "http://WebXml.com.cn/getWeatherbyCityName";

		SoapObject detail;

		try {
			System.out.println("rpc------");
			SoapObject rpc = new SoapObject(NAMESPACE, METHOD_NAME);
			System.out.println("rpc " + rpc);
			System.out.println("cityName is " + cityName);
			rpc.addProperty("theCityName", cityName);

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.bodyOut = rpc;
			envelope.dotNet = true;
			envelope.setOutputSoapObject(rpc);
		
			HttpTransport ht = new HttpTransport(URL);
			ht.debug = true;
			ht.call(SOAP_ACTION, envelope);

			detail = (SoapObject) envelope.getResponse();
		
			// http://www.webxml.com.cn/WebServices/WeatherWebService.asmx/getWeatherbyCityName
			// 数据看这个例子   图片路径    /images/weather/
			String date = detail.getProperty(6).toString();
			result.put("date", date.split(" ")[0]);
			result.put("type", date.split(" ")[1]);
			result.put("temp_range", detail.getProperty(5).toString());
			result.put("wind", detail.getProperty(7).toString());
			result.put("pic", detail.getProperty(8).toString());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}*/

	public static void main(String[] args) throws IOException, XmlPullParserException {
		try {
			doWeather("郑州");
			//getURLWeather("上海");
			//getWeather("郑州");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*String WSDL_URI = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?WSDL";//wsdl 的uri
		String namespace = "http://WebXml.com.cn/";//namespace
		String methodName = "getWeatherbyCityName";//要调用的方法名称
		
		SoapObject request = new SoapObject(namespace, methodName);
		request.addProperty("theCityName", "上海");//参数
		
		
		//创建SoapSerializationEnvelope 对象，同时指定soap版本号(之前在wsdl中看到的)
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
		envelope.bodyOut = request;//由于是发送请求，所以是设置bodyOut
		envelope.dotNet = true;//由于是.net开发的webservice，所以这里要设置为true
		
		HttpTransport httpTransport = new HttpTransport(WSDL_URI);
		httpTransport.call(null, envelope);//调用
		
		SoapObject response = (SoapObject) envelope.getResponse();//获得返回对象
		if(response!=null){
			System.out.println(response);
		}*/
	
	}
	
	public static String getURLWeather(String cityname) throws IOException{
		
		 //服务的地址
        URL wsUrl = new URL("http://www.webxml.com.cn/WebServices/WeatherWebService.asmx");   //?theCityName=%E4%B8%8A%E6%B5%B7
        String soap = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" + 
                "<soap:Body> <getWeatherbyCityName xmlns=\"http://WebXml.com.cn/\"><theCityName>"+cityname+"</theCityName>  </getWeatherbyCityName> </soap:Body> </soap:Envelope>";
        HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection();
 
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
       //conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(soap.getBytes().length));
        OutputStream os = conn.getOutputStream();
        
        //请求体
    
    
        os.write(soap.getBytes());
   
        InputStream is = conn.getInputStream();
        
        byte[] b = new byte[1024];
        int len = 0;
        String s = "";
        while((len = is.read(b)) != -1){
            String ss = new String(b,0,len,"UTF-8");
            s += ss;
        }
      System.out.println(s);
        
  
        
       // parseWeather(s);
        is.close();
        os.close();
        conn.disconnect();
        return s;
	}
	
	public static String doWeather(String city) throws Exception {
		// String url = ConfConstants.HWCLOUD_EQUIPMENT_URL;
		// Client client = new Client(new URL(url));
		// Object[] results = client.invoke(methodName, new Object[] { gprsId,
		// driverType, Integer.valueOf(pastHoursOrDays),
		// Integer.valueOf(type)});
		// return (String) results[0];
		 String soap = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" + 
	                "<soap:Body> <getWeatherbyCityName xmlns=\"http://WebXml.com.cn/\"><theCityName>上海</theCityName>  </getWeatherbyCityName> </soap:Body> </soap:Envelope>";
		OMElement ome = null;
		try {

			String wsdlUrl = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";
			String namespace = "http://WebXml.com.cn/";

			RPCServiceClient client = new RPCServiceClient();

			// 设置webservice服务地址
			Options options = client.getOptions();
			EndpointReference targetEPR = new EndpointReference(wsdlUrl);
			options.setTo(targetEPR);

			// 设置要调用的方法名、命名空间
			QName qName = new QName(namespace, "getWeatherbyCityName");

			// 设置入参
			Object[] inParams = new Object[] { soap};
			ome = client.invokeBlocking(qName, inParams);
			// 开始异步调用
			// client.invokeNonBlocking(qName, inParams,
			// new AxisCallback() {
			// @Override
			// public void onComplete() {
			//
			// }
			// @Override
			// public void onError(Exception ex) {
			//
			// }
			// @Override
			// public void onFault(MessageContext arg0) {
			//
			// }
			//
			// @Override
			// public void onMessage(MessageContext msgContext) {
			//
			// }
			//
			//
			// });

		} catch (Exception e) {

		}
		System.out.println(getResult(ome));
		return getResult(ome);
	}
	
	
	public static Map<String, String>  parseWeather(String is){
		Map<String, String> result = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(is);
			Element root = document.getRootElement();
			System.out.println("Root: " + root.getName());

	        // 获取所有子元素
	      /*  List<Element> childList = root.elements();
	        System.out.println("total child count: " + childList.size());
	        for (int i = 0; i < childList.size(); i++) {
				System.out.println(childList.get(0).getText());
			}*/
	        
	        
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	 /**
     * 文件内容替换
     * 
     * @param inFileName 源文件
     * @param from
     * @param to
     * @return 返回替换后文件
     * @throws IOException
     * @throws UnsupportedEncodingException
     */
    public static File replace(String inFileName, String from, String to)
            throws IOException, UnsupportedEncodingException {
        File inFile = new File(inFileName);
        BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream(inFile), "utf-8"));
        File outFile = new File(inFile + ".tmp");
        PrintWriter out = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(outFile), "utf-8")));
        String reading;
        while ((reading = in.readLine()) != null) {
            out.println(reading.replaceAll(from, to));
        }
        out.close();
        in.close();
        //infile.delete(); //删除源文件
        //outfile.renameTo(infile); //对临时文件重命名
        return outFile;
    }
    
    /**
     * 从输入流中读取数据
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len = inStream.read(buffer)) !=-1 ){
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();//网页的二进制数据
        outStream.close();
        inStream.close();
        return data;
    }
}
