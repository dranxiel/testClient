package com.incomex.cliente.application.dto.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDtoOut {
    private String name;
    private String description;
    private String pictureBase64;
}
