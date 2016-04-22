package test.colin.miniblog.core;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 16-4-7
 * Time: 下午5:22
 * To change this template use File | Settings | File Templates.
 */
public class RedisTest {

    public static void main(String[] args){
       /* Jedis jedis = new Jedis("112.124.11.129");
        System.out.println("Connection to server sucessfully"+jedis.ping());
        jedis.set("name","colin");
        System.out.println("获取放在redis中的内容" + jedis.get("name"));
        jedis.lpush("tutorial-list", "Redis");
        jedis.lpush("tutorial-list", "Mongodb");
        jedis.lpush("tutorial-list", "Mysql");
        // Get the stored data and print it
        List<String> list = jedis.lrange("tutorial-list", 0 ,5);
        for(int i=0; i<list.size(); i++) {
            System.out.println("Stored string in redis:: "+list.get(i));
        }
        System.out.print("------------------------------");
        List<String> list2 = (List<String>) jedis.keys("*");
        for(int i=0; i<list2.size(); i++) {
            System.out.println("List of stored keys:: "+list2.get(i));
        }*/
    }

}
