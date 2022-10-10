package com.incomex.cliente.application.port.input.service;

import com.incomex.cliente.application.dto.out.SupplierDtoOut;

public interface ISupplierService {
    SupplierDtoOut getById(int id);
}
