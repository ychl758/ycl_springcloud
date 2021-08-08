package com.ycl.gateway.config;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * mybatis-plus配置
 * @author yuchenglin
 * @date 2020/10/14
 */
@Configuration
public class MybatisPlusConfig {

    private  static final Logger LOGGER= LoggerFactory.getLogger(MybatisPlusConfig.class);

    /**
     * 分页插件
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        LOGGER.info("mybatis-plus分页插件加载完毕");
        return new PaginationInterceptor();
    }

    /**
     * SQL执行效率插件
     * 性能分析拦截器，用于输出每条 SQL 语句及其执行时间
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        LOGGER.info("sql监听配置加载完毕");
        return new PerformanceInterceptor().setFormat(true);
    }

}
