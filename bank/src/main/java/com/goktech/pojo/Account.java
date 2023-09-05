package com.goktech.pojo;

import java.math.BigDecimal;

/**
 * {@code @description:} 账户实体类
 */
public class Account {
    private long cardid;
    private String password;
    private BigDecimal balance;
    
    public long getCardid() {
        return cardid;
    }
    
    public void setCardid(long cardid) {
        this.cardid = cardid;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public BigDecimal getBalance() {
        return balance;
    }
    
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    @Override
    public String toString() {
        return "Account{" +
                "cardid=" + cardid +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }
}