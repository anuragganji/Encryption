package com.example.encryption;

import com.example.encryption.Exception.NullKeyException;
import org.springframework.stereotype.Component;

@Component
public class TokenGenerationService {

    private EncryptionStrategy encryptionStrategy;

    public void setEncryptionStrategyType(EncryptionStrategy encryptionStrategy) {
        this.encryptionStrategy = encryptionStrategy;
    }

    public String encrypt(String plainText) throws NullKeyException {
        return encryptionStrategy.encryptData(plainText);
    }

    public String decrypt(String cipherText) throws NullKeyException {
        return encryptionStrategy.decryptData(cipherText);
    }
}