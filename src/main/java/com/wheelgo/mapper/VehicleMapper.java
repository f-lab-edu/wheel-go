package com.wheelgo.mapper;

import com.wheelgo.dto.VehicleDto;
import com.wheelgo.entity.Vehicle;

public interface VehicleMapper {
    VehicleDto toDto(Vehicle vehicle);
}
