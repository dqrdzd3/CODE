package test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.wg.salescount.util.Pager;
import com.wg.salescount.weeklysales.dao.WeeklySalesDAO;
import com.wg.salescount.weeklysales.po.WeeklySalesPO;

public class WeeklySalesTest {
	
	private WeeklySalesDAO weeklySalesDAO; 
	
	//private WeeklySalesService weeklySalesService;
	@Before
	public void setUp() throws Exception {
		//ApplicationContext context =new ClassPathXmlApplicationContext("/applicationContext.xml");
		//ClassPathXmlApplicationContext 这个类,默认获取的是WEB-INF/classes/下的路径,也就是在myeclipse的src下的路径,所以用这个是获取不到WEB-INF下的配置文件的... 
		//引WEB-INF下的一个文件,用这种方式
		BeanFactory factory = new FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml");
		//weeklySalesDAO =(WeeklySalesDAO)  context.getBean("weeklySalesDAO");
		weeklySalesDAO =(WeeklySalesDAO)  factory.getBean("weeklySalesDAO");
		//weeklySalesService =(WeeklySalesService) factory.getBean("weeklySalesService");
	}

	@Test
	public void testQuery() {
		WeeklySalesPO weeklySales = new WeeklySalesPO();
		Pager pager = new Pager();
		pager.setCurrentPage(1);
		pager.setSize(4);
		weeklySales.setProductName("空气电台");
		Pager pagerForReturn = weeklySalesDAO.query(pager, weeklySales);
		//Pager pagerForReturn = weeklySalesService.getList(weeklySales, pager);
		
		System.out.println("pagerForReturn="+pagerForReturn);
		List<Object> WeeklySalesList = pagerForReturn.getList();
		for(Object weeklySalesEntity : WeeklySalesList){
			System.out.println("weeklySalesEntity="+weeklySalesEntity);
		}
		System.out.println("CurrentPage="+pagerForReturn.getCurrentPage());
		System.out.println("Size="+pagerForReturn.getSize());
		System.out.println("Total="+pagerForReturn.getTotal());
		System.out.println("TotalPage="+pagerForReturn.getTotalPage());	
		System.out.println("entity size="+WeeklySalesList.size());
	}
	
	@Test
	public void testQueryNoPager(){
		WeeklySalesPO weeklySales = new WeeklySalesPO();
		List<WeeklySalesPO> list = weeklySalesDAO.queryNoPager(weeklySales);
		for(WeeklySalesPO weeklySalesReturn : list){
			System.out.println("weeklySalesReturn="+weeklySalesReturn);
		}
	}

	@Test
	public void testDelete(){
		WeeklySalesPO weeklySales = new WeeklySalesPO();
		weeklySales.setWeeklySalesId("e2cea303377341cbbd7ef1ebf1469a7c");
		weeklySalesDAO.delete(weeklySales);
	}
	
	@Test
	public void testUpdate(){
		WeeklySalesPO weeklySales = new WeeklySalesPO();
		weeklySales.setProductName("qwe");
		weeklySales.setSpecificationsModel("ew");
		weeklySales.setUnitPrice(1);
		weeklySales.setQuantity(1);
		weeklySales.setTotalPrice(1);
		weeklySales.setWeek(1);
		weeklySales.setYear(1);
		weeklySales.setWeeklySalesId("2b5656c36bf049ff8a01801ca601d942");
		WeeklySalesPO weeklySalesReturn = weeklySalesDAO.update(weeklySales);
		System.out.println("weeklySalesReturn="+weeklySalesReturn);
	}
	
	@Test
	public void testSave(){
		WeeklySalesPO weeklySales = new WeeklySalesPO();
		//weeklySales.setWeeklySalesId(UUIDGenerator.getUUID());
		weeklySales.setProductName("abc");
		weeklySales.setSpecificationsModel("cba");
		weeklySales.setUnitPrice(1);
		weeklySales.setQuantity(1);
		weeklySales.setTotalPrice(1);
		weeklySales.setWeek(1);
		weeklySales.setYear(1);
		WeeklySalesPO weeklySalesReturn = weeklySalesDAO.save(weeklySales);
		System.out.println("weeklySalesReturn="+weeklySalesReturn);
	}
	
}
