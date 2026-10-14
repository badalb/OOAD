package org.example.consistent_hashing;

public class VirtualNode {
    private final String id;
    private final ServerNode server;

    public VirtualNode(String id, ServerNode server) {
        this.id = id;
        this.server = server;
    }

    public String getId() {
        return id;
    }

    public ServerNode getServer() {
        return server;
    }
}
