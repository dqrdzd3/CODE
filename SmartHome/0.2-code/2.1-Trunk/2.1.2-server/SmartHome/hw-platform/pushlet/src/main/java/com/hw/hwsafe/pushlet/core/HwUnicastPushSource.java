package com.hw.hwsafe.pushlet.core;

import java.io.Serializable;
import com.hw.hwsafe.platform.constants.ConfConstants;
import com.hw.hwsafe.platform.util.ServiceBeanUtils;
import com.hw.hwsafe.pushlet.service.IPushletMsgService;
import nl.justobjects.pushlet.core.EventPullSourceUnicast;
import nl.justobjects.pushlet.core.Session;
import nl.justobjects.pushlet.core.SessionManager;

/**
 * 单播推送的资源类
 * 
 * @author 马宁
 *
 */
public class HwUnicastPushSource implements Serializable{
	
	private static final long serialVersionUID = -2215623410178429773L;

	/**
	 * 推送资源内部类
	 * 
	 * @author 马宁
	 *
	 */
	public static class PushSource extends EventPullSourceUnicast{

		private IPushletMsgService pushletMsgService = 
				(IPushletMsgService) ServiceBeanUtils.getBean("pushletMsgService");
		
		@Override
		protected long getSleepTime() {
			return ConfConstants.PUSHLET_INTERVAL;
		}

		@Override
		protected void pullEvent() throws Exception {
			Session[] sessions = SessionManager.getInstance().getSessions();
			
			pushletMsgService.handleUnicast(sessions);
		}
		
	}

}
