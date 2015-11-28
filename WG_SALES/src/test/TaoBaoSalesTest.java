package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.wg.salescount.taobaosales.dao.TaoBaoSalesDAO;
import com.wg.salescount.taobaosales.po.TaoBaoSalesPO;
import com.wg.salescount.util.Pager;
import com.wg.salescount.util.UUIDGenerator;

public class TaoBaoSalesTest {

	private TaoBaoSalesDAO taoBaoSalesDAO;
	
	@Before
	public void setUp() throws Exception {
		//ApplicationContext context =new ClassPathXmlApplicationContext("applicationContext.xml");
		BeanFactory factory = new FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml");
		taoBaoSalesDAO =(TaoBaoSalesDAO)  factory.getBean("taoBaoSalesDAO");
	}


	@Test
	public void testQuery() {
		TaoBaoSalesPO taoBaoSales = new TaoBaoSalesPO();
		Pager pager = new Pager();
		pager.setCurrentPage(2);
		pager.setSize(4);
		taoBaoSales.setProductTitle("空气电台");
		Pager pagerForReturn = taoBaoSalesDAO.query(pager, taoBaoSales);
		List<Object> taoBaoSalesList = pagerForReturn.getList();
		for(Object taoBaoSalesEntity : taoBaoSalesList){
			System.out.println("taoBaoSalesEntity="+taoBaoSalesEntity);
		}
		System.out.println("CurrentPage="+pagerForReturn.getCurrentPage());
		System.out.println("Size="+pagerForReturn.getSize());
		System.out.println("Total="+pagerForReturn.getTotal());
		System.out.println("TotalPage="+pagerForReturn.getTotalPage());	
		System.out.println("entity size="+taoBaoSalesList.size());
	}

	@Test
	public void testQueryNoPager(){
		TaoBaoSalesPO taoBaoSales = new TaoBaoSalesPO();
		List<TaoBaoSalesPO> list = taoBaoSalesDAO.queryNoPager(taoBaoSales);
		for(TaoBaoSalesPO taoBaoSalesReturn : list){
			System.out.println("taoBaoSalesReturn="+taoBaoSalesReturn);
		}
	}
	
	@Test
	public void testSave(){
		TaoBaoSalesPO taoBaoSales = new TaoBaoSalesPO();
		//weeklySales.setWeeklySalesId(UUIDGenerator.getUUID());
		taoBaoSales.setTaobaoSalesId(UUIDGenerator.getUUID());
		taoBaoSales.setBuyersUsername("buyer12");
		taoBaoSales.setBuyersAlipayAccount("buyer-ali12");
		taoBaoSales.setPayables(36);
		taoBaoSales.setActualPayment(36);
		taoBaoSales.setTotalPrice(36);
		taoBaoSales.setOrderStatus(0);
		taoBaoSales.setConsigneeName("test2");
		taoBaoSales.setReceiverAddr("asdfasdfaz");
		taoBaoSales.setTransportMethods("train");
		taoBaoSales.setPhoneNo("010-34523523");
		taoBaoSales.setCellphoneNo("13600000000");
		taoBaoSales.setProductTitle("空气电台");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dt1;
		try {
			dt1 = sdf.parse("2015-5-19");
			taoBaoSales.setOrdersCreatedTime(dt1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.util.Date dt2;
		try {
			dt2 = sdf.parse("2015-5-19");
			taoBaoSales.setOrdersPayedTime(dt2);
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		TaoBaoSalesPO taoBaoSalesReturn = taoBaoSalesDAO.save(taoBaoSales);
		System.out.println("taoBaoSalesReturn="+taoBaoSalesReturn);
	}
	
	@Test
	public void testUpdate(){
		TaoBaoSalesPO taoBaoSales = new TaoBaoSalesPO();
		taoBaoSales.setTaobaoSalesId("72ccd2365ee84b4e88dc3582ec9b921b");
		taoBaoSales.setBuyersUsername("aaaaa");
		TaoBaoSalesPO taoBaoSalesReturn = taoBaoSalesDAO.update(taoBaoSales);
		System.out.println("taoBaoSalesReturn="+taoBaoSalesReturn);
	}
	
	@Test
	public void testDelete(){
		TaoBaoSalesPO taoBaoSales = new TaoBaoSalesPO();
		taoBaoSales.setTaobaoSalesId("72ccd2365ee84b4e88dc3582ec9b921b");
		taoBaoSalesDAO.delete(taoBaoSales);
	}
}
