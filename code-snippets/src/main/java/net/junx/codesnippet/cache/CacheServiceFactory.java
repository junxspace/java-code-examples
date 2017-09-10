package net.junx.codesnippet.cache;

import net.sf.cglib.proxy.Enhancer;

/**
 * 
 * @author  junX yangwenjun@jianzhimao.com
 * @date    Dec 11, 2016 10:44:01 PM <br/>
 * @version 
 * @since   JDK 1.7
 */
public class CacheServiceFactory {
  
  @SuppressWarnings("unchecked")
  public static <T> T create(Class<T> clazz, CacheServiceProxy proxy){
    
    Enhancer en = new Enhancer();  
    en.setSuperclass(clazz); 
    en.setCallback(proxy);  
    
    return (T)en.create();
  }  
  
  public static <T> T create(Class<T> clazz){
    return null;
//    return create(clazz, new DefaultCacheServiceProxy());
  }  
  
}
