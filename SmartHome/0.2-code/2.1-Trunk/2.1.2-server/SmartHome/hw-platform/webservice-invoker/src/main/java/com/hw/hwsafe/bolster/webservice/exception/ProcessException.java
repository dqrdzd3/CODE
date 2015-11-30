
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

public class ProcessException extends Exception{
	public ProcessException(){
		super("处理异常");
	}
	
	public ProcessException(String msg){
		super(msg);
	}
	
	public ProcessException(Throwable e){
		super(e);
		
	}
	public ProcessException(String msg,Throwable e){
		super(msg, e);
	}
}
