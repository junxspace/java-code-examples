/**
 * Project Name : mewtask-service
 * File Name    : CachePolicy.java
 * Package Name : com.jiuwei.mewtask.core.service.cache
 * Date         : Dec 12, 201612:49:20 AM
 *
 * Copyright (c) 2016, 广州九尾信息科技有限公司 All Rights Reserved.
 *
 */
package net.junx.codesnippets.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author  junX yangwenjun@jianzhimao.com
 * @date    Dec 12, 2016 12:49:20 AM <br/>
 * @version 
 * @since   JDK 1.7
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CachePolicy {
  
  /**
   * 缓存过期时间，单位秒 
   * 
   * @author junX  yangwenjun@jianzhimao.com
   * @return
   *
   */
  long expire();
  
  /**
   * 缓存名称
   * 
   * @author junX  yangwenjun@jianzhimao.com
   * @return
   *
   */
  String name();
  
  /**
   * 是否激活缓存，默认不激活
   * 
   * @author junX  yangwenjun@jianzhimao.com
   * @return
   *
   */
  boolean enabled() default false;
}
