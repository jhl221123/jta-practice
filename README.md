# 분산 데이터베이스 학습용 리포지토리

## 학습 목표

**분산 데이터베이스 환경에서 트랜잭션을 적용해본다.**

* JTA(Java Transaction API)의 구현체는 Atomikos를 사용한다.
* JPA와 MyBatis 각 방식으로 적용해본다.

## 프로젝트 의존성

```yml
// jpa
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'

// mybatis
implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.3'

// lombok
compileOnly 'org.projectlombok:lombok'
annotationProcessor 'org.projectlombok:lombok'

// mysql
runtimeOnly 'com.mysql:mysql-connector-j'

// test
testImplementation 'org.springframework.boot:spring-boot-starter-test'
testImplementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter-test:3.0.3'
testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
```