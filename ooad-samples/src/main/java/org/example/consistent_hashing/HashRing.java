package org.example.consistent_hashing;

import java.util.*;

public class HashRing {

    private final NavigableMap<Long, List<VirtualNode>> ring =
            new TreeMap<>();

    private final Map<String, ServerNode> servers =
            new HashMap<>();

    private final HashFunction hashFunction;

    private final int virtualNodesPerServer;

    public HashRing(HashFunction hashFunction,
                    int virtualNodesPerServer) {
        if (virtualNodesPerServer <= 0) {
            throw new IllegalArgumentException(
                    "Virtual node count must be positive");
        }

        this.hashFunction = hashFunction;
        this.virtualNodesPerServer = virtualNodesPerServer;
    }

    public synchronized void addServer(ServerNode server) {
        if (servers.containsKey(server.getId())) {
            throw new IllegalArgumentException(
                    "Server already exists: " + server.getId());
        }

        servers.put(server.getId(), server);

        for (int i = 0; i < virtualNodesPerServer; i++) {
            String vnodeId = server.getId() + "#" + i;

            VirtualNode vnode =
                    new VirtualNode(vnodeId, server);

            long position = hashFunction.hash(vnodeId);

            // Preserve entries even if hash positions collide.
            ring.computeIfAbsent(
                            position, ignored -> new ArrayList<>())
                    .add(vnode);
        }
    }

    public synchronized void removeServer(String serverId) {
        ServerNode server = servers.remove(serverId);

        if (server == null) {
            return;
        }

        for (int i = 0; i < virtualNodesPerServer; i++) {
            String vnodeId = serverId + "#" + i;
            long position = hashFunction.hash(vnodeId);

            List<VirtualNode> nodes = ring.get(position);

            if (nodes != null) {
                nodes.removeIf(
                        vnode -> vnode.getId().equals(vnodeId));

                if (nodes.isEmpty()) {
                    ring.remove(position);
                }
            }
        }
    }

    public synchronized ServerNode getServer(String key) {
        if (ring.isEmpty()) {
            throw new IllegalStateException(
                    "No servers are available");
        }

        long keyPosition = hashFunction.hash(key);

        // First position clockwise from the key.
        Map.Entry<Long, List<VirtualNode>> entry =
                ring.ceilingEntry(keyPosition);

        // Wrap around to the beginning of the ring.
        if (entry == null) {
            entry = ring.firstEntry();
        }

        // In a rare hash collision, use the first vnode
        // in the list consistently.
        return entry.getValue().get(0).getServer();
    }

    public synchronized int getVirtualNodeCount() {
        return ring.values().stream()
                .mapToInt(List::size)
                .sum();
    }

    public synchronized Set<String> getServerIds() {
        return new TreeSet<>(servers.keySet());
    }
}
