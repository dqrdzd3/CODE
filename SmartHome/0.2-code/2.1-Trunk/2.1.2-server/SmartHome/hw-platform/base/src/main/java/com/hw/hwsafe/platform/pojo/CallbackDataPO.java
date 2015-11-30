package com.hw.hwsafe.platform.pojo;


/**
 * 返回数据类
 * @author 王贺喜
 */
public class CallbackDataPO {
	
	/**
	 * 返回的状态
	 * 1:代表成功  0：代表失败
	 */
	private String code;
	/**
	 * 返回的消息
	 */
	private String message;
	/**
	 * 返回数据的条数
	 */
	private Integer count;
	/**
	 * 返回的数据内容JSON字符串
	 */
	private String data;
	/**
	 * 返回的数据内容与data内容相同，不过为JSON对象
	 */
	private Object dataObject;
	/**
	 * 数据表名
	 */
	private String tableName;

	
	
	public CallbackDataPO(){
		
	}
	/**
	 * @param code 代表状态(1:成功,0:失败)
	 * @param message 消息
	 * @param count 条数
	 * @param data 数据内容(所存内容为JSON字符串)
	 * @param dataObject 数据内容(所存内容为JSON对象)
	 * @param tableName 表名
	 */
	public CallbackDataPO(String code, String message, Integer count,
			String data,Object dataObject, String tableName) {
		super();
		this.code = code;
		this.message = message;
		this.count = count;
		this.data = data;
		this.dataObject = dataObject;
		this.tableName = tableName;
	}
	/**
	 * @param code 代表状态(1:成功,0:失败)
	 * @param message 消息
	 * @param count 条数
	 * @param data 数据内容
	 * @param tableName 表名
	 */
	public CallbackDataPO(String code, String message, Integer count,
			String data, String tableName) {
		super();
		this.code = code;
		this.message = message;
		this.count = count;
		this.data = data;
		this.tableName = tableName;
	}
	/**
	 * @param code 代表状态(1:成功,0:失败)
	 * @param message 消息
	 * @param dataObject 数据内容(所存内容为JSON对象)
	 */
	public CallbackDataPO(String code, String message,Integer count,Object dataObject) {
		super();
		this.code = code;
		this.message = message;
		this.dataObject = dataObject;
	}
	/**
	 * @param code 代表状态(1:成功,0:失败)
	 * @param message 消息
	 */
	public CallbackDataPO(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Object getDataObject() {
		return dataObject;
	}
	public void setDataObject(Object dataObject) {
		this.dataObject = dataObject;
	}

	
	
	
}
