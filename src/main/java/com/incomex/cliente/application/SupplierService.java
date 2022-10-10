package com.incomex.cliente.application;

import com.incomex.cliente.application.dto.out.SupplierDtoOut;
import com.incomex.cliente.application.port.input.repository.db.ISupplierDb;
import com.incomex.cliente.application.port.input.service.ISupplierService;
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
     *
     */
    @Override
    public SupplierDtoOut getById(int id) {
        SupplierDomain supplierLocal = supplierDb.getById(id);
        if (supplierLocal == null) {
            throw new ApplicationException(ErrorType.INFO_CATEGORY_ID_INVALID);
        }
        SupplierDtoOut supplierDtoOut = mapToSupplierOut(supplierLocal);
        return supplierDtoOut;
    }

    private SupplierDtoOut mapToSupplierOut(SupplierDomain supplierLocal) {
        SupplierDtoOut supplierDtoOut = new SupplierDtoOut(supplierLocal.getName(), supplierLocal.getDescription(), supplierLocal.getPictureBase64());
        return supplierDtoOut;
    }
}
