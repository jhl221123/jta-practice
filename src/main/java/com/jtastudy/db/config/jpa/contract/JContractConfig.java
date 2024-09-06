package com.jtastudy.db.config.jpa.contract;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.jtastudy.domain.repository.jpa.basic.contract",
	entityManagerFactoryRef = "jContractEntityManagerFactory",
	transactionManagerRef = "jContractTransactionManager")
public class JContractConfig {

	@Bean
	public LocalContainerEntityManagerFactoryBean jContractEntityManagerFactory(DataSource jContractDataSource) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(jContractDataSource);
		em.setPackagesToScan("com.jtastudy.domain.entity.jpa.contract");

		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.format_sql", "true");

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(properties);

		return em;
	}

	@Bean
	public PlatformTransactionManager jContractTransactionManager(
		@Qualifier("jContractEntityManagerFactory") LocalContainerEntityManagerFactoryBean jContractEntityManagerFactory) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(jContractEntityManagerFactory.getObject());

		return jpaTransactionManager;
	}
}
