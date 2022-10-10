package com.incomex.cliente.infrastructure.controller;

import com.incomex.cliente.application.ApplicationException;
import com.incomex.cliente.application.dto.in.CategoryDto;
import com.incomex.cliente.application.port.input.service.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class ProductoController {

    @Autowired
    private ICategoryService iCategoryService;

    @Operation(summary = "get reservations by room id")
    @GetMapping(value = "v1/room/{roomId}")
    public ResponseEntity<List<ReservationsDTO>> getReservationsByRoomId(@PathVariable long roomId) {
        List<ReservationsDTO> reservationsByRoomId = iCategoryService.getReservationsByRoomId(roomId);
        return ResponseEntity.ok(reservationsByRoomId);
    }

    @Operation(summary = "get dates availability by room id")
    @GetMapping(value = "v1/roomAvailability/{roomId}")
    public ResponseEntity<AvailableRoomDTO> getReservationsAvailabilityByRoomId(@PathVariable long roomId) {
        AvailableRoomDTO reservationsByRoomId = iCategoryService.getAvailableDates(roomId);
        return ResponseEntity.ok(reservationsByRoomId);
    }

    @Operation(summary = "Book a room", responses = {
            @ApiResponse(description = "Request failed",
                    responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApplicationException.class))),
            @ApiResponse(description = "Request failed",
                    responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))),
    })
    @PostMapping()
    public ResponseEntity<Long> makeReservation(@RequestBody @Validated CategoryDto categoryDto) {

    }
    @Operation(summary = "Edit reservation.", responses = {
            @ApiResponse(description = "Request failed",
                    responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApplicationException.class))),
            @ApiResponse(description = "Request failed",
                    responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))),
    })
    @PutMapping(value = "v1/room")
    public ResponseEntity<ReservationResponseOk> editReservation(@RequestBody @Validated ReservationsDTOEditEntry reservationsDTOEntry) {
        iCategoryService.editReservation(reservationsDTOEntry);
        return ResponseEntity.ok(new ReservationResponseOk("Your reservation have been modified.", HttpStatus.OK.value()));

    }
    @Operation(summary = "delete booking", responses = {
            @ApiResponse(description = "Request failed",
                    responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApplicationException.class))),
            @ApiResponse(description = "Request failed",
                    responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))),
    })
    @DeleteMapping(value = "v1/room")
    public ResponseEntity<ReservationResponseOk> deleteReservation(@RequestBody @Validated ReservationsDTOECancelEntry reservationsDTOECancelEntry) {
        iCategoryService.deleteReservation(reservationsDTOECancelEntry);
        return ResponseEntity.ok(new ReservationResponseOk("Reservation cancelled successfully", HttpStatus.OK.value()));

    }
}
