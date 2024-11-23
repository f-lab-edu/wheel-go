package com.wheelgo.service;

import com.wheelgo.dto.RentalDto;
import com.wheelgo.dto.UserDto;
import com.wheelgo.entity.Rental;
import com.wheelgo.entity.User;
import com.wheelgo.entity.Vehicle;
import com.wheelgo.exception.InvalidQRCodeException;
import com.wheelgo.exception.InvalidUserException;
import com.wheelgo.exception.VehicleNotAvailableException;
import com.wheelgo.mapper.RentalMapper;
import com.wheelgo.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final VehicleService vehicleService;
    private final UserService userService;
    private final QRCodeService qrCodeService;
    private final RentalMapper rentalMapper;

    public RentalService(RentalRepository rentalRepository, VehicleService vehicleService, UserService userService, QRCodeService qrCodeService, RentalMapper rentalMapper) {
        this.rentalRepository = rentalRepository;
        this.vehicleService = vehicleService;
        this.userService = userService;
        this.qrCodeService = qrCodeService;
        this.rentalMapper = rentalMapper;
    }

    @Transactional
    public RentalDto startRental(String qrCodeData, Long userId){
        // QR 코드 데이터 파싱
        String vehicleCode = qrCodeService.parseQRCodeData(qrCodeData);

        // 차량 이용 가능 여부 확인
        Vehicle vehicle = vehicleService.checkAvailability(vehicleCode);

        // 유저 조회
        User user = userService.findUser(userId);


        // 대여 기록 생성
        Rental rental = new Rental();
        rental.setUser(Optional.ofNullable(user));
        rental.setVehicle(vehicle);
        rental.setStartTime(LocalDateTime.now());
        rental.setStatus("ONGOING");

        rentalRepository.save(rental); // 대여 정보 저장

        // 차량 상태 업데이트
        vehicleService.updateStatus(vehicle, "RENTED");

        return rentalMapper.toDto(rental);
    }
}
