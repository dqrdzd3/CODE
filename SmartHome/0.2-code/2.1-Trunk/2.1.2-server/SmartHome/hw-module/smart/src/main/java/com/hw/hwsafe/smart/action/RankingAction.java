package com.hw.hwsafe.smart.action;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.smart.constants.SmartConstants;
import com.hw.hwsafe.smart.pojo.RankingPO;
import com.hw.hwsafe.smart.pojo.U001PO;
import com.hw.hwsafe.smart.service.IRankingService;
import com.hw.hwsafe.smart.service.IU001Service;

public class RankingAction extends BaseAction {

	@Autowired
	private IU001Service u001Service;
	
	@Autowired
	private IRankingService rankingService;
	/**
	 * 接口：查询排名
	 */
	public String doRank() throws Exception {
		String sessionId = request.getParameter("SESSIONID");
		String userId = request.getParameter("USERID");
		String strScore = request.getParameter("SCORE");
		String type = request.getParameter("GASTYPE");   //气体类型（0:温度，1:湿度，2:co2，3:pm2.5，4:voc，5:c6h6，6:ch2o,7:酒精，8:co,9:ch4）
		String directString = "";
		double score = 0;
		int allUserCount = 0;
		int intType = -1;
		RankingPO rankingPO = new RankingPO();
	
		String message = null;
		if (StringUtils.isBlank(sessionId)) {
			message = SmartConstants.SESSION_ID_ISNULL;
		} else if (StringUtils.isBlank(strScore)) {
			message = "分数无效";
		} else if (StringUtils.isBlank(userId)) {
			message = SmartConstants.USER_ID_ISNULL;
		}
		try {
			score = Double.parseDouble(strScore);
			
		} catch (Exception e) {
			// TODO: handle exception
			message = "分数无效";
		}
		try {
			intType = Integer.parseInt(type);
			
		} catch (Exception e) {
			intType = 3;
		
		}
		
		
		if (message != null) {
			callbackDataPO = new CallbackDataPO("0", message, 0, null, "Rank");
			
		} else {
			
			
			U001PO u001PO = u001Service.checkUserBySessionId(sessionId);
			if (u001PO != null) {
				
				rankingPO.setMa002(userId);
				rankingPO.setMa003(intType);
				rankingPO.setMa007(new Date());
				rankingPO.setMa005(score);
				
				allUserCount = u001Service.getUserCount().intValue();
				if (allUserCount==0) {
					allUserCount = 1;
				}
		
				//通过传递score 计算排名（升序列数）  根据（用户数-序列数）与用户数的比较得出百分比为超出用户的百分比
				
				int rank = rankingService.getRankByScore(rankingPO).intValue();
				
				
				RankingPO po = rankingService.retrieveInstanceByUserid(rankingPO);
				
				if (po!=null) {   //表示有此记录
					//将排名结果更新到rank中
					rankingPO.setMa006(++rank);     
					//读取rank是否已经有数据，没有则插入排名序列数，有则取出排名，与这次的排名进行比较来判断上升up或下降down，并更新
					if (po.getMa006()>=rank) {
						directString = "up";
					}else {
						directString = "down";
					}
					rankingPO.setMa001(po.getMa001());
					rankingService.updateInstance(rankingPO);
					
				}else {
					rankingPO.setMa001(UUID.randomUUID().toString());
					
					rankingPO.setMa006(rank);
					
					rankingService.insertInstance(rankingPO);
					
				}
				

				callbackDataPO = new CallbackDataPO("1",
						"访问成功", 0,
						(allUserCount-rank) * 100 / allUserCount + "%", directString, "Rank");

			} else {
				callbackDataPO = new CallbackDataPO("0",
						"访问失败", 0, null, "Rank");
			}
		}
		return JSON_DATA;
	} 
	
	
	public IU001Service getU001Service() {
		return u001Service;
	}

	public void setU001Service(IU001Service u001Service) {
		this.u001Service = u001Service;
	}

}
