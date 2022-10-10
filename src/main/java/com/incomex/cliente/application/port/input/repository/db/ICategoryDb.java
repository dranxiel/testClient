package com.incomex.cliente.application.port.input.repository.db;

import com.incomex.cliente.domain.CategoryDomain;

public interface ICategoryDb {
    int create(CategoryDomain category);

    CategoryDomain getByName(String categoryName);

    CategoryDomain getById(int id);
}
