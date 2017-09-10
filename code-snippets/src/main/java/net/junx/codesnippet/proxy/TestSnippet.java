package net.junx.codesnippet.proxy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 *
 * @author  junX
 * @date    May 8, 2017 10:10:07 AM <br/>
 * @version 
 *
 */
public class TestSnippet {
  public static void main(String[] args) {
    final ThreadFactory threadFactory = new ThreadFactoryBuilder()
        .setNameFormat("Orders-%d")
        .setDaemon(true)
        .build();
    
    final ExecutorService es = Executors.newFixedThreadPool(10, threadFactory);
    for (int i = 0; i < 20; i++) {
      es.execute(new Runnable() {
        
        @Override
        public void run() {
          System.out.println(Thread.currentThread().getName());
        }
      });
    }
    
  }
}
