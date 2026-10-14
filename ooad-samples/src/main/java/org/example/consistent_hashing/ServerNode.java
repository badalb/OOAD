package org.example.consistent_hashing;

import java.util.Objects;

public class ServerNode {
    private final String id;
    private final String endpoint;

    public ServerNode(String id, String endpoint) {
        this.id = Objects.requireNonNull(id);
        this.endpoint = Objects.requireNonNull(endpoint);
    }

    public String getId() {
        return id;
    }

    public String getEndpoint() {
        return endpoint;
    }

    @Override
    public String toString() {
        return id + " (" + endpoint + ")";
    }
}
