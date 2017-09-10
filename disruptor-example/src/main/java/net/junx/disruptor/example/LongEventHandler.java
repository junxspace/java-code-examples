package net.junx.disruptor.example;

import com.lmax.disruptor.EventHandler;

/**
 *
 * @author  junX
 * @date    May 30, 2017 8:18:34 PM <br/>
 * @version 
 *
 */
public class LongEventHandler implements EventHandler<LongEvent> {

  /**
   * @see com.lmax.disruptor.EventHandler#onEvent(java.lang.Object, long, boolean)
   */
  @Override
  public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
//    System.out.println("Event: " + event + ", sequence: " + sequence + ", endOfBatch: " + endOfBatch);
    
  }

}
