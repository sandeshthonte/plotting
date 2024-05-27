package com.arundhati.backend.plotting.config;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.beans.factory.annotation.Autowired;

// EncryptionConverter.java
@Converter
public class EncryptionConverter implements AttributeConverter<String, String> {
    @Autowired
    private EncryptionService encryptionService; // Assuming you have an EncryptionService for encryption/decryption

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return encryptionService.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return encryptionService.decrypt(dbData);
    }
}
