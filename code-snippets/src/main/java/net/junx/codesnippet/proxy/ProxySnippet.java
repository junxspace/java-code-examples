package net.junx.codesnippet.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.google.common.hash.Hashing;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;

/**
 *
 * @author junX
 * @date May 14, 2017 9:35:58 PM <br/>
 * @version
 *
 */
public class ProxySnippet {
  public static void main(String[] args) {
    InvocationHandler invocationHandler = new MyInvocationHandler();

    // Guava Dynamic Proxy implement
    IFoo foo = Reflection.newProxy(IFoo.class, invocationHandler);
    foo.doSomething();
    
//    // jdk Dynamic proxy implement
//    IFoo jdkFoo = (IFoo) Proxy.newProxyInstance(IFoo.class.getClassLoader(),
//        new Class<?>[] {IFoo.class}, invocationHandler);
//    jdkFoo.doSomething();
  }

  public static class MyInvocationHandler implements InvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      System.out.println("proxy println something");
      Invokable<IFoo, ?> invokable = new TypeToken<IFoo>() {}.method(method);
      method.invoke(proxy, args);
      System.out.println(invokable.getName());
      return null;
    }
  }

  public static interface IFoo {
    void doSomething();
  }
  
  public static class Foo implements IFoo{

    /**
     * @see net.junx.codesnippet.proxy.ProxySnippet.IFoo#doSomething()
     */
    @Override
    public void doSomething() {
      System.out.println("Hello, doSomething!");
    }
    
  }
  
}
