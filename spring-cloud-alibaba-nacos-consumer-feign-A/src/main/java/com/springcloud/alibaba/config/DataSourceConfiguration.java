package com.springcloud.alibaba.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import io.seata.rm.datasource.DataSourceProxy;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 将DruidDataSource数据源配交给 seata
 */
@Configuration
@EnableConfigurationProperties({MybatisPlusProperties.class})
public class DataSourceConfiguration {
  /**
   * @param sqlSessionFactory SqlSessionFactory
   * @return SqlSessionTemplate
   */
  @Bean
  public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory);
  }

  /**
   * 从配置文件获取属性构造datasource，注意前缀，这里用的是druid，根据自己情况配置,
   * 原生datasource前缀取"spring.datasource"
   *
   * @return
   */
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource dataSource() {
    DruidDataSource druidDataSource = new DruidDataSource();
    return druidDataSource;
  }

/*
  */
/**
   * 构造datasource代理对象，替换原来的datasource
   * @param //druidDataSource
   * @return
   */

/*  @Primary
  @Bean("dataSource")
  public DataSourceProxy dataSourceProxy(DataSource druidDataSource) {
    return new DataSourceProxy(druidDataSource);
  }*/

  @Bean(name = "sqlSessionFactory")
  public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {
    MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
    bean.setDataSource(dataSource);
    ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    // bean.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));
    bean.setMapperLocations(resolver.getResources("classpath*:mybatis/**/*mapper.xml"));

    SqlSessionFactory factory = null;
    try {
      factory = bean.getObject();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return factory;
  }

}
