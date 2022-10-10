package com.incomex.cliente.application.port.input.service;

import com.incomex.cliente.application.dto.in.CategoryDto;
import com.incomex.cliente.application.dto.out.CategoryOut;

public interface ICategoryService {

    CategoryOut Create(CategoryDto categoryDto);

    CategoryDto getById(int id);

}
