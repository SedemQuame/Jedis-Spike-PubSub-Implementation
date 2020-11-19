package com.connection.pubsubs;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        try{
            Jedis jedis = new Jedis("localhost");
//        Check if we connected to server successfully.
            System.out.println("Connection successful");
            System.out.println("Server running : " + jedis.ping());

//            setting user names
            jedis.set("name", "sedem-quame");

//            pushing things to a queue
            jedis.lpush("queue:jobs", "job-one");
            jedis.lpush("queue:jobs", "job-two");

//            jedis.get("queue:jobs");
//            getting sets of unordered collections
            //add members
            jedis.sadd("connected-users", "user1");
            jedis.sadd("connected-users", "user2");
            jedis.sadd("connected-users", "user3");
//remove members
            jedis.srem("connected-users", "user2");
//get set
            Set<String> connectedUserIds = jedis.smembers("connected-users");
            //check membership

            boolean isUserConnected = jedis.sismember("connected-users", "user1"); //true
            System.out.println("user1: is connected " + isUserConnected);
//            popping things from the queue
            String job = jedis.rpop("queue:jobs"); //will pop "job-one" & assign

            System.out.println("Stored user name is: " + jedis.get("name"));
        }catch (Exception e){
            System.out.println("Something bad happened");
        }
    }
}
