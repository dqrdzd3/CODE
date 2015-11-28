package com.wg.salescount.sales.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wg.salescount.sales.po.SalesPO;
import com.wg.salescount.sales.service.SalesService;
import com.wg.salescount.util.DateUtil;
import com.wg.salescount.util.Excel2Oracle;
import com.wg.salescount.util.Pager;
import com.wg.salescount.util.UUIDGenerator;

import jxl.read.biff.BiffException;

@Controller
@RequestMapping("sales.do")
public class SalesController {
	
	@Autowired
	private SalesService salesService;
	
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
		SalesPO salesEntity = new SalesPO();
		salesEntity.setProductName(httpRequest.getParameter("productName"));
		salesEntity.setModelType(httpRequest.getParameter("modelType"));
		salesEntity.setCustomerName(httpRequest.getParameter("customerName"));
		salesEntity.setConsigneeName(httpRequest.getParameter("consigneeName"));
		String moneyStatusString = httpRequest.getParameter("moneyStatus");
		int moneyStatus = 0;
		if(moneyStatusString != null){
			moneyStatus = Integer.parseInt(moneyStatusString);
		}//默认为全部		
		salesEntity.setMoneyStatus(moneyStatus);
		String invoiceString = httpRequest.getParameter("invoice");
		int invoice = 0;
		if(invoiceString != null){
			invoice = Integer.parseInt(invoiceString);
		}//默认为全部
		salesEntity.setInvoice(invoice);
		salesEntity.setMoneyStatus(moneyStatus);
		String salesPlatformString = httpRequest.getParameter("salesPlatform");
		int salesPlatform = 0;
		if(salesPlatformString != null){
			salesPlatform = Integer.parseInt(salesPlatformString);
		}//默认为空
		salesEntity.setSalesPlatform(salesPlatform);
		/*String moneyStatusString = httpRequest.getParameter("moneyStatus");
		System.out.println("moneyStatusString="+moneyStatusString);
		if(!moneyStatusString.equals("") && moneyStatusString != null){
			int moneyStatus = Integer.parseInt(moneyStatusString);
			System.out.println("moneyStatus"+moneyStatus);
			salesEntity.setMoneyStatus(moneyStatus);
		}else{
			salesEntity.setMoneyStatus(0);//默认未支付
		}*/
		/*String salesPlatformString = httpRequest.getParameter("salesPlatform");
		int salesPlatform = Integer.parseInt(salesPlatformString);
		salesEntity.setSalesPlatform(salesPlatform);*/
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
		/*else if(Integer.parseInt(cur)>pager.getTotalPage()){
			//int c=page.getTotalPage();
			cur = pager.getTotalPage()+"";
		}*/
		else if(Integer.parseInt(cur)<=0){
			cur="1";
		}
		else if(!cur.matches("^[0-9]+$")){
			cur = "1";
		}		
		pager.setCurrentPage(Integer.parseInt(cur));
		System.out.println("currentPage="+pager.getCurrentPage());
		System.out.println("pager="+pager);
		System.out.println("salesEntity="+salesEntity);
		pager = salesService.getList(salesEntity, pager);
		List<SalesPO> salesList = pager.getList();
		System.out.println("list="+salesList);
		httpRequest.setAttribute("method", method);
		httpRequest.setAttribute("page", pager);
		httpRequest.setAttribute("list",salesList);
		httpRequest.setAttribute("sales", salesEntity);
		try {
			httpRequest.getRequestDispatcher("salesIndex").forward(httpRequest, httpResponse);
			//httpRequest.getRequestDispatcher("/WEB-INF/sales/salesIndex.jsp").forward(httpRequest, httpResponse);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(params = "method=add")
	public void add(HttpServletRequest httpRequest,HttpServletResponse httpResponse){
		try {
			httpRequest.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpResponse.setCharacterEncoding("utf-8");
		httpResponse.setContentType("text/html");
		
		String salesId = UUIDGenerator.getUUID();
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
		String buyersNickname = httpRequest.getParameter("buyersNickname");
		String productName = httpRequest.getParameter("productName");
		String productId = httpRequest.getParameter("productId");
		String modelType = httpRequest.getParameter("modelType");
		String color = httpRequest.getParameter("color");
		String unitPriceString = httpRequest.getParameter("unitPrice");
		float unitPrice = 0L;
		if(!unitPriceString.equals("") && unitPriceString != null){
			unitPrice = Float.parseFloat(unitPriceString);
		}
		String quantityString = httpRequest.getParameter("quantity");
		int quantity = 0;
		if(!quantityString.equals("") && quantityString != null){
			quantity = Integer.parseInt(quantityString);
		}
		String totalPriceString = httpRequest.getParameter("totalPrice");
		float totalPrice = 0L;
		if(!totalPriceString.equals("") && totalPriceString != null){
			totalPrice = Float.parseFloat(totalPriceString);
		}
		String moneyStatusString = httpRequest.getParameter("moneyStatus");
		int moneyStatus = 0;//默认为未支付
		if(!moneyStatusString.equals("") && moneyStatusString != null){
			moneyStatus = Integer.parseInt(moneyStatusString);
		}
		System.out.println("moneyStatusString="+moneyStatusString);
		System.out.println("moneyStatus="+moneyStatus);
		String invoiceString = httpRequest.getParameter("invoice");
		int invoice = 0;//默认为不开票
		if(!invoiceString.equals("") && invoiceString != null){
			invoice = Integer.parseInt(invoiceString);
		}
		String invoiceNo = httpRequest.getParameter("invoiceNo");
		String salesPlatformString = httpRequest.getParameter("salesPlatform");
		int salesPlatform = 0;//默认为空
		if(!salesPlatformString.equals("") && salesPlatformString != null){
			salesPlatform = Integer.parseInt(salesPlatformString);
		}
		String courierCompany = httpRequest.getParameter("courierCompany");
		String courierNo = httpRequest.getParameter("courierNo");
		String signTimeString = httpRequest.getParameter("signTime");
		Date signTime = null;
		if(signTimeString != null && !signTimeString.equals("")){
			signTime = DateUtil.string2DateForController(signTimeString);
		}
		String courierCostString = httpRequest.getParameter("courierCost");
		float courierCost = 0L;
		if(!courierCostString.equals("") && courierCostString != null){
			courierCost = Float.parseFloat(courierCostString);
		}		
		float insuredCost = 0L;//不用的字段
		String city = null;
		int gender = 1;//默认为男性
		int age = 0;//
		String jobs = null;
		String remark = httpRequest.getParameter("remark");
		SalesPO salesEntity = new SalesPO(salesId,customerName,consigneeName,receiverAddr,
				phoneNo,deliveryTime,buyersNickname,productName,productId,modelType,color,
				unitPrice,quantity,totalPrice,moneyStatus,invoice,invoiceNo,salesPlatform,
				courierCompany,courierNo,signTime,courierCost,insuredCost,city,gender,age,jobs,remark);
		salesService.add(salesEntity);
		try {
			//httpRequest.getRequestDispatcher("/WEB-INF/sales/salesGet.jsp").forward(httpRequest, httpResponse);
			httpRequest.getRequestDispatcher("/WEB-INF/sales/salesIndex.jsp").forward(httpRequest, httpResponse);
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
		String salesId = httpRequest.getParameter("salesId");
		if(salesId == null || salesId == "" || salesId.isEmpty()){
			System.out.println("enter the method");
			httpRequest.setAttribute("saleEntity", null);
			try {
				httpRequest.getRequestDispatcher("/WEB-INF/sales/salesIndex.jsp").forward(httpRequest, httpResponse);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		SalesPO salesEntity = null;
		httpRequest.setAttribute("salesEntity", salesEntity);
		String customerName = httpRequest.getParameter("customerName");
		String consigneeName = httpRequest.getParameter("consigneeName");
		String receiverAddr = httpRequest.getParameter("receiverAddr");
		String phoneNo = httpRequest.getParameter("phoneNo");
		String deliveryTimeString = httpRequest.getParameter("deliveryTime");
		Date deliveryTime = DateUtil.string2DateForController(deliveryTimeString);
		String buyersNickname = httpRequest.getParameter("buyersNickname");
		String productName = httpRequest.getParameter("productName");
		String productId = httpRequest.getParameter("productId");
		String modelType = httpRequest.getParameter("modelType");
		String color = httpRequest.getParameter("color");
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
		String salesPlatformString = httpRequest.getParameter("salesPlatform");
		int salesPlatform = Integer.parseInt(salesPlatformString);
		String courierCompany = httpRequest.getParameter("courierCompany");
		String courierNo = httpRequest.getParameter("courierNo");
		String signTimeString = httpRequest.getParameter("signTime");
		Date signTime = DateUtil.string2DateForController(signTimeString);
		String courierCostString = httpRequest.getParameter("courierCost");
		float courierCost = Float.parseFloat(courierCostString);
		float insuredCost = 0L;
		/*String insuredCostString = httpRequest.getParameter("insuredCost");
		float insuredCost = Float.parseFloat(insuredCostString);*/
		String city = null;
		//String city = httpRequest.getParameter("city");
		int gender = 1;
		/*String genderString = httpRequest.getParameter("gender");
		int gender = Integer.parseInt(genderString);*/
		int age = 0;
		/*String ageString = httpRequest.getParameter("age");
		int age = Integer.parseInt(ageString);*/
		String jobs = null;
		//String jobs = httpRequest.getParameter("jobs");	
		String remark = httpRequest.getParameter("remark");
		salesEntity = new SalesPO(salesId,customerName,consigneeName,receiverAddr,
				phoneNo,deliveryTime,buyersNickname,productName,productId,modelType,color,
				unitPrice,quantity,totalPrice,moneyStatus,invoice,invoiceNo,salesPlatform,
				courierCompany,courierNo,signTime,courierCost,insuredCost,city,gender,age,jobs,remark);
		salesService.update(salesEntity);
		httpRequest.setAttribute("sales", salesEntity);
		try {
			//httpRequest.getRequestDispatcher("/WEB-INF/sales/salesGet.jsp").forward(httpRequest, httpResponse);
			httpRequest.getRequestDispatcher("/WEB-INF/sales/salesIndex.jsp").forward(httpRequest, httpResponse);
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
		String salesId = httpRequest.getParameter("salesId"); 
		System.out.println("salesId="+salesId);
		SalesPO salesEntity = salesService.queryById(salesId);
		Date deliveryTime = salesEntity.getDeliveryTime();
		String deliveryTimeString = DateUtil.date2StringForController(deliveryTime);
		Date signTime = salesEntity.getSignTime();
		String signTimeString = DateUtil.date2StringForController(signTime);
		System.out.println("salesEntity="+salesEntity.getMoneyStatus());
		httpRequest.setAttribute("sales", salesEntity);
		httpRequest.setAttribute("deliveryTime", deliveryTimeString);
		httpRequest.setAttribute("signTime", signTimeString);
		try {
			httpRequest.getRequestDispatcher("/WEB-INF/sales/salesUpdate.jsp").forward(httpRequest, httpResponse);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
	
	@RequestMapping(params = "method=searchByIdForDetails")
	public void searchByIdForDetails(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
		System.out.println("search by id details");
		try {
			httpRequest.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpResponse.setCharacterEncoding("utf-8");
		httpResponse.setContentType("text/html");
		String salesId = httpRequest.getParameter("salesId"); 
		System.out.println("salesId="+salesId);
		SalesPO salesEntity = salesService.queryById(salesId);
		System.out.println("salesEntity="+salesEntity);
		httpRequest.setAttribute("sales", salesEntity);
		Date deliveryTime = salesEntity.getDeliveryTime();
		String deliveryTimeString = DateUtil.date2StringForController(deliveryTime);
		Date signTime = salesEntity.getSignTime();
		String signTimeString = DateUtil.date2StringForController(signTime);
		httpRequest.setAttribute("deliveryTime", deliveryTimeString);
		httpRequest.setAttribute("signTime", signTimeString);
		try {
			httpRequest.getRequestDispatcher("/WEB-INF/sales/salesDetails.jsp").forward(httpRequest, httpResponse);
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
		String salesId = httpServletRequest.getParameter("salesId");
		SalesPO salesEntity = new SalesPO();
		salesEntity.setSalesId(salesId);
		salesService.delete(salesEntity);
		try {
			//httpServletRequest.getRequestDispatcher("/WEB-INF/sales/salesGet.jsp").forward(httpServletRequest, httpServletReponse);
			httpServletRequest.getRequestDispatcher("/WEB-INF/sales/salesIndex.jsp").forward(httpServletRequest, httpServletReponse);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(params = "method=export")
	public void export(HttpServletRequest httpRequest,HttpServletResponse httpResponse){
		try {
			httpRequest.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpResponse.setCharacterEncoding("utf-8");
		httpResponse.setContentType("text/html");
		SalesPO salesEntity = new SalesPO();
		List<SalesPO> salesList = new ArrayList<SalesPO>();
		salesList = salesService.getListWithoutPager(salesEntity);
		httpRequest.setAttribute("list",salesList);
		try {
			httpRequest.getRequestDispatcher("/WEB-INF/sales/salesExcel.jsp").forward(httpRequest, httpResponse);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(params = "method=import")
	public void upload(HttpServletRequest httpRequest,HttpServletResponse httpResponse){
		try {
			httpRequest.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpResponse.setCharacterEncoding("utf-8");
		httpResponse.setContentType("text/html");
		try {
			//String url = httpRequest.getRequestURL().toString();
			//System.out.println(url);
				//取得根目录路径  
		     //  String rootPath=getClass().getResource("/").getFile().toString();   
		     //  String currentPath2=getClass().getResource("").getFile().toString();  
		     //  System.out.println("rootPath="+rootPath);
		     //  System.out.println("currentPath2="+currentPath2);
			FileSystemView fsv = FileSystemView.getFileSystemView();//桌面路径
			String path = fsv.getHomeDirectory().toString(); 
			//String path = httpRequest.getSession().getServletContext().getRealPath("/").toString();
			
			//System.out.println(path);
			//Excel2Oracle.insert(path+"upload/upload.xls","WEEKLY_SALES_TEST");
			Excel2Oracle.insert(path+"/upload.xls","SC_SALES");
		} catch (BiffException | IOException e) {
			e.printStackTrace();
		}
		try {
			httpRequest.getRequestDispatcher("/WEB-INF/sales/salesIndex.jsp").forward(httpRequest, httpResponse);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*@RequestMapping(params = "method=upload")
	public void uploadFile(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
		
		System.out.println("enter the upload");
		try {
			httpRequest.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpResponse.setCharacterEncoding("utf-8");
		httpResponse.setContentType("text/html");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//factory.setRepository(new File(TEMP_FOLDER));
		factory.setSizeThreshold(1024*1024);
		ServletFileUpload upload = new ServletFileUpload(factory);  
		try {
			List<FileItem> list = upload.parseRequest(httpRequest);
			for(FileItem fileItem : list){
				String fileName = fileItem.getFieldName();
				System.out.println("fileName="+fileName);
				String pathFolder = "c:\\temp";
				
				try {
					fileItem.write(new File(pathFolder,fileName));
					PrintWriter writer = httpResponse.getWriter();  		              
		            writer.print("{");  
		            writer.print("msg:\"文件大小:"+fileItem.getSize()+",文件名:"+fileName+"\"");  
		            writer.print("}");  		              
		            writer.close();  
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			//FileItem item = getUpLoadFileItem(list);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}*/
}
