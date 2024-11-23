package com.wheelgo.controller;

import com.wheelgo.dto.RentalDto;
import com.wheelgo.entity.User;
import com.wheelgo.exception.InvalidQRCodeException;
import com.wheelgo.request.QRCodeRequest;
import com.wheelgo.service.RentalService;
import com.wheelgo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rental")
public class RentalController {
    private final RentalService rentalService;
    private final UserService userService;

    public RentalController(RentalService rentalService, UserService userService) {
        this.rentalService = rentalService;
        this.userService = userService;
    }

    @PostMapping("/start")
    public ResponseEntity<RentalDto> startRental(@RequestBody QRCodeRequest qrCodeRequest, Authentication authentication) {
        // 사용자 검증
        String username = authentication.getName();
        if(username == null || username.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // 사용자Id 조회
        User user = new User();

        // qr 코드 검증
        if(qrCodeRequest.getQrCodeData() == null || qrCodeRequest.getQrCodeData().isEmpty()) {
            throw new InvalidQRCodeException("Invalid QR Code");
        }

        // 대여 처리
        RentalDto rentalDto = rentalService.startRental(qrCodeRequest.getQrCodeData(), user.getId());

        return ResponseEntity.ok(rentalDto);
    }
}
