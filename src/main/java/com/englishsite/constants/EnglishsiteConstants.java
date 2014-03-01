package com.englishsite.constants;

/**
 *  常量
 * 
 */
public class EnglishsiteConstants {
	/**
	 * 返回成功
	 */
	public static final int RETURN_SUCCESS=200;
	/**
	 * 返回失败,对象不存在
	 */
	public static final int RETURN_NO_OBJECT=-1;
	/**
	 * 返回失败,更新失败
	 */
	public static final int RETURN_UPDATE_FAILTRUE=-2;
	/**
	 * 返回失败,非法参数
	 */
	public static final int RETURN_ILLEGAL_ARGUMENT=-10;
	/**
	 * 返回失败,系统错误
	 */
	public static final int RETURN_SYSTEM_ERROR=-11;
	/**
	 *  Servcie层出错向Api层抛出的异常
	 */
	public static final int RETURN_FAIL=-12;
	/**
	 *  主键冲突错误
	 */
	public static final int RETURN_DUPLICATE_KEY_EXCEPTION=-13;
	
	/**
	 * 返回失败,对象不存在
	 */
	/***
	 * 默认的密码加密
	 */
	public static final String DEFAULT_PASSWORD="password";
	
	/***
	 * 默认的邮件扩展
	 */
	public static final String DEFAULT_EMAIL_SUFFIX="@auto_create_stnts.com";
}
