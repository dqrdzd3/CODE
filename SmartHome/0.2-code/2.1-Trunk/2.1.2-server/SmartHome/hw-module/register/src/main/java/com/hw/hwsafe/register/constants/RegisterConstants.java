package com.hw.hwsafe.register.constants;

import com.hw.hwsafe.platform.constants.BasePropertiesManager;

public class RegisterConstants {

	private static final BasePropertiesManager registerPropertiesManager = RegisterPropertiesManager.getInstance();
	
	// -------------------- register --------------------
	public static final String REGISTER_SUCCESS_ONE;
	public static final String REGISTER_SUCCESS_TWO;
	public static final String REGISTER_SUCCESS_THREE;
	public static final String REGISTER_SUCCESS_FOUR;
	public static final String REGISTER_FAILURE;
	public static final String CHECK_SUCCESS_ONE;
	public static final String CHECK_SUCCESS_TWO;
	public static final String CHECK_SUCCESS_THREE;
	public static final String CHECK_SUCCESS_FOUR;
	public static final String CHECK_FAILURE_ONE;
	public static final String CHECK_FAILURE_TWO;
	public static final String CHECK_FAILURE_THREE;
	public static final String CHECK_FAILURE_FOUR;
	
	static{
		REGISTER_SUCCESS_ONE = registerPropertiesManager.getPropertyStr("REGISTER_SUCCESS_ONE");
		REGISTER_SUCCESS_TWO = registerPropertiesManager.getPropertyStr("REGISTER_SUCCESS_TWO");
		REGISTER_SUCCESS_THREE = registerPropertiesManager.getPropertyStr("REGISTER_SUCCESS_THREE");
		REGISTER_SUCCESS_FOUR = registerPropertiesManager.getPropertyStr("REGISTER_SUCCESS_FOUR");
		REGISTER_FAILURE = registerPropertiesManager.getPropertyStr("REGISTER_FAILURE");
		CHECK_SUCCESS_ONE = registerPropertiesManager.getPropertyStr("CHECK_SUCCESS_ONE");
		CHECK_SUCCESS_TWO = registerPropertiesManager.getPropertyStr("CHECK_SUCCESS_TWO");
		CHECK_SUCCESS_THREE = registerPropertiesManager.getPropertyStr("CHECK_SUCCESS_THREE");
		CHECK_SUCCESS_FOUR = registerPropertiesManager.getPropertyStr("CHECK_SUCCESS_FOUR");
		CHECK_FAILURE_ONE = registerPropertiesManager.getPropertyStr("CHECK_FAILURE_ONE");
		CHECK_FAILURE_TWO = registerPropertiesManager.getPropertyStr("CHECK_FAILURE_TWO");
		CHECK_FAILURE_THREE = registerPropertiesManager.getPropertyStr("CHECK_FAILURE_THREE");
		CHECK_FAILURE_FOUR = registerPropertiesManager.getPropertyStr("CHECK_FAILURE_FOUR");
	}
	
}
