package com.wheelgo.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration // 이 클래스를 Spring 설정 클래스로 지정합니다.
@ComponentScan(basePackages = "com.wheelgo.service") // "com.wheelgo.service" 패키지에서 컴포넌트를 스캔
@EnableJpaRepositories(basePackages = "com.wheelgo.repository") // "com.wheelgo.repository" 패키지에서 JPA 리포지토리를 활성화
public class AppConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        // JPA의 EntityManagerFactory를 생성하는 Bean
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource); // 데이터 소스를 설정한다.
        emf.setPackagesToScan("com.wheelgo.entity"); // 엔티티 클래스들이 위치한 패키지를 스캔
        emf.setJpaVendorAdapter(jpaVendorAdapter); // JPA 벤더 어댑터를 설정한다.
        return emf;
    }

    @Bean
    public DataSource dataSource() {
        // 데이터베이스 연결 정보를 제공하는 DataSource Bean
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver"); // H2 데이터베이스 드라이버
        dataSource.setUrl("jdbc:h2:mem:testdb"); // 메모리 기반의 H2 데이터베이스를 사용
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        // JPA 구현체에 대한 설정을 담당하는 Bean
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true); // 실행되는 SQL 문을 콘솔에 출력하도록 설정한다.
        adapter.setGenerateDdl(true); // 애플리케이션 실행 시 DDL을 자동 생성하도록 설정한다.
        adapter.setDatabase(org.springframework.orm.jpa.vendor.Database.H2); // H2 데이터베이스를 사용함을 명시한다.
        return adapter;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        // 트랜잭션 관리를 위한 TransactionManager Bean
        return new JpaTransactionManager(emf);
    }
}