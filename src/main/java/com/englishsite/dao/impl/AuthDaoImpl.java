package com.englishsite.dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.englishsite.constants.EnglishsiteConstants;
import com.englishsite.dao.AuthDao;
import com.englishsite.dao.SpringJDBCDaoSupport;
import com.englishsite.po.Account;
import com.englishsite.po.Auth;
import com.englishsite.util.PasswordUtil;

/**
 * Auth相关操作表
 * 
 */

@Repository(value = "authDao")
public class AuthDaoImpl extends SpringJDBCDaoSupport implements AuthDao {

	private static final Logger logger = Logger.getLogger(AuthDaoImpl.class);

	private static final String TABLE_NAME_AUTH = "auth";
	private static final String TABLE_NAME_ACCOUNT = "account";

	// 读操作///////////////////////////////////////////////////////////////////////////////
	@Override
	public Auth getAuthByLogin(String passport, String password)
	{
		passport = StringEscapeUtils.escapeSql(passport);
		password = StringEscapeUtils.escapeSql(password);
		String sql = "select * from " + TABLE_NAME_AUTH + " where passport=? and password=? and status!=1";
		try
		{
			return this.getJdbcTemplate().queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(Auth.class), new Object[] { passport, password });
		} catch (DataAccessException ex)
		{
			logger.error("[AuthReadDaoImpl][getAuthByLogin]passport: " + passport + "password: " + password + "-" + ex.getLocalizedMessage() + ":" + ex.getMessage(), ex.getCause());
			return null;
		}
	}

	@Override
	public Auth getAuthInfoById(int accountId)
	{
		String sql = "select * from " + TABLE_NAME_AUTH + " where account_id=?";
		try
		{
			return this.getJdbcTemplate().queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(Auth.class), accountId);
		} catch (DataAccessException ex)
		{
			logger.error("[AuthReadDaoImpl][getAuthInfoById]accountId: " + accountId + "-" + ex.getLocalizedMessage() + ":" + ex.getMessage(), ex.getCause());
			return null;
		}
	}

	@Override
	public Auth getAuthInfoByPassport(String passport)
	{
		passport = StringEscapeUtils.escapeSql(passport);
		String sql = "select * from " + TABLE_NAME_AUTH + " where passport=?";
		try
		{
			return this.getJdbcTemplate().queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(Auth.class), passport);
		} catch (DataAccessException ex)
		{
			logger.error("[AuthReadDaoImpl][getAuthInfoByPassport]passport: " + passport + "-" + ex.getLocalizedMessage() + ":" + ex.getMessage(), ex.getCause());
			return null;
		}
	}

	@Override
	public Auth getAuthInfoByEmail(String email)
	{
		email = StringEscapeUtils.escapeSql(email);
		String sql = "select * from " + TABLE_NAME_ACCOUNT + " where email=?";
		Account account = null;
		try
		{
			account = this.getJdbcTemplate().queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(Account.class), email);
			return account == null ? null : this.getAuthInfoById(account.getId());
		} catch (DataAccessException ex)
		{
			logger.error("[AuthReadDaoImpl][getAuthInfoByEmail]email: " + email + "-" + ex.getLocalizedMessage() + ":" + ex.getMessage(), ex.getCause());
			return null;
		}
	}

	// 写操作///////////////////////////////////////////////////////////////////////////////////////////////////
	public int addAuth(Auth auth)
	{
		StringBuilder sql = new StringBuilder();
		sql.append("insert into auth(passport,password,nick_name,security_question_1,security_answer_1) ");
		sql.append("values(?,?,?,?,?)");
		int result = 0;
		try
		{
			result = this.getJdbcTemplate().update(
					sql.toString(),
					new Object[] { StringEscapeUtils.escapeSql(auth.getPassport()), StringEscapeUtils.escapeSql(auth.getPassword()), StringEscapeUtils.escapeSql(auth.getNickName()),
							StringEscapeUtils.escapeSql(auth.getSecurityQuestion1()), StringEscapeUtils.escapeSql(auth.getSecurityAnswer1()) },
					new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR });
		} catch (DuplicateKeyException ex)
		{
			logger.error("[AuthWriteDaoImpl][addAuth]auth: " + auth + "-" + ex.getLocalizedMessage() + ":" + ex.getMessage(), ex.getCause());
			return EnglishsiteConstants.RETURN_DUPLICATE_KEY_EXCEPTION;
		} catch (DataAccessException ex)
		{
			logger.error("[AuthWriteDaoImpl][addAuth]auth: " + auth + "-" + ex.getLocalizedMessage() + ":" + ex.getMessage(), ex.getCause());
			return EnglishsiteConstants.RETURN_SYSTEM_ERROR;
		}
		return result;
	}

	@Override
	public int updateAuthPassword(int accountId, String password)
	{
		password = StringEscapeUtils.escapeSql(password);
		String sql = "update " + TABLE_NAME_AUTH + " set password=?,status=0 where account_id=?";
		int result = 0;
		try
		{
			result = this.getJdbcTemplate().update(sql, new Object[] { password, accountId }, new int[] { Types.VARCHAR, Types.INTEGER });
		} catch (DataAccessException ex)
		{
			logger.error("[AuthWriteDaoImpl][updateAuthPassword]accountId: " + accountId + "password: " + password + "-" + ex.getLocalizedMessage() + ":" + ex.getMessage(), ex.getCause());
			return EnglishsiteConstants.RETURN_SYSTEM_ERROR;
		}
		return result;
	}

	@Override
	public int updateSecurityQuestion(Auth auth)
	{

		String sql = "update auth set security_question_1=?,security_answer_1=? where passport=?";

		int result = 0;
		try
		{
			result = this.getJdbcTemplate().update(sql,
					new Object[] { StringEscapeUtils.escapeSql(auth.getSecurityQuestion1()), StringEscapeUtils.escapeSql(auth.getSecurityAnswer1()), auth.getPassport() },
					new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR });
		} catch (DataAccessException ex)
		{
			logger.error("[AuthWriteDaoImpl][updateSecurityQuestion]auth: " + auth + "-" + ex.getLocalizedMessage() + ":" + ex.getMessage(), ex.getCause());
			return EnglishsiteConstants.RETURN_SYSTEM_ERROR;
		}
		return result;
	}

	@Override
	public int updateNewAccount(String userId, String password, String nickName, String email, int uid)
	{
		int result = 0;

		List<String> args = new ArrayList<String>();
		try
		{
			password = StringEscapeUtils.escapeSql(password);
			nickName = StringEscapeUtils.escapeSql(nickName);
			email = StringEscapeUtils.escapeSql(email);
			StringBuffer sql = new StringBuffer();
			sql.append("update auth set");
			if (StringUtils.isNotBlank(password))
			{
				sql.append(" password=?");
				args.add(PasswordUtil.encrptyPasswordMd5(password));
			}

			if (StringUtils.isBlank(password) && StringUtils.isNotBlank(nickName))
			{
				sql.append(" nick_name=?");
				args.add(nickName);
				this.getJdbcTemplate().update("update account set nick_name=? where id=?", new Object[] { nickName, uid });

			} else if (StringUtils.isNotBlank(password) && StringUtils.isNotBlank(nickName))
			{
				sql.append(",nick_name=?");
				args.add(nickName);
			}

			if (StringUtils.isNotBlank(email))
			{
				result = this.getJdbcTemplate().update("update account set email=?,email_enable=0 where id=?", new Object[] { email, uid });
			}

			if (StringUtils.isBlank(password) && StringUtils.isBlank(nickName))
			{
				return result;
			} else
			{
				sql.append(" where account_id=").append(uid);
				result = this.getJdbcTemplate().update(sql.toString(), args.toArray());
			}
		} catch (DataAccessException ex)
		{
			logger.error("[AuthWriteDaoImpl][updateNewAccount]userId: " + userId + " password:" + password + "nickName:" + nickName + "email:" + email + "uid:" + uid + "-" + ex.getLocalizedMessage()
					+ ":" + ex.getMessage(), ex.getCause());
			return EnglishsiteConstants.RETURN_SYSTEM_ERROR;
		}
		return result;
	}
}
