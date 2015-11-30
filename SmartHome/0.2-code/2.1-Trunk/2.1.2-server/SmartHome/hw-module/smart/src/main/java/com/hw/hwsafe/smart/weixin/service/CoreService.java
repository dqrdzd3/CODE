package com.hw.hwsafe.smart.weixin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hw.hwsafe.platform.util.ServiceBeanUtils;
import com.hw.hwsafe.smart.service.IWeixinService;
import com.hw.hwsafe.smart.weixin.main.MenuManager;
import com.hw.hwsafe.smart.weixin.main.WeixinConstant;
import com.hw.hwsafe.smart.weixin.message.resp.Article;
import com.hw.hwsafe.smart.weixin.message.resp.NewsMessage;
import com.hw.hwsafe.smart.weixin.message.resp.TextMessage;
import com.hw.hwsafe.smart.weixin.pojo.AccessToken;
import com.hw.hwsafe.smart.weixin.util.MessageUtil;
import com.hw.hwsafe.smart.weixin.util.UploadAttach;
import com.hw.hwsafe.smart.weixin.util.WeixinUtil;

/**
 * 核心服务类
 * 
 * @author liufeng
 * @date 2013-07-25
 */
public class CoreService {
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	 private static IWeixinService wxService =
	 (IWeixinService)ServiceBeanUtils.getBean("weixinService");

	private static Log log = LogFactory.getLog(CoreService.class);

	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			respMessage = "请求处理异常，请稍候尝试！";
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 默认回复此文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			// textMessage.setFuncFlag(0);
			// // 由于href属性值必须用双引号引起，这与字符串本身的双引号冲突，所以要转义

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				// 接收用户发送的文本消息内容
				String content = requestMap.get("Content").trim();
				
				textMessage.setContent("欢迎");
				respMessage = MessageUtil.textMessageToXml(textMessage);

				if (content.length()==11) {
					textMessage.setContent(wxService.getSensorDetailToWX(content));
					respMessage = MessageUtil.textMessageToXml(textMessage);
				}
				
				
				if (content.length()==12) {
					textMessage.setContent(wxService.getSensorHistoryToWX(content));
					respMessage = MessageUtil.textMessageToXml(textMessage);
				}
				
				

				// 创建图文消息
				NewsMessage newsMessage = new NewsMessage();
				newsMessage.setToUserName(fromUserName);
				newsMessage.setFromUserName(toUserName);
				newsMessage.setCreateTime(new Date().getTime());
				newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				// newsMessage.setFuncFlag(0);

				List<Article> articleList = new ArrayList<Article>();
				// 单图文消息
				if ("1".equals(content)) {
					Article article = new Article();
					article.setTitle("微信公众帐号开发教程Java版");
					article.setDescription("柳峰，80后，微信公众帐号开发经验4个月。为帮助初学者入门，特推出此系列教程，也希望借此机会认识更多同行！");
					article.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");
					article.setUrl("http://blog.csdn.net/lyq8479");
					articleList.add(article);
					// 设置图文消息个数
					newsMessage.setArticleCount(articleList.size());
					// 设置图文消息包含的图文集合
					newsMessage.setArticles(articleList);
					// 将图文消息对象转换成xml字符串
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}
				// 单图文消息---不含图片
				else if ("2".equals(content)) {
					Article article = new Article();
					article.setTitle("微信公众帐号开发教程Java版");
					// 图文消息中可以使用QQ表情、符号表情
					article.setDescription("柳峰，80后，"
							+ emoji(0x1F6B9)
							+ "，微信公众帐号开发经验4个月。为帮助初学者入门，特推出此系列连载教程，也希望借此机会认识更多同行！\n\n目前已推出教程共12篇，包括接口配置、消息封装、框架搭建、QQ表情发送、符号表情发送等。\n\n后期还计划推出一些实用功能的开发讲解，例如：天气预报、周边搜索、聊天功能等。");
					// 将图片置为空
					article.setPicUrl("");
					article.setUrl("http://blog.csdn.net/lyq8479");
					articleList.add(article);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}
				// 多图文消息
				else if ("3".equals(content)) {

					Article article1 = new Article();
					article1.setTitle("豫神金牛\n引言");
					article1.setDescription("河南豫神劲牛动物保健品有限公司，是集动物药品、饲料、保健品研制开发、生产销售和技术服务为一体并独立通过农业部GMP认证的现代化高新技术企业。公司座落于河南开封市范村工业园区，这里风景秀丽，交通便捷，信息灵通，古城开封厚重的历史文化，浓烈的现代气息，为公司的创立和发展提供了良好的自然环境和人文氛围。");
					article1.setPicUrl("http://www.hnysjn.com/images/corp.jpg");
					article1.setUrl("http://www.hnysjn.com");

					Article article2 = new Article();
					article2.setTitle("产品\n劲牛1号");
					article2.setDescription("突破以往用苦寒之品以毒攻毒的疗法，而采取舒肝解郁、健脾化滞的疗法，因势利导以化解烈性瘟症，反而起到立杆见影的理想效果，虽为纯中药，但效果之快堪比任何西药");
					article2.setPicUrl("http://www.hnysjn.com/images/jn1h.jpg");
					article2.setUrl("http://www.hnysjn.com/detail.html");

					Article article3 = new Article();
					article3.setTitle("喉炎净散");
					article3.setDescription("本品具有广泛的抗病毒、止咳平喘、抗菌消炎、退烧、止血收敛");
					article3.setPicUrl("http://www.hnysjn.com/images/gyjs.jpg");
					article3.setUrl("http://www.hnysjn.com/detail.html");

					Article article4 = new Article();
					article4.setTitle("小柴胡散");
					article4.setDescription("主治：用于急发或危重型病毒性疾病，肉、蛋鸡均适用！产品特点：1、本品为荆防解毒散国标配方，对各种病毒性疾病都有良好的治疗效果，尤其对很多病毒疾病初发有立竿见影的治疗效果，使用越早效果越明显，如到后期须配合抗菌药物治疗");
					article4.setPicUrl("http://www.hnysjn.com/images/xcgs.jpg");
					article4.setUrl("http://www.hnysjn.com/detail.html");

					articleList.add(article1);
					articleList.add(article2);
					articleList.add(article3);
					articleList.add(article4);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);

				}
				// 多图文消息---首条消息不含图片
				else if ("4".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("微信公众帐号开发教程Java版");
					article1.setDescription("");
					// 将图片置为空
					article1.setPicUrl("");
					article1.setUrl("http://blog.csdn.net/lyq8479");

					Article article2 = new Article();
					article2.setTitle("第4篇\n消息及消息处理工具的封装");
					article2.setDescription("");
					article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
					article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8949088");

					Article article3 = new Article();
					article3.setTitle("第5篇\n各种消息的接收与响应");
					article3.setDescription("");
					article3.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
					article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8952173");

					Article article4 = new Article();
					article4.setTitle("第6篇\n文本消息的内容长度限制揭秘");
					article4.setDescription("");
					article4.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
					article4.setUrl("http://blog.csdn.net/lyq8479/article/details/8967824");

					articleList.add(article1);
					articleList.add(article2);
					articleList.add(article3);
					articleList.add(article4);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}
				// 多图文消息---最后一条消息不含图片
				else if ("5".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("第7篇\n文本消息中换行符的使用");
					article1.setDescription("");
					article1.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");
					article1.setUrl("http://blog.csdn.net/lyq8479/article/details/9141467");

					Article article2 = new Article();
					article2.setTitle("第8篇\n文本消息中使用网页超链接");
					article2.setDescription("");
					article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
					article2.setUrl("http://blog.csdn.net/lyq8479/article/details/9157455");

					Article article3 = new Article();
					article3.setTitle("如果觉得文章对你有所帮助，请通过博客留言或关注微信公众帐号xiaoqrobot来支持柳峰！");
					article3.setDescription("");
					// 将图片置为空
					article3.setPicUrl("");
					article3.setUrl("http://blog.csdn.net/lyq8479");

					articleList.add(article1);
					articleList.add(article2);
					articleList.add(article3);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);

				}
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respMessage = "您发送的是图片信息";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respMessage = "您发送的是地理位置信息";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respMessage = "您发送的是链接信息";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respMessage = "您发送的是音频信息";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respMessage = "欢迎关注 威果科技 \n功能1:输入手机号可查询实时数据；\n功能2：输入设备号可查询过去7天历史数据";
	
					textMessage.setContent(respMessage);

					respMessage = MessageUtil.textMessageToXml(textMessage);

					createMenu();
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
					log.info("事件：取消订阅");
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应

					textMessage.setContent(respMessage);
					respMessage = MessageUtil.textMessageToXml(textMessage);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				log.info("异常：" + e.getMessage());

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				log.info("异常：" + e1.getMessage());
			}
		}
		return respMessage;
	}

	/**
	 * emoji表情转换(hex -> utf-16)
	 * 
	 * @param hexEmoji
	 * @return
	 */
	public static String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}

	/**
	 * 自定义menu
	 * 
	 * @throws Exception
	 */
	public static void createMenu() throws Exception {
		// 第三方用户唯一凭证
		String appId = WeixinConstant.appId;
		// 第三方用户唯一凭证密钥
		String appSecret = WeixinConstant.appSecret;

		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			// 调用接口创建菜单

			int result = WeixinUtil.createMenu(MenuManager.getMenu(),
					at.getToken());

		}
	}

	public static String createTW(String fromUserName, String toUserName,
			List<Article> articleList) {
		String respMessage = null;
		// 默认返回的文本消息内容
		respMessage = "请求处理异常，请稍候尝试！";
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		// newsMessage.setFuncFlag(0);

		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);
		respMessage = MessageUtil.newsMessageToXml(newsMessage);

		return respMessage;
	}

	public static String upload() throws Exception {
		// 第三方用户唯一凭证
		String appId = WeixinConstant.appId;
		// 第三方用户唯一凭证密钥
		String appSecret = WeixinConstant.appSecret;

		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			// 调用接口创建菜单

			return UploadAttach.Upload(at.getToken(), "image", "E:/bg.png")
					.toString();

		}
		return "sdddd";
	}

}
