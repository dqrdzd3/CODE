package com.hw.hwsafe.smart.weixin.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.hw.hwsafe.smart.weixin.service.CoreService;
import com.hw.hwsafe.smart.weixin.util.SignUtil;
import com.hw.hwsafe.smart.weixin.util.WeixinUtil;





public class CoreServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static  Log log = LogFactory.getLog(CoreServlet.class);
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		String signature = request.getParameter("signature");

	

		String timestamp = request.getParameter("timestamp");

	

		String nonce = request.getParameter("nonce");



		String echostr = request.getParameter("echostr");

		PrintWriter out = response.getWriter();
	
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {

			out.print(echostr);
			
		}

		out.close();

		out = null;

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		log.info("接收来自微信的回复");
	

				String respMessage = CoreService.processRequest(request);
		
				PrintWriter out = response.getWriter();
				out.print(respMessage);
				out.close();

	}

}
