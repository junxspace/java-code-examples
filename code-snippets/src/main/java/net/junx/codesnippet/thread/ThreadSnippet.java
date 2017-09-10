package net.junx.codesnippet.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author junX
 * @date May 13, 2017 11:29:38 PM <br/>
 * @version
 *
 */
public class ThreadSnippet {
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    int poolSize = 10;
//    final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(100);
//    ExecutorService executorService =
//        new ThreadPoolExecutor(poolSize, poolSize, 0L, TimeUnit.MILLISECONDS, queue);
//    
//    for (int i = 0; i < 1000; i++) {
//      final CompletableFuture<String> future = CompletableFuture.supplyAsync(ThreadSnippet::calculate, executorService);
//      System.out.printlnk("get: " + future.get());
//    }
//    ExecutorService  es = new ScheduledThreadPoolExecutor(poolSize);
    ExecutorService  es = new ForkJoinPool();
    
    
  }
  
  public static String calculate(){
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return Thread.currentThread().getName();
  }
}


class Notify implements Callable<String> {

  /**
   * @see java.util.concurrent.Callable#call()
   */
  @Override
  public String call() throws Exception {
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    String rs = Thread.currentThread().getName();
    System.out.println(rs);
    return rs;
  }

 

}
