package com.incomex.cliente.application.port.input.repository.db;

import com.incomex.cliente.domain.ProductDomain;

import java.util.List;

public interface IProductDb {
    int create(ProductDomain productDomain);

    ProductDomain getByProductName(String productName);

    List<ProductDomain> getByProducts(int offset, int fetch);

    ProductDomain getByProductID(int id);

}
