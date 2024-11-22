package com.wheelgo.dto;

import java.time.LocalDateTime;

public class RentalDto {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private UserDto userDto;
    private VehicleDto vehicleDto;
    private String status;

    public RentalDto(Long id, LocalDateTime startTime, LocalDateTime endTime, UserDto userDto, VehicleDto vehicleDto, String status) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.userDto = userDto;
        this.vehicleDto = vehicleDto;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public VehicleDto getVehicleDto() {
        return vehicleDto;
    }

    public void setVehicleDto(VehicleDto vehicleDto) {
        this.vehicleDto = vehicleDto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
