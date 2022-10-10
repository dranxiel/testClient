package com.incomex.cliente.application;

import com.incomex.cliente.domain.SupplierDomain;

public interface ISupplierService {
    SupplierDomain getById(int id);
}
