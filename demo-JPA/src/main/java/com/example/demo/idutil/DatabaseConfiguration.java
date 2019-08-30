package com.example.demo.idutil;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDataSourceConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseConfigurer;


@Configuration(value="IDGenDBConfig")
@PropertySource("classpath:/application.properties")
public class DatabaseConfiguration  {
    Logger log = LoggerFactory.getLogger("com.example.demo.idutil.DatabaseConfiguration");


//    @Bean
//    @Primary
//    @ConfigurationProperties("spring.datasource2")
//    public DataSourceProperties dataSourceProperties2() {
//        return new DataSourceProperties();
//    }
//
//
//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource2")
//    public DataSource dataSource2() {
//        log.info("datasource 2!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        return  dataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
//    }
//
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource2.hikari")
//    public HikariConfig hikariConfig() {
//        return new HikariConfig();
//    }
//
//
//    @Autowired
//    private ApplicationContext applicationContext;
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory2(@Autowired @Qualifier("mysql2DataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/*.xml"));
//        return sqlSessionFactoryBean.getObject();
//    }
//
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate2(@Autowired @Qualifier("sqlSessionFactory2") SqlSessionFactory factory) {
//        return new SqlSessionTemplate(factory);
//    }
//
//    @Bean
//    @Primary
//    @ConfigurationProperties("spring.datasource")
//    public DataSourceProperties dataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//
//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSource() {
//        log.info("datasource !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        return  dataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
//    }
//
//
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory( DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/*.xml"));
//        return sqlSessionFactoryBean.getObject();
//    }
//
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory factory) {
//        return new SqlSessionTemplate(factory);
//    }

}