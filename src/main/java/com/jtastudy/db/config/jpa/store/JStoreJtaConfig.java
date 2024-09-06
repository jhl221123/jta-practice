package com.jtastudy.db.config.jpa.store;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.engine.transaction.jta.platform.internal.AtomikosJtaPlatform;
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
@EnableJpaRepositories(basePackages = "com.jtastudy.domain.repository.jpa.jta.store",
	entityManagerFactoryRef = "jStoreJtaEntityManagerFactory",
	transactionManagerRef = "jtaTransactionManager")
public class JStoreJtaConfig {

	@Bean
	public LocalContainerEntityManagerFactoryBean jStoreJtaEntityManagerFactory(DataSource jStoreJtaDataSource) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(jStoreJtaDataSource);
		em.setPackagesToScan("com.jtastudy.domain.entity.jpa.store");

		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

		// JPA에서 JTA 활용을 위한 설정.
		properties.setProperty("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
		properties.setProperty("javax.persistence.transactionType", "JTA");

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(properties);

		return em;
	}
}
