/**
 * @ProjectName : code-snippets
 * @FileName    : CglibSnippet.java
 * @PackageName : net.junx.codesnippets
 * @Date         : Dec 11, 20168:06:13 PM
 * @Author       : junX (junxspace@hotmail.com)
 */
package net.junx.codesnippet.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 *
 * @author  junX yangwenjun@jianzhimao.com
 * @date    Dec 11, 2016 8:06:13 PM <br/>
 * @version 
 *
 */
public class CglibSnippet {

  public CglibSnippet() {
    // TODO Auto-generated constructor stub
  }
  
  public static void main(String[] args) throws Exception, Exception {
//    Func f = FuncFactory.create(Func.class);
//    System.out.println(f.fun("junX"));
    Func f = new Func();
    System.out.println(f.getClass().getDeclaredMethod("fun", String.class).getDeclaringClass().getSimpleName());
    System.out.println(f.getClass().getDeclaredMethod("fun", String.class).getName());
    System.out.println(f.getClass().getSimpleName());
    System.out.println(Integer.toHexString(f.hashCode()));
  }
  
  
}

class FuncFactory{
  
  @SuppressWarnings("unchecked")
  public static <T> T create(Class<T> clazz){
    AuthProxy authProxy = new AuthProxy();
    Enhancer en = new Enhancer();  
    //进行代理  
    en.setSuperclass(clazz); 
    
    //设置织入逻辑
    en.setCallback(authProxy);  

    //生成代理实例  
    return (T)en.create();
  }
}


class AuthProxy implements MethodInterceptor {

  /**
   * @see net.sf.cglib.proxy.MethodInterceptor#intercept(java.lang.Object, java.lang.reflect.Method, java.lang.Object[], net.sf.cglib.proxy.MethodProxy)
   */
  @Override
  public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3)
      throws Throwable {
    CachePolicy cp = arg1.getAnnotation(CachePolicy.class);
    System.out.println(arg1);
    System.out.println(arg1.getDeclaringClass());
    System.out.println(cp);
    if(null != cp){
      System.out.println("name=" + cp.name() + ",  type=" + cp.type());
    }
    
    System.out.println(arg0.getClass());
    System.out.println(arg1);
    System.out.println(arg2[0]);
    System.out.println(arg3);
    return "Hi, ^_^!";
//    return arg3.invokeSuper(arg0, arg2);
  }
  
}


class  Func{
  
  @CachePolicy(name ="@CachePolicy Method", type =2)
  public String fun(String name){
    return "";
  }
}
