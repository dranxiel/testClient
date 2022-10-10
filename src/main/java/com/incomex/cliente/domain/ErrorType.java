package com.incomex.cliente.domain;

import lombok.Getter;

@Getter
public enum ErrorType {
    INFO_CATEGORY_NAME_IN_USED("ERROR-01","El nombre de la categoria ya se encuentra Utilizado"),
    INFO_PRODUCT_NAME_IN_USED("ERROR-02","El nombre del producto ya se encuentra utilizado"),
    INFO_CATEGORY_ID_INVALID("ERROR-03","El id de la categoria no se encuentra registrado"),
    INFO_SUPPLIER_ID_INVALID("ERROR-04","El id de la supplier no se encuentra registrado");
    private String code;
    private String message;

    ErrorType(String codigo, String mensaje) {
        this.code = codigo;
        this.message = mensaje;
    }
}
