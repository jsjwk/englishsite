package com.englishsite.po;

import java.util.Date;

/**
 * 账号信息表
 * 
 */
public class Account implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户Id
	 */
	private int id;

	/**
	 * 通行证
	 */
	private String passport;

	/**
	 * 邮箱地址
	 */
	private String email;

	/**
	 * 邮件是否启用，默认0-启用，1-不启用
	 */
	private short emailEnable;
	public static Short EMAIL_UNABLE = 0; 
	public static Short EMAIL_ENABLE = 1; 
	/**
	 * 手机号码
	 */
	private String phone;

	/**
	 * 身份证
	 */
	private String identificationCard;

	/**
	 * 头像路径
	 */
	private String logoPath;

	/**
	 * QQ号码
	 */
	private int qq;

	/**
	 * 星座
	 */
	private String constellation;

	/**
	 * 等级
	 */
	private int degree;

	/**
	 * 积分
	 */
	private long integration;

	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 性别：M-男，L-女
	 */
	private char gender;
	/**
	 * M-男
	 */
	public static char GENDER_M = 'M';
	/**
	 * L-女
	 */
	public static char GENDER_L = 'L';

	/**
	 * 出生日期
	 */
	private Date birthday;

	/**
	 * 注册时间
	 */
	private Date registerDate;

	/**
	 * 注册IP
	 */
	private long registerIp;

	/**
	 * 国家
	 */
	private String country;

	/**
	 * 省份
	 */
	private String province;

	/**
	 * 城市
	 */
	private String city;

	/**
	 * 地区
	 */
	private String area;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 邮政编码
	 */
	private String zip;
	/**
	 * 用户标示
	 */
	private short flags;

	/**
	 * 标示关联依据
	 */
	private String flagsLinked;

	public Account() {
		super();
	}

	public Account(int id, String passport, String email, short emailEnable,
			String phone, String identificationCard, String logoPath, int qq,
			String constellation, int degree, long integration,
			String nickName, String realName, char gender, Date birthday,
			Date registerDate, long registerIp, String country,
			String province, String city, String area, String address,
			String zip, short flags, String flagsLinked) {
		super();
		this.id = id;
		this.passport = passport;
		this.email = email;
		this.emailEnable = emailEnable;
		this.phone = phone;
		this.identificationCard = identificationCard;
		this.logoPath = logoPath;
		this.qq = qq;
		this.constellation = constellation;
		this.degree = degree;
		this.integration = integration;
		this.nickName = nickName;
		this.realName = realName;
		this.gender = gender;
		this.birthday = birthday;
		this.registerDate = registerDate;
		this.registerIp = registerIp;
		this.country = country;
		this.province = province;
		this.city = city;
		this.area = area;
		this.address = address;
		this.zip = zip;
		this.flags = flags;
		this.flagsLinked = flagsLinked;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public short getEmailEnable() {
		return emailEnable;
	}

	public void setEmailEnable(short emailEnable) {
		this.emailEnable = emailEnable;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdentificationCard() {
		return identificationCard;
	}

	public void setIdentificationCard(String identificationCard) {
		this.identificationCard = identificationCard;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public int getQq() {
		return qq;
	}

	public void setQq(int qq) {
		this.qq = qq;
	}

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public long getIntegration() {
		return integration;
	}

	public void setIntegration(long integration) {
		this.integration = integration;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public long getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(long registerIp) {
		this.registerIp = registerIp;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public short getFlags() {
		return flags;
	}

	public void setFlags(short flags) {
		this.flags = flags;
	}

	public String getFlagsLinked() {
		return flagsLinked;
	}

	public void setFlagsLinked(String flagsLinked) {
		this.flagsLinked = flagsLinked;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Account [id=");
		builder.append(id);
		builder.append(", passport=");
		builder.append(passport);
		builder.append(", email=");
		builder.append(email);
		builder.append(", emailEnable=");
		builder.append(emailEnable);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", identificationCard=");
		builder.append(identificationCard);
		builder.append(", logoPath=");
		builder.append(logoPath);
		builder.append(", qq=");
		builder.append(qq);
		builder.append(", constellation=");
		builder.append(constellation);
		builder.append(", degree=");
		builder.append(degree);
		builder.append(", integration=");
		builder.append(integration);
		builder.append(", nickName=");
		builder.append(nickName);
		builder.append(", realName=");
		builder.append(realName);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", birthday=");
		builder.append(birthday);
		builder.append(", registerDate=");
		builder.append(registerDate);
		builder.append(",country=");
		builder.append(country);
		builder.append(",province=");
		builder.append(province);
		builder.append(", city=");
		builder.append(city);
		builder.append(", area=");
		builder.append(area);
		builder.append(", address=");
		builder.append(address);
		builder.append(", zip=");
		builder.append(zip);
		builder.append(", flags=");
		builder.append(flags);
		builder.append(", flagsLinked=");
		builder.append(flagsLinked);
		builder.append("]");
		return builder.toString();
	}
}
