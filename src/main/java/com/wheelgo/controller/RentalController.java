package com.wheelgo.controller;

import com.wheelgo.dto.RentalDTO;
import com.wheelgo.service.RentalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/rental-service")
public class RentalController {

    private static final Logger logger = LoggerFactory.getLogger(RentalController.class);

    private RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    // 차량 대여 요청
    @PostMapping("/rent")
    public RentalDTO rentVehicle(@RequestBody RentalDTO rentalDto) {
        logger.info("rentVehicle method called with RentalDTO: {}", rentalDto);

        return rentalService.rentVehicle(
                rentalDto.getVehicleQrCode(),
                rentalDto.getVehicleType(),
                rentalDto.getUserId(),
                rentalDto.getUsername(),
                rentalDto.getLocation()
             );
    }

    // 차량 반납 요청
    @PostMapping("/return")
    public RentalDTO returnVehicle(@RequestParam String qrCode) {
        logger.info("returnVehicle method called with qrCode: {}", qrCode);
        return rentalService.returnVehicle(qrCode);
    }
}