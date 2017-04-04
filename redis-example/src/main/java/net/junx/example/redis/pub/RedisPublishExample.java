/**
 * @ProjectName : redis-example
 * @FileName    : RedisPublishExample.java
 * @PackageName : net.junx.example.redis.pub
 * @Date         : Feb 25, 20179:48:08 PM
 * @Author       : junX (junxspace@hotmail.com)
 */
package net.junx.example.redis.pub;

import redis.clients.jedis.Jedis;

/**
 *
 * @author  junX yangwenjun@jianzhimao.com
 * @date    Feb 25, 2017 9:48:08 PM <br/>
 * @version 
 *
 */
public class RedisPublishExample {
  public static void main(String[] args) {
    Jedis jedis = new Jedis("127.0.0.1", 6379);
    long res = jedis.publish("test_channel", "Hello junX");
    System.out.println("Result: " + res);
    jedis.close();
  
  }
}
