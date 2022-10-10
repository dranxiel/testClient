package com.incomex.cliente.application;

import com.incomex.cliente.application.port.input.repository.db.ISupplierDb;
import com.incomex.cliente.domain.ErrorType;
import com.incomex.cliente.domain.SupplierDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService implements ISupplierService {

    @Autowired
    private ISupplierDb supplierDb;


    /**
     * @param id recibe un id de categoria, si existe la retorna, si no retorna una excepcion
     *           se deja abierto  el retorno para futura reutilizacion.
     */
    @Override
    public SupplierDomain getById(int id) {
        SupplierDomain categoryLocal = supplierDb.getById(id);
        if (categoryLocal == null) {
            throw new ApplicationException(ErrorType.INFO_CATEGORY_ID_INVALID);
        }
        return categoryLocal;
    }
}
