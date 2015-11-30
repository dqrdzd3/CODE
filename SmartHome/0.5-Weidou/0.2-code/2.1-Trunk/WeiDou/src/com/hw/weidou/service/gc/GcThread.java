package com.hw.weidou.service.gc;

import com.hw.util.ThreadClassTemplet;

/**
 * @author 曾凡
 * @time 2014年11月6日 下午2:39:08
 */
public class GcThread extends ThreadClassTemplet {

	public GcThread(int sleepTimes) {
		super(sleepTimes);
	}

	@Override
	protected void runFun() {
		System.gc();
	}
}
