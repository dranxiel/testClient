package com.incomex.cliente.application;

import com.incomex.cliente.application.dto.in.CategoryDto;
import com.incomex.cliente.application.dto.in.ProductDtoIn;
import com.incomex.cliente.application.dto.out.ProductDtoOut;
import com.incomex.cliente.application.dto.out.ProductDtoOutList;
import com.incomex.cliente.application.dto.out.ProductOutCreate;
import com.incomex.cliente.application.dto.out.SupplierDtoOut;
import com.incomex.cliente.application.port.input.repository.db.IProductDb;
import com.incomex.cliente.application.port.input.service.ICategoryService;
import com.incomex.cliente.application.port.input.service.IProductService;
import com.incomex.cliente.application.port.input.service.ISupplierService;
import com.incomex.cliente.componets.util.Settings;
import com.incomex.cliente.domain.ErrorType;
import com.incomex.cliente.domain.ProductDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductDb productDb;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ISupplierService supplierService;

    @Autowired
    private Settings settings;

    /**
     * @param productDtoIn Recibe un objeto categoria. Solo es requerido el nombre. La foto se maneja como base 64.
     */
    @Override
    public ProductOutCreate Create(ProductDtoIn productDtoIn) {

        validateName(productDtoIn);

        //No se lanza asincrono por que son dos procesos. si fueran mas de 4 se podrian async.
        CategoryDto category = getCategoryDto(productDtoIn.getCategoryID());

        SupplierDtoOut supplier = getSupplierDtoOut(productDtoIn.getSupplierID());

        ProductDomain productDomain = mapProductIn(productDtoIn);

        return new ProductOutCreate(productDb.create(productDomain));
    }


    private ProductDomain mapProductIn(ProductDtoIn productDtoIn) {
        return new ProductDomain(0, productDtoIn.getName(),
                productDtoIn.getSupplierID(), productDtoIn.getCategoryID(), productDtoIn.getQuantityPerUnit(), productDtoIn.getUnitsPrice(), productDtoIn.getUnitsInStock(),
                productDtoIn.getUnitsOnOrder(), productDtoIn.getReorderLevel(), productDtoIn.getDiscontinued());
    }

    /**
     * @param productDtoIn Recibe un objeto Producto. Y retorna un error si existe.
     */
    private void validateName(ProductDtoIn productDtoIn) {
        ProductDomain productDomain = productDb.getByProductName(productDtoIn.getName());
        if (productDomain != null) {
            throw new ApplicationException(ErrorType.INFO_PRODUCT_NAME_IN_USED);
        }
    }

    /**
     * @param id Recibe un id Producto.
     * @return retorna un Producto
     */
    @Override
    public ProductDtoOut getById(int id) {
        ProductDomain productDomain = productDb.getByProductID(id);

        return mapToProductDtoOut(productDomain);
    }

    /**
     * @param page Recibe un numero pagina.
     * @return retorna una lista de Productos, con un maximo de productos. Definido en application Yml product: byPage
     */
    @Override
    public ProductDtoOutList getByPage(int page) {
        if (page < 1) {
            throw new ApplicationException(ErrorType.INFO_PRODUCT_PAGE_NO_VALID);
        }
        int offset = (page - 1) * settings.getProductByPage();
        List<ProductDomain> products = productDb.getByProducts(offset, settings.getProductByPage());

        List<ProductDtoOut> productDtoOuts = products.stream().map(this::mapToProductDtoOut).collect(Collectors.toList());

        return new ProductDtoOutList(productDtoOuts, page);
    }


    private ProductDtoOut mapToProductDtoOut(ProductDomain productDomain) {

        CategoryDto category = getCategoryDto(productDomain.getCategoryID());

        SupplierDtoOut supplier = getSupplierDtoOut(productDomain.getSupplierID());

        return new ProductDtoOut(productDomain.getId(), productDomain.getName(), supplier, category, productDomain.getQuantityPerUnit(),
                productDomain.getUnitsPrice(), productDomain.getUnitsInStock(),
                productDomain.getUnitsOnOrder(), productDomain.getReorderLevel(), productDomain.getDiscontinued());
    }

    private CategoryDto getCategoryDto(Integer idCategory) {
        CategoryDto category = null;
        if (idCategory != null) {
            category = categoryService.getById(idCategory);
        }
        return category;
    }

    private SupplierDtoOut getSupplierDtoOut(Integer idSupplierId) {
        SupplierDtoOut supplier = null;
        if (idSupplierId != null) {
            supplier = supplierService.getById(idSupplierId);
        }
        return supplier;
    }
}
