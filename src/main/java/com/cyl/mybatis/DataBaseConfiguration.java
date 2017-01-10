/**
 * Project Name:sboot_mspart
 * File Name:DataBaseConfiguration.java
 * Package Name:com.cyl.mybatis
 * Date:2017年1月9日下午6:06:46
 * Copyright (c) 2017, caoyuliang All Rights Reserved.
 *
*/

package com.cyl.mybatis;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * ClassName:DataBaseConfiguration  ;
 * Function: TODO ADD FUNCTION ;
 * Reason:	 TODO ADD REASON ;
 * Date:     2017年1月9日 下午6:06:46  ;
 * @author   cyliang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */

@Configuration 
public class DataBaseConfiguration {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${spring.datasource.type}")  
    private Class<? extends DataSource> dataSourceType;  
	/**
	 * 
	 * writeDataSource:(配置主库) ;
	 * @author cyliang
	 * @return
	 * @since JDK 1.8
	 */
    @Bean(name="writeDataSource")  
    @Primary  
    @ConfigurationProperties(prefix = "spring.datasource.write")  
    public DataSource writeDataSource() {  
        logger.info("-------------------- writeDataSource init ---------------------");  
        return DataSourceBuilder.create().type(dataSourceType).build();  
    } 

    @Bean(name = "readDataSource1")  
    @ConfigurationProperties(prefix = "spring.datasource.read1")  
    public DataSource readDataSourceOne(){  
        logger.info("-------------------- readDataSource init ---------------------");  
        return DataSourceBuilder.create().type(dataSourceType).build();  
    }  
    @Bean("readDataSources")  
    public List<DataSource> readDataSources(){  
        List<DataSource> dataSources=new ArrayList<>();  
        dataSources.add(readDataSourceOne());  
        return dataSources;  
    }  

}

