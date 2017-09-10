/**
 * Project Name : code-snippet
 * File Name    : AntPathMatcherSnippet.java
 * Package Name : net.junx.codesnippet
 * Date         : Nov 7, 20167:25:01 PM
 * Author       : junX / junxspace@hotmail.com
 *
 */
package net.junx.codesnippet.match;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 *
 * @author  junX yangwenjun@jianzhimao.com
 * @date    Nov 7, 2016 7:25:01 PM <br/>
 * @version 
 * @since   JDK 1.7
 */
public class AntPathMatcherSnippet {
  
  public static void main(String[] args) {
    // ? matches one character
    // * matches zero or more characters
    // ** matches zero or more directories in a path
    
    // com/t?st.jsp — matches com/test.jsp but also com/tast.jsp or com/txst.jsp
    // com/*.jsp — matches all .jsp files in the com directory
    // com/**/test.jsp — matches all test.jsp files underneath the com path
    // org/springframework/**/*.jsp — matches all .jsp files underneath the org/springframework path
    // org/**/servlet/bla.jsp — matches org/springframework/servlet/bla.jsp but also org/springframework/testing/servlet/bla.jsp and org/servlet/bla.jsp
    
    PathMatcher matcher = new AntPathMatcher();
    String pattern = "";
    String path = "";
    boolean isMatched = false;
    
    pattern = "com/t?st.jsp";
    path = "com/test.jsp";
    isMatched = matcher.match(pattern, pattern);
    System.out.println(">> pattern: " + pattern + ", path: " + path + ", isMatched:" + isMatched);
    
    pattern = "com/**/test.jsp";
    path = "com/test.jsp";
    isMatched = matcher.match(pattern, pattern);
    System.out.println(">> pattern: " + pattern + ", path: " + path + ", isMatched:" + isMatched);
    
    pattern = "org/springframework/**/*.jsp";
    path = "org/springframework/test.jsp";
    isMatched = matcher.match(pattern, pattern);
    System.out.println(">> pattern: " + pattern + ", path: " + path + ", isMatched:" + isMatched);    
    
  }
}
