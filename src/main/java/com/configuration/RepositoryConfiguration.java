package com.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by rudolfplettenberg on 05.05.16.
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.database.clientDB.model","com.database.employeeDB.model", "com.database.projectDB.model"})
@EnableJpaRepositories(basePackages = {"com.database.clientDB.repository","com.database.employeeDB.repository", "com.database.projectDB.model"})
@EnableTransactionManagement
//@ImportResource("classpath:/config/spring-config.xml")
public class RepositoryConfiguration {

    /*@Bean(name = "entityManagerFactory")
    public EntityManagerFactory getEntityManagerFactory() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("camel");
        return factory;
    }*/

}