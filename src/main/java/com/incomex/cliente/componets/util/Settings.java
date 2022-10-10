package com.incomex.cliente.componets.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Getter
@Service
public class Settings {

    @Value("${product.byPage}")
    private int productByPage;
}
