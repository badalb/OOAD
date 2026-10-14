package org.example.consistent_hashing;

public class ConsistentHashRouter {
    private final HashRing ring;

    public ConsistentHashRouter(
            HashFunction hashFunction, int virtualNodesPerServer) {
        ring = new HashRing(
                hashFunction, virtualNodesPerServer);
    }

    public void addServer(ServerNode server) {
        ring.addServer(server);
    }

    public void removeServer(String serverId) {
        ring.removeServer(serverId);
    }

    public ServerNode locate(String key) {
        return ring.getServer(key);
    }
}
