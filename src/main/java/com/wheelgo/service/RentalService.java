package com.wheelgo.service;

import com.wheelgo.dto.RentalDTO;
import com.wheelgo.entity.Rental;
import com.wheelgo.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    // 차량 대여
    public RentalDTO rentVehicle(String qrCode, String vehicleType, Long userId, String username, String location){
        // 현재 차량이 이미 대여 중인지 확인
        Optional<Rental> existingRental = rentalRepository
                .findByVehicleQrCodeAndReturnedFalse(qrCode)
                .stream()
                .findFirst();

        if(existingRental.isPresent()){
            throw new RuntimeException("Vehicle is already rented");
        }

        // 대여 정보 저장
        Rental rental = new Rental();
        rental.setUserId(userId);
        rental.setUsername(username);
        rental.setVehicleQrCode(qrCode);
        rental.setVehicleType(vehicleType);
        rental.setLocation(location);
        rental.setRentalStartTime(LocalDateTime.now());
        rental.setReturned(false);

        Rental savedRental = rentalRepository.save(rental);

        // 엔티티를 DTO로 변환
        return toDTO(savedRental);
    }

    // 차량 반납 처리
    public RentalDTO returnVehicle(String qrCode) {
        // 차량 대여 정보 조회
        Rental rental = rentalRepository
                .findByVehicleQrCodeAndReturnedFalse(qrCode)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No active rental found for this vehicle"));

        // 반납 처리
        rental.setReturned(true);
        rental.setRentalEndTime(LocalDateTime.now());
        Rental updatedRental = rentalRepository.save(rental);

        // 엔티티를 DTO로 변환
        return toDTO(updatedRental);
    }

    // Rental 엔티티를 RentalDTO로 변환
    private RentalDTO toDTO(Rental rental) {
        RentalDTO dto = new RentalDTO();
        dto.setId(rental.getId());
        dto.setUserId(rental.getUserId());
        dto.setUsername(rental.getUsername());
        dto.setVehicleQrCode(rental.getVehicleQrCode());
        dto.setVehicleType(rental.getVehicleType());
        dto.setLocation(rental.getLocation());
        dto.setReturned(rental.isReturned());
        dto.setRentalStartTime(rental.getRentalStartTime());
        dto.setRentalEndTime(rental.getRentalEndTime());
        return dto;
    }
}
