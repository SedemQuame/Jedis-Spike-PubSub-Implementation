package com.connection.pubsubs;

import redis.clients.jedis.Jedis;

import java.util.*;

public class ChannelPublish {
    public static void main(String[] args) {
        Jedis jedis = null;

        try{
//            Creating new jedis object for connecting to the servers
            jedis = new Jedis("localhost", 8090);

            Scanner scanner = new Scanner(System.in);
            while (true){
//              Get channel to publish to.
                System.out.println("\n Please enter channel name.");
                String channel = scanner.next();

//                System.out.println("Please enter a message.\n");
//                String message = scanner.next();
                Club club = new Club(1, "clubs", "info_pane", new Date(), 1);
                Club club2 = new Club(2, "clubs", "info_pane", new Date(), 2);
                Club club3 = new Club(1, "clubs", "info_pane", new Date(), 3);
                Club club4 = new Club(2, "clubs", "info_pane", new Date(), 4);
                Club club5 = new Club(1, "clubs", "info_pane", new Date(), 5);
                Club club6 = new Club(2, "clubs", "info_pane", new Date(), 6);
                Club club7 = new Club(1, "clubs", "info_pane", new Date(), 7);
                Club club8 = new Club(2, "clubs", "info_pane", new Date(), 8);
                Club club9 = new Club(1, "clubs", "info_pane", new Date(), 9);
                Club club10 = new Club(2, "clubs", "info_pane", new Date(), 10);
                Club club11 = new Club(1, "clubs", "info_pane", new Date(), 11);
                Club club12 = new Club(2, "clubs", "info_pane", new Date(), 12);

                List<Club> listOfClubs = new ArrayList<>();
                listOfClubs.add(club);
                listOfClubs.add(club2);
                listOfClubs.add(club3);
                listOfClubs.add(club4);
                listOfClubs.add(club5);
                listOfClubs.add(club6);
                listOfClubs.add(club7);
                listOfClubs.add(club8);
                listOfClubs.add(club9);
                listOfClubs.add(club10);
                listOfClubs.add(club11);
                listOfClubs.add(club12);

//                serialising the objects
                ObjectSerializer serializer = new ObjectSerializer();
                jedis.publish(channel, Base64.getEncoder().encodeToString(serializer.serialize(listOfClubs)));

            }

        }catch (Exception e){
//            Print exception messages
            System.out.println("\nException message: " + e.getMessage());
        }finally {
//          Release resources
            if(jedis != null)
                jedis.close();
        }
    }
}
