package org.example.consistent_hashing;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        ConsistentHashRouter router =
                new ConsistentHashRouter(
                        new Sha256HashFunction(), 100);

        ServerNode serverA =
                new ServerNode("A", "10.0.0.1:8080");
        ServerNode serverB =
                new ServerNode("B", "10.0.0.2:8080");
        ServerNode serverC =
                new ServerNode("C", "10.0.0.3:8080");

        router.addServer(serverA);
        router.addServer(serverB);
        router.addServer(serverC);

        List<String> keys = Arrays.asList(
                "user-101", "user-202", "user-303",
                "user-404", "user-505", "user-606");

        System.out.println("Before adding server D:");

        Map<String, String> before = new LinkedHashMap<>();

        for (String key : keys) {
            String serverId =
                    router.locate(key).getId();

            before.put(key, serverId);
            System.out.println(
                    key + " -> " + serverId);
        }

        // Add a new physical server.
        router.addServer(
                new ServerNode("D", "10.0.0.4:8080"));

        System.out.println("\nAfter adding server D:");

        int changed = 0;

        for (String key : keys) {
            String newServerId =
                    router.locate(key).getId();

            System.out.println(
                    key + " -> " + newServerId);

            if (!before.get(key).equals(newServerId)) {
                changed++;
            }
        }

        System.out.println("\nKeys remapped in this sample: "
                + changed + " out of " + keys.size());

        // Remove a server.
        router.removeServer("B");

        System.out.println("\nAfter removing server B:");

        for (String key : keys) {
            System.out.println(
                    key + " -> " + router.locate(key).getId());
        }
    }

}
