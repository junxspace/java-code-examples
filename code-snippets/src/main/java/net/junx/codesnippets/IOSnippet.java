/**
 * @ProjectName : code-snippets
 * @FileName : Test.java
 * @PackageName : net.junx.codesnippets
 * @Date : Nov 10, 20167:39:51 PM
 * @Author : junX (junxspace@hotmail.com)
 */
package net.junx.codesnippets;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author junX yangwenjun@jianzhimao.com
 * @date Nov 10, 2016 7:39:51 PM <br/>
 * @version
 *
 */
public class IOSnippet {
  public static void main(String[] args) {

    InputStream in = null;
    byte[] data = null;
    // 读取图片字节数组
    try {
      in = new FileInputStream("/Users/junx/Downloads/双11传单-02.jpg");
      data = new byte[in.available()];
      in.read(data);
      in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    // String s = Base64.encodeBase64(data) ;
    // System.out.println(s);

    String s = Base64.encodeBase64URLSafeString(data);
    System.out.println(s);
  }
}
