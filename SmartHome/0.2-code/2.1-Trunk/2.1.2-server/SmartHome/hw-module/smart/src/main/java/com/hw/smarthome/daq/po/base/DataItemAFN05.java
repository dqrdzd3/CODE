package com.hw.smarthome.daq.po.base;

public abstract class DataItemAFN05 extends DataItem {

	private static final long serialVersionUID = -833274440703465513L;
	/** 是否下发成功 */
	public boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
