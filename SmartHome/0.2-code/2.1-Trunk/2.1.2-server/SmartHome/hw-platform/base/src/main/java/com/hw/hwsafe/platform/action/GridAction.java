package com.hw.hwsafe.platform.action;

import java.io.IOException;
import java.io.PrintWriter;

public class GridAction extends BaseAction {
	
	public String testGrid(){
		return SUCCESS;
	}
	
	public String grf1a(){
		return "1aGrf";
	}
	
	public void generateData() throws IOException{
		StringBuffer XmlText = new StringBuffer ("<xml>\n");

		XmlText.append("<row>");
		XmlText.append("<CustomerID>1</CustomerID>");
		XmlText.append("<CompanyName>公司名称</CompanyName>");
		XmlText.append("<ContactName>联系人</ContactName>");
		XmlText.append("<ContactTitle>contactTitle</ContactTitle>");
		XmlText.append("<Address>地址</Address>");
		XmlText.append("<City>城市~</City>");
		XmlText.append("<Region>地区</Region>");
		XmlText.append("<Phone>电话</Phone>");
		XmlText.append("<Country>国家</Country>");
		XmlText.append("</row>\n");
		XmlText.append("</xml>\n");

		response.resetBuffer();
		response.setCharacterEncoding(request.getCharacterEncoding());
		response.setContentType("application/xml");
		
		PrintWriter pw = response.getWriter();
		pw.print(XmlText.toString());
		pw.close();  //终止后续不必要内容输出
	}
}
