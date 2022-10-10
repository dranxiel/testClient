package com.incomex.cliente.application.port.input.repository.cache;

import lombok.SneakyThrows;

public interface ICache<T> {
    T get(String key, Class classBase);

    T get(int id, String className, Class classBase);

    void save(String key, T object);

    void save(int id, String className, T object);

}
