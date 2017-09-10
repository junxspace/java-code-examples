package net.junx.disruptor.example;

/**
 *
 * @author  junX
 * @date    May 30, 2017 8:48:11 PM <br/>
 * @version 
 *
 */
public class Tracker {
  
  private long start;
  
  private long end;
  
  public void start(){
    this.start = System.currentTimeMillis();
    
  }
  
  public void end(){
    this.end = System.currentTimeMillis();
    
  }
  
  public long spend(){
    return end - start;
  }
  
}
