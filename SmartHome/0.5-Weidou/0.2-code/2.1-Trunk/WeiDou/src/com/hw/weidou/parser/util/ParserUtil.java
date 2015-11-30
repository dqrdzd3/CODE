package com.hw.weidou.parser.util;

import com.hw.weidou.parser.ParserDeamon;
import com.hw.weidou.po.WeidouPo;

/**
 * @author 曾凡
 * @time 2014年12月4日 下午5:01:04
 */
public class ParserUtil {
	/**
	 * 清除威豆
	 * 
	 * @author 曾凡
	 * @time 2014年12月4日 下午5:02:56
	 */
	public static void clearWeidou() {
		WeidouPo weidou = ParserDeamon.getCurrentWeidou();
		weidou.setAvgValue("");
		weidou.setDataContent("");
		weidou.setEquip((byte) 0);
		weidou.setFunc((byte) 0);
		weidou.setPower(0);
		weidou.setMaxValue("");
		weidou.setEquipName("");
		weidou.setVersion("");
	}
}
