package com.jtastudy.db.datasource.jpa.jta;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.atomikos.spring.AtomikosDataSourceBean;

@Configuration
public class JStoreJtaDataSource {
	private final String driverClassName;
	private final String url;
	private final String username;
	private final String password;

	public JStoreJtaDataSource(@Value("${spring.datasource.jta.jstore.driver-class-name}") String driverClassName
		, @Value("${spring.datasource.jta.jstore.url}") String url
		, @Value("${spring.datasource.jta.jstore.username}") String username
		, @Value("${spring.datasource.jta.jstore.password}") String password) {
		this.driverClassName = driverClassName;
		this.url = url;
		this.username = username;
		this.password = password;
	}

	@Bean
	public DataSource jStoreJtaDataSource() {
		AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
		atomikosDataSourceBean.setXaDataSourceClassName(driverClassName);

		Properties properties = new Properties();
		properties.setProperty("user", username);
		properties.setProperty("password", password);
		properties.setProperty("url", url);
		atomikosDataSourceBean.setXaProperties(properties);
		return atomikosDataSourceBean;
	}
}
