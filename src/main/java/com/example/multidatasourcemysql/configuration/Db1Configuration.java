package com.example.multidatasourcemysql.configuration;

import com.example.multidatasourcemysql.model.db1.Post;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.multidatasourcemysql.repository.db1",
        entityManagerFactoryRef = "db1EntityManager",
        transactionManagerRef = "db1TransactionManager")
public class Db1Configuration {

    @Bean
    @Primary
    @ConfigurationProperties("datasource.db1")
    public DataSourceProperties db1Properties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("datasource.db1")
    public DataSource db1DataSource() {
        return db1Properties().initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "db1ManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean db1ManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(db1DataSource()).packages(Post.class).build();
    }

    @Bean(name = "db1TransactionManager")
    @Primary
    public PlatformTransactionManager db1TransactionManager(final @Qualifier("db1ManagerFactory") LocalContainerEntityManagerFactoryBean db1ManagerFactory) {
        return new JpaTransactionManager(db1ManagerFactory.getObject());
    }
}
