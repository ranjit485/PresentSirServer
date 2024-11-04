package com.ranjit.ps.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import jakarta.persistence.EntityManagerFactory; // Updated import

@Configuration
@EnableJpaRepositories(basePackages = "com.ranjit.ps.repository") // Specify your repository package
@EntityScan(basePackages = "com.ranjit.ps.model") // Specify your entity package
@EnableTransactionManagement // Enable transaction management
public class JpaConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/presentsir");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.ranjit.ps.model");

        // Set the persistence provider
        em.setPersistenceProvider(new HibernatePersistenceProvider());

        // Set additional properties
        java.util.Properties properties = new java.util.Properties();
        properties.put("hibernate.hbm2ddl.auto", "update"); // Automatically create/update database schema
        properties.put("hibernate.show_sql", "true"); // Show SQL queries
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect"); // or "MySQL8Dialect"

        em.setJpaProperties(properties);

        return em;
    }

}
