package com.test.hero.heroproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;

@Configuration
@EnableJpaRepositories({ "com.test.hero.heroproject.repository" })
@EnableTransactionManagement
public class DatabaseConfiguration {
}
