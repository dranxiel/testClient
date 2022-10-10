package com.incomex.cliente.application.dto.out;

import com.incomex.cliente.application.dto.in.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//Se usa los tipos clase para darle soporte a null
public class ProductDtoOut {
    private int id;
    private String name;
    private SupplierDtoOut SupplierID;
    private CategoryDto Category;
    private Integer QuantityPerUnit;
    private Double UnitsPrice;
    private Integer UnitsInStock;
    private Integer UnitsOnOrder;
    private Integer ReorderLevel;
    private Boolean Discontinued;
}
