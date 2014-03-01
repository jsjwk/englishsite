package com.englishsite.po;

import java.io.Serializable;

/**
 * 账号安全信息表
 * 
 */
public class Auth implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 通行证，用户名
	 */
	private String passport;
	
	/**
	 * 密码，如：MD5加密，加盐
	 */
	private String password;
	
	/**
	 * 昵称
	 */
	private String nickName;
	
	/**
	 * 用户的状态,0表示新注册的用户，可用，-1表示转移的用户，可用状态,1为不可用状态.
	 */
	private short status;
	public static Short STATUS_FORBID= 1; 
	public static Short STATUS_ENABLE = 0; 
	public static Short STATUS_UNABLE = -1; 
	
	/**
	 * 安全问题1
	 */
	private String securityQuestion1;
	
	/**
	 * 安全答案1
	 */
	private String securityAnswer1;

	public Auth() {
		super();
	}
	
	public Auth(String passport, String password,
			String nickName, short status,
			String securityQuestion1, String securityAnswer1) 
	{
		super();
		this.passport = passport;
		this.password = password;
		this.nickName = nickName;
		this.status=status;
		this.securityQuestion1 = securityQuestion1;
		this.securityAnswer1 = securityAnswer1;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public String getSecurityQuestion1() {
		return securityQuestion1;
	}

	public void setSecurityQuestion1(String securityQuestion1) {
		this.securityQuestion1 = securityQuestion1;
	}

	public String getSecurityAnswer1() {
		return securityAnswer1;
	}

	public void setSecurityAnswer1(String securityAnswer1) {
		this.securityAnswer1 = securityAnswer1;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Auth [");
		//builder.append("Auth [accountId=");
		//builder.append(accountId);
		builder.append("passport=");
		builder.append(passport);
		builder.append(", password=");
		//builder.append(password);
		builder.append(", nickName=");
		builder.append(nickName);
//		builder.append(", channelId=");
//		builder.append(channelId);
		builder.append(", status=");
		builder.append(status);
		builder.append(", forumAccounts=");
//		builder.append(forumAccounts); 
		builder.append(", securityQuestion1=");
		builder.append(securityQuestion1);
		builder.append(", securityAnswer1=");
		builder.append(securityAnswer1);
		builder.append("]");
        return builder.toString();
	}
}