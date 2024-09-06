package com.jtastudy.db.datasource.jpa.jta.env;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpMethod;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "spring.datasource.jta.jcompany")
public class JCompanyProperties {
	private final String driverClassName;
	private final String url;
	private final String username;
	private final String password;
}
