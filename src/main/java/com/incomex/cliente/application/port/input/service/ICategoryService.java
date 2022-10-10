package com.incomex.cliente.application.port.input.service;

import com.incomex.cliente.application.dto.in.CategoryDto;
import com.incomex.cliente.domain.CategoryDomain;

public interface ICategoryService {

    int Create(CategoryDto categoryDto);

    CategoryDto getById(int id);

}
