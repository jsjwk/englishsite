package com.englishsite.dao;

import com.englishsite.po.Account;

/**
 * 
 * Account相关的操作接口
 * 
 */
public interface AccountDao {
	
	//读操作///////////////////////////////////////////////////////////////////////////////
	/**
	 * 检查通行证
	 * @param passport 通行证
	 * @return 
	 */
	public int isPassport(String passport);
	
	/**
	 *  检查用户邮箱 
	 * @param email 邮箱地址
	 * @return
	 */
	public int isEmail(String email);
	
	/**
	 * 检查用户邮箱是否可用
	 * @param account_id 账号ID
	 * @param email 邮箱
	 * @return 
	 */
	public int isEmail(int accountId,String email);
	
	/**
	 *  检查敏感字
	 * @param checkStr 待检查的字符串
	 * @return 
	 */
	public int isSensitiveWords(String checkStr);
	
    /**
     * 根据账号ID获得账号信息	
     * @param accountId 账号ID
     * @return Account对象
     */
	public Account getAccountInfoById(int accountId);
	
	/**
     * 根据通行证获得账号信息	
     * @param passport 通行证
     * @return Account对象
     */
	public Account getAccountInfoByPassport(String passport);
	
	/**
     * 根据邮箱获得账号信息	
     * @param email 邮箱
     * @return Account对象
     */
	public Account getAccountInfoByEmail(String email);
	
	
	//写操作///////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 *  保存用户到account中
	 * @param account Account对象
	 * @return int 返回的结果值
	 */
	public int addAccount(Account account);
	
	/**
	 * 更新账号信息
	 * @param account对象
	 * @param id 账号的ID
	 * @return int 返回的结果值
	 */
	public int updateAccount(Account account,int id);
	
	/**
	 * 更新邮箱绑定信息
	 * @param accountId  账号的ID 
	 * @param code 随机的验证码
	 * @param email 邮箱地址
	 * @return int 返回的结果值
	 */
	public int updateBindAccountEmail(int accountId,String code,String email);
	
	
	/**
	 * 更新手机绑定信息
	 * @param accountId  账号的ID 
	 * @param 手机 邮箱地址
	 * @return int 返回的结果值
	 */
	public int updateBindAccountPhone(int accountId,String phone);
	
}
