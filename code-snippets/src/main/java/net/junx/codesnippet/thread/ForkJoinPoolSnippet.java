package net.junx.codesnippet.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 *
 * @author  junX
 * @date    May 14, 2017 6:29:47 PM <br/>
 * @version 
 *
 */
public class ForkJoinPoolSnippet {
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    
    
    ForkJoinPool es = new ForkJoinPool();
    ForkJoinTask<Long> fjTask = es.submit(new Calculator(0L, 10000000L));
    long result = 0L;
    long start = 0L;
    long end = 0L;
    
    start = System.currentTimeMillis();
    result = fjTask.get();
    end = System.currentTimeMillis();
    
    System.out.println("thread size:" + Runtime.getRuntime().availableProcessors());
    System.out.println("result: " + result);
    System.out.println("elapsed: " + (end - start));
  }
}

class Calculator extends RecursiveTask<Long> {

  private static final long serialVersionUID = 8229264565883327462L;
  private final static int THRESHOLD = 10;
  
  private Long start;
  
  private Long end;
  
  public Calculator(Long start, Long end){
    this.start = start;
    this.end = end;
  }
  
  /**
   * @see java.util.concurrent.RecursiveTask#compute()
   */
  @Override
  protected Long compute() {
//    System.out.println(">>" + Thread.currentThread().getName());
    Long sum = 0L;
    if(end - start <= THRESHOLD){
      for (Long i = start; i <= end; i++) {
        sum += i;
      }
    }else{
      long mid = (end + start) / 2;
      Calculator l = new Calculator(start, mid);
      Calculator r = new Calculator(mid + 1, end);
      l.fork();
      r.fork();
      sum = l.join() + r.join();
    }
    
    return sum;
  }

}
