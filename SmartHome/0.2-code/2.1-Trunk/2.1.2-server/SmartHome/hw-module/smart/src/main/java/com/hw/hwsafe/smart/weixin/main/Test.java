package com.hw.hwsafe.smart.weixin.main;

import java.io.IOException;
import java.io.StringReader;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.hw.hwsafe.smart.weixin.aes.WXBizMsgCrypt;
import com.hw.hwsafe.smart.weixin.message.resp.TextMessage;
import com.hw.hwsafe.smart.weixin.util.MessageUtil;
import com.qq.weixin.mp.aes.AesException;





public class Test {

	/**
	 * @param args
	 * @throws ParserConfigurationException 
	 */
	public static void main(String[] args){
		// TODO Auto-generated method stub
		/*
		//
		// 第三方回复公众平台
		//

		// 需要加密的明文
		String encodingAesKey = "ofTtBZnj87n5sA51Wz8D73pVUy0xeksnzQsDTqkN1l8";
		String token = "pamtest";
		String timestamp = "1409304348";
		String nonce = "1542652";
		String appId = "wxb11529c136998cb6";
		String replyMsg = " 中文<xml><ToUserName><![CDATA[oia2TjjewbmiOUlr6X-1crbLOvLw]]></ToUserName><FromUserName><![CDATA[gh_7f083739789a]]></FromUserName><CreateTime>1407743423</CreateTime><MsgType><![CDATA[video]]></MsgType><Video><MediaId><![CDATA[eYJ1MbwPRJtOvIEabaxHs7TX2D-HV71s79GUxqdUkjm6Gs2Ed1KF3ulAOA9H1xG0]]></MediaId><Title><![CDATA[testCallBackReplyVideo]]></Title><Description><![CDATA[testCallBackReplyVideo]]></Description></Video></xml>";

		WXBizMsgCrypt pc;
		try {
			pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
			String mingwen = pc.encryptMsg(replyMsg, timestamp, nonce);
			System.out.println("加密后: " + mingwen);

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			StringReader sr = new StringReader(mingwen);
			InputSource is = new InputSource(sr);
			Document document = db.parse(is);

			Element root = document.getDocumentElement();
			NodeList nodelist1 = root.getElementsByTagName("Encrypt");
			NodeList nodelist2 = root.getElementsByTagName("MsgSignature");

			String encrypt = nodelist1.item(0).getTextContent();
			String msgSignature = nodelist2.item(0).getTextContent();

			String format = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
			String fromXML = String.format(format, encrypt);

			//
			// 公众平台发送消息给第三方，第三方处理
			//

			// 第三方收到公众号平台发送的消息
			String result2 = pc.decryptMsg(msgSignature, timestamp, nonce, fromXML);
			System.out.println("解密后明文: " + result2);
		}  catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (com.hw.hwsafe.smart.weixin.aes.AesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		*/
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName("dddddddd");
		textMessage.setFromUserName("fffffff");
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		//textMessage.setFuncFlag(0);
		String respMessage = "欢迎关注";
		
		textMessage.setContent(respMessage);
		System.out.println( MessageUtil.textMessageToXml(textMessage));
	}

}
