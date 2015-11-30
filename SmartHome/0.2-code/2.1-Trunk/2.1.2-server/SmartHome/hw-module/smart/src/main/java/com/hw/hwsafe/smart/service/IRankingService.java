package com.hw.hwsafe.smart.service;

import com.hw.hwsafe.platform.service.IBaseService;
import com.hw.hwsafe.smart.pojo.RankingPO;

public interface IRankingService extends IBaseService {

	/**
	 * 通过userid获取实例
	 */
	RankingPO retrieveInstanceByUserid(RankingPO po) throws Exception;
	/**
	 * 插入
	 */

	void insertInstance(RankingPO po) throws Exception;
	/**
	 * 修改实例
	 */
	void updateInstance(RankingPO po) throws Exception;
	/**
	 * 通过分数获得名次
	 */
	Integer getRankByScore(RankingPO po) throws Exception;
}
