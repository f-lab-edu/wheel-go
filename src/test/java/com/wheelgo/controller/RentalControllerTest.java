package com.wheelgo.controller;

import com.wheelgo.dto.RentalDTO;
import com.wheelgo.service.RentalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class RentalControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RentalService rentalService;

    @InjectMocks
    private RentalController rentalController;

    @Test
    void  렌트시_유효한_렌트DTO가_부여된_렌트차량_확인후_반환확인() throws Exception {
        // Given
        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setVehicleQrCode("QR123");
        rentalDTO.setVehicleType("scooter");
        rentalDTO.setUserId(1L);
        rentalDTO.setUsername("testUser");
        rentalDTO.setLocation("Location A");

        // Mock 반환 값 설정
        when(rentalService.rentVehicle(anyString(), anyString(), anyLong(), anyString(), anyString()))
                .thenReturn(rentalDTO);

        // When
        RentalDTO result = rentalController.rentVehicle(rentalDTO);

        // Then
        assertNotNull(result);
        assertEquals("QR123", result.getVehicleQrCode());
        verify(rentalService, times(1)).rentVehicle("QR123", "scooter", 1L, "testUser", "Location A");
    }

    @Test
    void 반납시_유효한_qrCode가_주어지면_반납된_렌트DTO_반환() throws Exception {
        // Given
        String qrCode = "QR123";
        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setVehicleQrCode(qrCode);
        rentalDTO.setReturned(true);

        // Mock 반환 값 설정
        when(rentalService.returnVehicle(qrCode)).thenReturn(rentalDTO);

        // When
        RentalDTO result = rentalController.returnVehicle(qrCode);

        // Then
        assertNotNull(result);
        assertEquals(qrCode, result.getVehicleQrCode());
        assertTrue(result.isReturned());
        verify(rentalService, times(1)).returnVehicle(qrCode);
    }
}