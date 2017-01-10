/**
 * Project Name:sboot_mspart
 * File Name:DataSourceType.java
 * Package Name:com.cyl.mybatis
 * Date:2017年1月9日下午6:12:41
 * Copyright (c) 2017, caoyuliang All Rights Reserved.
 *
*/

package com.cyl.mybatis;


/**
 * ClassName:DataSourceType  ;
 * Function: TODO ADD FUNCTION ;
 * Reason:	 TODO ADD REASON ;
 * Date:     2017年1月9日 下午6:12:41  ;
 * @author   cyliang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public enum  DataSourceType {

    read("read", "从库"),  
    write("write", "主库");  

	private String type;  

    private String name;  
  
    DataSourceType(String type, String name) {  
        this.setType(type);  
        this.setName(name);  
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

