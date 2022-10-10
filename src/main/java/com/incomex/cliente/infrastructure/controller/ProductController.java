package com.incomex.cliente.infrastructure.controller;

import com.incomex.cliente.application.dto.in.ProductDtoIn;
import com.incomex.cliente.application.dto.out.ErrorResponse;
import com.incomex.cliente.application.dto.out.ProductDtoOut;
import com.incomex.cliente.application.dto.out.ProductDtoOutList;
import com.incomex.cliente.application.port.input.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Operation(summary = "lista productos por page", responses = {
            @ApiResponse(description = "Error con el nombre",
                    responseCode = "202", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(description = "Ok",
                    responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDtoOutList.class))),
            @ApiResponse(description = "Internal Server Error",
                    responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))),
    })
    @GetMapping(value = "/{page}")
    public ResponseEntity<ProductDtoOutList> getReservationsByRoomId(@PathVariable int page) {
        ProductDtoOutList productDtoOutList = productService.getByPage(page);

        return ResponseEntity.ok(productDtoOutList);
    }

    @Operation(summary = "Obtiene un producto por page", responses = {
            @ApiResponse(description = "Error con el nombre",
                    responseCode = "202", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(description = "Ok",
                    responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDtoOut.class))),
            @ApiResponse(description = "Internal Server Error",
                    responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))),
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDtoOut> getReservationsAvailabilityByRoomId(@PathVariable int id) {
        ProductDtoOut ProductDtoOut = productService.getById(id);

        return ResponseEntity.ok(ProductDtoOut);
    }

    @Operation(summary = "Crear un producto", responses = {
            @ApiResponse(description = "Error con el nombre",
                    responseCode = "202", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(description = "Ok",
                    responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class))),
            @ApiResponse(description = "Internal Server Error",
                    responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))),
    })
    @PostMapping(value = "/")
    public ResponseEntity<Integer> create(@RequestBody @Validated ProductDtoIn productDtoIn) {

        int idProduct = productService.Create(productDtoIn);
        return ResponseEntity.ok(idProduct);
    }
}
