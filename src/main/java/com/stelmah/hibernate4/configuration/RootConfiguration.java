package com.stelmah.hibernate4.configuration;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
//@WebAppConfiguration
//@EnableWebMvc
@EnableTransactionManagement
@PropertySource({"classpath:jdbc.properties" , "classpath:hibernate.properties"})
@ComponentScan(basePackages = {"com.stelmah.hibernate4"})
public class RootConfiguration extends WebMvcConfigurerAdapter {

    private static final String PROPERTY_DATABASE_DRIVER = "jdbc.driverClassName";
    private static final String PROPERTY_DATABASE_URL = "jdbc.databaseurl";
    private static final String PROPERTY_DATABASE_PASSWORD = "jdbc.username";
    private static final String PROPERTY_DATABASE_USERNAME = "jdbc.password";


    private static final String PROPERTY_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_HIBERNATE_FORMAT_SQL = "format_sql";


    @Resource
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_DATABASE_DRIVER));
        dataSource.setUrl(env.getRequiredProperty(PROPERTY_DATABASE_URL));
        dataSource.setUsername(env.getRequiredProperty(PROPERTY_DATABASE_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(PROPERTY_DATABASE_PASSWORD));

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[]{"com.stelmah.hibernate4.model"});
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    private Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty(PROPERTY_HIBERNATE_DIALECT, env.getProperty(PROPERTY_HIBERNATE_DIALECT));
                setProperty(PROPERTY_HIBERNATE_SHOW_SQL, env.getProperty(PROPERTY_HIBERNATE_SHOW_SQL));
                setProperty(PROPERTY_HIBERNATE_FORMAT_SQL, env.getProperty(PROPERTY_HIBERNATE_FORMAT_SQL));
            }
        };
    }

    @Autowired
    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }
}
