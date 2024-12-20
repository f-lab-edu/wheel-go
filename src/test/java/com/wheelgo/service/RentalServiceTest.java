package com.wheelgo.service;

import com.wheelgo.dto.RentalDTO;
import com.wheelgo.entity.Rental;
import com.wheelgo.repository.RentalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RentalServiceTest {

    @Mock
    private RentalRepository rentalRepository;

    @InjectMocks
    private RentalService rentalService;

    @Test
    void rentVehicle_success() {
        String qrCode = "123ABC";
        String vehicleType = "Bike";
        Long userId = 1L;
        String username = "TestUser";
        String location = "TestLocation";

        when(rentalRepository.findByVehicleQrCodeAndReturnedFalse(qrCode))
                .thenReturn(Collections.emptyList());

        Rental savedRental = new Rental();
        savedRental.setId(1L);
        savedRental.setUserId(userId);
        savedRental.setUsername(username);
        savedRental.setVehicleQrCode(qrCode);
        savedRental.setVehicleType(vehicleType);
        savedRental.setLocation(location);
        savedRental.setRentalStartTime(LocalDateTime.now());
        savedRental.setReturned(true);

        when(rentalRepository.save(any(Rental.class))).thenReturn(savedRental);

        RentalDTO rentalDTO = rentalService.rentVehicle(qrCode, vehicleType, userId, username, location);

        assertNotNull(rentalDTO);
        assertEquals(userId, rentalDTO.getUserId());
        assertEquals(username, rentalDTO.getUsername());
        assertEquals(qrCode, rentalDTO.getVehicleQrCode());
        assertEquals(vehicleType, rentalDTO.getVehicleType());
        assertEquals(location, rentalDTO.getLocation());

        verify(rentalRepository, times(1)).findByVehicleQrCodeAndReturnedFalse(qrCode);
        verify(rentalRepository, times(1)).save(any(Rental.class));
    }

    @Test
    void rentVehicle_alreadyRented() {
        String qrCode = "123ABC";

        Rental existingRental = new Rental();
        existingRental.setVehicleQrCode(qrCode);
        existingRental.setReturned(false);

        when(rentalRepository.findByVehicleQrCodeAndReturnedFalse(qrCode))
                .thenReturn(Collections.singletonList(existingRental));

        Exception exception = assertThrows(RuntimeException.class, () ->
                rentalService.rentVehicle(qrCode, "Bike", 1L, "TestUser", "TestLocation"));

        assertEquals("Vehicle is already rented", exception.getMessage());

        verify(rentalRepository, times(1)).findByVehicleQrCodeAndReturnedFalse(qrCode);
        verify(rentalRepository, never()).save(any(Rental.class));
    }

    @Test
    void returnVehicle_success() {
        String qrCode = "123ABC";

        Rental rental = new Rental();
        rental.setId(1L);
        rental.setVehicleQrCode(qrCode);
        rental.setReturned(false);
        rental.setRentalStartTime(LocalDateTime.now());

        when(rentalRepository.findByVehicleQrCodeAndReturnedFalse(qrCode))
                .thenReturn(Collections.singletonList(rental));

        when(rentalRepository.save(any(Rental.class))).thenAnswer(invocation -> {
            Rental updatedRental = invocation.getArgument(0);
            updatedRental.setRentalEndTime(LocalDateTime.now());
            updatedRental.setReturned(true);
            return updatedRental;
        });

        RentalDTO rentalDTO = rentalService.returnVehicle(qrCode);

        assertNotNull(rentalDTO);
        assertTrue(rentalDTO.isReturned());
        assertNotNull(rentalDTO.getRentalEndTime());

        verify(rentalRepository, times(1)).findByVehicleQrCodeAndReturnedFalse(qrCode);
        verify(rentalRepository, times(1)).save(any(Rental.class));
    }

    @Test
    void returnVehicle_noActiveRental() {
        String qrCode = "123ABC";

        when(rentalRepository.findByVehicleQrCodeAndReturnedFalse(qrCode))
                .thenReturn(Collections.emptyList());

        Exception exception = assertThrows(RuntimeException.class, () ->
                rentalService.returnVehicle(qrCode));

        assertEquals("No active rental found for this vehicle", exception.getMessage());

        verify(rentalRepository, times(1)).findByVehicleQrCodeAndReturnedFalse(qrCode);
        verify(rentalRepository, never()).save(any(Rental.class));
    }
}
