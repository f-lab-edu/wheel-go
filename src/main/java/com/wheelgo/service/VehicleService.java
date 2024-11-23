package com.wheelgo.service;

import com.wheelgo.entity.Vehicle;
import com.wheelgo.exception.VehicleNotAvailableException;
import com.wheelgo.repository.VehicleRepository;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    private VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle checkAvailability(String vehicleCode) throws VehicleNotAvailableException {
        Vehicle vehicle = vehicleRepository.findByVehicleCode(vehicleCode)
                .orElseThrow(() -> new VehicleNotAvailableException("차량을 찾을 수 없습니다."));
        if (!"AVAILABLE".equals(vehicle.getStatus())) {
            throw new VehicleNotAvailableException("차량이 이용 불가능합니다.");
        }
        return vehicle;
    }

    public void updateStatus(Vehicle vehicle, String status) {
        vehicle.setStatus(status);
        vehicleRepository.save(vehicle);
    }
}
