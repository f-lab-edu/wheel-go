package com.wheelgo.controller;

import com.wheelgo.dto.RentalDTO;
import com.wheelgo.service.RentalService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/rental-service")
public class RentalController {

    private RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    // 차량 대여 요청
    @PostMapping("/rent")
    public RentalDTO rentVehicle(@RequestBody HashMap rentalDto) {
        System.out.println("들어오나?" + rentalDto);
        return null;
    }

    // 차량 반납 요청
    @PostMapping("/return")
    public RentalDTO returnVehicle(@RequestParam String qrCode) {
        return rentalService.returnVehicle(qrCode);
    }
}