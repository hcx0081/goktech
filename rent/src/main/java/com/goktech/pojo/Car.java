package com.goktech.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * {@code @Description:}
 */
public class Car {
    private String license;
    private int brandId;
    private String location;
    private int status;
    private Timestamp addTime;
    private Timestamp modTime;
    private BigDecimal dailyRent;
    
    public Car() {
    }
    
    public Car(String license, int brandId, String location, int status, Timestamp addTime, Timestamp modTime, BigDecimal dailyRent) {
        this.license = license;
        this.brandId = brandId;
        this.location = location;
        this.status = status;
        this.addTime = addTime;
        this.modTime = modTime;
        this.dailyRent = dailyRent;
    }
    
    public String getLicense() {
        return license;
    }
    
    public void setLicense(String license) {
        this.license = license;
    }
    
    public int getBrandId() {
        return brandId;
    }
    
    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    public Timestamp getAddTime() {
        return addTime;
    }
    
    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }
    
    public Timestamp getModTime() {
        return modTime;
    }
    
    public void setModTime(Timestamp modTime) {
        this.modTime = modTime;
    }
    
    public BigDecimal getDailyRent() {
        return dailyRent;
    }
    
    public void setDailyRent(BigDecimal dailyRent) {
        this.dailyRent = dailyRent;
    }
    
    @Override
    public String toString() {
        return "Car{" +
                "license='" + license + '\'' +
                ", brandId=" + brandId +
                ", location='" + location + '\'' +
                ", status=" + status +
                ", addTime=" + addTime +
                ", modTime=" + modTime +
                ", dailyRent=" + dailyRent +
                '}';
    }
}