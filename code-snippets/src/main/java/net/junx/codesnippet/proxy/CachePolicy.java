/**
 * @ProjectName : code-snippets
 * @FileName    : CachePolicy.java
 * @PackageName : net.junx.codesnippets
 * @Date         : Dec 12, 201612:47:46 AM
 * @Author       : junX (junxspace@hotmail.com)
 */
package net.junx.codesnippet.proxy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author  junX yangwenjun@jianzhimao.com
 * @date    Dec 12, 2016 12:47:46 AM <br/>
 * @version 
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CachePolicy{
  String name();
  
  int type();
  
  
} 