package com.wheelgo.mapper;

import com.wheelgo.dto.VehicleDto;
import com.wheelgo.entity.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapperImpl implements VehicleMapper {
    @Override
    public VehicleDto toDto(Vehicle vehicle) {
        if (vehicle == null) {
            return null;
        }

        VehicleDto vehicleDto = new VehicleDto(vehicle.getId(), vehicle.getVehicleCode(), vehicle.getStatus(), vehicle.getLocation());

        return vehicleDto;
    }
}