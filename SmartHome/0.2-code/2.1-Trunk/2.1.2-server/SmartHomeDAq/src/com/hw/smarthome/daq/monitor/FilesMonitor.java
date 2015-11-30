package com.hw.smarthome.daq.monitor;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.hw.smarthome.daq.constant.HintConstant;
import com.hw.smarthome.daq.constant.SysConstant;
import com.hw.util.Ln;
import com.hw.util.ThreadClassTemplet;

public class FilesMonitor extends ThreadClassTemplet {
	private static Logger log = Logger
			.getLogger(FilesMonitor.class);
	private int lastLength;

	public FilesMonitor(int sleepTimes) {
		super(sleepTimes);
	}

	@Override
	protected void runFun() {
		try {
			int length = new File(SysConstant.UPDATE_FILE_ADDR)
					.listFiles().length;
			if (length != lastLength) {
				SysConstant.initUpdateFiles();
				log.info(HintConstant.UPDATE_FILE_LIST_SCUESS);
			}
			lastLength = length;
		} catch (IOException e) {
			Ln.e(this.getClass(),
					HintConstant.UPDATE_FILE_LIST_FAIL, e);
		}
	}
}
