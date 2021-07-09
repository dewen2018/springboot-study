package com.dewen.alipay2.utils;

public class AlipayConfig {

	public static String app_id = "";//�ں�̨��ȡ���������ã�
	
	public static String merchant_private_key = "";//�̳̲鿴��ȡ��ʽ���������ã�

	public static String alipay_public_key = "";//�̳̲鿴��ȡ��ʽ���������ã�
	
	public static String notify_url = "http://localhost:8080/alipay/alipayNotifyNotice.action";
	
	public static String return_url = "http://localhost:8080/alipay/alipayReturnNotice.action";
	
	public static String sign_type = "RSA2";
	
	public static String charset = "utf-8";
	
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";//ע�⣺ɳ����Ի�������ʽ����Ϊ��https://openapi.alipay.com/gateway.do
}
