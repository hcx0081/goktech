package com.goktech.dao.impl;

import com.goktech.dao.CarDao;
import com.goktech.pojo.Brand;
import com.goktech.pojo.Car;
import com.goktech.utils.DpUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.util.List;

/**
 * {@code @Description:}
 */
public class CarDaoImpl implements CarDao {
    /**
     * 查询所有车辆信息
     *
     * @return
     */
    @Override
    public List<Car> queryCars() {
        Connection connection = null;
        try {
            connection = DpUtils.getConnectionByDruid();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "select license,brand_id as brandId,location,status,add_time as addTime,mod_time as modTime,daily_rent as dailyRent from car";
            List<Car> cars = queryRunner.query(connection, sql, new BeanListHandler<>(Car.class));
            return cars;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DpUtils.closeResourceByDBUtils(null, null, connection);
        }
        return null;
    }
    
    /**
     * 根据品牌id查询车辆信息
     *
     * @param brandId
     * @return
     */
    @Override
    public List<Car> queryCarsByBrandId(int brandId) {
        Connection connection = null;
        try {
            connection = DpUtils.getConnectionByDruid();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "select license,brand_id as brandId,location,status,add_time as addTime,mod_time as modTime,daily_rent as dailyRent  from car where brand_id=?";
            List<Car> cars = queryRunner.query(connection, sql, new BeanListHandler<>(Car.class), brandId);
            return cars;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DpUtils.closeResourceByDBUtils(null, null, connection);
        }
        return null;
    }
    
    /**
     * 根据车牌号查询车辆信息
     *
     * @param license
     * @return
     */
    @Override
    public Car queryCarsByLicense(String license) {
        Connection connection = null;
        try {
            connection = DpUtils.getConnectionByDruid();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "select license,brand_id as brandId,location,status,add_time as addTime,mod_time as modTime,daily_rent as dailyRent  from car where license=?";
            Car car = queryRunner.query(connection, sql, new BeanHandler<>(Car.class), license);
            return car;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DpUtils.closeResourceByDBUtils(null, null, connection);
        }
        return null;
    }
    
    /**
     * 根据品牌id和车牌号查询车辆信息
     *
     * @param brandId
     * @param license
     * @return
     */
    @Override
    public List<Car> queryCarsByBrandIdAndLicense(int brandId, String license) {
        Connection connection = null;
        try {
            connection = DpUtils.getConnectionByDruid();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "select license,brand_id as brandId,location,status,add_time as addTime,mod_time as modTime,daily_rent as dailyRent  from car where brand_id=? and license=?";
            List<Car> cars = queryRunner.query(connection, sql, new BeanListHandler<>(Car.class), brandId, license);
            return cars;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DpUtils.closeResourceByDBUtils(null, null, connection);
        }
        return null;
    }
    
    /**
     * 添加汽车品牌
     *
     * @param brandId
     * @param brandName
     */
    @Override
    public void addBrand(int brandId, String brandName) {
        Connection connection = null;
        try {
            connection = DpUtils.getConnectionByDruid();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "insert into brand(brand_id,brand_name) values (?,?)";
            queryRunner.update(connection, sql, brandId, brandName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DpUtils.closeResourceByDBUtils(null, null, connection);
        }
    }
    
    /**
     * 根据品牌id查找该品牌名称
     *
     * @param brandId
     * @return
     */
    @Override
    public String findBrand(int brandId) {
        Connection connection = null;
        try {
            connection = DpUtils.getConnectionByDruid();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "select brand_name as brandName from brand where brand_id=?";
            String brandName = queryRunner.query(connection, sql, new ScalarHandler<>(), brandId);
            if (brandName == null) {
                return null;
            }
            return brandName;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DpUtils.closeResourceByDBUtils(null, null, connection);
        }
        return null;
    }
    
    /**
     * 根据车牌号查找汽车信息
     *
     * @param license
     * @return
     */
    @Override
    public Car findCar(String license) {
        Connection connection = null;
        try {
            connection = DpUtils.getConnectionByDruid();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "select license,brand_id as brandId,location,status,add_time as addTime,mod_time as modTime,daily_rent as dailyRent  from car where license=?";
            Car car = queryRunner.query(connection, sql, new BeanHandler<>(Car.class), license);
            return car;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DpUtils.closeResourceByDBUtils(null, null, connection);
        }
        return null;
    }
    
    /**
     * 查询所有品牌
     *
     * @return
     */
    @Override
    public List<Brand> queryBrand() {
        Connection connection = null;
        try {
            connection = DpUtils.getConnectionByDruid();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "select brand_id brandId,brand_name brandName from brand";
            List<Brand> brands = queryRunner.query(connection, sql, new BeanListHandler<>(Brand.class));
            return brands;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DpUtils.closeResourceByDBUtils(null, null, connection);
        }
        return null;
    }
    
    /**
     * 录入汽车信息
     *
     * @param car
     */
    @Override
    public void addCar(Car car) {
        Connection connection = null;
        try {
            connection = DpUtils.getConnectionByDruid();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "insert into car(license,brand_id,location,status,add_time,mod_time,daily_rent) " +
                    "values (?,?,?,?,?,?,?)";
            queryRunner.update(connection, sql, car.getLicense(), car.getBrandId(), car.getLocation(), car.getStatus(), car.getAddTime(), car.getModTime(), car.getDailyRent());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DpUtils.closeResourceByDBUtils(null, null, connection);
        }
    }
    
    /**
     * 根据车牌号修改租赁状态
     *
     * @param license
     */
    @Override
    public void updateStatus(String license, int status) {
        Connection connection = null;
        try {
            connection = DpUtils.getConnectionByDruid();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "update car set status= ? where license= ?";
            queryRunner.update(connection, sql, status, license);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DpUtils.closeResourceByDBUtils(null, null, connection);
        }
        
    }
    
    /**
     * 根据租赁状态查询车辆信息
     *
     * @param status
     * @return
     */
    @Override
    public List<Car> queryCarsByStatus(int status) {
        Connection connection = null;
        try {
            connection = DpUtils.getConnectionByDruid();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "select license,brand_id as brandId,location,status,add_time as addTime,mod_time as modTime,daily_rent as dailyRent  from car where status=?";
            List<Car> cars = queryRunner.query(connection, sql, new BeanListHandler<>(Car.class), status);
            return cars;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DpUtils.closeResourceByDBUtils(null, null, connection);
        }
        return null;
    }
}