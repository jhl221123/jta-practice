package com.jtastudy.db.config.jta;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;

import jakarta.transaction.TransactionManager;
import jakarta.transaction.UserTransaction;

/**
 * <pre>
 *     JTA 트랜잭션 설정 클래스.
 *
 *     자바의 JTA 사용을 위해 주요 인터페이스인
 *     {@link UserTransaction}
 *     {@link UserTransactionManager}
 *     구현체를 반환한다.
 *     구현체는 atomikos를 활용.
 * </pre>
 */
@Configuration
@EnableTransactionManagement
public class JtaConfig {

	/**
  	 * <pre>
     *     atomikos의 {@link UserTransactionImp} 반환
     * </pre>
	*/
	@Bean
	public UserTransaction userTransaction() throws Throwable {
		UserTransactionImp userTransactionImp = new UserTransactionImp();
		userTransactionImp.setTransactionTimeout(10000);

		return userTransactionImp;
	}

	/**
	 * <pre>
	 *     atomikos의 {@link UserTransactionManager} 반환
	 * </pre>
	 */
	@Bean
	public TransactionManager atomikosTransactionManager() {
		UserTransactionManager userTransactionManager = new UserTransactionManager();
		userTransactionManager.setForceShutdown(false);

		return userTransactionManager;
	}

	/**
	 * <pre>
	 *     Spring에서 트랜잭션 활용을 위해  {@link PlatformTransactionManager} 구현체 중
	 *     {@link JtaTransactionManager} 구현체 반환.
	 * </pre>
	 */
	@Bean
	public PlatformTransactionManager jtaTransactionManager(UserTransaction userTransaction,
		TransactionManager atomikosTransactionManager) {
		return new JtaTransactionManager(userTransaction, atomikosTransactionManager);
	}
}
