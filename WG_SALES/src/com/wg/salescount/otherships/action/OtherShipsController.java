package com.wg.salescount.otherships.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wg.salescount.otherships.po.OtherShipsPO;
import com.wg.salescount.otherships.service.OtherShipsService;
import com.wg.salescount.taobaosales.po.TaoBaoSalesPO;
import com.wg.salescount.util.DateUtil;
import com.wg.salescount.util.Pager;
import com.wg.salescount.util.UUIDGenerator;

@Controller
@RequestMapping("otherShips.do")
public class OtherShipsController {

	@Autowired	
	private OtherShipsService otherShipsService;
	
	@RequestMapping(params = "method=search")
	public void search(HttpServletRequest httpRequest,HttpServletResponse httpResponse,String method){
		try {
			httpRequest.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpResponse.setCharacterEncoding("utf-8");
		httpResponse.setContentType("text/html");
		OtherShipsPO otherShipsEntity = new OtherShipsPO();	
		otherShipsEntity.setConsigneeName(httpRequest.getParameter("consigneeName"));
		otherShipsEntity.setProductName(httpRequest.getParameter("productName"));
		otherShipsEntity.setCourierNo(httpRequest.getParameter("courierNo"));
		String moneyStatusString = httpRequest.getParameter("moneyStatus");
		int moneyStatus = 0;
		/*if(!moneyStatusString.equals("") && moneyStatusString != null){
			moneyStatus = Integer.parseInt(moneyStatusString);
		}
		otherShipsEntity.setMoneyStatus(moneyStatus);*/		
		int currentPage=1;
		Pager pager = new Pager();		
		String size=httpRequest.getParameter("size");
		if("".equals(size)||null==size) size="100";
		if(!size.matches("^[0-9]+$"))size = "100";
		if(Integer.parseInt(size)<=0){
			size = "100";
		}
		pager.setSize(Integer.parseInt(size));
		String cur=httpRequest.getParameter("currentPage");
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
		pager = otherShipsService.getList(otherShipsEntity, pager);
		List<OtherShipsPO> otherShipsList = pager.getList();
		System.out.println("list="+otherShipsList);
		httpRequest.setAttribute("method", method);
		httpRequest.setAttribute("page", pager);
		httpRequest.setAttribute("list",otherShipsList);
		httpRequest.setAttribute("otherShips", otherShipsEntity);
		try {
			httpRequest.getRequestDispatcher("/WEB-INF/otherships/otherShipsIndex.jsp").forward(httpRequest, httpResponse);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(params = "method=add")
	public void add(HttpServletRequest httpRequest,HttpServletResponse httpResponse){
		System.out.println("controller-add");
		try {
			httpRequest.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpResponse.setCharacterEncoding("utf-8");
		httpResponse.setContentType("text/html");	
		String otherShipsId = UUIDGenerator.getUUID();
		String customerName = httpRequest.getParameter("customerName");
		String consigneeName = httpRequest.getParameter("consigneeName");
		String receiverAddr = httpRequest.getParameter("receiverAddr");
		String phoneNo = httpRequest.getParameter("phoneNo");
		String deliveryTimeString = httpRequest.getParameter("deliveryTime");
		//Date deliveryTime = new Date(deliveryTimeString);
		Date deliveryTime = null;
		if(deliveryTimeString != null && !deliveryTimeString.equals("")){
			deliveryTime = DateUtil.string2DateForController(deliveryTimeString);
		}
		String productName = httpRequest.getParameter("productName");
		String productId = httpRequest.getParameter("productId");
		float unitPrice = 0L;
		String unitPriceString = httpRequest.getParameter("unitPrice");
		if(unitPriceString != null && !unitPriceString.equals("")){
			unitPrice = Float.parseFloat(unitPriceString);
		}
		int quantity = 0;
		String quantityString = httpRequest.getParameter("quantity");
		if(quantityString != null && !quantityString.equals("")){
			quantity = Integer.parseInt(quantityString);
		} 
		float totalPrice = 0L;
		String totalPriceString = httpRequest.getParameter("totalPrice");
		if(totalPriceString != null && !totalPriceString.equals("")){
			totalPrice = Float.parseFloat(totalPriceString);
		}
		int moneyStatus = 0;//默认到款情况为未支付
		String moneyStatusString = httpRequest.getParameter("moneyStatus");
		if(moneyStatusString != null && !moneyStatusString.equals("")){
			moneyStatus = Integer.parseInt(moneyStatusString);
		}
		int invoice = 0;//是否开票默认为否
		String invoiceString = httpRequest.getParameter("invoice");
		if(invoiceString != null && !invoiceString.equals("")){
			invoice = Integer.parseInt(invoiceString);
		}
		String invoiceNo = httpRequest.getParameter("invoiceNo");
		String salesPlatform = httpRequest.getParameter("salesPlatform");
		String courierCompany = httpRequest.getParameter("courierCompany");
		String courierNo = httpRequest.getParameter("courierNo");
		String signTimeString = httpRequest.getParameter("signTime");
		Date signTime = null;
		if(signTimeString != null && !signTimeString.equals("")){
			signTime = DateUtil.string2DateForController(signTimeString);
		}
		float courierCost = 0L;
		String courierCostString = httpRequest.getParameter("courierCost");
		if(courierCostString != null && !courierCostString.equals("")){
			courierCost = Float.parseFloat(courierCostString);
		}
		float insuredCost = 0L;
		String insuredCostString = httpRequest.getParameter("insuredCost");
		if(insuredCostString != null && !insuredCostString.equals("")){
			insuredCost = Float.parseFloat(insuredCostString);
		}
		String city = httpRequest.getParameter("city");
		int gender = 1;//性别默认为男
		String genderString = httpRequest.getParameter("gender");
		if(genderString != null && !genderString.equals("")){
			gender = Integer.parseInt(genderString);
		}
		int age = 0;//年龄默认为0
		String ageString = httpRequest.getParameter("age");
		if(ageString != null && !ageString.equals("")){
			age = Integer.parseInt(ageString);
		}
		String jobs = httpRequest.getParameter("jobs");
		OtherShipsPO otherShipsEntity = new OtherShipsPO(otherShipsId,customerName,consigneeName,receiverAddr,
				phoneNo,deliveryTime,productName,productId,unitPrice,quantity,totalPrice,moneyStatus,invoice,
				invoiceNo,salesPlatform,courierCompany,courierNo,signTime,courierCost,insuredCost,city,gender,age,jobs);
		otherShipsService.add(otherShipsEntity);
		try {
			//httpRequest.getRequestDispatcher("/WEB-INF/otherships/otherShipsGet.jsp").forward(httpRequest, httpResponse);
			httpRequest.getRequestDispatcher("/WEB-INF/otherships/otherShipsIndex.jsp").forward(httpRequest, httpResponse);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(params = "method=update")
	public void update(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
		System.out.println("controller-update");
		try {
			httpRequest.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpResponse.setCharacterEncoding("utf-8");
		httpResponse.setContentType("text/html");
		String otherShipsId = httpRequest.getParameter("otherShipsId");
		if(otherShipsId == null || otherShipsId == "" || otherShipsId.isEmpty()){
			System.out.println("enter the method");
			httpRequest.setAttribute("otherShipsEntity", null);
			try {
				httpRequest.getRequestDispatcher("/WEB-INF/otherships/otherShipsIndex.jsp").forward(httpRequest, httpResponse);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		OtherShipsPO otherShipsEntity = null;
		httpRequest.setAttribute("otherShipsEntity", otherShipsEntity);
		
		//
		String customerName = httpRequest.getParameter("customerName");
		String consigneeName = httpRequest.getParameter("consigneeName");
		String receiverAddr = httpRequest.getParameter("receiverAddr");
		String phoneNo = httpRequest.getParameter("phoneNo");
		String deliveryTimeString = httpRequest.getParameter("deliveryTime");
		Date deliveryTime = DateUtil.string2DateForController(deliveryTimeString);
		String productName = httpRequest.getParameter("productName");
		String productId = httpRequest.getParameter("productId");
		String unitPriceString = httpRequest.getParameter("unitPrice");
		float unitPrice = Float.parseFloat(unitPriceString);
		String quantityString = httpRequest.getParameter("quantity");
		int quantity = Integer.parseInt(quantityString);
		String totalPriceString = httpRequest.getParameter("totalPrice");
		float totalPrice = Float.parseFloat(totalPriceString);
		String moneyStatusString = httpRequest.getParameter("moneyStatus");
		int moneyStatus = Integer.parseInt(moneyStatusString);
		String invoiceString = httpRequest.getParameter("invoice");
		int invoice = Integer.parseInt(invoiceString);
		String invoiceNo = httpRequest.getParameter("invoiceNo");
		String salesPlatform = httpRequest.getParameter("salesPlatform");
		String courierCompany = httpRequest.getParameter("courierCompany");
		String courierNo = httpRequest.getParameter("courierNo");
		String signTimeString = httpRequest.getParameter("signTime");
		Date signTime = DateUtil.string2DateForController(signTimeString);
		String courierCostString = httpRequest.getParameter("courierCost");
		float courierCost = Float.parseFloat(courierCostString);
		String insuredCostString = httpRequest.getParameter("insuredCost");
		float insuredCost = Float.parseFloat(insuredCostString);
		String city = httpRequest.getParameter("city");
		String genderString = httpRequest.getParameter("gender");
		int gender = Integer.parseInt(genderString);
		String ageString = httpRequest.getParameter("age");
		int age = Integer.parseInt(ageString);
		String jobs = httpRequest.getParameter("jobs");
		otherShipsEntity = new OtherShipsPO(otherShipsId,customerName,consigneeName,receiverAddr,
				phoneNo,deliveryTime,productName,productId,unitPrice,quantity,totalPrice,moneyStatus,invoice,
				invoiceNo,salesPlatform,courierCompany,courierNo,signTime,courierCost,insuredCost,city,gender,age,jobs);
		otherShipsService.update(otherShipsEntity);		
		httpRequest.setAttribute("otherShips", otherShipsEntity);
		try {
			//httpRequest.getRequestDispatcher("/WEB-INF/otherships/otherShipsGet.jsp").forward(httpRequest, httpResponse);
			httpRequest.getRequestDispatcher("/WEB-INF/otherships/otherShipsIndex.jsp").forward(httpRequest, httpResponse);
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
		String otherShipsId = httpRequest.getParameter("otherShipsId"); 
		System.out.println("otherShipsId="+otherShipsId);
		OtherShipsPO otherShipsEntity = otherShipsService.queryById(otherShipsId);
		Date deliveryTime = otherShipsEntity.getDeliveryTime();
		String deliveryTimeString = DateUtil.date2StringForController(deliveryTime);
		Date signTime = otherShipsEntity.getSignTime();
		String signTimeString = DateUtil.date2StringForController(signTime);
		System.out.println("otherShipsEntity="+otherShipsEntity);
		httpRequest.setAttribute("otherShips", otherShipsEntity);
		httpRequest.setAttribute("deliveryTime", deliveryTimeString);
		httpRequest.setAttribute("signTime", signTimeString);
		try {
			httpRequest.getRequestDispatcher("/WEB-INF/otherships/otherShipsUpdate.jsp").forward(httpRequest, httpResponse);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
	
	@RequestMapping(params = "method=searchByIdForDetails")
	public void searchByIdForDetails(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
		System.out.println("search by id");
		try {
			httpRequest.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpResponse.setCharacterEncoding("utf-8");
		httpResponse.setContentType("text/html");
		String otherShipsId = httpRequest.getParameter("otherShipsId"); 
		System.out.println("otherShipsId="+otherShipsId);
		OtherShipsPO otherShipsEntity = otherShipsService.queryById(otherShipsId);
		Date deliveryTime = otherShipsEntity.getDeliveryTime();
		String deliveryTimeString = DateUtil.date2StringForController(deliveryTime);
		Date signTime = otherShipsEntity.getSignTime();
		String signTimeString = DateUtil.date2StringForController(signTime);
		System.out.println("otherShipsEntity="+otherShipsEntity);
		httpRequest.setAttribute("otherShips", otherShipsEntity);
		httpRequest.setAttribute("deliveryTime", deliveryTimeString);
		httpRequest.setAttribute("signTime", signTimeString);
		try {
			httpRequest.getRequestDispatcher("/WEB-INF/otherships/otherShipsDetails.jsp").forward(httpRequest, httpResponse);
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
		String otherShipsId = httpServletRequest.getParameter("otherShipsId");
		OtherShipsPO otherShipsEntity = new OtherShipsPO();
		otherShipsEntity.setOtherShipsId(otherShipsId);
		otherShipsService.delete(otherShipsEntity);
		try {
			//httpServletRequest.getRequestDispatcher("/WEB-INF/otherships/otherShipsGet.jsp").forward(httpServletRequest, httpServletReponse);
			httpServletRequest.getRequestDispatcher("/WEB-INF/otherships/otherShipsIndex.jsp").forward(httpServletRequest, httpServletReponse);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
