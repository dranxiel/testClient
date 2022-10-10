package com.incomex.cliente.application.port.input.service;

import com.incomex.cliente.application.dto.in.ProductDtoIn;
import com.incomex.cliente.application.dto.out.ProductDtoOut;
import com.incomex.cliente.application.dto.out.ProductDtoOutList;

import java.util.List;

public interface IProductService {

    int Create(ProductDtoIn productDtoIn);

    ProductDtoOut getById(int id);

    ProductDtoOutList getByPage(int page);
}
