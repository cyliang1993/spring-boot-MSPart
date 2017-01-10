/**
 * Project Name:sboot_mspart
 * File Name:MybatisConfiguration.java
 * Package Name:com.cyl.mybatis
 * Date:2017年1月9日下午6:23:01
 * Copyright (c) 2017, caoyuliang All Rights Reserved.
 *
*/

package com.cyl.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.slf4j.Slf4j;

/**
 * ClassName:MybatisConfiguration  ;
 * Function: TODO ADD FUNCTION ;
 * Reason:	 TODO ADD REASON ;
 * Date:     2017年1月9日 下午6:23:01  ;
 * @author   cyliang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Slf4j  
@Configuration  
@ConditionalOnClass({EnableTransactionManagement.class})  
@Import({ DataBaseConfiguration.class})  
@MapperScan(basePackages={"com.cyl.dao"})  
public class MybatisConfiguration {
    @Value("${spring.datasource.type}")  
    private Class<? extends DataSource> dataSourceType;  
  
    @Value("${spring.datasource.readSize}")  
    private String dataSourceSize;  
    @Resource(name = "writeDataSource")  
    private DataSource dataSource;  
    @Resource(name = "readDataSources")  
    private List<DataSource> readDataSources;  
  
    @Bean  
    @ConditionalOnMissingBean  
    public SqlSessionFactory sqlSessionFactory() throws Exception {  
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();  
        sqlSessionFactoryBean.setDataSource(roundRobinDataSouceProxy());  
        sqlSessionFactoryBean.setTypeAliasesPackage("com.cyl.domain"); 
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();  
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/com/cyl/mapper/*.xml"));  
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);  
        return sqlSessionFactoryBean.getObject();  
    }  
    /** 
     * 有多少个数据源就要配置多少个bean 
     * @return 
     */  
    @Bean  
    public AbstractRoutingDataSource roundRobinDataSouceProxy() {  
        int size = Integer.parseInt(dataSourceSize);  
        MyAbstractRoutingDataSource proxy = new MyAbstractRoutingDataSource(size);  
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();  
        //DataSource writeDataSource = SpringContextHolder.getBean("writeDataSource");  
        // 写  
        targetDataSources.put(DataSourceType.write.getType(),dataSource);  
        //单个数据库时
        // targetDataSources.put(DataSourceType.read.getType(),readDataSource);  
        //多个读数据库时  
        for (int i = 0; i < size; i++) {  
            targetDataSources.put(i, readDataSources.get(i));  
        }  
        proxy.setDefaultTargetDataSource(dataSource);  
        proxy.setTargetDataSources(targetDataSources);  
        return proxy;  
    }  
}

