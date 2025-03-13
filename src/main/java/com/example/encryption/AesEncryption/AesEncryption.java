package com.example.encryption.AesEncryption;

import com.example.encryption.EncryptionStrategy;
import com.example.encryption.Exception.NullKeyException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


@Component("aesEncryptionStrategy")
public class AesEncryption implements EncryptionStrategy {

    @Value("${external.encryption.key}")
    private String keyString;

    // This method is used to generate a secret key for AES encryption
    private static Key getKey(String keyString) throws NoSuchAlgorithmException {
        byte[] key = keyString.getBytes(StandardCharsets.UTF_8);
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16);
        return new SecretKeySpec(key, "AES");
    }

    @Override
    public String encryptData(String plainText) throws RuntimeException, NullKeyException {
        if (!ObjectUtils.isEmpty(keyString)) {
            Cipher cipher;
            try {
                cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
                cipher.init(Cipher.ENCRYPT_MODE, getKey(keyString));
                return Base64.encodeBase64String(cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8)));
            } catch (Exception e) {
                throw new RuntimeException("Error while encrypting: " + plainText, e);
            }
        } else {
            throw new NullKeyException("Key String Not Found");
        }
    }

    @Override
    public String decryptData(String cipherText) throws RuntimeException, NullKeyException {
        if (!ObjectUtils.isEmpty(keyString)) {
            Cipher cipher;
            try {
                cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
                cipher.init(Cipher.DECRYPT_MODE, getKey(keyString));
                return new String(cipher.doFinal(Base64.decodeBase64(cipherText)), StandardCharsets.UTF_8);
            } catch (Exception e) {
                throw new RuntimeException("Error while decrypting " + cipherText, e);
            }
        } else {
            throw new NullKeyException("Key String Not Found");
        }
    }
}
