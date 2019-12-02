package com.hust.hydroelectric_backend.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.sql.DataSource;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/18 15:23
 */
@Configuration
@ImportResource("classpath:spring/spring-dataSource.xml")
public class DataSourceConfig {

    @Bean(name = "hydroDataSource")
    @Qualifier("hydroDataSource")
    public DataSource testDataSource() throws Exception {
        return getDataSource("jdbc:mysql://localhost:3306/hydro_electric?useSSL=false&serverTimezone=GMT%2B8", "root", "19960731");
    }

    private DataSource getDataSource(String url, String username, String password){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setValidationQuery("select 1");
        dataSource.setValidationQueryTimeout(3);
        return dataSource;
    }
}
