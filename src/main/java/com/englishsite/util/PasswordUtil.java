package com.englishsite.util;
/**
 * 通行证密码工具类
 * @author wangkui
 *
 */
public class PasswordUtil {
	/**
	 * 校验密码
	 * @param passwordSrc 明文密码
	 * @param passwordCrypt 加密后密码(md5+(md5+salt))
	 * @return
	 */
	public static boolean verifyPasswrodMd5(String passwordSrc, String passwordCrypt) 
	{
		passwordSrc = PPTools.md5(passwordSrc);
		String[] arr = passwordCrypt.split("\\$");
		String passwordCrypt2 = MD5Crypt.crypt(passwordSrc, arr[0])	.substring(3);
		return passwordCrypt2.equals(passwordCrypt);
	}
	
	/**
	 * 将明文密码进行加密
	 * 密码 进行 md5+salt
	 * @param passwordSrc 已经md5过密码
	 * @return 加密后密码
	 */
	public static String encrptyPasswordMd5(String passwordMd5)
	{
		String passwordCrypt= MD5Crypt.genCryptPasswordFromMingWen(passwordMd5);
		return passwordCrypt;
	}
}
