package com.incomex.cliente.application;

import com.incomex.cliente.util.Settings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private IReservationsRepository reservationsRepository;

    @Mock
    private IRoomRepository roomRepository;

    @Mock
    private ICanceledBookingsRepository canceledBookingsRepository;

    @Mock
    private Settings settings;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void getReservationsByRoomIdSuccess() throws Exception {
        LocalDate localDate = LocalDate.now().minusDays(1);

        Room room = getRoom();

        Reservations reservations = Reservations.builder().clientName("Test").room(room).dateFrom(LocalDate.now()).dateTo(LocalDate.now()).build();

        Mockito.when(reservationsRepository.getReservationsByRoomId(1L, localDate)).thenReturn(Arrays.asList(reservations));

        List<ReservationsDTO> result = categoryService.getReservationsByRoomId(1L);

        assertEquals(result.size(), 1);
        Mockito.verify(reservationsRepository, Mockito.times(1)).getReservationsByRoomId(1L, localDate);
    }

    @Test
    public void getAvailableDatesShouldHave30DaysIfThereIsNoReservation(){
        Room room = getRoom();
        List<Reservations> reservationsList = new ArrayList<>();
        Mockito.when(roomRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(room));
        Mockito.when(reservationsRepository.getReservationsNext30Days(room.getId(), LocalDate.now().plusDays(1), LocalDate.now().plusDays(30)))
                .thenReturn(reservationsList);

        AvailableRoomDTO result = categoryService.getAvailableDates(1L);

        assertEquals(result.getAvailableDates().size(), 30);
    }

    @Test
    public void getAvailableDatesShouldFail(){
        Room room = getRoom();
        room.setAvailable(false);
        Mockito.when(roomRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(room));

        ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            categoryService.getAvailableDates(1L);
        });

        assertEquals("This room is not available", exception.getMessage());
    }

    @Test
    public void makeReservationSuccess(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        Room room = getRoom();
        List<Reservations> reservationsList = new ArrayList<>();

        ReservationsDTOEntry reservationsDTOEntry = ReservationsDTOEntry.builder()
                .clientName("Test").roomId(1L).dateFrom("05/28/2022").dateTo("05/28/2022").build();

        LocalDate dateFrom = LocalDate.parse(reservationsDTOEntry.getDateFrom(), formatter);
        LocalDate dateTo = LocalDate.parse(reservationsDTOEntry.getDateTo(), formatter);
        Mockito.when(settings.getMaxDayReserv()).thenReturn(3);
        Mockito.when(roomRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(room));
        Mockito.when(reservationsRepository.getReservationsByPeriod(1L, dateFrom, dateTo)).thenReturn(reservationsList);

        categoryService.makeReservation(reservationsDTOEntry);
        Mockito.verify(reservationsRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void editReservationSuccess(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        Room room = getRoom();
        List<Reservations> reservationsList = new ArrayList<>();

        ReservationsDTOEditEntry reservationsDTOEditEntry = ReservationsDTOEditEntry.builder()
                .roomId(1L).dateFrom("05/28/2022").dateTo("05/28/2022").idReservation(1L).build();

        LocalDate dateFrom = LocalDate.parse(reservationsDTOEditEntry.getDateFrom(), formatter);
        LocalDate dateTo = LocalDate.parse(reservationsDTOEditEntry.getDateTo(), formatter);
        Mockito.when(settings.getMaxDayReserv()).thenReturn(3);
        Mockito.when(roomRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(room));
        Mockito.when(reservationsRepository.getReservationsByPeriod(1L, dateFrom, dateTo)).thenReturn(reservationsList);

        categoryService.editReservation(reservationsDTOEditEntry);
        Mockito.verify(reservationsRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void deleteReservationSuccess(){
        ReservationsDTOECancelEntry reservationsDTOECancelEntry = ReservationsDTOECancelEntry.builder()
                .idReservation(1l).motive("Test").build();
        Room room = getRoom();
        Reservations reservations = Reservations.builder().clientName("Test").room(room).dateFrom(LocalDate.now()).dateTo(LocalDate.now()).build();

        Mockito.when(reservationsRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(reservations));
        Mockito.doNothing().when(reservationsRepository).delete(reservations);
        Mockito.when(canceledBookingsRepository.save(Mockito.any())).thenReturn(null);

        categoryService.deleteReservation(reservationsDTOECancelEntry);

        Mockito.verify(reservationsRepository, Mockito.times(1)).delete(Mockito.any());
    }


    private Room getRoom(){
        return Room.builder().roomName("Test").roomType("Test").roomPrice(200.00).available(true).id(1L).build();
    }

}