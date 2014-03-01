package com.englishsite.util;

import java.security.MessageDigest;

/**
 * 签名工具类
 * @author wangkui
 *
 */
public class PPTools {
	private static MessageDigest md5Digester;
	static {
		try {
			md5Digester = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static final char[] HEX_CHARS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String md5(String message) {
		if (message == null) {
			return null;
		}
		try {
			MessageDigest digester = (MessageDigest) md5Digester.clone();
			byte[] digest = digester.digest(message.getBytes());

			StringBuffer buf = new StringBuffer(digest.length * 2);
			for (int i = 0; i < digest.length; i++)
				buf.append(HEX_CHARS[(digest[i] >> 4) & 0x0F]).append(
						HEX_CHARS[digest[i] & 0x0F]);

			return buf.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}

}