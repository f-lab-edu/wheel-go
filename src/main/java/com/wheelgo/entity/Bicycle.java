package com.wheelgo.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/*
 * 차량 > 자전거 테이블
 */
@Entity
@Table(name = "bicycles")
public class Bicycle extends Vehicle {
    private String bicycleCode;

    public Bicycle(String bicycleCode) {
        this.bicycleCode = bicycleCode;
    }

    public Bicycle(Long id, String vehicleCode, String status, String location, String bicycleCode) {
        super(id, vehicleCode, status, location);
        this.bicycleCode = bicycleCode;
    }

    public Bicycle() {

    }

    public String getBicycleCode() {
        return bicycleCode;
    }

    public void setBicycleCode(String bicycleCode) {
        this.bicycleCode = bicycleCode;
    }
}
