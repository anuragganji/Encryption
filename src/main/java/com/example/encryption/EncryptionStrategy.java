package com.example.encryption;
import com.example.encryption.Exception.NullKeyException;

public interface EncryptionStrategy {

    String encryptData(String plainText) throws NullKeyException;
    String decryptData(String cipherText) throws NullKeyException;
}
