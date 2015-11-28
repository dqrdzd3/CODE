package com.wg.salescount.weeklysales.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wg.salescount.util.Pager;
import com.wg.salescount.util.UUIDGenerator;
import com.wg.salescount.weeklysales.po.WeeklySalesPO;
import com.wg.salescount.weeklysales.service.WeeklySalesService;

@Controller
@RequestMapping("weeklySales.do")
public class WeeklySalesController {
	
	@Autowired
	private WeeklySalesService weeklySalesService;
		
	@RequestMapping(params ="method=search")
	public void search(HttpServletRequest httpRequest,HttpServletResponse httpResponse,String method){
		System.out.println("search enter");
		try {
			httpRequest.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpResponse.setCharacterEncoding("utf-8");
		httpResponse.setContentType("text/html");
		WeeklySalesPO weeklySalesEntity = new WeeklySalesPO();
		weeklySalesEntity.setProductName(httpRequest.getParameter("productName"));
		weeklySalesEntity.setSpecificationsModel(httpRequest.getParameter("specificationsModel"));
		int currentPage=1;
		//List<WeeklySales> weeklySalesList=new ArrayList<WeeklySales>();
		Pager pager = new Pager();		
		String size=httpRequest.getParameter("size");
		if("".equals(size)||null==size) size="100";
		if(!size.matches("^[0-9]+$"))size = "100";
		if(Integer.parseInt(size)<=0){
			size = "100";
		}
		pager.setSize(Integer.parseInt(size));
		String cur=httpRequest.getParameter("currentPage");
		System.out.println("size="+size);
		System.out.println("currentPage="+cur);
		if("".equals(cur)||null==cur){
			cur="1";
		}
		else if(Integer.parseInt(cur)>pager.getTotalPage()){
			//int c=page.getTotalPage();
			cur = pager.getTotalPage()+"";
		}
		else if(Integer.parseInt(cur)<=0){
			cur="1";
		}
		else if(!cur.matches("^[0-9]+$")){
			cur = "1";
		}		
		pager.setCurrentPage(Integer.parseInt(cur));
		System.out.println("currentPage="+pager.getCurrentPage());
		System.out.println("pager="+pager);
		System.out.println("weeklySalesEntity="+weeklySalesEntity);
		pager = weeklySalesService.getList(weeklySalesEntity, pager);
		List<WeeklySalesPO> weeklySalesList = pager.getList();
		System.out.println("list="+weeklySalesList);
		httpRequest.setAttribute("method", method);
		httpRequest.setAttribute("page", pager);
		httpRequest.setAttribute("list",weeklySalesList);
		httpRequest.setAttribute("weeklySales", weeklySalesEntity);
		try {
			httpRequest.getRequestDispatcher("/WEB-INF/weeklysales/weeklySalesIndex.jsp").forward(httpRequest, httpResponse);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
/*	@RequestMapping(params = "method=searchAll")
	public void getListTest1(HttpServletRequest httpRequest,HttpServletResponse httpResponse,String method){
		System.out.println("controller-searchAll");
		try {
			httpRequest.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpResponse.setCharacterEncoding("utf-8");
		httpResponse.setContentType("text/html");
				Pager page = new Pager();
				int total=weeklySalesDAO.getAllNum();
				page.setTotal(total);
				String size=httpRequest.getParameter("size");
				if("".equals(size)||null==size) size="5";
				if(!size.matches("^[0-9]+$"))size = "5";
				if(Integer.parseInt(size)<=0){
					size = "5";
				}
				page.setSize(Integer.parseInt(size));
				String cur=httpRequest.getParameter("currentPage");
				if("".equals(cur)||null==cur) cur="1";
				if(!cur.matches("^[0-9]+$"))cur = "1";
				if(Integer.parseInt(cur)<=0){
					cur = "1";
				}else if(Integer.parseInt(cur)>page.getTotalPage()){
					cur = page.getTotalPage()+"";
				}
				page.setCurrentPage(Integer.parseInt(cur));
		
		List<WeeklySales> weeklySalesList = weeklySalesService.getListTest(page);
		System.out.println("method =="+method);
		System.out.println("list =="+weeklySalesList);
		httpRequest.setAttribute("method", method);
		httpRequest.setAttribute("list", weeklySalesList);
		httpRequest.setAttribute("page", page);
		try {
			httpRequest.getRequestDispatcher("weeklySalesIndex.jsp").forward(httpRequest, httpResponse);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}*/
	
	@RequestMapping(params = "method=add")
	public void add(HttpServletRequest httpRequest,HttpServletResponse httpResponse){
		try {
			httpRequest.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpResponse.setCharacterEncoding("utf-8");
		httpResponse.setContentType("text/html");	
		String weeklySalesId = UUIDGenerator.getUUID();
		String productName = httpRequest.getParameter("productName");
		String specificationsModel = httpRequest.getParameter("specificationsModel");
		System.out.println("productName="+productName);
		System.out.println("specificationsModel="+specificationsModel);
		String unitPriceString = httpRequest.getParameter("unitPrice");
		float unitPrice=0L;
		if(unitPriceString != null && !unitPriceString.equals("")){
			unitPrice = Float.parseFloat(unitPriceString);
		} 
		int quantity=0;
		String quantityString = httpRequest.getParameter("quantity");
		if(quantityString != null && !quantityString.equals("")){
			quantity = Integer.parseInt(quantityString);
		}
		float totalPrice=0L;
		String totalPriceString = httpRequest.getParameter("totalPrice");
		if(totalPriceString != null && !totalPriceString.equals("")){
			totalPrice = Float.parseFloat(totalPriceString);
		}
		int week = 0;
		String weekString = httpRequest.getParameter("week");
		if(weekString != null && !weekString.equals("")){
			week = Integer.parseInt(weekString);
		}
		int year = 0;
		String yearString = httpRequest.getParameter("year");
		if(yearString != null && !yearString.equals("")){
			year = Integer.parseInt(yearString);
		}
		WeeklySalesPO weeklySalesEntity = new WeeklySalesPO(weeklySalesId,productName,specificationsModel,unitPrice,quantity,totalPrice,week,year);
		weeklySalesService.add(weeklySalesEntity);
		try {
			httpRequest.getRequestDispatcher("/WEB-INF/weeklysales/weeklySalesIndex.jsp").forward(httpRequest, httpResponse);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(params = "method=update")
	public void update(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
		try {
			httpRequest.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpResponse.setCharacterEncoding("utf-8");
		httpResponse.setContentType("text/html");
		String weeklySalesId = httpRequest.getParameter("weeklySalesId");
		if(weeklySalesId == null || weeklySalesId == "" || weeklySalesId.isEmpty()){
			System.out.println("enter the method");
			httpRequest.setAttribute("weeklySaleEntity", null);
			try {
				httpRequest.getRequestDispatcher("/WEB-INF/weeklysales/weeklySalesIndex.jsp").forward(httpRequest, httpResponse);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		WeeklySalesPO weeklySalesEntity = null;
		httpRequest.setAttribute("weeklySalesEntity", weeklySalesEntity);
		String productName = httpRequest.getParameter("productName");
		String specificationsModel = httpRequest.getParameter("specificationsModel");
		String unitPriceString = httpRequest.getParameter("unitPrice");
		float unitPrice = Float.parseFloat(unitPriceString);
		String quantityString = httpRequest.getParameter("quantity");
		int quantity = Integer.parseInt(quantityString);
		String totalPriceString = httpRequest.getParameter("totalPrice");
		float totalPrice = Float.parseFloat(totalPriceString);
		String weekString = httpRequest.getParameter("week");
		int week = Integer.parseInt(weekString);
		String yearString = httpRequest.getParameter("year");
		int year = Integer.parseInt(yearString);
		weeklySalesEntity = new WeeklySalesPO(weeklySalesId,productName,specificationsModel,unitPrice,quantity,totalPrice,week,year);
		weeklySalesService.update(weeklySalesEntity);
		httpRequest.setAttribute("weeklySales", weeklySalesEntity);
		try {
			httpRequest.getRequestDispatcher("/WEB-INF/weeklysales/weeklySalesIndex.jsp").forward(httpRequest, httpResponse);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(params = "method=searchById")
	public void searchById(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
		System.out.println("search by id");
		try {
			httpRequest.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpResponse.setCharacterEncoding("utf-8");
		httpResponse.setContentType("text/html");
		String weeklySalesId = httpRequest.getParameter("weeklySalesId"); 
		System.out.println("weeklySalesId="+weeklySalesId);
		WeeklySalesPO weeklySalesEntity = weeklySalesService.queryById(weeklySalesId);
		System.out.println("weeklySalesEntity="+weeklySalesEntity);
		httpRequest.setAttribute("weeklySales", weeklySalesEntity);
		try {
			httpRequest.getRequestDispatcher("/WEB-INF/weeklysales/weeklySalesUpdate.jsp").forward(httpRequest, httpResponse);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
	
	@RequestMapping(params = "method=delete")
	public void delete(HttpServletRequest httpServletRequest,HttpServletResponse httpServletReponse){
		try {
			httpServletRequest.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpServletReponse.setCharacterEncoding("utf-8");
		httpServletReponse.setContentType("text/html");	
		String weeklySalesId = httpServletRequest.getParameter("weeklySalesId");
		WeeklySalesPO weeklySalesEntity = new WeeklySalesPO();
		weeklySalesEntity.setWeeklySalesId(weeklySalesId);
		weeklySalesService.delete(weeklySalesEntity);
		try {
			httpServletRequest.getRequestDispatcher("/WEB-INF/weeklysales/weeklySalesIndex.jsp").forward(httpServletRequest, httpServletReponse);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
