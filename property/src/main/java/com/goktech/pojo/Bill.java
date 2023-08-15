package com.goktech.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * {@code @Description:} 账单实体类
 */
public class Bill {
    private String billId;
    private Long phone;
    private Date date;
    private BigDecimal money;
    private String status;
    private String type;
    private Timestamp payTime;
    
    public Bill() {
    }
    
    public Bill(String billId, Long phone, Date date, BigDecimal money, String status, String type, Timestamp payTime) {
        this.billId = billId;
        this.phone = phone;
        this.date = date;
        this.money = money;
        this.status = status;
        this.type = type;
        this.payTime = payTime;
    }
    
    public String getBillId() {
        return billId;
    }
    
    public void setBillId(String billId) {
        this.billId = billId;
    }
    
    public Long getPhone() {
        return phone;
    }
    
    public void setPhone(Long phone) {
        this.phone = phone;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public BigDecimal getMoney() {
        return money;
    }
    
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Timestamp getPayTime() {
        return payTime;
    }
    
    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }
    
    @Override
    public String toString() {
        return "Bill{" +
                "billId='" + billId + '\'' +
                ", phone=" + phone +
                ", date=" + date +
                ", money=" + money +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", payTime=" + payTime +
                '}';
    }
}