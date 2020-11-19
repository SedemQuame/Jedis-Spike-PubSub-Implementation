package com.connection.pubsubs;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

public class ChannelSubscribe {
    public static void main(String[] args) {
        Jedis jedis = null;
        Scanner scanner = new Scanner(System.in);
        try{
//            Jedis object for connecting to the redis-server
            jedis = new Jedis("localhost", 8090);

//            creating JedisPubSub object for subscribing to channels
            JedisPubSub jedisPubSub = new JedisPubSub() {
                @Override
                public void onMessage(String channel, String message) {
                    System.out.println("\nChannel " + channel + " has sent a message");

//                    convert message to Club object.
//                    ObjectSerializer serializer = new ObjectSerializer();
                    byte[] data = Base64.getDecoder().decode(message.getBytes());
                    List listOfClubs = (List) ObjectSerializer.unserizlize(data);

//                    if(getObject instanceof Club){
//                        System.out.println(getObject.toString());
//                    }

                    assert listOfClubs != null;
                    listOfClubs.forEach(club -> System.out.println(club.toString()));
                }

                @Override
                public void onPMessage(String pattern, String channel, String message) {
                    System.out.println("\nChannel " + channel + " has sent a message");
                    super.onPMessage(pattern, channel, message);
                }

                @Override
                public void onSubscribe(String channel, int subscribedChannels) {
                    System.out.println("\nClient subscribed to channel " + channel);
                    System.out.println("Client has subscribed to " + subscribedChannels + " channels.\n");
//                    super.onSubscribe(channel, subscribedChannels);
                }

                @Override
                public void onUnsubscribe(String channel, int subscribedChannels) {
                    System.out.println("\nClient unsubscribed to channel " + channel);
                    System.out.println("Client has subscribed to " + subscribedChannels + " channels.\n");
//                    super.onUnsubscribe(channel, subscribedChannels);
                }
            };

            while (true){
                jedis.subscribe(jedisPubSub, "C1");
            }
        }catch (Exception e){
            System.out.println("Exception message is: " + e.getMessage());
        }finally {
            if(jedis != null)
                jedis.close();
        }
    }
}
