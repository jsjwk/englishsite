package com.englishsite.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * Spring基本操作类
 * 
 */
@Repository
public class SpringJDBCDaoSupport extends JdbcDaoSupport
{
	@Resource(name="englishsiteDataSource")
	private DataSource dataSource;

	@PostConstruct
	public void initDataSource()
	{
		super.setDataSource(dataSource);
	}
}
