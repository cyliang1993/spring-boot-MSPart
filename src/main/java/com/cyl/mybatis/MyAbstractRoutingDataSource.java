/**
 * Project Name:sboot_mspart
 * File Name:MyAbstractRoutingDataSource.java
 * Package Name:com.cyl.mybatis
 * Date:2017年1月9日下午6:20:21
 * Copyright (c) 2017, caoyuliang All Rights Reserved.
 *
*/

package com.cyl.mybatis;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * ClassName:MyAbstractRoutingDataSource  ;
 * Function: TODO ADD FUNCTION ;
 * Reason:	 TODO ADD REASON ;
 * Date:     2017年1月9日 下午6:20:21  ;
 * @author   cyliang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class MyAbstractRoutingDataSource extends AbstractRoutingDataSource {
	 private final int dataSourceNumber;  
	    private AtomicInteger count = new AtomicInteger(0);  
	  
	    public MyAbstractRoutingDataSource(int dataSourceNumber) {  
	        this.dataSourceNumber = dataSourceNumber;  
	    }  
	  
	    @Override  
	    protected Object determineCurrentLookupKey() {  
	        String typeKey = DataSourceContextHolder.getJdbcType();  
	        if (typeKey.equals(DataSourceType.write.getType()))  
	            return DataSourceType.write.getType();  
	        // 读 简单负载均衡  
	        int number = count.getAndAdd(1);  
	        int lookupKey = number % dataSourceNumber;  
	        return new Integer(lookupKey);  
	    }  
}

