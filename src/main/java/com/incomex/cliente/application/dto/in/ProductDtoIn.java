package com.incomex.cliente.application.dto.in;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
//Se usa los tipos clase para darle soporte a null
public class ProductDtoIn {
    @NotEmpty
    private String name;
    private Integer SupplierID;
    private Integer CategoryID;
    private Integer QuantityPerUnit;
    private Double UnitsPrice;
    private Integer UnitsInStock;
    private Integer UnitsOnOrder;
    private Integer ReorderLevel;
    private Boolean Discontinued;
}
