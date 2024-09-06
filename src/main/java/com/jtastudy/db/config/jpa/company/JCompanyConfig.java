package com.jtastudy.db.config.jpa.company;

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
@EnableJpaRepositories(basePackages = "com.jtastudy.domain.repository.jpa.basic.company",
	entityManagerFactoryRef = "jCompanyEntityManagerFactory",
	transactionManagerRef = "jCompanyTransactionManager")
public class JCompanyConfig {
	/**
	 * <pre>
	 *     {@link LocalContainerEntityManagerFactoryBean} 구현체에서 사용되는 datasource는 {@link JCompanyDatasource#jCompanyDataSource()}를 사용.
	 * </pre>
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean jCompanyEntityManagerFactory(DataSource jCompanyDataSource) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(jCompanyDataSource);
		em.setPackagesToScan("com.jtastudy.domain.entity.jpa.company");

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

	/**
	 * <pre>
	 *     JCompany를 활용한 JPA 단일트랜잭션 반환.
	 * </pre>
	 */
	@Bean
	public PlatformTransactionManager jCompanyTransactionManager(
		@Qualifier("jCompanyEntityManagerFactory") LocalContainerEntityManagerFactoryBean jCompanyEntityManagerFactory) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(jCompanyEntityManagerFactory.getObject());

		return jpaTransactionManager;
	}
}
