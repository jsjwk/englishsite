package com.englishsite.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.englishsite.dao.AccountDao;
import com.englishsite.dao.AuthDao;
import com.englishsite.po.Account;
import com.englishsite.po.Auth;
import com.englishsite.service.AccountService;
import com.englishsite.util.PasswordUtil;

/**
 * 账户相关的业务实现
 * 
 * @author wangkui
 * 
 */
@Service(value = "accountService")
public class AccountServiceImpl implements AccountService {

	@Resource(name = "accountDao")
	private AccountDao accountDao;
	@Resource(name = "authDao")
	private AuthDao authDao;

	/*
	 * 保存用户信息（用户注册）
	 */
	@Override
	public boolean registerAccountInfo(Account account, Auth auth)
	{
		boolean flag = false;
		int n = accountDao.addAccount(account);
		if (n > 0)
		{
			String passport = auth.getPassword();
			auth.setPassword(PasswordUtil.encrptyPasswordMd5(passport));
			n = authDao.addAuth(auth);
			if (n <= 0)
			{
				throw new RuntimeException("保存失败，事物回滚。");
			}
			flag = true;
		}
		return flag;
	}

	/* 
	 * 用户登录
	 */
	@Override
	public boolean login(String passport, String password)
	{
		Auth auth = null;
		if (StringUtils.isNotBlank(passport) && StringUtils.isNotBlank(password))
		{
			auth = this.authDao.getAuthInfoByPassport(passport.toLowerCase());
			if (auth != null)
			{
				return PasswordUtil.verifyPasswrodMd5(password, auth.getPassword());
			}
		}
		return false;
	}

}
