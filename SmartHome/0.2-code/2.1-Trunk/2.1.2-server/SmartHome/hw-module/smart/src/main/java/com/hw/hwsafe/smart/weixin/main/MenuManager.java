package com.hw.hwsafe.smart.weixin.main;

import com.hw.hwsafe.smart.weixin.pojo.AccessToken;
import com.hw.hwsafe.smart.weixin.pojo.Button;
import com.hw.hwsafe.smart.weixin.pojo.CommonButton;
import com.hw.hwsafe.smart.weixin.pojo.ComplexButton;
import com.hw.hwsafe.smart.weixin.pojo.Menu;
import com.hw.hwsafe.smart.weixin.pojo.ViewButton;
import com.hw.hwsafe.smart.weixin.util.WeixinUtil;







/**
 * 菜单管理器类
 * 
 * @author liufeng
 * @date 2013-08-08
 */
public class MenuManager {
	//private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "wxa3f56f4f6db4de40";
		// 第三方用户唯一凭证密钥
		String appSecret = "f8b68818f49d4989ba5bd94afbeb2023";

//		/System.out.println(	JSONObject.fromObject(getMenu()).toString());
		
		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(), at.getToken());

			// 判断菜单创建结果
			if (0 == result){
			//	log.info("菜单创建成功！");
				System.out.println("成功吧");
			}
			else{
			//	log.info("菜单创建失败，错误码：" + result);
				System.out.println("失败");
			}
		}
	}

	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	public static Menu getMenu() {
//		CommonButton btn11 = new CommonButton();
//		btn11.setName("威店专购");
//		btn11.setType("click");  
//		btn11.setKey("11");
	 ViewButton btn11 = new ViewButton();
	btn11.setName("威店专购");
	  btn11.setType("view");  
	 btn11.setUrl("http://wd.koudai.com/?userid=316021702");

	 ViewButton btn12 = new ViewButton();
		btn12.setName("微信小店");
		btn12.setType("view");  
		btn12.setUrl("http://mp.weixin.qq.com/bizmall/mallshelf?id=&t=mall/list&biz=MzA4MDIwNDQ3OA==&shelf_id=2&showwxpaytitle=1#wechat_redirect");

		
		//产品信息
		ViewButton btn21 = new ViewButton();
		btn21.setName("空气电台A1");
		btn21.setType("view");
		btn21.setUrl("http://mp.weixin.qq.com/s?__biz=MzA4MDIwNDQ3OA==&mid=202473888&idx=1&sn=36feaf7a9f4414f9fdf860d2ae42b376#rd");

		ViewButton btn22 = new ViewButton();
		btn22.setName("空气电台R1");
		btn22.setType("view");
		btn22.setUrl("http://mp.weixin.qq.com/s?__biz=MzA4MDIwNDQ3OA==&mid=202473614&idx=1&sn=9f912563754a82e5a92b2c5ee72b063a#rd");

		ViewButton btn23 = new ViewButton();
		btn23.setName("空气电台E1");
		btn23.setType("view");
		btn23.setUrl("http://mp.weixin.qq.com/s?__biz=MzA4MDIwNDQ3OA==&mid=202473412&idx=1&sn=7a9d02ccc4abb3550004e57f2cf71ca5&scene=18#rd");

		ViewButton btn24 = new ViewButton();
		btn24.setName("核心传感器");
		btn24.setType("view");
		btn24.setUrl("http://mp.weixin.qq.com/s?__biz=MzA4MDIwNDQ3OA==&mid=202475772&idx=1&sn=fd16b7030bcd65954d86f41a8d2a21f0#rd");

		
		//更多体验
		ViewButton btn31 = new ViewButton();
		btn31.setName("专业咨询");
		btn31.setType("view");
		btn31.setUrl("http://mp.weixin.qq.com/s?__biz=MzA4MDIwNDQ3OA==&mid=202474139&idx=1&sn=3362d4cf28ec1473f55170e71997b531#rd");

		ViewButton btn32 = new ViewButton();
		btn32.setName("威微社区");
		  btn32.setType("view");  
		 btn32.setUrl("http://m.wsq.qq.com/264322696");

		 ViewButton btn33 = new ViewButton();
		btn33.setName("威果是谁");
		  btn33.setType("view");  
		 btn33.setUrl("http://mp.weixin.qq.com/s?__biz=MzA4MDIwNDQ3OA==&mid=202474703&idx=1&sn=02143c9151cc1883326de976db22c7a4#rd");
		
		

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("网上商城");
		mainBtn1.setSub_button(new Button[] { btn11, btn12 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("空气电台");
		mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23, btn24 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("关于威果");
		mainBtn3.setSub_button(new Button[] { btn31, btn32, btn33});

		/**
		 * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 * 
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}
}
