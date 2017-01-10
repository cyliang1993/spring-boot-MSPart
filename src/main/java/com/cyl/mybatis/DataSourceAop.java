/**
 * Project Name:sboot_mspart
 * File Name:DataSourceAop.java
 * Package Name:com.cyl.mybatis
 * Date:2017年1月9日下午6:47:04
 * Copyright (c) 2017, caoyuliang All Rights Reserved.
 *
*/

package com.cyl.mybatis;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * ClassName:DataSourceAop  ;
 * Function: TODO ADD FUNCTION ;
 * Reason:	 TODO ADD REASON ;
 * Date:     2017年1月9日 下午6:47:04  ;
 * @author   cyliang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Aspect  
@Component  
@Slf4j  
public class DataSourceAop {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

	
    @Before("execution(* com.cyl.dao..*.find*(..)) || execution(* com.cyl.dao..*.get*(..))")  
    public void setReadDataSourceType() {  
        DataSourceContextHolder.read();  
        log.info("dataSource切换到：Read");  
    }  
  
    @Before("execution(* com.cyl.dao..*.insert*(..)) || execution(* com.cyl.dao..*.update*(..)) || execution(* com.cyl.dao..*.add*(..))")  
    public void setWriteDataSourceType() {  
        DataSourceContextHolder.write();  
        log.info("dataSource切换到：write");  
    }  
}


