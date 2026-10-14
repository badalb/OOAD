package org.example.consistent_hashing;

public interface HashFunction {
    long hash(String value);
}