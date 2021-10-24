package org.jinku.sync.application.bootstrap;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@ComponentScan(basePackages = "org.jinku.sync")
@MapperScan("org.jinku.sync.repository.dao")
public class ApplicationConfig {
    @Resource
    protected Environment env;

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("datasource.url"));
        dataSource.setUsername(env.getProperty("datasource.username"));
        dataSource.setPassword(env.getProperty("datasource.password"));
        dataSource.setInitialSize(Integer.parseInt(Objects.requireNonNull(env.getProperty("datasource.initialSize"))));
        dataSource.setMinIdle(Integer.parseInt(Objects.requireNonNull(env.getProperty("datasource.minIdle"))));
        dataSource.setMaxActive(Integer.parseInt(Objects.requireNonNull(env.getProperty("datasource.maxActive"))));
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(("classpath:mappers/*Mapper.xml")));
        factoryBean.setConfiguration(mybatisConfig());
        return factoryBean.getObject();
    }

    @Bean
    public org.apache.ibatis.session.Configuration mybatisConfig() {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setCacheEnabled(false);
        configuration.setMapUnderscoreToCamelCase(true);
        return configuration;
    }
}
