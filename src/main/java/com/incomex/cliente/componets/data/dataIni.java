package com.incomex.cliente.componets.data;

import com.incomex.cliente.application.port.input.repository.db.IProductDb;
import com.incomex.cliente.domain.ProductDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * Componet para insertar data demo
 * ApplicationReadyEvent despues que termina la carga base del sistema
 */
@Component
public class dataIni implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private IProductDb productDb;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (productDb.getByProducts(1, 1).size() == 0) {
            for (int i = 0; i < 100000; i++) {
                ProductDomain productDomain =
                        new ProductDomain(0, stringRandom(), null, intCategoryRandom(), intRandom(), doubleRandom(), intRandom(), intRandom(), intRandom(), false);
                productDb.create(productDomain);
            }
        }
    }

    /**
     * Generar un string random
     */
    private String stringRandom() {
        byte[] array = new byte[20]; // length is bounded by 7
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }

    /**
     * Generar un int random
     */
    private int intRandom() {
        return new Random(50000).nextInt();
    }

    private int intCategoryRandom() {
        return new Random().nextInt(2)+1;
    }

    /**
     * Generar un double random
     */
    private double doubleRandom() {
        return new Random(50000).nextDouble();
    }
}
