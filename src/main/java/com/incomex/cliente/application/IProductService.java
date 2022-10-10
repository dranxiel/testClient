package com.incomex.cliente.application;

import com.incomex.cliente.application.dto.in.ProductDtoIn;

public interface IProductService {
    int Create(ProductDtoIn productDtoIn);
}
