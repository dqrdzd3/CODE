package com.wg.salescount.delivery.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wg.salescount.delivery.dao.impl.P001DAOImpl;
import com.wg.salescount.delivery.po.P001PO;
import com.wg.salescount.delivery.service.DeliveryBySupplierService;
import com.wg.salescount.delivery.service.DeliveryByThirdPartyService;
import com.wg.salescount.delivery.service.P001Service;
import com.wg.salescount.delivery.vo.DeliveryBySupplierVO;
import com.wg.salescount.delivery.vo.DeliveryByThirdPartyVO;

@Controller
@RequestMapping("delivery.do")
public class DeliveryController {

	private static Log log = LogFactory.getLog(DeliveryController.class);
	
	@Autowired
	DeliveryBySupplierService deliveryBySupplierService;
	@Autowired
	DeliveryByThirdPartyService deliveryByThirdPartyService;
	@Autowired
	P001Service p001Service;
	
	public DeliveryController() {
	}

	@RequestMapping(params ="method=update")
	public void update(HttpServletRequest httpRequest,HttpServletResponse httpResponse){
		try {
			httpRequest.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpResponse.setCharacterEncoding("utf-8");
		httpResponse.setContentType("text/html");
		String equipmentId = httpRequest.getParameter("equipmentId");
		String type = httpRequest.getParameter("type");
		log.debug("type="+type);
		//System.out.println(type);
		/*int type = 1;
		if(typeString != null){
			type = Integer.parseInt(typeString);
		}*/
		String companyName = httpRequest.getParameter("companyName");
		log.debug("companyName="+companyName);
		//System.out.println(companyName);
		if(type.equals("toB")){//toB
			String[] equipmentIdArray = null;
			if(equipmentId.contains("*")){
				//
				if(equipmentId.startsWith("*")){
					equipmentId = equipmentId.substring(1);
					if(equipmentId.contains("*")){
							equipmentIdArray = equipmentId.split("[*]");
				        }
					}else{
						if(equipmentId.contains("*")){
							equipmentIdArray = equipmentId.split("[*]");
						}
					}
				//
				List<DeliveryBySupplierVO> deliveryBySupplierVOList = new ArrayList<DeliveryBySupplierVO>();
				//String[] equipmentIdArray = equipmentId.split("#");
				for (int i = 0; i < equipmentIdArray.length; i++) {
					DeliveryBySupplierVO vo = new DeliveryBySupplierVO();
					vo.setEquipmentId(equipmentIdArray[i]);
					vo.setCompanyName(companyName);
					//vo.setCompanyName(companyName);
					DeliveryBySupplierVO deliveryBySupplier = deliveryBySupplierService.queryCompanyId(vo);
					deliveryBySupplier.setEquipmentId(equipmentIdArray[i]);
					deliveryBySupplierVOList.add(deliveryBySupplier);
		        }
				log.debug("deliveryBySupplierVOList="+deliveryBySupplierVOList);
				//System.out.println("deliveryBySupplierVOList="+deliveryBySupplierVOList);
				List<DeliveryBySupplierVO> list = deliveryBySupplierService.batchUpdate(deliveryBySupplierVOList);
				/*for(DeliveryBySupplierVO vo : deliveryBySupplierVOList){
					System.out.println("vo="+vo);
					DeliveryBySupplierVO deliveryBySupplier = deliveryBySupplierService.queryCompanyId(vo);
					deliveryBySupplier.setEquipmentId(equipmentId);
					System.out.println("deliveryBySupplier="+deliveryBySupplier);
				}*/
				log.debug("update resultlist="+list);
				//System.out.println("update resultlist="+list);
				if(list == null || list.isEmpty()){
					httpRequest.setAttribute("flag", false);
				}else{
					httpRequest.setAttribute("flag", true);
				}
			}else{
				DeliveryBySupplierVO vo = new DeliveryBySupplierVO();
				//vo.setCompanyName(companyName);
				vo.setCompanyName(companyName);
				vo.setEquipmentId(equipmentId);
				DeliveryBySupplierVO deliveryBySupplier = deliveryBySupplierService.queryCompanyId(vo);
				//String corpBusinessId = deliveryBySupplier.getCorpBusinessId();
				deliveryBySupplier.setEquipmentId(equipmentId);	
				DeliveryBySupplierVO deliveryBySupplierForUpdate = deliveryBySupplierService.updateP001(deliveryBySupplier);
				if(deliveryBySupplierForUpdate == null){
					httpRequest.setAttribute("flag", false);
				}else{
					httpRequest.setAttribute("flag", true);
				}
				log.debug("update result="+deliveryBySupplierForUpdate);
				//System.out.println("update result="+deliveryBySupplierForUpdate);
			}			
		}else if(type.equals("接口")){//interface
			if(equipmentId.contains("*")){
				log.debug("enter interface");
				//System.out.println("enter jiekou duo");
				String[] equipmentIdArray = null;
				//
				if(equipmentId.startsWith("*")){
					equipmentId = equipmentId.substring(1);
					if(equipmentId.contains("*")){
							equipmentIdArray = equipmentId.split("[*]");
				        }
					}else{
						if(equipmentId.contains("*")){
							equipmentIdArray = equipmentId.split("[*]");
						}
					}
				//
				List<DeliveryByThirdPartyVO> deliveryByThirdPartyVOList = new ArrayList<DeliveryByThirdPartyVO>();
				//String[] equipmentIdArray = equipmentId.split("#");
				for (int i = 0; i < equipmentIdArray.length; i++) {
					DeliveryByThirdPartyVO vo = new DeliveryByThirdPartyVO();
					vo.setEquipmentId(equipmentIdArray[i]);
					vo.setCompanyName(companyName);
					//vo.setCompanyName(companyName);
					DeliveryByThirdPartyVO deliveryByThirdParty = deliveryByThirdPartyService.queryCompanyId(vo);
					deliveryByThirdParty.setEquipmentId(equipmentIdArray[i]);
					deliveryByThirdPartyVOList.add(deliveryByThirdParty);
		        }
				log.debug("deliveryByThirdPartyVOList="+deliveryByThirdPartyVOList);
				//System.out.println("deliveryByThirdPartyVOList="+deliveryByThirdPartyVOList);
				List<DeliveryByThirdPartyVO> list = deliveryByThirdPartyService.batchUpdate(deliveryByThirdPartyVOList);
				log.debug("list for return ="+list);
				//System.out.println("list for return ="+list);
				if(list == null || list.isEmpty()){
					httpRequest.setAttribute("flag", false);
				}else{
					httpRequest.setAttribute("flag", true);
				}
			}else{
				log.debug("enter single interface");
				//System.out.println("enter jiekou dan");
				DeliveryByThirdPartyVO vo = new DeliveryByThirdPartyVO();
				vo.setCompanyName(companyName);
				vo.setEquipmentId(equipmentId);
				DeliveryByThirdPartyVO deliveryByThirdParty = deliveryByThirdPartyService.queryCompanyId(vo);
				//String interfaceCompanyId = deliveryByThirdParty.getInterfaceCompanyId();
				deliveryByThirdParty.setEquipmentId(equipmentId);
				DeliveryByThirdPartyVO deliveryByThirdPartyForUpdate = deliveryByThirdPartyService.updateP001(deliveryByThirdParty);
				System.out.println("update result="+deliveryByThirdPartyForUpdate);
				if(deliveryByThirdPartyForUpdate == null){
					httpRequest.setAttribute("flag", false);
				}else{
					httpRequest.setAttribute("flag", true);
				}
			}		
									
		}else{
			httpRequest.setAttribute("flag", false);
		}
		httpRequest.setAttribute("secondTime", true);
		try {
			httpRequest.getRequestDispatcher("deliveryBinding").forward(httpRequest, httpResponse);
			//httpRequest.getRequestDispatcher("deliveryBinding1.jsp").forward(httpRequest,httpResponse);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(params ="method=go")
	public void getCompanyName(HttpServletRequest httpRequest,HttpServletResponse httpResponse){
		try {
			httpRequest.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpResponse.setCharacterEncoding("utf-8");
		httpResponse.setContentType("text/html");
		List<String> companyName = new ArrayList<String>();
		String typeString = httpRequest.getParameter("type");
		int type = 1;
		if(typeString != null){
			type = Integer.parseInt(typeString);
		}
		if(type == 1){
			//String 		
			companyName = deliveryBySupplierService.queryCompanyName();
		}else{
			companyName = deliveryByThirdPartyService.queryCompanyName();
		}
		log.debug("companyName"+companyName);
		//System.out.println(companyName);
		httpRequest.setAttribute("companyName", companyName);
		try {
			httpRequest.getRequestDispatcher("deliveryBinding").forward(httpRequest, httpResponse);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
	

	
	
	@RequestMapping(params ="method=validate1")
	public void validateBinding(HttpServletRequest httpRequest,HttpServletResponse httpResponse){
		log.debug("enter validate controller");
		//System.out.println("enter validate controller");
		try {
			httpRequest.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpResponse.setCharacterEncoding("utf-8");
		httpResponse.setContentType("text/html");
		String equipmentId = httpRequest.getParameter("equipmentId");
		String equipmentIdForExist = httpRequest.getParameter("equipmentId");
		String[] equipmentIdArray = null;
		List<P001PO> p001POList = new ArrayList<P001PO>();
		if(equipmentId.contains("*")){
			//
			if(equipmentId.startsWith("*")){
				equipmentId = equipmentId.substring(1);
				if(equipmentId.contains("*")){
						equipmentIdArray = equipmentId.split("[*]");
			        }
				}else{
					if(equipmentId.contains("*")){
						equipmentIdArray = equipmentId.split("[*]");
					}
				}
			
			for (int i = 0; i < equipmentIdArray.length; i++) {
				P001PO po = new P001PO();
				po.setMA002(equipmentIdArray[i]);
				List<P001PO> p001List = p001Service.queryExist(po);
				if(!p001List.isEmpty()){
					for(P001PO p001 : p001List){
						p001POList.add(p001);
					}
				}
	        }
			
		}else{
			P001PO po = new P001PO();
			po.setMA002(equipmentId);
			List<P001PO> p001List = p001Service.queryExist(po);
			if(!p001List.isEmpty()){
				for(P001PO p001 : p001List){
					p001POList.add(p001);
				}
			}
		}
		equipmentIdForExist = equipmentIdForExist.trim();
		if(!p001POList.isEmpty() && equipmentIdForExist != null){
			log.debug("equipmentId="+equipmentId);
			//System.out.println("equipmentId="+equipmentId);
			httpRequest.setAttribute("exist", true);
		}else{
			httpRequest.setAttribute("exist", false);
		}
		try {
			httpRequest.getRequestDispatcher("deliveryBinding").forward(httpRequest, httpResponse);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
}
