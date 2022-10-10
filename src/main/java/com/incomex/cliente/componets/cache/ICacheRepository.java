package com.incomex.cliente.componets.cache;

public interface ICacheRepository {
    void save(String key, String value, int expiration);

    String get(String key);

}
