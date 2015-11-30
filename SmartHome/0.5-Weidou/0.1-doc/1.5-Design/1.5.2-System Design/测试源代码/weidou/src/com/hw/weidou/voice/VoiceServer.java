package com.hw.weidou.voice;

/**
 * 接受音频口传递的声音，并将声音按时间截取后发送给Sample
 * 
 * @author 曾凡
 * @time 2014年9月15日 下午3:11:12
 */
public class VoiceServer {
	public VoiceServer() {
		init();
	}

	public void init() {

	}

	/**
	 * 如果有任何的[高电平/正玄波]经过则从这个开始截取声音
	 * 
	 * @author 曾凡
	 * @time 2014年9月15日 下午3:13:18
	 */
	private void subVoice() {
		subByDigital();
	}

	private void subByFourier() {
		
	}

	private void subByDigital() {
		
	}
}
