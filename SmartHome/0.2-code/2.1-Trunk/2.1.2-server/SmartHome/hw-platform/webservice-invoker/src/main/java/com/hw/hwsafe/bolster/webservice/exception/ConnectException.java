
/**
 * @title ConnectException.java
 * @package com.hw.hwsafe.bolster.webservice.exception
 * @author 杜群星
 * @create_time 2013-11-14 下午4:53:30
 * <p>Copyright 河南汉威电子股份有限公司软件部 Corporation 2013</p>
 */
	
package com.hw.hwsafe.bolster.webservice.exception;


/**
 * <p>
 * 这是写类描述，如果有换行则用<br>
 * </p>
 */

public class ConnectException extends Exception{
	public ConnectException(){
		super("连接异常");
	}
	
	public ConnectException(String msg){
		super(msg);
	}
	
	public ConnectException(Throwable e){
		super(e);
		
	}
	public ConnectException(String msg,Throwable e){
		super(msg, e);
	}
}
