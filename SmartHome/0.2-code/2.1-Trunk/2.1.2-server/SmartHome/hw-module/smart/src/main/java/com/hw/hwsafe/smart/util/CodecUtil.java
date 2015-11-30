package com.hw.hwsafe.smart.util;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import javax.imageio.ImageIO;




public class CodecUtil {
	
	  
	    private CodecUtil() {
	    }
	  
	    public static byte[] short2bytes(short s) {
	        byte[] bytes = new byte[2];
	        for (int i = 1; i >= 0; i--) {
	            bytes[i] = (byte)(s % 256);
	            s >>= 8;
	        }
	        return bytes;
	    }
	  
	    public static short bytes2short(byte[] bytes) {
	        short s = (short)(bytes[1] & 0xFF);
	        s |= (bytes[0] << 8) & 0xFF00;
	        return s;
	    }
	  
	    /*
* 获取crc校验的short形式
	     */
	   
	    public static short[] crc16Shorts(byte[] data){
	    	short[] result = new short[data.length];
	    	for (int i = 0; i < data.length; i++) {
				result[i] = data[i];
			}
	    	return result;
	    }
	    /**
		* 将两个ASCII字符合成�?��字节�?
		* 如："EF"--> 0xEF
		* @param src0 byte
		* @param src1 byte
		* @return byte
		*/
		public static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[]{src0})).byteValue();
		_b0 = (byte)(_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[]{src1})).byteValue();
		byte ret = (byte)(_b0 ^ _b1);
		return ret;
		} 

		  /**
		   * 将指定字符串src，以每两个字符分割转换为16进制形式
		   * 如："2B44EFD9" �? byte[]{0x2B, 0×44, 0xEF, 0xD9}
		   * @param src String
		   * @return byte[]
		   */
		  public static byte[] HexString2Bytes(String src){
		    byte[] ret = new byte[src.length()/2];
		    byte[] tmp = src.getBytes();
		    for(int i=0; i< tmp.length/2; i++){
		      ret[i] = uniteBytes(tmp[i*2], tmp[i*2+1]);
		    }
		    return ret;
		  }
		  
		  /**
	   * 将指定byte数组�?6进制的形式打印到控制台�?
		   * @param hint String
		   * @param b byte[]
		   * @return void
		   */
		  public static void printHexString(String hint, byte[] b) {
		    System.out.print(hint);
		    for (int i = 0; i < b.length; i++) {
		      String hex = Integer.toHexString(b[i] & 0xFF);
		      if (hex.length() == 1) {
		        hex = '0' + hex;
		      }
		      System.out.print(hex.toUpperCase() + " ");
		    }
		    System.out.println("");
		  }
		  
		  /**
		   *
		   * @param b byte[]
		   * @return String
		   */
		  public static String Bytes2HexString(byte[] b) {
		    String ret = "";
		    for (int i = 0; i < b.length; i++) {
		      String hex = Integer.toHexString(b[i] & 0xFF);
		      if (hex.length() == 1) {
		        hex = '0' + hex;
		      }
		      ret += hex.toUpperCase();
		    }
		    return ret;
		  }
		  /**
		   * ���?
		   * @param b byte[]
		   * @return byte[]
		   */
		  public static byte[] XorBytes(byte[] b){
			  byte[] source = {(byte)0xBC,(byte)0xE1,(byte)0xB3,(byte) 0xD6};
			  byte[] ret = new byte[b.length];
			  for (int i = 0; i < b.length; i++) {   
				  ret[i] = (byte) (b[i] ^ source[i]);  
			  }
			  return ret;
		  }
	    /**
	     * 获得智能家居编码内容
	     * @param args
	     */
		  public static String getZnjjContent(String flag,int nums){
			  byte[] b = HexString2Bytes(flag+String.format("%07X", nums));
		    	
	
	  	    	byte[] test = XorBytes(b);    //异或
	  	    	
	  	    	short[] data = crc16Shorts(test);
	  	    	
	  	    	short d = AllProtocol.CRC16(data);   //crc����
	  	    	
	  	    	
	  	       byte[] crc = short2bytes(d);
	  	      
	  	       
	  	        byte[] testc = new byte[test.length + 2];
	  	        for (int i = 0; i < b.length; i++) {
	  	            testc[i] = b[i];
	  	        }
	  	        testc[test.length] = crc[1];
	  	        testc[test.length + 1] = crc[0];
	  	  
	  	    
	  	      String filename = Bytes2HexString(testc);
	  	      return filename;
		  }
	    public static void main(String[] args) {
	    	
	    	String flag = "5";
	    	int begin = 256;
	    	int nums = 1;
	    	for (int j = begin; j < begin+nums; j++) {
	    		
	    		byte[] b = HexString2Bytes(flag+String.format("%07X", j));
	    	
		    	
		    	
	  	      // byte[] test = new byte[] {(byte)0xEC,(byte)0xE1,(byte)0xB3,(byte) 0xD7};
	  	    	byte[] test = XorBytes(b);    //异或
	  	    	
	  	    	short[] data = crc16Shorts(test);
	  	    	
	  	    	short d = AllProtocol.CRC16(data);   //crc����
	  	    	
	  	    	
	  	       byte[] crc = short2bytes(d);
	  	      
	  	       
	  	        byte[] testc = new byte[test.length + 2];
	  	        for (int i = 0; i < b.length; i++) {
	  	            testc[i] = b[i];
	  	        }
	  	        testc[test.length] = crc[1];
	  	        testc[test.length + 1] = crc[0];
	  	  
	  	      System.out.println("校验后："+flag+String.format("%07X", j));
	  	      String filename = Bytes2HexString(testc);
	    	}

	       
	    }
	   
	  
}
