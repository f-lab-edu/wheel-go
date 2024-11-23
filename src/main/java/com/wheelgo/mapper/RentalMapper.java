package com.wheelgo.mapper;

import com.wheelgo.dto.RentalDto;
import com.wheelgo.entity.Rental;

public interface RentalMapper {
    // 엔티티 -> DTO
    RentalDto toDto(Rental rental);

    // DTO -> 엔티티
    Rental toEntity(RentalDto rentalDto);
}
