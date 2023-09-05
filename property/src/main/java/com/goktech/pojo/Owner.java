package com.goktech.pojo;

import java.util.Date;

/**
 * {@code @description:} 业主信息实体类
 */
public class Owner {
    private String id;
    private String name;
    private Long phone;
    private String room;
    private Date live;// 入住时间
    
    public Owner(String id, String name, Long phone, String room, Date live, String billId) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.room = room;
        this.live = live;
    }
    
    public Owner() {
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Long getPhone() {
        return phone;
    }
    
    public void setPhone(Long phone) {
        this.phone = phone;
    }
    
    public String getRoom() {
        return room;
    }
    
    public void setRoom(String room) {
        this.room = room;
    }
    
    public Date getLive() {
        return live;
    }
    
    public void setLive(Date live) {
        this.live = live;
    }
    
    
    @Override
    public String toString() {
        return "Owner{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", room='" + room + '\'' +
                ", live=" + live +
                '}';
    }
}