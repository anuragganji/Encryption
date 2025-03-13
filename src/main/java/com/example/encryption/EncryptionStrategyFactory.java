package com.example.encryption;

import com.example.encryption.enums.EncryptionStrategyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class EncryptionStrategyFactory {

    @Autowired
    @Qualifier("aesEncryptionStrategy")
    private EncryptionStrategy aesEncryption;

    @Autowired
    @Qualifier("base64EncryptionStrategy")
    private EncryptionStrategy base64Encryption;

    @Autowired
    @Qualifier("rsaEncryptionStrategy")
    private EncryptionStrategy rsaEncryption;

    public EncryptionStrategy getEncryptionStrategy(EncryptionStrategyType strategy) {
        return switch (strategy) {
            case BASE64 -> base64Encryption;
            case AES -> aesEncryption;
            case RSA -> rsaEncryption;
            default -> aesEncryption;
        };
    }
}




