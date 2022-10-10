package com.incomex.cliente.application.port.input.repository.db;

import com.incomex.cliente.domain.SupplierDomain;

public interface ISupplierDb {
    SupplierDomain getById(int id);
}
