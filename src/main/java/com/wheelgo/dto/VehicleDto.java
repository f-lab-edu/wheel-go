package com.wheelgo.dto;

public class VehicleDto {
    private Long id;
    private String vehicleCode;
    private String status;
    private String location;

    public VehicleDto(Long id, String vehicleCode, String status, String location) {
        this.id = id;
        this.vehicleCode = vehicleCode;
        this.status = status;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVehicleCode() {
        return vehicleCode;
    }

    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
