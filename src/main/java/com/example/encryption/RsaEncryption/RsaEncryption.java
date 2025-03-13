package com.example.encryption.RsaEncryption;


import com.example.encryption.EncryptionStrategy;
import org.springframework.stereotype.Component;

@Component("rsaEncryptionStrategy")
public class RsaEncryption implements EncryptionStrategy {

    @Override
    public String encryptData(String plainText) {
        return "RSA Encrypted: " + plainText;
    }

    @Override
    public String decryptData(String cipherText) {
        return "RSA Decrypted: " + cipherText;
    }
}
