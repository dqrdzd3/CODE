package com.wg.salescount.user.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wg.salescount.user.po.UserPO;
import com.wg.salescount.user.service.UserService;
import com.wg.salescount.util.MD5Util;
import com.wg.salescount.util.UUIDGenerator;

@Controller
@RequestMapping("user.do")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(params ="method=update")
	public void update(HttpServletRequest httpRequest,HttpServletResponse httpResponse){
		try {
			httpRequest.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpResponse.setCharacterEncoding("utf-8");
		httpResponse.setContentType("text/html");
		String userId = httpRequest.getParameter("userId");
		if(userId == null || userId == "" || userId.isEmpty()){
			System.out.println("enter the method");
			httpRequest.setAttribute("userEntity", null);
			try {
				httpRequest.getRequestDispatcher("index.jsp").forward(httpRequest, httpResponse);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(params ="method=add")
	public void add(HttpServletRequest httpRequest,HttpServletResponse httpResponse){
		System.out.println("enter user add");
		try {
			httpRequest.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpResponse.setCharacterEncoding("utf-8");
		httpResponse.setContentType("text/html");
		String userId = UUIDGenerator.getUUID();
		String userName = httpRequest.getParameter("userName");
		String name = httpRequest.getParameter("name");
		String password = httpRequest.getParameter("password");
		String passwordEncryption = MD5Util.md5(password);
		String statusString = httpRequest.getParameter("status");
		int status = 0;
		if(statusString != null && statusString.equals("")){
			status = Integer.parseInt(statusString);
		}
		String competenceString = httpRequest.getParameter("competence");
		int competence = 0;
		if(competenceString != null && competenceString.equals("")){
			competence = Integer.parseInt(competenceString);
		}
		UserPO userEntity = new UserPO(userId,userName,name,passwordEncryption,status,competence);
		userService.add(userEntity);
		try{
			httpRequest.getRequestDispatcher("index.jsp").forward(httpRequest, httpResponse);
		}catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
/*	@RequestMapping(params ="method=delete")
	public void delete(HttpServletRequest httpServletRequest,HttpServletResponse httpServletReponse){
		try {
			httpServletRequest.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpServletReponse.setCharacterEncoding("utf-8");
		httpServletReponse.setContentType("text/html");	
		String userId = httpServletRequest.getParameter("userId");
		UserPO userEntity = new UserPO();
		userEntity.setUserId(userId);
		userService.delete(userEntity);
		try {
			httpServletRequest.getRequestDispatcher("Index.jsp").forward(httpServletRequest, httpServletReponse);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}*/
	@RequestMapping(params ="method=exist")
	public void exist(HttpServletRequest httpServletRequest,HttpServletResponse httpServletReponse){
		try {
			httpServletRequest.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpServletReponse.setCharacterEncoding("utf-8");
		httpServletReponse.setContentType("text/html");	
		String userName = httpServletRequest.getParameter("userName");
		String password = httpServletRequest.getParameter("password");
		String passwordEncryption = MD5Util.md5(password);		
		UserPO userEntity = userService.queryByUserName(userName);
		if(userEntity == null){
			try {
				httpServletRequest.getRequestDispatcher("index.jsp").forward(httpServletRequest, httpServletReponse);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}else{
			String passwordFromDB = userEntity.getPassword();
			String userId = userEntity.getUserId();
			//HttpSession session = httpServletRequest.getSession();
			if(passwordEncryption.equals(passwordFromDB) && userId != null && !userId.equals("")){
				//session.setAttribute("userId", userId);
				//String userIdForTest = (String)session.getAttribute("userId");
				//System.out.println("userIdForTest="+userIdForTest);
				try {
					httpServletRequest.getRequestDispatcher("salesIndex").forward(httpServletRequest, httpServletReponse);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				try {
					httpServletRequest.getRequestDispatcher("index.jsp").forward(httpServletRequest, httpServletReponse);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
		}
			
	}
}
