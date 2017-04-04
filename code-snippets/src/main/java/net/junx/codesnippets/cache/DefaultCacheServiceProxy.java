//package net.junx.codesnippets.cache;
//
//import java.lang.reflect.Method;
//import java.util.Arrays;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.google.common.base.Strings;
//import com.jiuwei.base.cache.redis.CommonCacheService;
//import com.jiuwei.commons.utils.helper.ListHelper;
//
//import net.sf.cglib.proxy.MethodProxy;
//
///**
// * @author  junX yangwenjun@jianzhimao.com
// * @date    Dec 11, 2016 10:44:38 PM <br/>
// * @version 
// * @since   JDK 1.7
// */
//public class DefaultCacheServiceProxy extends CommonCacheService implements CacheServiceProxy {
//  private static final Logger logger = LoggerFactory.getLogger(DefaultCacheServiceProxy.class);
//  
//
//  /**
//   * @see net.sf.cglib.proxy.MethodInterceptor#intercept(java.lang.Object, java.lang.reflect.Method, java.lang.Object[], net.sf.cglib.proxy.MethodProxy)
//   */
//  @Override
//  public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
//      throws Throwable {
//    CachePolicy cp = method.getAnnotation(CachePolicy.class);
//    if(null == cp || !cp.enabled()){
//      logger.warn("类{}中的方法{}未开启缓存", method.getDeclaringClass().getName(), method.getName());
//      return proxy.invokeSuper(obj, args);
//    }
//    
//    String key = buildCacheKey(cp, method, args);
//    Object result = readK(key); // 先从缓存获取
//    
//    if(null == result){
//      result = proxy.invokeSuper(obj, args);
//      saveKV(key, result, (int)cp.expire());
//    }
//    
//    return result;
//  }
//
//  /**
//   * 
//   * @author junX  yangwenjun@jianzhimao.com
//   * @param cp 
//   * @param method
//   * @param args
//   * @return
//   * 
//   */
//  private String buildCacheKey(CachePolicy cp, Method method, Object[] args) {
//    String key = cp.name();
//    if(Strings.isNullOrEmpty(key)){
//      key = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "@" + Integer.toHexString(method.hashCode());
//    }
//    
//    if(null == args){
//      return key;
//    }
//    
//    int len = args.length;
//    StringBuilder sb = new StringBuilder();
//    for (int i = 0; i < len; i++) {
//      sb.append(Integer.toHexString(args[i].hashCode()));
//      if(i < len - 1){
//        sb.append("_");
//      }
//    }
//
//    return buildCacheKey2(key, sb.toString());
//  }
//  
//  
//  protected String buildCacheKey2(Object ... keys){
//    return ListHelper.listToStr(Arrays.asList(keys), ":");
//  }
//
//}
