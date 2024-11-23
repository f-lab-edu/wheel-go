package com.wheelgo.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/*
 * 차량 > 킥보드 테이블
 */
@Entity
@Table(name =  "kickboards")
public class Kickboard extends Vehicle{
    private String kickboardCode;

    public Kickboard(String kickboardCode) {
        this.kickboardCode = kickboardCode;
    }

    public Kickboard(Long id, String vehicleCode, String status, String location, String kickboardCode) {
        super(id, vehicleCode, status, location);
        this.kickboardCode = kickboardCode;
    }

    public Kickboard() {

    }

    public String getKickboardCode() {
        return kickboardCode;
    }

    public void setKickboardCode(String kickboardCode) {
        this.kickboardCode = kickboardCode;
    }
}
