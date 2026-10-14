package org.example.consistent_hashing;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256HashFunction implements HashFunction {

    @Override
    public long hash(String value) {
        try {
            MessageDigest digest =
                    MessageDigest.getInstance("SHA-256");

            byte[] bytes = digest.digest(
                    value.getBytes(StandardCharsets.UTF_8));

            // Use the first 64 bits as a ring position.
            return ByteBuffer.wrap(bytes).getLong();

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(
                    "SHA-256 is unavailable", e);
        }
    }
}