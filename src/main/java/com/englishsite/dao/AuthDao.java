package com.englishsite.dao;

import com.englishsite.po.Auth;


/**
 * Auth读相关操作接口 
 * 
 * @author wangfucai
 * @since  1.0
 * @version 1.0  AuthWriteDao.java 
   2013年10月17日 下午3:30:00
 */
public interface AuthDao {

	//读操作///////////////////////////////////////////////////////////////////////////////
	/**
	 *  获得Auth信息通过登录验证
	 *  
	 * @param passport 通行证
	 * @param password 密码
	 * @return Auth对象
	 */
	public Auth getAuthByLogin(String passport,String password);
	 /**
     * 根据账号ID获得Auth信息	
     * @param accountId 账号ID
     * @return Auth对象
     */
	public Auth getAuthInfoById(int accountId);
	
	/**
     * 根据通行证获得Auth信息	
     * @param passport 通行证
     * @return Auth对象
     */
	public Auth getAuthInfoByPassport(String passport);
	
	/**
     * 根据邮箱获得Auth信息	
     * @param email 邮箱
     * @return Auth对象
     */
	public Auth getAuthInfoByEmail(String email);
	
	
	//写操作///////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 增加一个Auth对象
	 * @param auth Auth对象
	 * @return 返回的结果值
	 */
	public int addAuth(Auth auth);
	/**
	 * 更新密码
	 * @param accountId  账号ID
	 * @param password   密码
	 * @return 返回的结果值
	 */
	public int updateAuthPassword(int accountId,String password);
	
	
	/**
	 * 更改Auth密保问题
	 * @param auth Auth对象
	 * @return 返回的结果值
	 */
	public int updateSecurityQuestion(Auth auth);
	
	/**
	 *  新的用户中心的兼容,目的是为了兼容性
	 *  @param userId 用户的账号
	 *  @param password 密码
	 *  @param nickName 用户昵称
	 *  @param email 用户的邮箱
	 *  @param uid 注册用户的自增ID
	 */
	public int updateNewAccount(String userId,String password,String nickName,String email,int uid);
}

