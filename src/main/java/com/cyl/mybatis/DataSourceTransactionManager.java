/**
 * Project Name:sboot_mspart
 * File Name:DataSourceTransactionManager.java
 * Package Name:com.cyl.mybatis
 * Date:2017年1月9日下午6:40:17
 * Copyright (c) 2017, caoyuliang All Rights Reserved.
 *
*/

package com.cyl.mybatis;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.slf4j.Slf4j;

/**
 * ClassName:DataSourceTransactionManager  ;
 * Function: TODO ADD FUNCTION ;
 * Reason:	 TODO ADD REASON ;
 * Date:     2017年1月9日 下午6:40:17  ;
 * @author   cyliang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Configuration  
@EnableTransactionManagement  
@Slf4j  
public class DataSourceTransactionManager {
    /** 
     * 自定义事务 
     * MyBatis自动参与到spring事务管理中，无需额外配置，只要org.mybatis.spring.SqlSessionFactoryBean引用的数据源与DataSourceTransactionManager引用的数据源一致即可，否则事务管理会不起作用。 
     * @return 
     */  
    protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "writeDataSource")  
    private DataSource dataSource;  
    @Bean(name = "transactionManager")  
    public org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManagers() {  
        log.info("-------------------- transactionManager init ---------------------");  
        return new org.springframework.jdbc.datasource.DataSourceTransactionManager(dataSource);  
    }  
}

