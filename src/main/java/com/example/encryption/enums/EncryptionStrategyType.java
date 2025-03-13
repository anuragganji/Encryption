package com.example.encryption.enums;

import lombok.Getter;

@Getter
public enum EncryptionStrategyType {

    AES("AES"),
    BASE64("BASE64"),
    RSA("RSA");

    private String name;

    EncryptionStrategyType(String value) {
        this.name = value;
    }


}
