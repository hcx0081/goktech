package com.goktech.dao;

import com.goktech.pojo.Brand;
import com.goktech.pojo.Car;

import java.util.List;

/**
 * {@code @description:}
 */
public interface CarDao {
    
    /**
     * 查询所有车辆信息
     *
     * @return
     */
    List<Car> queryCars();
    
    /**
     * 根据品牌id查询车辆信息
     *
     * @param brandId
     * @return
     */
    List<Car> queryCarsByBrandId(int brandId);
    
    /**
     * 根据车牌号查询车辆信息
     *
     * @param license
     * @return
     */
    Car queryCarsByLicense(String license);
    
    /**
     * 根据品牌id和车牌号查询车辆信息
     *
     * @param brandId
     * @param license
     * @return
     */
    List<Car> queryCarsByBrandIdAndLicense(int brandId, String license);
    
    /**
     * 添加汽车品牌
     *
     * @param brandId
     * @param brandName
     */
    void addBrand(int brandId, String brandName);
    
    /**
     * 根据品牌id查找该品牌名称
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
     * 查询所有品牌
     *
     * @return
     */
    List<Brand> queryBrand();
    
    
    /**
     * 录入汽车信息
     *
     * @param car
     */
    void addCar(Car car);
    
    /**
     * 根据车牌号修改租赁状态
     *
     * @param license
     * @param status
     */
    void updateStatus(String license, int status);
    
    
    /**
     * 根据租赁状态查询车辆信息
     *
     * @param status
     * @return
     */
    List<Car> queryCarsByStatus(int status);
}