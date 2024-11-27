package com.wheelgo.controller;

import com.wheelgo.dto.RentalDTO;
import com.wheelgo.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rental-service")
public class RentalController {

    private RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    // 차량 대여 요청
    @PostMapping("/rent")
    @ResponseBody
    public RentalDTO rentVehicle(
            @RequestParam String qrCode,
            @RequestParam String vehicleType,
            @RequestParam Long userId,
            @RequestParam String username,
            @RequestParam String location) {
        return rentalService.rentVehicle(qrCode, vehicleType, userId, username, location);
    }

    // 차량 반납 요청
    @PostMapping("/return")
    public RentalDTO returnVehicle(@RequestParam String qrCode) {
        return rentalService.returnVehicle(qrCode);
    }
}