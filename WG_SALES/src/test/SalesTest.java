package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.wg.salescount.sales.dao.SalesDAO;
import com.wg.salescount.sales.po.SalesPO;
import com.wg.salescount.util.Pager;
import com.wg.salescount.util.UUIDGenerator;

public class SalesTest {
	
	private SalesDAO salesDAO;
	@Before
	public void setUp() throws Exception {
		//ApplicationContext context =new ClassPathXmlApplicationContext("applicationContext.xml");
		BeanFactory factory = new FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml");
		salesDAO =(SalesDAO)  factory.getBean("salesDAO");
	}


	@Test
	public void testQuery() {
		SalesPO sales = new SalesPO();
		Pager pager = new Pager();
		pager.setCurrentPage(2);
		pager.setSize(4);
		sales.setProductName("空气电台");
		Pager pagerForReturn = salesDAO.query(pager, sales);
		//System.out.println("pagerForReturn="+pagerForReturn);
		List<Object> salesList = pagerForReturn.getList();
		for(Object salesEntity : salesList){
			System.out.println("salesEntity="+salesEntity);
		}
		System.out.println("CurrentPage="+pagerForReturn.getCurrentPage());
		System.out.println("Size="+pagerForReturn.getSize());
		System.out.println("Total="+pagerForReturn.getTotal());
		System.out.println("TotalPage="+pagerForReturn.getTotalPage());	
		System.out.println("entity size="+salesList.size());
	}

	@Test
	public void testQueryNoPager(){
		SalesPO sales = new SalesPO();
		List<SalesPO> list = salesDAO.queryNoPager(sales);
		for(SalesPO salesReturn : list){
			System.out.println("salesReturn="+salesReturn);
		}
	}
	
	@Test
	public void testSave(){
		SalesPO sales = new SalesPO();
		sales.setSalesId(UUIDGenerator.getUUID());
		sales.setCustomerName("buyer1");
		sales.setConsigneeName("test1");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dt1;
		try {
			dt1 = sdf.parse("2015-5-19");
			sales.setDeliveryTime(dt1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		sales.setProductName("空气电台");
		sales.setUnitPrice(33);
		sales.setQuantity(1);
		sales.setTotalPrice(33);
		SalesPO salesReturn = salesDAO.save(sales);
		System.out.println("salesReturn="+salesReturn);
	}
	
	@Test
	public void testUpdate(){
		SalesPO sales = new SalesPO();
		sales.setSalesId("48e0ea3e4360452f8ce173c094a38d85");
		sales.setCustomerName("aaa");
		SalesPO salesReturn = salesDAO.update(sales);
		System.out.println("salesReturn="+salesReturn);
	}
	
	@Test
	public void testDelete(){
		SalesPO sales = new SalesPO();
		sales.setSalesId("48e0ea3e4360452f8ce173c094a38d85");
		salesDAO.delete(sales);
	}
}
