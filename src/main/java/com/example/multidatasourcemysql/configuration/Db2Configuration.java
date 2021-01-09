package com.example.multidatasourcemysql.configuration;

import com.example.multidatasourcemysql.model.db1.Post;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.multidatasourcemysql.repository.db1",
        entityManagerFactoryRef = "db1EntityManager",
        transactionManagerRef = "db1TransactionManager")
public class Db2Configuration {
    @Bean
    @ConfigurationProperties("datasource.db2")
    public DataSourceProperties db2Properties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("datasource.db2")
    public DataSource db2DataSource() {
        return db2Properties().initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "db2ManagerFactory")
    public LocalContainerEntityManagerFactoryBean db2ManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(db2DataSource()).packages(Post.class).build();
    }

    @Bean(name = "db2TransactionManager")
    public PlatformTransactionManager db2TransactionManager(final @Qualifier("db2ManagerFactory") LocalContainerEntityManagerFactoryBean db2ManagerFactory) {
        return new JpaTransactionManager(db2ManagerFactory.getObject());
    }
}
