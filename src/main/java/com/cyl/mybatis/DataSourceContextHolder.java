/**
 * Project Name:sboot_mspart
 * File Name:DataSourceContextHolder.java
 * Package Name:com.cyl.mybatis
 * Date:2017年1月9日下午6:17:34
 * Copyright (c) 2017, caoyuliang All Rights Reserved.
 *
*/

package com.cyl.mybatis;

import lombok.extern.slf4j.Slf4j;

/**
 * ClassName:DataSourceContextHolder  ;
 * Function: TODO ADD FUNCTION ;
 * Reason:	 TODO ADD REASON ;
 * Date:     2017年1月9日 下午6:17:34  ;
 * @author   cyliang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Slf4j
public class DataSourceContextHolder {

    private static final ThreadLocal<String> local = new ThreadLocal<String>();  
    
    public static ThreadLocal<String> getLocal() {  
        return local;  
    }  
    /** 
     * 读可能是多个库 
     */  
    public static void read() {  
  
        local.set(DataSourceType.read.getType());  
    }  
    /** 
     * 写只有一个库 
     */  
    public static void write() {  
        local.set(DataSourceType.write.getType());  
    }  
  
    public static String getJdbcType() {  
        return local.get();  
    }  
}

