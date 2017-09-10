package net.junx.disruptor.example;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;

/**
 *
 * @author  junX
 * @date    May 30, 2017 8:19:31 PM <br/>
 * @version 
 *
 */
public class LongEventProducer {
  private final RingBuffer<LongEvent> ringBuffer;

  public LongEventProducer(RingBuffer<LongEvent> ringBuffer)
  {
      this.ringBuffer = ringBuffer;
  }

  public void onData(ByteBuffer bb)
  {
      long sequence = ringBuffer.next();  // Grab the next sequence
      try
      {
          LongEvent event = ringBuffer.get(sequence); // Get the entry in the Disruptor
                                                      // for the sequence
          event.set(bb.getLong(0));  // Fill with data
      }
      finally
      {
          ringBuffer.publish(sequence);
      }
  }
}