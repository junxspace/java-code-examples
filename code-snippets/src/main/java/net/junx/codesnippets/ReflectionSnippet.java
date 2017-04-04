/**
 * @ProjectName : java-code-snippet
 * @FileName : ReflectionSnippet.java
 * @PackageName : net.junx.codesnippet
 * @Date : Nov 8, 201610:49:23 AM
 * @Author : junX (junxspace@hotmail.com)
 */
package net.junx.codesnippets;

import java.lang.reflect.Field;

/**
 *
 * @author junX yangwenjun@jianzhimao.com
 * @date Nov 8, 2016 10:49:23 AM <br/>
 * @version
 * @since JDK 1.8
 */
public class ReflectionSnippet {
  public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException,
      NoSuchFieldException, SecurityException {
    ReflectionSnippet ref = new ReflectionSnippet();
    ref.reflect();
  }
  
  
  public void reflect() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
    Foo foo = new Foo();
    Class<?> fooClass = foo.getClass();
    // 获取Foo类中成员变量名为boo的Field
    Field booField = fooClass.getDeclaredField("boo");
    // 忽略类中定义的访问修饰符
    booField.setAccessible(true);
    // 判断Field的类型是否为Boo或其子类
    if (booField.getType().isAssignableFrom(Boo.class)) {
      Boo boo = (Boo) booField.get(foo);// 从foo中获取boo的值
      Class<?> booClass = boo.getClass();
      // 获取Boo类中成员变量名为name的Field
      Field nameField = booClass.getDeclaredField("name");
      nameField.setAccessible(true);
      // 判断Field的类型是否为String
      if (nameField.getType().isAssignableFrom(String.class)) {
        String name = (String) nameField.get(boo);// 从boo中获取name的值
        System.out.println(name);
      }
    }    
  }

  class Foo {
    private final Boo boo = new Boo();

    public Foo() {}
  }

  class Boo {
    private final String name = "my name is Boo!";

  }

}
