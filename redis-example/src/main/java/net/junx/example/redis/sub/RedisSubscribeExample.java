/**
 * @ProjectName : redis-example
 * @FileName    : RedisSubscribeExample.java
 * @PackageName : net.junx.example.redis.sub
 * @Date         : Feb 25, 20179:48:46 PM
 * @Author       : junX (junxspace@hotmail.com)
 */
package net.junx.example.redis.sub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 *
 * @author  junX yangwenjun@jianzhimao.com
 * @date    Feb 25, 2017 9:48:46 PM <br/>
 * @version 
 *
 */
public class RedisSubscribeExample {
  
  public static void main(String[] args) {
    Jedis jedis = new Jedis("127.0.0.1", 6379);
    long res = jedis.publish("test_channel", "Hello junX");
    jedis.subscribe(new JedisPubSub(){}, "");
    
    System.out.println("Result: " + res);
    jedis.close();
  }
}
