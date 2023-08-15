package com.goktech.service;

import com.goktech.pojo.Car;

import java.util.List;

/**
 * {@code @Description:}
 */
public interface CarService {
    /**
     * 查询所有车辆信息
     *
     * @return
     */
    List<Car> queryCars();
    
    /**
     * 添加汽车品牌
     */
    void addBrand();
    
    /**
     * 根据品牌id查找是否有该品牌
     *
     * @param brandId
     * @return
     */
    String findBrand(int brandId);
    
    /**
     * 根据车牌号查找汽车信息
     *
     * @param license
     * @return
     */
    Car findCar(String license);
    
    /**
     * 录入汽车信息
     */
    void addCar();
    
    /**
     * 车辆查询
     */
    void searchCars();
    
    /**
     * 车辆租用
     */
    void borrowCar();
    
    /**
     * 车辆归还
     */
    void returnCar();
}