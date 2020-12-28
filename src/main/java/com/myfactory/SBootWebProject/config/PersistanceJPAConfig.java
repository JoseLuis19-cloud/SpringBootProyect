package com.myfactory.SBootWebProject.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;

// @Configuration
// @PropertySource(value= {"classpath:application.properties"})
public class PersistanceJPAConfig {

  @Autowired
  Environment environment;

  @Bean(name = "springboot")
  @ApplicationScope 

  public DataSource datasource() throws PropertyVetoException {
      final DriverManagerDataSource dataSource = new DriverManagerDataSource();
       dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
       dataSource.setUrl(environment.getProperty("spring.datasource.url"));
       dataSource.setUsername(environment.getProperty("spring.datasource.username"));
       dataSource.setPassword(environment.getProperty("spring.datasource.password"));
      return dataSource;
    } 
} 

/* @Bean 
public DataSource dataSource(){
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getProperty("jdbc.driverClass"));
    dataSource.setUrl(env.getProperty("jdbc.url"));
    dataSource.setUsername(env.getProperty("jdbc.username"));
    dataSource.setPassword(env.getProperty("jdbc.password"));   
    return dataSource;
}

private Properties hibernateProperties() {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
    properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
    properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
    properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
    return properties;        
}


@Bean
public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();     
    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject()); 
    return transactionManager;

}

@Bean 
public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProvider(new HibernatePersistenceProvider());
        entityManagerFactoryBean.setPackagesToScan(env.getProperty("packages.to.scan"));
        entityManagerFactoryBean.setJpaProperties(hibernateProperties());
        return entityManagerFactoryBean;
}*/
