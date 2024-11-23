package com.wheelgo.mapper;

import com.wheelgo.dto.RentalDto;
import com.wheelgo.entity.Rental;
import org.springframework.stereotype.Component;

@Component
public class RentalMapperImpl implements RentalMapper {
    private final UserMapper userMapper;
    private final VehicleMapper vehicleMapper;

    public RentalMapperImpl(UserMapper userMapper, VehicleMapper vehicleMapper) {
        this.userMapper = userMapper;
        this.vehicleMapper = vehicleMapper;
    }

    @Override
    public RentalDto toDto(Rental rental) {
        if (rental == null) {
            return null;
        }

        return new RentalDto(
                rental.getId(),
                rental.getStartTime(),
                rental.getEndTime(),
                userMapper.toDto(rental.getUser()),       // User -> UserDto
                vehicleMapper.toDto(rental.getVehicle()), // Vehicle -> VehicleDto
                rental.getStatus()
        );
    }

    @Override
    public Rental toEntity(RentalDto rentalDto) {
        // 필요시 작성 (현재는 DTO -> 엔티티 변환 생략)
        return null;
    }
}
