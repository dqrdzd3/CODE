
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

public class AuthException extends Exception{
	public AuthException(){
		super("认证异常");
	}
	
	public AuthException(String msg){
		super(msg);
	}
	
	public AuthException(Throwable e){
		super(e);
		
	}
	public AuthException(String msg,Throwable e){
		super(msg, e);
	}
}
