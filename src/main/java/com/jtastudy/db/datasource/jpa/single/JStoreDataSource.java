package com.jtastudy.db.datasource.jpa.single;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class JStoreDataSource {
	private final String driverClassName;
	private final String url;
	private final String username;
	private final String password;

	public JStoreDataSource(@Value("${spring.datasource.mysql.jstore.driver-class-name}") String driverClassName
		, @Value("${spring.datasource.mysql.jstore.url}") String url
		, @Value("${spring.datasource.mysql.jstore.username}") String username
		, @Value("${spring.datasource.mysql.jstore.password}") String password) {
		this.driverClassName = driverClassName;
		this.url = url;
		this.username = username;
		this.password = password;
	}

	@Bean
	public DataSource jStoreDataSource() {
		HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setPoolName("j-store-pool");
		hikariDataSource.setDriverClassName(this.driverClassName);
		hikariDataSource.setUsername(this.username);
		hikariDataSource.setJdbcUrl(this.url);
		hikariDataSource.setPassword(this.password);

		return hikariDataSource;
	}
}
