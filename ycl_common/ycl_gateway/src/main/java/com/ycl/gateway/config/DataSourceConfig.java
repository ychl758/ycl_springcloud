package com.ycl.gateway.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 数据源配置
 *
 * @author yuchenglin
 * @description: 配置数据源
 * @date: 2021/7/30 14:30
 */
@Configuration
public class DataSourceConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);

    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        LOGGER.info("数据源配置加载完毕，当前加载的是spring.datasource下的数据源");
        return new DruidDataSource();
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager() {
        LOGGER.info("数据库事务配置加载完毕");
        return new DataSourceTransactionManager(dataSource());
    }


}

