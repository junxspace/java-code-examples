package org.code.snippets;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Unit test for simple App.
 */
public class AppTest {

  public static void main(String[] args) {
    long start = 0L;
    long end = 0L;
    int max = 1000_0000;
    
    start = System.currentTimeMillis();
    random1(max);
    end = System.currentTimeMillis();
    System.out.println("Max : " + max + ", elapsed: " + (end - start) + "ms");
    
    start = System.currentTimeMillis();
    random2(max);
    end = System.currentTimeMillis();
    System.out.println("Max : " + max + ", elapsed: " + (end - start) + "ms");
  }
  
  public static  void random1(int max){
    for (int i = 0; i < max; i++) {
      Math.random();
    }
  } 
  
  public static  void random2(int max){
    for (int i = 0; i < max; i++) {
      ThreadLocalRandom.current().nextDouble();
    }
  } 
}
