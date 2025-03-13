package com.example.encryption.RsaEncryption;


import com.example.encryption.EncryptionStrategy;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

@Component("rsaEncryptionStrategy")
public class RsaEncryption implements EncryptionStrategy {

    private PublicKey publicKey;
    private PrivateKey privateKey;

    private void init(){
        try {
            KeyPair keyPair = generateKeyPair();
            this.privateKey = keyPair.getPrivate();
            this.publicKey = keyPair.getPublic();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error in RSA Encryption");
            e.printStackTrace();
        }
    }

    private KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        return keyPairGenerator.generateKeyPair();
    }

    @Override
    public String encryptData(String plainText) {
        try{
            init();
            System.out.println("Public Key: " + publicKey);
            System.out.println("Private Key: " + privateKey);

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedValue = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encryptedValue);
        } catch (Exception e){
            System.err.println("Error in RSA Decryption");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String decryptData(String cipherText) {
        try{
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedValue = cipher.doFinal(Base64.getMimeDecoder().decode(cipherText));
            return new String(decryptedValue);
        } catch (Exception e){
            System.err.println("Error in RSA Decryption");
            e.printStackTrace();
            return null;
        }
    }

}
