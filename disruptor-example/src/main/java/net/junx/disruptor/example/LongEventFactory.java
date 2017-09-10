package net.junx.disruptor.example;

import com.lmax.disruptor.EventFactory;

/**
 *
 * @author  junX
 * @date    May 30, 2017 8:17:43 PM <br/>
 * @version 
 *
 */
public class LongEventFactory implements EventFactory<LongEvent> {

  /**
   * @see com.lmax.disruptor.EventFactory#newInstance()
   */
  @Override
  public LongEvent newInstance() {
    return new LongEvent();
  }

}
