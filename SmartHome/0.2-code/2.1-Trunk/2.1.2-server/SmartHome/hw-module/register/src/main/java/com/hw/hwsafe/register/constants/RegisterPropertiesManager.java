package com.hw.hwsafe.register.constants;

import com.hw.hwsafe.platform.constants.BasePropertiesManager;

public class RegisterPropertiesManager extends BasePropertiesManager {

	/**
	 * 资源文件路径
	 */
	private static final String PROPERTIES_PATH = "/register_constants.properties";

	private static final RegisterPropertiesManager instance = new RegisterPropertiesManager();

	private RegisterPropertiesManager() {
	}

	public static RegisterPropertiesManager getInstance() {
		return instance;
	}

	@Override
	protected String getPorpertiesPath() {
		return PROPERTIES_PATH;
	}

}
