package com.example.encryption.Runner;

import com.example.encryption.EncryptionStrategyFactory;
import com.example.encryption.Exception.NullKeyException;
import com.example.encryption.TokenGenerationService;
import com.example.encryption.enums.EncryptionStrategyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class EncryptionRunner implements CommandLineRunner {

    @Autowired
    private EncryptionStrategyFactory encryptionStrategyFactory;

    @Override
    public void run(String... args) throws Exception {

        TokenGenerationService tokenGenerationService = new TokenGenerationService();

        tokenGenerationService.setEncryptionStrategyType(encryptionStrategyFactory.getEncryptionStrategy(EncryptionStrategyType.BASE64));

        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();

        try {
            System.out.println("BASE_64 Encryption!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("String to encrypt: " + text);
            String base64Encrypted = tokenGenerationService.encrypt(text);
            System.out.println("Encrypted String: " + base64Encrypted);
            System.out.println("String to decrypt: " + base64Encrypted);
            String base64Decrypted = tokenGenerationService.decrypt(base64Encrypted);
            System.out.println("Decrypted String: " + base64Decrypted);
        } catch (NullKeyException e) {
            throw new RuntimeException(e);
        }
        tokenGenerationService.setEncryptionStrategyType(encryptionStrategyFactory.getEncryptionStrategy(EncryptionStrategyType.AES));
        try {
            System.out.println("AES Encryption!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("String to encrypt: " + text);
            String aesEncrypted = tokenGenerationService.encrypt(text);
            System.out.println("Encrypted String: " + aesEncrypted);
            System.out.println("String to decrypt: " + aesEncrypted);
            String aesDecrypted = tokenGenerationService.decrypt(aesEncrypted);
            System.out.println("Decrypted String: " + aesDecrypted);
        } catch (NullKeyException e) {
            throw new RuntimeException(e);
        }
        tokenGenerationService.setEncryptionStrategyType(encryptionStrategyFactory.getEncryptionStrategy(EncryptionStrategyType.RSA));
        try {
            System.out.println("RSA Encryption!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("String to encrypt: " + text);
            String rsaEncrypted = tokenGenerationService.encrypt(text);
            System.out.println("Encrypted String: " + rsaEncrypted);
            System.out.println("String to decrypt: " + rsaEncrypted);
            String rsaDecrypted = tokenGenerationService.decrypt(rsaEncrypted);
            System.out.println("Decrypted String: " + rsaDecrypted);
        } catch (NullKeyException e) {
            throw new RuntimeException(e);
        }

    }
}
