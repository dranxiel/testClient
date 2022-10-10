package com.incomex.cliente.application.port.input.service;

import com.incomex.cliente.application.dto.out.SupplierDtoOut;
import com.incomex.cliente.domain.SupplierDomain;

public interface ISupplierService {
    SupplierDtoOut getById(int id);
}
