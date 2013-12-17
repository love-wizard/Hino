package com.hino.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import junit.framework.Assert;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Java 加解密工具类
 * 
 * @author mosesxin@gmail.com
 * 
 */
public class EncryptUtil {

	public static String MD2 = "MD2";
	public static String MD5 = "MD5";
	public static String SHA1 = "SHA-1";
	public static String SHA256 = "SHA-256";
	public static String SHA384 = "SHA-384";
	public static String SHA512 = "SHA-512";
	private static final String UTF8 = "utf-8";
	
	// 定义 加密算法,可用 DES,DESede,Blowfish
	private static final String ALGORITHM_DESEDE = "DESede";

	/**
	 * @param str
	 *            加密明文
	 * @param algorithm
	 *            加密算法
	 * @return 加密结果字符串
	 * @throws UnsupportedEncodingException
	 */
	public static String encrypt(String str, String algorithm)
			throws UnsupportedEncodingException {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.reset();
			messageDigest.update(str.getBytes(UTF8));
			byte[] res = messageDigest.digest();
			return byte2HexStr(res);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return str;
		}
	}

	/**
	 * SHA1数字签名
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public String SHA1Digest(String src) throws Exception {
		return encrypt(src, EncryptUtil.SHA1);
	}
	
	/**
	 * MD5数字签名
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public String md5Digest(String src) throws Exception {
		return encrypt(src, EncryptUtil.MD5);
	}

	/**
	 * BASE64 加密
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public String base64Encoder(String src) throws Exception {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(src.getBytes(UTF8));
	}

	/**
	 * BASE64解密
	 * 
	 * @param dest
	 * @return
	 * @throws Exception
	 */
	public String base64Decoder(String dest) throws Exception {
		BASE64Decoder decoder = new BASE64Decoder();
		return new String(decoder.decodeBuffer(dest), UTF8);
	}

	/**
	 * 3DES加密
	 * 
	 * @param src
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String desedeEncoder(String src, String key) throws Exception {
		SecretKey secretKey = new SecretKeySpec(build3DesKey(key),
				ALGORITHM_DESEDE);
		Cipher cipher = Cipher.getInstance(ALGORITHM_DESEDE);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] b = cipher.doFinal(src.getBytes(UTF8));

		return byte2HexStr(b);
	}

	/**
	 * 3DES解密
	 * 
	 * @param dest
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String desedeDecoder(String dest, String key) throws Exception {
		SecretKey secretKey = new SecretKeySpec(build3DesKey(key),
				ALGORITHM_DESEDE);
		Cipher cipher = Cipher.getInstance(ALGORITHM_DESEDE);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] b = cipher.doFinal(str2ByteArray(dest));

		return new String(b, UTF8);

	}

	/**
	 * 字节数组转化为大写16进制字符串
	 * 
	 * @param b
	 * @return
	 */
	private static String byte2HexStr(byte[] b) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < b.length; i++) {
			String s = Integer.toHexString(b[i] & 0xFF);
			if (s.length() == 1) {
				sb.append("0");
			}

			sb.append(s.toUpperCase());
		}

		return sb.toString();
	}

	/**
	 * 字符串转字节数组
	 * 
	 * @param s
	 * @return
	 */
	private byte[] str2ByteArray(String s) {
		int byteArrayLength = s.length() / 2;
		byte[] b = new byte[byteArrayLength];
		for (int i = 0; i < byteArrayLength; i++) {
			byte b0 = (byte) Integer.valueOf(s.substring(i * 2, i * 2 + 2), 16)
					.intValue();
			b[i] = b0;
		}

		return b;
	}

	/**
	 * 构造3DES加解密方法key
	 * 
	 * @param keyStr
	 * @return
	 * @throws Exception
	 */
	private byte[] build3DesKey(String keyStr) throws Exception {
		byte[] key = new byte[24];
		byte[] temp = keyStr.getBytes(UTF8);
		if (key.length > temp.length) {
			System.arraycopy(temp, 0, key, 0, temp.length);
		} else {
			System.arraycopy(temp, 0, key, 0, key.length);
		}

		return key;
	}
}
