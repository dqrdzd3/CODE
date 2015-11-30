package com.hw.hwsafe.smart.pojo;

import java.io.Serializable;
import java.util.Date;

public class CorpMaterialDetailViewPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4568394674011774686L;

	private String ma001;    //主键
	private String ma002;   //耗材id
	private String ma003;    //设备id
	private Date ma004;      //使用开始日期
	private Date ma005;      //使用截至日期
	private int ma006;       //状态     0 ：使用中     1：废弃      2：超期
	private String ma007;    //备用
	private String ma008;    //耗材名称
	private String ma009;
	private String ma010;
	private String ma011;
}
