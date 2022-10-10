package com.incomex.cliente.componets.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Getter
@Service
public class Settings {

    @Value("${product.byPage}")
    private int productByPage;

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.password}")
    private String redisPassword;

    @Value("${spring.redis.timeout}")
    private Integer redisTimeout;

    @Value("${spring.redis.enable}")
    private boolean redisEnable;

    @Value("${spring.project.name}")
    private String projectName;

    @Value("${spring.redis.time}")
    private int redisTime;
}

