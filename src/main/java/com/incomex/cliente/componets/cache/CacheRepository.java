package com.incomex.cliente.componets.cache;

import com.incomex.cliente.componets.util.Settings;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;


@Log
@Repository
public class CacheRepository implements ICacheRepository {

    private final StringRedisTemplate template;

    @Autowired
    private Settings settings;

    public CacheRepository(StringRedisTemplate template) {
        this.template = template;
    }

    @Override
    public void save(String key, String value, int expiration) {
        if (settings.isRedisEnable()) {
            try {
                template.opsForValue().set(key, value);
                template.expire(key, expiration, TimeUnit.MINUTES);
            } catch (Exception e) {
                log.warning("Redis server error al guardar key " + key + ". Message " + e.getMessage());
            }
        }
    }

    @Override
    public String get(String key) {
        if (settings.isRedisEnable()) {
            try {
                return template.opsForValue().get(key);
            } catch (Exception e) {
                log.warning("Redis server error al obtener key " + key + ". Message " + e.getMessage());
            }
        }
        return null;

    }
}
