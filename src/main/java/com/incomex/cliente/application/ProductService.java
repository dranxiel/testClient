package com.incomex.cliente.application;

import com.incomex.cliente.application.dto.in.ProductDtoIn;
import com.incomex.cliente.application.port.input.repository.db.IProductDb;
import com.incomex.cliente.application.port.input.service.ICategoryService;
import com.incomex.cliente.domain.CategoryDomain;
import com.incomex.cliente.domain.ErrorType;
import com.incomex.cliente.domain.ProductDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductDb productDb;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ISupplierService supplierService;

    /**
     * @param productDtoIn Recibe un objeto categoria. Solo es requerido el nombre. La foto se maneja como base 64.
     */
    @Override
    public int Create(ProductDtoIn productDtoIn) {

        validateName(productDtoIn);

        //No se lanza asincrono por que son dos procesos. si fueran mas de 4 se podrian async.
        categoryService.getById(productDtoIn.getCategoryID());

        supplierService.getById(productDtoIn.getSupplierID());

        ProductDomain productDomain = mapProduct(productDtoIn);

        return productDb.create(productDomain);
    }


    private ProductDomain mapProduct(ProductDtoIn productDtoIn) {
        return new ProductDomain(0, productDtoIn.getName(),
                productDtoIn.getSupplierID(), productDtoIn.getCategoryID(), productDtoIn.getQuantityPerUnit(), productDtoIn.getUnitsPrice(), productDtoIn.getUnitsInStock(),
                productDtoIn.getUnitsOnOrder(), productDtoIn.getReorderLevel(), productDtoIn.getDiscontinued());
    }

    /**
     * @param categoryDto Recibe un objeto categoria. Y retorna un error si existe la categoria.
     */
    private void validateName(ProductDtoIn categoryDto) {
        ProductDomain productDomain = productDb.getByProductName(categoryDto.getName());
        if (productDomain == null) {
            throw new ApplicationException(ErrorType.INFO_PRODUCT_NAME_IN_USED);
        }
    }
}
