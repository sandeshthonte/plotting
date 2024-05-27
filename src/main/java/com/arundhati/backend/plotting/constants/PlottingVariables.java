package com.arundhati.backend.plotting.constants;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class PlottingVariables {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.password}")
    private String redisPassword;

    @Value("${encryption.init.vector}")
    private String encryptionInitVector;

    @Value("${encryption.secret.key}")
    private String encryptionSecretKey;

    @Value("${encryption.cipher.instance}")
    private String encryptionCipherInstance;


}
