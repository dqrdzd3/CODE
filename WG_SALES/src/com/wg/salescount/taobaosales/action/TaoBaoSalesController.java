package com.wg.salescount.taobaosales.action;

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

import com.wg.salescount.sales.po.SalesPO;
import com.wg.salescount.taobaosales.po.TaoBaoSalesPO;
import com.wg.salescount.taobaosales.service.TaoBaoSalesService;
import com.wg.salescount.util.DateUtil;
import com.wg.salescount.util.Pager;
import com.wg.salescount.util.UUIDGenerator;
import com.wg.salescount.weeklysales.po.WeeklySalesPO;

@Controller
@RequestMapping("taobaoSales.do")
public class TaoBaoSalesController {

	@Autowired
	private TaoBaoSalesService taobaoSalesService;
	
	@RequestMapping(params = "method=search")
	public void search(HttpServletRequest httpRequest,HttpServletResponse httpResponse,String method){
		try {
			httpRequest.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpResponse.setCharacterEncoding("utf-8");
		httpResponse.setContentType("text/html");
		TaoBaoSalesPO taobaoSalesEntity = new TaoBaoSalesPO();
		taobaoSalesEntity.setConsigneeName(httpRequest.getParameter("consigneeName"));
		taobaoSalesEntity.setBuyersUsername(httpRequest.getParameter("buyersUsername"));
		taobaoSalesEntity.setPhoneNo(httpRequest.getParameter("phoneNo"));
		taobaoSalesEntity.setProductTitle(httpRequest.getParameter("productTitle"));
		taobaoSalesEntity.setProductSort(httpRequest.getParameter("productSort"));
		String payablesString = httpRequest.getParameter("payables");
		/*float payables = 0L;
		if(!payablesString.equals("") && payablesString != null){
			payables = Float.parseFloat(payablesString);
		}
		taobaoSalesEntity.setPayables(payables);
		String actualPaymentString = httpRequest.getParameter("actualPayment");
		float actualPayment = 0L;
		if(!actualPaymentString.equals("") && actualPaymentString != null){
			actualPayment = Float.parseFloat(actualPaymentString);
		}
		taobaoSalesEntity.setActualPayment(actualPayment);
		String orderStatusString = httpRequest.getParameter("orderStatus");
		int orderStatus = 0;//默认为交易失败
		if(!orderStatusString.equals("") && orderStatusString != null){
			orderStatus = Integer.parseInt(orderStatusString);
		}*/
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
		pager = taobaoSalesService.getList(taobaoSalesEntity, pager);
		List<TaoBaoSalesPO> taoBaoSalesList = pager.getList();
		System.out.println("list="+taoBaoSalesList);
		httpRequest.setAttribute("method", method);
		httpRequest.setAttribute("page", pager);
		httpRequest.setAttribute("list",taoBaoSalesList);
		httpRequest.setAttribute("taoBaoSales", taobaoSalesEntity);
		try {
			httpRequest.getRequestDispatcher("/WEB-INF/taobaosales/taobaoSalesIndex.jsp").forward(httpRequest, httpResponse);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(params = "method=details")
	public void details(HttpServletRequest httpRequest,HttpServletResponse httpResponse,String method){
		try {
			httpRequest.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpResponse.setCharacterEncoding("utf-8");
		httpResponse.setContentType("text/html");
		TaoBaoSalesPO taobaoSalesEntity = new TaoBaoSalesPO();		
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
		pager = taobaoSalesService.getList(taobaoSalesEntity, pager);
		List<TaoBaoSalesPO> taoBaoSalesList = pager.getList();
		System.out.println("list="+taoBaoSalesList);
		httpRequest.setAttribute("method", method);
		httpRequest.setAttribute("page", pager);
		httpRequest.setAttribute("list",taoBaoSalesList);
		httpRequest.setAttribute("taoBaoSales", taobaoSalesEntity);
		try {
			httpRequest.getRequestDispatcher("/WEB-INF/taobaosales/taobaoSalesDetails.jsp").forward(httpRequest, httpResponse);
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
		String taobaoSalesId = UUIDGenerator.getUUID();
		String buyersUsername = httpRequest.getParameter("buyersUsername");
		String buyersAlipayAccount = httpRequest.getParameter("buyersAlipayAccount");
		String payablesString = httpRequest.getParameter("payables"); 
		float payables = 0L;//应付货款默认为0
		if(!payablesString.equals("") && payablesString != null){
			payables = Float.parseFloat(payablesString);
		}
		String actualPaymentString = httpRequest.getParameter("actualPayment");
		float actualPayment = 0L;//实际付款默认为0
		if(!actualPaymentString.equals("") && actualPaymentString != null){
			actualPayment = Float.parseFloat(actualPaymentString);
		}
		String totalPriceString = httpRequest.getParameter("totalPrice");
		float totalPrice = 0L;//实际付款默认为0
		if(!totalPriceString.equals("") && totalPriceString != null){
			totalPrice = Float.parseFloat(totalPriceString);
		}
		String orderStatusString = httpRequest.getParameter("orderStatus");
		int orderStatus = 1;//订单状态默认为1---成功
		if(!orderStatusString.equals("") && orderStatusString != null){
			orderStatus = Integer.parseInt(orderStatusString);
		}
		String buyersMessage = httpRequest.getParameter("buyersMessage");
		String consigneeName = httpRequest.getParameter("consigneeName");
		String receiverAddr = httpRequest.getParameter("receiverAddr");
		String transportMethods = httpRequest.getParameter("transportMethods");
		String phoneNo = httpRequest.getParameter("phoneNo");
		String cellphoneNo = httpRequest.getParameter("cellphoneNo");
		String ordersCreatedTimeString = httpRequest.getParameter("ordersCreatedTime");
		Date ordersCreatedTime = null;
		if(ordersCreatedTimeString != null && !ordersCreatedTimeString.equals("")){
			ordersCreatedTime = DateUtil.string2DateForController(ordersCreatedTimeString);
		}
		System.out.println("ordersCreatedTime="+ordersCreatedTime);
		String ordersPayedTimeString = httpRequest.getParameter("ordersPayedTime");
		Date ordersPayedTime = null;
		if(ordersPayedTimeString != null && !ordersPayedTimeString.equals("")){
			ordersPayedTime = DateUtil.string2DateForController(ordersPayedTimeString);
		}
		System.out.println("ordersPayedTime="+ordersPayedTime);
		System.out.println("ordersCreatedTimeString==="+ordersCreatedTimeString);
		String productTitle = httpRequest.getParameter("productTitle");
		String productSort = httpRequest.getParameter("productSort");
		String quantityString = httpRequest.getParameter("quantity");
		int quantity = 0;//数量默认为0
		if(!quantityString.equals("") && quantityString != null){
			quantity = Integer.parseInt(quantityString);
		}
		String logisticsNo = httpRequest.getParameter("logisticsNo");
		String logisticsCompany = httpRequest.getParameter("logisticsCompany");
		String storeId = httpRequest.getParameter("storeId");
		String storeName = httpRequest.getParameter("storeName");
		String closedReason = httpRequest.getParameter("closedReason");
		TaoBaoSalesPO taobaoSalesEntity = new TaoBaoSalesPO(taobaoSalesId,buyersUsername,buyersAlipayAccount,
				payables,actualPayment,totalPrice,orderStatus,buyersMessage,consigneeName,receiverAddr,transportMethods,
				phoneNo,cellphoneNo,ordersCreatedTime,ordersPayedTime,productTitle,productSort,quantity,logisticsNo,
				logisticsCompany,storeId,storeName,closedReason);
		taobaoSalesService.add(taobaoSalesEntity);
		try {
			//httpRequest.getRequestDispatcher("/WEB-INF/taobaosales/taobaoSalesGet.jsp").forward(httpRequest, httpResponse);
			httpRequest.getRequestDispatcher("/WEB-INF/taobaosales/taobaoSalesIndex.jsp").forward(httpRequest, httpResponse);
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
		String taobaoSalesId = httpRequest.getParameter("taobaoSalesId");
		if(taobaoSalesId == null || taobaoSalesId == "" || taobaoSalesId.isEmpty()){
			System.out.println("enter the method");
			httpRequest.setAttribute("taobaoSalesEntity", null);
			try {
				httpRequest.getRequestDispatcher("/WEB-INF/taobaosales/taobaoSalesIndex.jsp").forward(httpRequest, httpResponse);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		TaoBaoSalesPO taobaoSalesEntity = null;
		httpRequest.setAttribute("taobaoSalesEntity", taobaoSalesEntity);
		String buyersUsername = httpRequest.getParameter("buyersUsername");
		String buyersAlipayAccount = httpRequest.getParameter("buyersAlipayAccount");
		String payablesString = httpRequest.getParameter("payables");
	    float payables = Float.parseFloat(payablesString);
		String actualPaymentString = httpRequest.getParameter("actualPayment");
		float actualPayment = Float.parseFloat(actualPaymentString);
		String totalPriceString = httpRequest.getParameter("totalPrice");
		float totalPrice = Float.parseFloat(totalPriceString);
		String orderStatusString = httpRequest.getParameter("orderStatus");
		int orderStatus = Integer.parseInt(orderStatusString);
		String buyersMessage = httpRequest.getParameter("buyersMessage");
		String consigneeName = httpRequest.getParameter("consigneeName");
		String receiverAddr = httpRequest.getParameter("receiverAddr");
		String transportMethods = 	httpRequest.getParameter("transportMethods");
		String phoneNo = httpRequest.getParameter("phoneNo");	
		String cellphoneNo = httpRequest.getParameter("cellphoneNo");	
		String ordersCreatedTimeString = httpRequest.getParameter("ordersCreatedTime");	
		Date ordersCreatedTime = DateUtil.string2DateForController(ordersCreatedTimeString);
		String ordersPayedTimeString = httpRequest.getParameter("ordersPayedTime");
		Date ordersPayedTime = DateUtil.string2DateForController(ordersPayedTimeString);
		String productTitle = httpRequest.getParameter("productTitle");
		String productSort = httpRequest.getParameter("productSort");
		String quantityString = httpRequest.getParameter("quantity");
		int quantity = Integer.parseInt(quantityString);
		String logisticsNo = httpRequest.getParameter("logisticsNo");
		String logisticsCompany = httpRequest.getParameter("logisticsCompany");
		String storeId = httpRequest.getParameter("storeId");
		String storeName = httpRequest.getParameter("storeName");
		String closedReason = httpRequest.getParameter("closedReason");
		taobaoSalesEntity = new TaoBaoSalesPO(taobaoSalesId,buyersUsername,buyersAlipayAccount,
				payables,actualPayment,totalPrice,orderStatus,buyersMessage,consigneeName,receiverAddr,transportMethods,
				phoneNo,cellphoneNo,ordersCreatedTime,ordersPayedTime,productTitle,productSort,quantity,logisticsNo,
				logisticsCompany,storeId,storeName,closedReason);
		taobaoSalesService.add(taobaoSalesEntity);
		httpRequest.setAttribute("taobaoSales", taobaoSalesEntity);
		try {
			//httpRequest.getRequestDispatcher("/WEB-INF/taobaosales/taobaoSalesGet.jsp").forward(httpRequest, httpResponse);
			httpRequest.getRequestDispatcher("/WEB-INF/taobaosales/taobaoSalesIndex.jsp").forward(httpRequest, httpResponse);
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
		String taobaoSalesId = httpRequest.getParameter("taobaoSalesId"); 
		System.out.println("taobaoSalesId="+taobaoSalesId);
		TaoBaoSalesPO taobaoSalesEntity = taobaoSalesService.queryById(taobaoSalesId);
		Date ordersCreatedTime = taobaoSalesEntity.getOrdersCreatedTime();
		String ordersCreatedTimeString = DateUtil.date2StringForController(ordersCreatedTime);
		Date ordersPayedTime = taobaoSalesEntity.getOrdersPayedTime();
		String ordersPayedTimeString = DateUtil.date2StringForController(ordersPayedTime);
		System.out.println("taobaoSalesEntity="+taobaoSalesEntity);
		httpRequest.setAttribute("taobaoSales", taobaoSalesEntity);
		httpRequest.setAttribute("ordersCreatedTime", ordersCreatedTimeString);
		httpRequest.setAttribute("ordersPayedTime", ordersPayedTimeString);
		try {
			httpRequest.getRequestDispatcher("/WEB-INF/taobaosales/taobaoSalesUpdate.jsp").forward(httpRequest, httpResponse);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
	
	@RequestMapping(params = "method=searchByIdForDetails")
	public void searchByIdForDetails(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
		System.out.println("search by id for details");
		try {
			httpRequest.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpResponse.setCharacterEncoding("utf-8");
		httpResponse.setContentType("text/html");
		String taobaoSalesId = httpRequest.getParameter("taobaoSalesId"); 
		System.out.println("taobaoSalesId="+taobaoSalesId);
		TaoBaoSalesPO taobaoSalesEntity = taobaoSalesService.queryById(taobaoSalesId);
		Date ordersCreatedTime = taobaoSalesEntity.getOrdersCreatedTime();
		String ordersCreatedTimeString = DateUtil.date2StringForController(ordersCreatedTime);
		Date ordersPayedTime = taobaoSalesEntity.getOrdersPayedTime();
		String ordersPayedTimeString = DateUtil.date2StringForController(ordersPayedTime);
		System.out.println("taobaoSalesEntity="+taobaoSalesEntity);
		httpRequest.setAttribute("taobaoSales", taobaoSalesEntity);
		httpRequest.setAttribute("ordersCreatedTime", ordersCreatedTimeString);
		httpRequest.setAttribute("ordersPayedTime", ordersPayedTimeString);
		try {
			httpRequest.getRequestDispatcher("/WEB-INF/taobaosales/taobaoSalesDetails.jsp").forward(httpRequest, httpResponse);
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
		String taobaoSalesId = httpServletRequest.getParameter("taobaoSalesId");
		TaoBaoSalesPO taobaoSalesEntity = new TaoBaoSalesPO();
		taobaoSalesEntity.setTaobaoSalesId(taobaoSalesId);
		taobaoSalesService.delete(taobaoSalesEntity);
		try {
			//httpServletRequest.getRequestDispatcher("/WEB-INF/taobaosales/taobaoSalesGet.jsp").forward(httpServletRequest, httpServletReponse);
			httpServletRequest.getRequestDispatcher("/WEB-INF/taobaosales/taobaoSalesIndex.jsp").forward(httpServletRequest, httpServletReponse);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
