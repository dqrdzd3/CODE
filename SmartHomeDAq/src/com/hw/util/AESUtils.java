package com.hw.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {
	public static byte[] encrypt(byte[] seed, byte[] content)
			throws Exception {
		SecretKeySpec key = new SecretKeySpec(seed, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encrypted = cipher.doFinal(content);
		return encrypted;
	}

	public static byte[] decrypt(byte[] seed, byte[] content)
			throws Exception {
		SecretKeySpec key = new SecretKeySpec(seed, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");// 创建密码器
		cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
		byte[] decrypted = cipher.doFinal(content);
		return decrypted;
	}
}