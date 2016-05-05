package com.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by rudolfplettenberg on 05.05.16.
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.database"})
@EnableJpaRepositories(basePackages = {"com.database"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}