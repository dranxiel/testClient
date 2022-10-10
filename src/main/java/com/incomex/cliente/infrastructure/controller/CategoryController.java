package com.incomex.cliente.infrastructure.controller;

import com.incomex.cliente.application.ApplicationException;
import com.incomex.cliente.application.dto.in.CategoryDto;
import com.incomex.cliente.application.dto.out.ErrorResponse;
import com.incomex.cliente.application.port.input.service.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService iCategoryService;
    @Operation(summary = "Crear una categoria", responses = {
            @ApiResponse(description = "Error con el nombre",
                    responseCode = "202", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(description = "Ok",
                    responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class))),
    })
    @PostMapping(value = "/")
    public ResponseEntity<Integer> makeReservation(@RequestBody @Validated CategoryDto categoryDto) {
        int idCategory = iCategoryService.Create(categoryDto);
        return ResponseEntity.ok(idCategory);
    }

}
