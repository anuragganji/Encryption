package com.example.encryption.base64encryption;

import com.example.encryption.EncryptionStrategy;
import org.springframework.stereotype.Component;

@Component("base64EncryptionStrategy")
public class Base64Encryption implements EncryptionStrategy {

    @Override
    public String encryptData(String plainText) {
        return java.util.Base64.getEncoder().encodeToString(plainText.getBytes());
    }

    @Override
    public String decryptData(String cipherText) {
        byte[] decodedBytes = java.util.Base64.getDecoder().decode(cipherText);
        return new String(decodedBytes);
    }
}
