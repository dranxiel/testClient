package com.incomex.cliente.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.incomex.cliente.application.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @InjectMocks
    private CategoryController reservationController;

    @Mock
    private CategoryService categoryService;

    private MockMvc mockMvc;

    @Test
    public void getReservationsByRoomIdSuccess() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();
        Mockito.when(categoryService.getReservationsByRoomId(Mockito.anyLong())).thenReturn(new ArrayList<>());

        MvcResult result = this.mockMvc.perform(get("/reservation/v1/room/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int statusCode = result.getResponse().getStatus();
        Assertions.assertEquals(200, statusCode);
    }

    @Test
    public void getReservationsAvailabilityByRoomIdSuccess() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();
        Mockito.when(categoryService.getAvailableDates(Mockito.anyLong())).thenReturn(new AvailableRoomDTO());

        MvcResult result = this.mockMvc.perform(get("/reservation/v1/roomAvailability/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int statusCode = result.getResponse().getStatus();
        Assertions.assertEquals(200, statusCode);
    }

    @Test
    public void makeReservationSuccess() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();

        ReservationsDTOEntry reservationsDTOEntry = ReservationsDTOEntry.builder().roomId(1).dateFrom("05/28/2022")
                .dateTo("05/28/2022").clientName("Test").build();

        MvcResult result = this.mockMvc.perform(post("/reservation/v1/room")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(reservationsDTOEntry)))
                .andReturn();

        int statusCode = result.getResponse().getStatus();
        Assertions.assertEquals(200, statusCode);
    }

    @Test
    public void editReservationSuccess() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();

        ReservationsDTOEditEntry reservationsDTOEditEntry = ReservationsDTOEditEntry.builder()
                .roomId(1).dateFrom("05/28/2022").dateTo("05/28/2022").idReservation(1).build();

        MvcResult result = this.mockMvc.perform(put("/reservation/v1/room")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(reservationsDTOEditEntry)))
                .andReturn();

        int statusCode = result.getResponse().getStatus();
        Assertions.assertEquals(200, statusCode);
    }

    @Test
    public void deleteReservation() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();

        ReservationsDTOECancelEntry reservationsDTOECancelEntry = ReservationsDTOECancelEntry.builder()
                .idReservation(1).motive("Test").build();

        MvcResult result = this.mockMvc.perform(delete("/reservation/v1/room")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(reservationsDTOECancelEntry)))
                .andReturn();

        int statusCode = result.getResponse().getStatus();
        Assertions.assertEquals(200, statusCode);
    }

}