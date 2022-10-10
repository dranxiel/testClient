package com.incomex.cliente.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//Se usa los tipos clase para darle soporte a null
public class ProductDomain {
    private int id;
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
