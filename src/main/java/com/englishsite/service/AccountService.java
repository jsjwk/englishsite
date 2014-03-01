package com.englishsite.service;

import com.englishsite.po.Account;
import com.englishsite.po.Auth;

/**
 * 账户相关的业务接口
 * @author wangkui
 *
 */
public interface AccountService {

	/**
	 * 保存用户信息（用户注册）
	 * @param account
	 * @param auth
	 * @return
	 */
	public boolean registerAccountInfo(Account account,Auth auth);
	
	/**
	 * 登录
	 * @param passport
	 * @param password
	 * @return
	 */
	public boolean login(String passport, String password);
	
}
