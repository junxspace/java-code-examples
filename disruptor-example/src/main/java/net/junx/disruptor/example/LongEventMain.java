package net.junx.disruptor.example;

import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

/**
 *
 * @author  junX
 * @date    May 30, 2017 8:19:49 PM <br/>
 * @version 
 *
 */
public class LongEventMain {
  public static void main(String[] args) throws InterruptedException {
    // Executor that will be used to construct new threads for consumers
    Executor executor = Executors.newCachedThreadPool();

    // The factory for the event
    LongEventFactory factory = new LongEventFactory();

    // Specify the size of the ring buffer, must be power of 2.
    int bufferSize = 1024;

    // Construct the Disruptor
    Disruptor<LongEvent> disruptor = new Disruptor<>(factory, bufferSize, executor);

    // Connect the handler
    disruptor.handleEventsWith(new LongEventHandler());

    // Start the Disruptor, starts all threads running
    disruptor.start();

    // Get the ring buffer from the Disruptor to be used for publishing.
    RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

    LongEventProducer producer = new LongEventProducer(ringBuffer);

    ByteBuffer bb = ByteBuffer.allocate(8);
    

    
    CountDownLatch latch = new CountDownLatch(1); 
    long start = System.currentTimeMillis();
    long max = 10_0000;
    System.out.println("start============");
    new Thread(()->{
      for (long l = 0; l < max; l++)
      {
          bb.putLong(0, l);
          producer.onData(bb);
      }
      latch.countDown();
    }).start();
    latch.await();
    long end = System.currentTimeMillis();
    
    long spend = end - start;

    System.out.println("end============");
    System.out.println("spend: " +spend + "ms");
    System.out.println("QPS: " +( (max / spend) * 1000) + "t/s");
    
  }
}
