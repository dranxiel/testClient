package com.incomex.cliente.infrastructure.repository.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.incomex.cliente.application.port.input.repository.cache.ICache;
import com.incomex.cliente.componets.cache.ICacheRepository;
import com.incomex.cliente.componets.util.Settings;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Cache<T> implements ICache<T> {

    @Autowired
    private ICacheRepository cacheRepository;

    @Autowired
    private Settings settings;

    @Override
    @SneakyThrows
    public T get(String key, Class classBase) {
        String s = cacheRepository.get(settings.getProjectName() + "_" + key);
        T t = null;
        if (s != null) {
            t = (T) new ObjectMapper().readValue(s, classBase);
        }
        return t;
    }

    @Override
    @SneakyThrows
    public T get(int id, String className, Class classBase) {

        String key = className + "_" + id;
        return get(key, classBase);
    }

    @Override
    @SneakyThrows
    public void save(String key, T object) {

        String s = new ObjectMapper().writeValueAsString(object);
        cacheRepository.save(settings.getProjectName() + "_" + key, s, settings.getRedisTime());

    }

    @Override
    @SneakyThrows
    public void save(int id, String className, T object) {
        String key = className + "_" + id;
        save(key, object);

    }
}
