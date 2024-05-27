package com.arundhati.backend.plotting.config;

import com.arundhati.backend.plotting.constants.PlottingVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class EncryptionService {

    @Autowired
    private PlottingVariables plottingVariables;

    public String encrypt(String data) {
        try {
            IvParameterSpec iv = new IvParameterSpec(plottingVariables.getEncryptionInitVector().getBytes(StandardCharsets.UTF_8));
            SecretKeySpec secretKeySpec = new SecretKeySpec(plottingVariables.getEncryptionSecretKey().getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance(plottingVariables.getEncryptionCipherInstance());
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
            byte[] encryptedData = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String decrypt(String encryptedData) {
        try {
            IvParameterSpec iv = new IvParameterSpec(plottingVariables.getEncryptionInitVector().getBytes(StandardCharsets.UTF_8));
            SecretKeySpec secretKeySpec = new SecretKeySpec(plottingVariables.getEncryptionSecretKey().getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance(plottingVariables.getEncryptionCipherInstance());
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
            byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
            return new String(decryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
