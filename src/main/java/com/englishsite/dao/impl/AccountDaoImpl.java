package com.englishsite.dao.impl;

import java.sql.Types;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.englishsite.constants.EnglishsiteConstants;
import com.englishsite.dao.AccountDao;
import com.englishsite.dao.SpringJDBCDaoSupport;
import com.englishsite.po.Account;

/***
 *  AccountDao的实现类
 * 
 */
@Repository(value="accountDao")
public class AccountDaoImpl extends SpringJDBCDaoSupport implements AccountDao {

	private static final Logger logger=Logger.getLogger(AccountDaoImpl.class);
	
	private static final String TABLE_NAME_ACCOUNT="account";
	
	//读操作///////////////////////////////////////////////////////////////////////////////
	@Override
	public int isPassport(String passport) {
		passport = StringEscapeUtils.escapeSql(passport);
		int result=0;
		StringBuilder sb=new StringBuilder();
		sb.append("select count(0) from "+TABLE_NAME_ACCOUNT+" where passport=?");
		try
		{
			result=this.getJdbcTemplate().queryForInt(sb.toString(),new Object[]{passport});
		}catch(DataAccessException ex)
		{ 
			logger.error("[AccountReadDaoImpl][isPassport]passport: " + passport + "-"+ex.getLocalizedMessage()+":"+ex.getMessage(),ex.getCause());
			return EnglishsiteConstants.RETURN_SYSTEM_ERROR;
		}
		return result;
	}
	
	@Override
	public int isEmail(String email) {
		email = StringEscapeUtils.escapeSql(email);
	    int result=0;
		String sql="select count(0) from "+TABLE_NAME_ACCOUNT+" where email=?";
		try
		{
			result=this.getJdbcTemplate().queryForInt(sql,new Object[]{email});
		}catch(DataAccessException ex)
		{ 
			logger.error("[AccountReadDaoImpl][isEmail]email: " + email + "-"+ex.getLocalizedMessage()+":"+ex.getMessage(),ex.getCause());
			return EnglishsiteConstants.RETURN_SYSTEM_ERROR;
		}
		return result;
	}
	
	@Override
	public int isEmail(int accountId, String email) {
		email = StringEscapeUtils.escapeSql(email);
		String sql="select count(0) from "+TABLE_NAME_ACCOUNT+" where id!=? and email=?"; 
		int result=0;
		try
		{
		    result=this.getJdbcTemplate().queryForInt(sql,new Object[]{accountId,email});
		}catch(DataAccessException ex)
		{ 
			logger.error("[AccountReadDaoImpl][isEmail]accountId: " + accountId +" email: "+email+"-"+ex.getLocalizedMessage()+":"+ex.getMessage(),ex.getCause());
			return EnglishsiteConstants.RETURN_SYSTEM_ERROR;
		}
		return result;
	}
	@Override
	public int isSensitiveWords(String checkStr) {
		return 0;
	}
	@Override
	public Account getAccountInfoById(int accountId) {
		
		String sql="select * from "+TABLE_NAME_ACCOUNT+" where id=?";
		try
		{
			return this.getJdbcTemplate().queryForObject(sql,ParameterizedBeanPropertyRowMapper.newInstance(Account.class),accountId);
		}catch(DataAccessException ex)
		{
			logger.error("[AccountReadDaoImpl][getAccountInfoById]accountId: " + accountId +"-"+ex.getLocalizedMessage()+":"+ex.getMessage(),ex.getCause());
			return null;
		}
	}
	
	@Override
	public Account getAccountInfoByPassport(String passport) {
		passport = StringEscapeUtils.escapeSql(passport);
		String sql="select * from "+TABLE_NAME_ACCOUNT+" where passport=?";
		try
		{
			return this.getJdbcTemplate().queryForObject(sql,ParameterizedBeanPropertyRowMapper.newInstance(Account.class),passport);
		}catch(DataAccessException ex)
		{
			logger.error("[AccountReadDaoImpl][getAccountInfoByPassport]passport: " + passport +"-"+ex.getLocalizedMessage()+":"+ex.getMessage(),ex.getCause());
			return  null;
		}
	}
	@Override
	public Account getAccountInfoByEmail(String email) {
		email = StringEscapeUtils.escapeSql(email);
		String sql="select * from "+TABLE_NAME_ACCOUNT+" where email=?";
		try
		{
		   return this.getJdbcTemplate().queryForObject(sql,ParameterizedBeanPropertyRowMapper.newInstance(Account.class),email);
		}catch(DataAccessException ex)
		{
			logger.error("[AccountReadDaoImpl][getAccountInfoByEmail]email: " + email +"-"+ex.getLocalizedMessage()+":"+ex.getMessage(),ex.getCause());
			return  null;
		}
	}
	
	//写操作///////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public int addAccount(Account account) {
		int result=0;
		StringBuilder sql=new StringBuilder();
		sql.append("insert into account(passport,email,email_enable,phone,identification_card,logo_path,qq,constellation,degree,integration,nick_name,real_name,gender,birthday,");
		sql.append("register_date,register_ip,country,province,city,area,address,zip,flags,flags_linked)");
		sql.append("values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		try
		{
			result=this.getJdbcTemplate().update(sql.toString(),new Object[]{
            StringEscapeUtils.escapeSql(account.getPassport()),StringEscapeUtils.escapeSql(account.getEmail()),
            account.getEmailEnable(),StringEscapeUtils.escapeSql(account.getPhone()),
            StringEscapeUtils.escapeSql(account.getIdentificationCard()), StringEscapeUtils.escapeSql(account.getLogoPath()),account.getQq(),
            StringEscapeUtils.escapeSql(account.getConstellation()),account.getDegree(),account.getIntegration(),
            StringEscapeUtils.escapeSql(account.getNickName()),StringEscapeUtils.escapeSql(account.getRealName()),
			account.getGender(),account.getBirthday(),account.getRegisterDate(),account.getRegisterIp(),StringEscapeUtils.escapeSql(account.getCountry()),
			StringEscapeUtils.escapeSql(account.getProvince()),StringEscapeUtils.escapeSql(account.getCity()),
			StringEscapeUtils.escapeSql(account.getArea()),StringEscapeUtils.escapeSql(account.getAddress()),
			StringEscapeUtils.escapeSql(account.getZip()),account.getFlags(),
			StringEscapeUtils.escapeSql(account.getFlagsLinked())
			}, new int[]{
		    	Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.CHAR,
		    	Types.TIMESTAMP,Types.TIMESTAMP,Types.LONGVARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,
		    	Types.INTEGER,Types.VARCHAR});
		}catch(DuplicateKeyException ex)
	    {
			logger.error("[AccountWriteDaoImpl][addAccount]account: " + account + "-"+ex.getLocalizedMessage()+":"+ex.getMessage(),ex.getCause());
			return EnglishsiteConstants.RETURN_DUPLICATE_KEY_EXCEPTION;
	    }catch(DataAccessException ex)
		{ 
			logger.error("[AccountWriteDaoImpl][addAccount]account: " + account + "-"+ex.getLocalizedMessage()+":"+ex.getMessage(),ex.getCause());
			return EnglishsiteConstants.RETURN_SYSTEM_ERROR;
		}
		return result;
	}
	@Override
	public int updateAccount(Account account,int id) {
		StringBuilder sql=new StringBuilder();
		sql.append("update account set phone=?,identification_card=?,logo_path=?,qq=?,constellation=?,nick_name=?,real_name=?,gender=?,birthday=?,");
		sql.append("country=?,province=?,city=?,area=?,address=?,zip=?,flags=?,flags_linked=? where id=? ");
		int result=0;
		try
		{
			    result=this.getJdbcTemplate().update(sql.toString(),new Object[]{
				StringEscapeUtils.escapeSql(account.getPhone()),
				StringEscapeUtils.escapeSql(account.getIdentificationCard()),StringEscapeUtils.escapeSql(account.getLogoPath()),
				account.getQq(),StringEscapeUtils.escapeSql(account.getConstellation()),
				StringEscapeUtils.escapeSql(account.getNickName()),StringEscapeUtils.escapeSql(account.getRealName()),account.getGender(),
				account.getBirthday(),StringEscapeUtils.escapeSql(account.getCountry()),StringEscapeUtils.escapeSql(account.getProvince()),
				StringEscapeUtils.escapeSql(account.getCity()),StringEscapeUtils.escapeSql(account.getArea()),
				StringEscapeUtils.escapeSql(account.getAddress()),StringEscapeUtils.escapeSql(account.getZip()),
				account.getFlags(),StringEscapeUtils.escapeSql(account.getFlagsLinked()),
				id
			},new int[]{
				Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.CHAR,Types.TIMESTAMP,
				Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.INTEGER
			});
			this.getJdbcTemplate().update("update auth set nick_name=? where account_id=?",new Object[]{account.getNickName(),id});    
		}catch(DataAccessException ex)
		{ 
			logger.error("[AccountWriteDaoImpl][updateAccount]account: " + account + " id:"+ id +"-"+ex.getLocalizedMessage()+":"+ex.getMessage(),ex.getCause());
			return EnglishsiteConstants.RETURN_SYSTEM_ERROR;
		}
		return result;
	}
	
	@Override
	public int updateBindAccountEmail(int accountId,String code,String email) {
		String sql="update account set email=?,email_enable=0 where id=?";
		code=StringEscapeUtils.escapeSql(code);
		int result=0;
		try
		{
			result=this.getJdbcTemplate().update(sql,new Object[]{email,accountId},new int[]{
					Types.VARCHAR,Types.INTEGER
			});
			this.getJdbcTemplate().update("update email_active set status=1 where code=?",new Object[]{code});   
		}catch(DataAccessException ex)
		{ 
			logger.error("[AccountWriteDaoImpl][updateBindAccountEmail]accountId: " + accountId + " email:"+ email +"-"+ex.getLocalizedMessage()+":"+ex.getMessage(),ex.getCause());
			return EnglishsiteConstants.RETURN_SYSTEM_ERROR;
		}
		return result;
	}
	@Override
	public int updateBindAccountPhone(int accountId, String phone) {
		String sql="update account set phone=? where id=?";
		int result=0;
		try
		{
			result=this.getJdbcTemplate().update(sql,new Object[]{phone,accountId},new int[]{
					Types.VARCHAR,Types.INTEGER
			});
		}catch(DataAccessException ex)
		{ 
			logger.error("[AccountWriteDaoImpl][updateBindAccountPhone]accountId: " + accountId + " phone:"+ phone +"-"+ex.getLocalizedMessage()+":"+ex.getMessage(),ex.getCause());
			return EnglishsiteConstants.RETURN_SYSTEM_ERROR;
		}
		return result;
	}
}