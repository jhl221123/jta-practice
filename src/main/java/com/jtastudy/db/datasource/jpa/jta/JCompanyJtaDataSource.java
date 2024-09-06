package com.jtastudy.db.datasource.jpa.jta;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.atomikos.spring.AtomikosDataSourceBean;
import com.jtastudy.db.datasource.jpa.jta.env.JCompanyProperties;

import lombok.RequiredArgsConstructor;

@EnableConfigurationProperties({JCompanyProperties.class})
@RequiredArgsConstructor
@Configuration
public class JCompanyJtaDataSource {
	private final JCompanyProperties jCompanyProperties;

	@Bean
	public DataSource jCompanyJtaDataSource() {
		AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
		atomikosDataSourceBean.setXaDataSourceClassName(jCompanyProperties.getDriverClassName());

		Properties properties = new Properties();
		properties.setProperty("url", jCompanyProperties.getUrl());
		properties.setProperty("user", jCompanyProperties.getUsername());
		properties.setProperty("password", jCompanyProperties.getPassword());
		atomikosDataSourceBean.setXaProperties(properties);
		return atomikosDataSourceBean;
	}
}
