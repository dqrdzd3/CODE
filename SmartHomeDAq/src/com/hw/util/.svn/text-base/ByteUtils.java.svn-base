package com.hw.util;

import java.nio.charset.Charset;

public class ByteUtils {

	/**
	 * 返回小数点后的数据
	 * 
	 * @author 曾凡
	 * @param num
	 *            数据
	 * @param pointIndex
	 *            小数点位数
	 * @return
	 * @time 2015年1月12日 下午1:54:17
	 */
	public static String formatPoint(String raw, int pointIndex) {
		if (pointIndex == 0) {
			return raw;
		}
		if (pointIndex >= raw.length()) {
			StringBuilder sb = new StringBuilder("0.");
			for (int i = 0; i < pointIndex - raw.length(); i++) {
				sb.append("0");
			}
			raw = sb.append(raw).toString();
		} else {
			char[] cArray = ("." + raw).toCharArray();
			char tempC = 0;
			for (int i = 0; i < pointIndex; i++) {
				tempC = cArray[i + 1];
				cArray[i + 1] = cArray[i];
				cArray[i] = tempC;
			}
			raw = new String(cArray);
		}
		return raw;
	}

	// public static void main(String[] args) {
	// String t1 = "1234";
	// String t2 = "134";
	// String t3 = "12";
	// String t4 = "1";
	// System.out.println(formatPoint(t1, 0));
	// System.out.println(formatPoint(t1, 1));
	// System.out.println(formatPoint(t1, 2));
	// System.out.println(formatPoint(t1, 3));
	// System.out.println(formatPoint(t1, 4));
	// System.out.println(formatPoint(t1, 5));
	// System.out.println(formatPoint(t4, 3));
	//
	// }

	public static String getHexWithFill(int num, int formatLength) {
		return format0(Integer.toHexString(num), formatLength);
	}

	public static int getBin(byte[] bytes, int index) {
		return Integer.valueOf(getBinHexStrs(bytes, index, 1),
				16);
	}

	public static int getBins(byte[] bytes, int index, int length) {
		return Integer.valueOf(
				getBinHexStrs(bytes, index, length), 16);
	}

	public static String getBinHexStrs(byte[] bytes, int index,
			int length) {
		StringBuilder sb = new StringBuilder();
		length = index + length;
		for (int i = index; i < length; i++) {
			sb.append(format0(toHEXStr(bytes[i]), 2));
		}
		return sb.toString().toUpperCase();
	}

	public static String getBinHexStr(byte[] bytes, int index) {
		StringBuilder sb = new StringBuilder();
		for (int i = index; i < index + 1; i++) {
			sb.append(format0(toHEXStr(bytes[i]), 2));
		}
		return sb.toString().toUpperCase();
	}

	public static int getReverseBins(byte[] bytes, int index,
			int length) {
		return Integer.valueOf(
				getReverseHexBins(bytes, index, length), 16);
	}

	public static String getReverseHexBins(byte[] bytes,
			int index, int length) {
		StringBuilder sb = new StringBuilder();
		length = index + length;
		byte b = 0;
		for (int i = index; i < length; i++) {
			b = bytes[index + length - i - 1];
			sb.append(format0(toHEXStr(b), 2));
		}
		return sb.toString().toUpperCase();
	}

	public static byte[] reverseBins(byte[] bytes) {
		byte[] res = new byte[bytes.length];
		for (int i = 0; i < res.length; i++) {
			res[i] = bytes[res.length - i - 1];
		}
		return res;
	}

	public static String formatAfter0(String str,
			int formatLength) {

		StringBuilder sb = new StringBuilder(str);
		if (str != null) {
			for (int i = 0; i < formatLength - str.length(); i++) {
				sb.append(0);
			}
			sb.append(str);
		} else {
			for (int i = 0; i < formatLength; i++) {
				sb.append(0);
			}
		}
		return sb.toString();

	}

	/**
	 * 将元数据前补零，补后的总长度为指定的长度，以字符串的形式返回
	 * 
	 * @param sourceDate
	 * @param formatLength
	 * @return 重组后的数据
	 */
	public static String format0(String num, int formatLength) {
		StringBuilder sb = new StringBuilder();
		if (num != null) {
			for (int i = 0; i < formatLength - num.length(); i++) {
				sb.append(0);
			}
			sb.append(num);
		} else {
			for (int i = 0; i < formatLength; i++) {
				sb.append(0);
			}
		}
		return sb.toString();
	}

	public static byte getByte(int i) {
		return (byte) (0xFF & i);
	}

	public static String toHEXStr(byte b) {
		return Integer.toHexString(0xFF & b);
	}

	public static int toHEX(byte b) {
		return Integer.valueOf(toHEXStr(b), 16);
	}

	public static byte toByteFromHex(String hexStr) {
		return getByte(Integer.valueOf(hexStr, 16));
	}

	public static String toHex(String txt) {
		return toHex(txt.getBytes());
	}

	public static String fromHex(String hex) {
		return new String(toBytes(hex));
	}

	public static byte[] toBytes(String hexString) {
		int len = hexString.length() / 2;
		byte[] result = new byte[len];
		for (int i = 0; i < len; i++)
			result[i] = Integer.valueOf(
					hexString.substring(2 * i, 2 * i + 2), 16)
					.byteValue();
		return result;
	}

	public static String toHex(byte[] buf) {
		if (buf == null)
			return "";
		StringBuffer result = new StringBuffer(2 * buf.length);
		for (int i = 0; i < buf.length; i++) {
			appendHex(result, buf[i]);
		}
		return result.toString();
	}

	public static void appendHex(StringBuffer sb, byte b) {
		final String HEX = "0123456789ABCDEF";
		sb.append(HEX.charAt((b >> 4) & 0x0f)).append(
				HEX.charAt(b & 0x0f));
	}

	private static Charset charset = Charset.forName("US-ASCII");

	/** 获取ASCII码 */
	public static String getAscII(byte[] bytes, int pos,
			int length) {
		byte[] temp = new byte[length];
		System.arraycopy(bytes, pos, temp, 0, length);

		return new String(temp, charset);
	}

	public static byte[] toAscII(String str) {
		return str.getBytes(charset);
	}

	public static byte[] fillAscII(String str, int length) {
		byte[] asc = toAscII(str);
		if (length == asc.length) {
			return asc;
		} else {
			byte[] res = new byte[length];
			System.arraycopy(asc, 0, res, 0, asc.length);
			return res;
		}
	}

	public static void main(String[] args) {
		// byte[] bytes = { (byte) 0x75, (byte) 0x70, (byte) 0x64,
		// (byte) 0x61, (byte) 0x74, (byte) 0x61,
		// (byte) 0x2E, (byte) 0x74, (byte) 0x78,
		// (byte) 0x74, (byte) 0x00, (byte) 0x00 };
		// System.out.println(getAscII(bytes, 0, bytes.length));
		// byte[] bytes = { (byte) 0x05, (byte) 0x70, (byte) 0x64,
		// (byte) 0x01 };
		// System.out.println(getReverseHexBins(bytes, 0,
		// bytes.length));
		// System.out
		// .println(getReverseBins(bytes, 0, bytes.length));
	}
}
