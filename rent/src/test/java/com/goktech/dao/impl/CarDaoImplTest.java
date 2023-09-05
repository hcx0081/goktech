package com.goktech.dao.impl;

import com.goktech.dao.CarDao;
import com.goktech.pojo.Car;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * {@code @description:}
 */
class CarDaoImplTest {
    CarDao carDao = new CarDaoImpl();
    
    @Test
    void queryAll() {
        carDao.queryCars();
    }
    
    @Test
    void addBrand() {
        carDao.addBrand(111, "特斯拉");
    }
    
    @Test
    void findBrand() {
        String brand = carDao.findBrand(112);
        System.out.println(brand);
    }
    
    @Test
    void addCar() {
        carDao.addCar(new Car("asdas", 12343, "sda", 1, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), new BigDecimal(12)));
        
    }
    
    @Test
    void findCar() {
        System.out.println(carDao.findCar("as"));
    }
}