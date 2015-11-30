package com.hw.hwsafe.core.action;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.constants.HwsafeProjectManager;

public class MainRightAction extends BaseAction {

	private static final String GAOXIN = "gaoxin";
	
	@Override
	public String execute() throws Exception {
		
		return HwsafeProjectManager.isGaoxinProject()
				? GAOXIN
				: SUCCESS;
	}
	
}
