package com.hw.hwsafe.smart.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.hw.hwsafe.attachment.util.AppFileUpLoad;
import com.hw.hwsafe.smart.pojo.BinUpdatePo;
import com.hw.hwsafe.smart.pojo.CtrlContent;
import com.hw.hwsafe.smart.pojo.SensorCtrlDetail;
import com.hw.hwsafe.smart.service.impl.D002ServiceImpl;
import com.hw.smarthome.daq.po.DAqPo;
import com.hw.smarthome.daq.po.DataItemAFN05FN01;
import com.hw.smarthome.daq.po.DataItemAFN09FN01;
import com.hw.smarthome.daq.po.DataItemAFN0FFN01;
import com.hw.smarthome.daq.po.DataItemAFN10FN01;
import com.hw.smarthome.daq.po.base.DataItem;

public class SocketReceiverListener implements ServletContextListener {

	private class SocketReceiver extends ThreadClassTemplet {

		private ServerSocket server = null;
		private Map<String, BinUpdatePo> mMap = D002ServiceImpl
				.getBinVersionCacheMap();
		private Socket client = null;

		public SocketReceiver(int sleepTimes) {
			super(sleepTimes);
			try {
				server = new ServerSocket(
						Integer.parseInt(AppFileUpLoad.socket_local_port));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		protected void runFun() {
			DAqPo po = null;
			try {
				if (server == null || server.isClosed()) {
					server = new ServerSocket(
							Integer.parseInt(AppFileUpLoad.socket_local_port));
				}
				client = server.accept();
				ObjectInputStream oin = new ObjectInputStream(
						new BufferedInputStream(client.getInputStream()));
				Object obj = oin.readObject();
				oin.close();
				po = (DAqPo) obj;
				List<DataItem> dataItems = null;
				switch (po.getAfn()) {
				case 0x09:// 查询硬件版本
				{
					String softVerString = "";
					String hardVerString = "";
					dataItems = po.getDataItems();
					for (DataItem item : dataItems) {
						softVerString = ((DataItemAFN09FN01) item).getSoftVer();
						hardVerString = ((DataItemAFN09FN01) item).getHardVer();
					}
					BinUpdatePo binUpdatePo = new BinUpdatePo();

					binUpdatePo.setRemoteSoftVersion(softVerString);
					binUpdatePo.setRemoteHardVersion(hardVerString);
					binUpdatePo.setDate(new Date());
					binUpdatePo.setSensorId(po.getSensorId());
					mMap.put(po.getSensorId(), binUpdatePo);
				}
					break;
				case 0x05:// 开始更新硬件版本
				{
					String softVerString = "";
					String hardVerString = "";
					dataItems = po.getDataItems();
					for (DataItem item : dataItems) {
						softVerString = ((DataItemAFN05FN01) item).getSoftVer();
						hardVerString = ((DataItemAFN05FN01) item).getHardVer();
					}
					BinUpdatePo binUpdatePo = new BinUpdatePo();
					binUpdatePo.setRemoteSoftVersion(softVerString);
					binUpdatePo.setRemoteHardVersion(hardVerString);
					binUpdatePo.setDate(new Date());
					binUpdatePo.setSensorId(po.getSensorId());
					mMap.put(po.getSensorId(), binUpdatePo);
					
				}
				break;
				case 0x0F:// 开始更新硬件版本
				{
					String softVerString = "";
					String hardVerString = "";
					long fileTotal = 0 ;
					long fileOff = 0;
					long data = 0;
					dataItems = po.getDataItems();
					for (DataItem item : dataItems) {
						softVerString = ((DataItemAFN0FFN01) item).getSwVersion();
						hardVerString = ((DataItemAFN0FFN01) item).getHwVersion();
						fileTotal = ((DataItemAFN0FFN01) item).getFileSize();
						fileOff = ((DataItemAFN0FFN01) item).getFileOffset();
						data = ((DataItemAFN0FFN01) item).getDataLen();
					}
					BinUpdatePo binUpdatePo = new BinUpdatePo();
					binUpdatePo.setRemoteSoftVersion(softVerString);
					binUpdatePo.setRemoteHardVersion(hardVerString);
					binUpdatePo.setDate(new Date());
					binUpdatePo.setSensorId(po.getSensorId());
					binUpdatePo.setFileSize(fileTotal);
					binUpdatePo.setFileOffset(fileOff);
					binUpdatePo.setDataLen(data);
					mMap.put(po.getSensorId(), binUpdatePo);
					
				}
					break;
				case 0x10:{
					/* 返回遠程控制的結果 */
					SensorCtrlDetail ctrlDetail = new SensorCtrlDetail();
					Map<String,CtrlContent> tempCtrlContents = new HashMap<String, CtrlContent>();;
					ctrlDetail.setCtrlContent(tempCtrlContents);
					ctrlDetail.setDeviceId(po.getSensorId());
					CtrlContent tempCtrlContent = null;
					int switchNum = 0;
					String switchType = "";
					String switchState = "";
					dataItems = po.getDataItems();
					for(DataItem item:dataItems){
						tempCtrlContent = new CtrlContent();
						switchNum = ((DataItemAFN10FN01)item).getSwitchNum();
						switchType= ((DataItemAFN10FN01)item).getSwitchType();
						switchState= ((DataItemAFN10FN01)item).getSwitchState();
						tempCtrlContent.setSwitchState(switchState);
						tempCtrlContent.setSwitchType(switchType);
						tempCtrlContents.put(switchNum+"", tempCtrlContent);
					}
				}
					break;
				default:
					break;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		new SocketReceiver(-1).start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
