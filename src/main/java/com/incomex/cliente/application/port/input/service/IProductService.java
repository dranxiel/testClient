package com.incomex.cliente.application.port.input.service;

import com.incomex.cliente.application.dto.in.ProductDtoIn;
import com.incomex.cliente.application.dto.out.ProductDtoOut;
import com.incomex.cliente.application.dto.out.ProductDtoOutList;
import com.incomex.cliente.application.dto.out.ProductOutCreate;

public interface IProductService {

    ProductOutCreate Create(ProductDtoIn productDtoIn);

    ProductDtoOut getById(int id);

    ProductDtoOutList getByPage(int page);
}
