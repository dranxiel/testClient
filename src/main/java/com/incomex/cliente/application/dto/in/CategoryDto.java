package com.incomex.cliente.application.dto.in;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CategoryDto {
    @NotEmpty
    private String name;
    private String description;
    private String pictureBase64;
}
