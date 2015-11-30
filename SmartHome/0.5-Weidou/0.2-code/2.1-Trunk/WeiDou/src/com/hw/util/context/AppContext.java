package com.hw.util.context;

import android.app.Application;


public class AppContext extends Application {
	public static final int PAGE_SIZE = 10;// 默认分页大小

	public static final String UTF_8 = "UTF-8";

	public static final boolean DEBUG = false;
	/**
	 * 是不是在测试服务器
	 */
	public static boolean IS_TEST_SERVER = false;
	

}
