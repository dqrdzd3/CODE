package com.hw.hwsafe.sms.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.hw.hwsafe.sms.socket.ServerSender;

public class Server2SMSServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		ServerSender.getInstance().start();
	}
}