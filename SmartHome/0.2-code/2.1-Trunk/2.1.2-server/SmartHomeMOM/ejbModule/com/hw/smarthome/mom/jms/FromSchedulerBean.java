package com.hw.smarthome.mom.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import com.hw.smarthome.daq.po.DAqPo;
import com.hw.smarthome.daq.po.DataItemAFN07FN01;
import com.hw.smarthome.daq.po.DataItemAFN08;
import com.hw.smarthome.daq.po.base.DataItem;
import com.hw.smarthome.daq.po.base.DataItemAFN07;
import com.hw.smarthome.mom.constant.HintConstant;
import com.hw.smarthome.mom.po.DataItemAFN08MomPo;
import com.hw.smarthome.mom.service.MOMServiceLocal;

/**
 * Message-Driven Bean implementation class for: FromSchedulerBean
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "FromSchedulerQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class FromSchedulerBean implements MessageListener {
	private static Logger log = Logger
			.getLogger(FromSchedulerBean.class);
	// @EJB
	// private ShHistoryHourFacadeLocal shHistoryHourFacade;
	@EJB
	private MOMServiceLocal service;

	public FromSchedulerBean() {
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
				try {
					// System.out.println(((ObjectMessage) message)
					// .getText());
					// scheduWebservice(((TextMessage) message)
					// .getText());
					// System.out.println(shHistoryHourFacade.findAll());
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (message instanceof ObjectMessage) {
				DAqPo po = (DAqPo) (((ObjectMessage) message)
						.getObject());
				if (log.isDebugEnabled()) {
					log.debug("Debug获取JMS数据" + "："
							+ po.getDataItems());
				}

				handler(po);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void handler(DAqPo po) throws Exception {
		if (po != null && po.getDataItems() != null
				&& po.getDataItems().size() > 0) {
			if (po.getAfn() == 0x08) {
				handleAFN08(po);
			} else if (po.getAfn() == 0x07) {
				handleAFN07(po);
			}
		} else {
			throw new Exception(HintConstant.RECEIVE_JMS_FAIL);
		}
	}

	private void handleAFN07(DAqPo po) {
		if (po.getDataItems().size() >=2) {
			String sensorId = ((DataItemAFN07FN01) po
					.getDataItems().get(0)).getSerialNum();
			po.setSensorId(sensorId);
			for (DataItem dataItem : po.getDataItems()) {
				((DataItemAFN07) dataItem).setSensorId(sensorId);
				service.setCtrlCurHisSensors((DataItemAFN07) dataItem);
			}
		}

	}

	private void handleAFN08(DAqPo po) {
		for (DataItem dataItem : po.getDataItems()) {
			if (dataItem instanceof DataItemAFN08) {
				service.setCurHisSensors(trans08(
						po.getSensorId(),
						(DataItemAFN08) dataItem));
			}
		}

	}

	private DataItemAFN08MomPo trans08(String sensorId,
			DataItemAFN08 dataItem) {
		DataItemAFN08MomPo po = new DataItemAFN08MomPo();
		po.setGprs_id(dataItem.getMedia());
		// po.setAddress(dataItem.getAddresss());
		po.setChanlvalue(dataItem.getChanlvalue());
		po.setGprs_id(sensorId);
		po.setMedia(dataItem.getMedia());
		po.setStatus(dataItem.getStatus());
		po.setUnit(dataItem.getUnit());
		return po;
	}
}
