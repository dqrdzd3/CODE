package com.wg.salescount.util;

public interface ConstantInterface {
	//ö�����͵�ʹ���ٿ���
/*	enum MoneyStatus{//�������
		PUBLIC_ACCOUNTS ,ALIPAY;
	}*/
	public final static int PUBLICACCOUNTS = 0;
	public final static int ALIPAY = 1;
/*	enum Invoice{//�Ƿ�Ʊ
		YES,NO;
	}*/
	public final static boolean NO = false;
	public final static boolean YES = true;
/*	enum Gender{//�Ա�
		MALE,FEMALE;
	}*/
	public final static int FEMALE = 0;
	public final static int MALE = 1;
	
/*	enum StoreName{//��������
		TAOBAO,
		BUSINESS_TAOBAO,
		WECHAT_MALL,//΢��
		WETCHAT_SHOP,//΢��С��
		OFFLINE_CONTRACT;//���º�ͬ
	}*/
	
	public final static int TAOBAO = 0;
	public final static int BUSINESS_TAOBAO = 1;
	public final static int WECHAT_MALL = 2;
	public final static int WETCHAT_SHOP = 3;
	public final static int OFFLINE_CONTRACT = 4;
	
	
/*	enum Color{
		SILVER,RED,BLUE,GOLDEN;
	}*/
	public final static int SILVER = 0;
	public final static int RED = 1;
	public final static int BLUE = 2;
	public final static int GOLDEN = 3;
}
