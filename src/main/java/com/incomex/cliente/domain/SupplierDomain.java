package com.incomex.cliente.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierDomain {
    private int id;
    private String name;
    private String description;
    private String pictureBase64;
}
