package net.junx.example.redis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class RedisExample {
  public static void main(String[] args) {
    Jedis jedis = new Jedis("127.0.0.1", 6379);
    String idKey = "province:city:type";
    
//    Map<String, Double> rs = new HashMap<>();
//    rs.put("taksId1001", 1.0);
//    rs.put("taksId1002", 2.0);
//    rs.put("taksId1003", 3.0);
//    rs.put("taksId1004", 4.0);
//    rs.put("taksId1005", 5.0);
//    rs.put("taksId1006", 6.0);
//    rs.put("taksId1007", 7.0);
//    rs.put("taksId1008", 8.0);
//    rs.put("taksId1009", 9.0);
//    Long res = jedis.zadd(idKey, rs);
//    System.out.println(">> " + res);
//    String testKey = "testkey";
//    System.out.println(jedis.lrange("test", 0, 1));
    System.out.println(jedis.sadd("helo", "a", "b", "c"));
    System.out.println(jedis.sadd("helo", "a", "b", "c"));
    System.out.println(jedis.sinter("helo"));
    
//    String[] vals = new String[]{"a", "b", "c", "d", "e"};
//    long res = jedis.rpush(testKey, vals);
//    System.out.println(res);
    
//    Set<String> ss = jedis.zrangeByScore(idKey, 2.0, 4.0);
//    System.out.println(ss);
    
//    res = jedis.expire(idKey, 60);
//    System.out.println("Result: " + res);
    jedis.close();
//    
//    ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);  
//    scheduledThreadPool.schedule(new Runnable() {  
//     public void run() {  
//      System.out.println("delay 3 seconds");  
//     }  
//    }, 3, TimeUnit.SECONDS);      
    
  }
}
