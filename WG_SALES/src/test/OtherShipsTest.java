package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.wg.salescount.otherships.dao.OtherShipsDAO;
import com.wg.salescount.otherships.po.OtherShipsPO;
import com.wg.salescount.sales.po.SalesPO;
import com.wg.salescount.util.Pager;
import com.wg.salescount.util.UUIDGenerator;

public class OtherShipsTest {

	private OtherShipsDAO otherShipsDAO;
	
	@Before
	public void setUp() throws Exception {
		BeanFactory factory = new FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml");
		otherShipsDAO =(OtherShipsDAO)  factory.getBean("otherShipsDAO");
	}

	@Test
	public void testQuery() {
		OtherShipsPO otherShips = new OtherShipsPO();
		Pager pager = new Pager();
		pager.setCurrentPage(2);
		pager.setSize(4);
		otherShips.setOtherShipsId(UUIDGenerator.getUUID());
		otherShips.setCustomerName("buyer1");
		otherShips.setConsigneeName("test1");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dt1;
		try {
			dt1 = sdf.parse("2015-5-19");
			otherShips.setDeliveryTime(dt1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		otherShips.setProductName("空气电台");
		Pager pagerForReturn = otherShipsDAO.query(pager, otherShips);
		//System.out.println("pagerForReturn="+pagerForReturn);
		List<Object> otherShipsList = pagerForReturn.getList();
		for(Object otherShipsEntity : otherShipsList){
			System.out.println("otherShipsEntity="+otherShipsEntity);
		}
		System.out.println("CurrentPage="+pagerForReturn.getCurrentPage());
		System.out.println("Size="+pagerForReturn.getSize());
		System.out.println("Total="+pagerForReturn.getTotal());
		System.out.println("TotalPage="+pagerForReturn.getTotalPage());	
		System.out.println("entity size="+otherShipsList.size());
	}

	@Test
	public void testQueryNoPager(){
		OtherShipsPO otherShips = new OtherShipsPO();
		List<OtherShipsPO> list = otherShipsDAO.queryNoPager(otherShips);
		for(OtherShipsPO otherShipsReturn : list){
			System.out.println("otherShipsReturn="+otherShipsReturn);
		}
	}
	
	@Test
	public void testSave(){
		OtherShipsPO otherShips = new OtherShipsPO();
		otherShips.setOtherShipsId(UUIDGenerator.getUUID());
		otherShips.setCustomerName("buyer1");
		otherShips.setConsigneeName("test1");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dt1;
		try {
			dt1 = sdf.parse("2015-5-19");
			otherShips.setDeliveryTime(dt1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		otherShips.setProductName("空气电台");
		otherShips.setUnitPrice(33);
		otherShips.setQuantity(1);
		otherShips.setTotalPrice(33);
		OtherShipsPO otherShipsReturn = otherShipsDAO.save(otherShips);
		System.out.println("otherShipsReturn="+otherShipsReturn);
	}
	
	@Test
	public void testUpdate(){
		OtherShipsPO otherShips = new OtherShipsPO();
		otherShips.setOtherShipsId("6cbb3ff3d63247aabfe528227cee93bb");
		otherShips.setCustomerName("aaa");
		OtherShipsPO otherShipsReturn = otherShipsDAO.update(otherShips);
		System.out.println("otherShipsReturn="+otherShipsReturn);
	}
	
	@Test
	public void testDelete(){
		OtherShipsPO otherShips = new OtherShipsPO();
		otherShips.setOtherShipsId("6cbb3ff3d63247aabfe528227cee93bb");
		otherShipsDAO.delete(otherShips);
	}
}
