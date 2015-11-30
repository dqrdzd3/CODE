package com.hw.hwsafe.smart.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.smart.pojo.S004PO;
import com.hw.hwsafe.smart.pojo.S005PO;
import com.hw.hwsafe.smart.service.IS004Service;
import com.hw.hwsafe.smart.service.IS005Service;
import com.hw.hwsafe.smart.util.PageUtil;
import com.hw.hwsafe.smart.util.SmartCheckUser;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 创建人：李羽皓 讨论区的回复
 * 
 */
public class S005Action extends BaseAction {
	@Autowired
	private IS005Service s005Service;
	@Autowired
	private IS004Service s004Service;
	private S005PO s005po;
	private S004PO s004po;
	private String ids;

	/**
	 * 
	 * 进入主题
	 * 
	 * @return
	 * @author liyuhao
	 * @create_time 2014年7月30日上午9:25:24
	 */
	public String doIndex() {
		List<Map<String, Object>> replyList = null;
		try {
			String ma001 = null;
			if (s004po != null) {
				ma001 = s004po.getMa001();
			} else {// 翻页时这值传递不过来
				ma001 = request.getParameter("ma001");
			}
			replyList = s005Service.getReplyList(ma001);// 得到该主题下的所有回复，从表
			S004PO s004po = s004Service.retrieveInstanceById(ma001);// 得到该主题的所有信息，主表

			if (replyList != null) {

				// 拿到客户端当前页
				String cur_page = request.getParameter("cur_page");
				// 判断第一次显示时当前页为空时
				if (null == cur_page) {
					cur_page = "1";
				}

				int curPage = Integer.parseInt(cur_page);

				// 数据显示
				PageUtil pageUtil = new PageUtil(replyList, curPage, 5);
				// PageUtil page = new PageUtil(replyList, curPage);
				// 拿到当前数据
				replyList = (List<Map<String, Object>>) pageUtil.getData();

				// 保存
				request.setAttribute("replyList", replyList);
				request.setAttribute("s004po", s004po);

				// 拿到分页工具条，并传递url
				String toolBar = pageUtil.getToolBarForBootStrap("s005?judge=show&ma001=" + ma001);

				// 保存工具条
				request.setAttribute("toolBar", toolBar);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 删除用户的讨论
	 * 
	 * @return
	 * @author liyuhao
	 * @create_time 2014年8月1日下午2:27:33
	 */
	public String doDel() {
		try {
			s005Service.deleteInstanceById(s005po.getMa001());
			setDelSuccessMsg();
		} catch (Exception e) {
			setDelFailedMsg();
			e.printStackTrace();
		}
		return JSON_MSG;
	}

	// ----------接口------------------
	/**
	 * 
	 * 点击某个主题回复
	 * 
	 * @return
	 * @author liyuhao
	 * @create_time 2014年7月10日上午9:45:22
	 */
	public String doReply() {
		// 获取参数
		String ma002 = request.getParameter("TITLEID");
		String ma003 = request.getParameter("REPLYMSG");
		String ma005 = request.getParameter("USERID");
		String sessionId = request.getParameter("SESSIONID");
		Map<String, Object> vertify = new SmartCheckUser().vertify(sessionId);
		callbackDataPO=(CallbackDataPO) vertify.get("callbackDataPO");
		if ((Boolean) vertify.get("isVertify")) {//验证通过
			if (ma002 != null && ma003 != null && ma005 != null) {
				s005po = new S005PO();
				s005po.setMa001(UUIDGenerater.getUUID());
				s005po.setMa002(ma002);
				s005po.setMa003(ma003);
				s005po.setMa005(ma005);
				// 调用service插入数据库
				try {
					s005Service.addMessage(s005po);
					// 服务器端传递到客户端的内容：
					callbackDataPO = new CallbackDataPO("1", "回复成功", 1, null, null, "S005");
				} catch (Exception e) {
					callbackDataPO = new CallbackDataPO("0", "回复失败", 1, null, null, "S005");
					e.printStackTrace();
				}
			} else {
				callbackDataPO = new CallbackDataPO("0", "回复失败", 1, null, null, "S005");
			}
		}

		// 返回客户端消息
		return JSON_DATA;

	}

	/**
	 * 
	 * 点击某个主题查看所有人的讨论记录
	 * 
	 * @return
	 * @author Liyuhao
	 * @create_time 2014年7月14日上午10:00:09
	 */
	public String doGetReplayList() {
		// 获取参数
		String sessionId = request.getParameter("SESSIONID");
		String ma002 = request.getParameter("TITLEID");
		Map<String, Object> vertify = new SmartCheckUser().vertify(sessionId);
		callbackDataPO=(CallbackDataPO) vertify.get("callbackDataPO");
		if ((Boolean) vertify.get("isVertify")) {//验证通过
			List<Map<String, Object>> titleList = null;
			List<S005PO> s005List = new ArrayList<S005PO>();
			if (ma002 != null) {
				try {
					titleList = s005Service.getReplyList(ma002);// 调用service,查询数据库
					for (Map<String, Object> map : titleList) {
						S005PO s005po = new S005PO();
						s005po.setMa001(map.get("ma001")==null?"":map.get("ma001").toString());
						s005po.setMa002(map.get("ma002")==null?"":map.get("ma002").toString());
						s005po.setMa003(map.get("ma003")==null?"":map.get("ma003").toString());
						s005po.setMa004(map.get("ma004")==null?"":map.get("ma004").toString());
						s005po.setMa005(map.get("ma005")==null?"":map.get("ma005").toString());
						s005po.setMa006(map.get("ma006")==null?"":map.get("ma006").toString());
						s005po.setMa007(map.get("ma007")==null?"":map.get("ma007").toString());
						s005po.setMa008(map.get("ma008")==null?"":map.get("ma008").toString());
						s005po.setMa009(map.get("ma009")==null?"":map.get("ma009").toString());
//						if (map.get("ma009")!=null) {
//							
//							Blob blob = (Blob)map.get("ma009");
//							s005po.setMa009(InputStreamToByte(blob.getBinaryStream()));
//						}
						
						s005List.add(s005po);
						// String dstPath =
						// ServletActionContext.getServletContext().getRealPath("/")+map.get("ma009");
//						String path = request.getContextPath();
//						String basePath = request.getScheme() + "://" + request.getServerName() + ":"
//								+ request.getServerPort() + path + "/";
//						String dstPath = basePath + "upload/" + map.get("ma009");
//						if (map.get("ma009") == null) {
//							map.put("ma009", "");
//						} else {
//							map.put("ma009", dstPath);
//						}
					}
					callbackDataPO = new CallbackDataPO("1", "操作成功", s005List.size(), null,
							JSONArray.fromObject(s005List), "S005");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				callbackDataPO = new CallbackDataPO("0", "操作失败", 1, null, null, "S005");
			}
		}
		// 返回数据
		return JSON_DATA;
	}
	private static byte[] InputStreamToByte(InputStream is) throws IOException {  
		ByteArrayOutputStream byteArrOut = new ByteArrayOutputStream();  
		  byte[] temp = new byte[1024];  
		  int len = 0;    
		  while ((len = is.read(temp)) != -1) {  
		   byteArrOut.write(temp, 0, len);  
		  }    
		  byteArrOut.flush();    
		  byte[] bytes = byteArrOut.toByteArray();  
		  byteArrOut.close();
		  is.close();
		  return bytes; 
		 
		}
	public IS005Service getS005Service() {
		return s005Service;
	}

	public void setS005Service(IS005Service s005Service) {
		this.s005Service = s005Service;
	}

	public S005PO getS005po() {
		return s005po;
	}

	public void setS005po(S005PO s005po) {
		this.s005po = s005po;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public S004PO getS004po() {
		return s004po;
	}

	public void setS004po(S004PO s004po) {
		this.s004po = s004po;
	}

	public IS004Service getS004Service() {
		return s004Service;
	}

	public void setS004Service(IS004Service s004Service) {
		this.s004Service = s004Service;
	}

}
