package com.jtastudy.db.config.jpa.store;

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
@EnableJpaRepositories(basePackages = "com.jtastudy.domain.repository.jpa.basic.store",
	entityManagerFactoryRef = "jStoreEntityManagerFactory",
	transactionManagerRef = "jStoreTransactionManager")
public class JStoreConfig {

	@Bean
	public LocalContainerEntityManagerFactoryBean jStoreEntityManagerFactory(DataSource jStoreDataSource) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(jStoreDataSource);
		em.setPackagesToScan("com.jtastudy.domain.entity.jpa.store");

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
	public PlatformTransactionManager jStoreTransactionManager(
		@Qualifier("jStoreEntityManagerFactory") LocalContainerEntityManagerFactoryBean jStoreEntityManagerFactory) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(jStoreEntityManagerFactory.getObject());

		return jpaTransactionManager;
	}
}
