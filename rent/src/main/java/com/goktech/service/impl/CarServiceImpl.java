package com.goktech.service.impl;

import com.goktech.dao.CarDao;
import com.goktech.dao.impl.CarDaoImpl;
import com.goktech.pojo.Brand;
import com.goktech.pojo.Car;
import com.goktech.service.CarService;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

/**
 * {@code @Description:}
 */
public class CarServiceImpl implements CarService {
    Scanner scanner = new Scanner(System.in);
    private CarDao carDao = new CarDaoImpl();
    
    /**
     * 查询所有车辆信息
     *
     * @return
     */
    @Override
    public List<Car> queryCars() {
        List<Car> cars = carDao.queryCars();
        System.out.println("[车牌号]" + "\t\t\t" + "[品牌ID]" + "\t\t" + "[品牌名]" + "\t\t"
                + "[位置]" + "\t\t" + "[状态]" + "\t\t"
                + "[添加时间]" + "\t\t\t\t\t" + "[修改时间]" + "\t\t\t\t\t" + "[日租金]");
        for (Car car : cars) {
            String s = null;
            if (car.getStatus() == 1) {
                s = "已租赁";
            }
            if (car.getStatus() == 0) {
                s = "空闲";
            }
            System.out.println(car.getLicense() + "\t\t\t" + car.getBrandId() + "\t\t\t"
                    + carDao.findBrand(car.getBrandId()) + "\t\t\t" + car.getLocation() + "\t\t\t" + s + "\t\t"
                    + car.getAddTime() + "\t\t" + car.getModTime() + "\t\t" + car.getDailyRent());
        }
        return cars;
    }
    
    /**
     * 添加汽车品牌
     */
    @Override
    public void addBrand() {
        
        while (true) {
            System.out.println("请输入要添加的品牌ID：");
            int brandId = scanner.nextInt();
            if (carDao.findBrand(brandId) != null) {
                System.out.println("该品牌已存在，请重新输入！");
            } else {
                System.out.println("请输入要添加的品牌名称：");
                String brandName = scanner.next();
                carDao.addBrand(brandId, brandName);
                System.out.println("添加成功！");
                break;
            }
        }
    }
    
    /**
     * 根据品牌id查找是否有该品牌
     *
     * @param brandId
     * @return
     */
    @Override
    public String findBrand(int brandId) {
        return carDao.findBrand(brandId);
    }
    
    /**
     * 根据车牌号查找汽车信息
     *
     * @param license
     * @return
     */
    @Override
    public Car findCar(String license) {
        return carDao.findCar(license);
    }
    
    /**
     * 录入汽车信息
     */
    @Override
    public void addCar() {
        System.out.println("----录入汽车信息----");
        
        
        String license;
        while (true) {
            System.out.println("请输入车牌号：");
            license = scanner.next();
            if (carDao.findCar(license) != null) {
                System.out.println("该汽车已存在，请重新输入！");
            } else {
                break;
            }
        }
        
        int brandId;
        while (true) {
            List<Brand> brands = carDao.queryBrand();
            System.out.println("----[品牌列表]----");
            System.out.println("[品牌ID]" + "\t" + "[品牌名称]");
            for (Brand brand : brands) {
                System.out.println(brand.getBrandId() + "\t\t" + brand.getBrandName());
            }
            System.out.println("----[品牌列表]----");
            System.out.println("请输入品牌ID：");
            
            brandId = scanner.nextInt();
            
            // 如果输入的品牌id数据库有，则往下执行
            if (carDao.findBrand(brandId) != null) {
                break;
            } else {
                System.out.println("输入有误，请重新输入！");
            }
        }
        System.out.println("请输入位置信息：");
        String location = scanner.next();
        System.out.println("请输入租赁状态（0/1：1代表已租赁，0代表空闲）：");
        int status = scanner.nextInt();
        System.out.println("请输入日租金：");
        BigDecimal dailyRent = scanner.nextBigDecimal();
        
        Car car = new Car(license, brandId, location, status,
                new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), dailyRent);
        
        carDao.addCar(car);
        System.out.println("录入成功！");
    }
    
    /**
     * 车辆查询
     */
    @Override
    public void searchCars() {
        
        List<Brand> brands = carDao.queryBrand();
        System.out.println("----[品牌列表]----");
        System.out.println("[品牌ID]" + "\t" + "[品牌名称]");
        for (Brand brand : brands) {
            System.out.println(brand.getBrandId() + "\t\t" + brand.getBrandName());
        }
        System.out.println("----[品牌列表]----");
        
        while (true) {
            System.out.println("请输入你想搜索的品牌ID（输入-1代表不需要查询该条件）：");
            int brandId = scanner.nextInt();
            System.out.println("请输入你想搜索的车牌号（输入-1代表不需要查询该条件）：");
            String license = scanner.next();
            if (brandId != -1) {
                // 如果条件是品牌id和车牌号
                if (!("-1".equals(license))) {
                    List<Car> cars = carDao.queryCarsByBrandIdAndLicense(brandId, license);
                    System.out.println("[车牌号]" + "\t" + "[品牌名称]" + "\t" + "[位置信息]" + "\t" + "[租赁状态]"
                            + "\t" + "[日租金]");
                    for (Car car : cars) {
                        String s = null;
                        if (car.getStatus() == 1) {
                            s = "已租赁";
                        }
                        if (car.getStatus() == 0) {
                            s = "空闲";
                        }
                        System.out.println(car.getLicense() + "\t" + carDao.findBrand(car.getBrandId()) + "\t\t\t"
                                + car.getLocation() + "\t\t\t" + s + "\t\t" + car.getDailyRent());
                    }
                    break;
                    
                    // 如果条件只有品牌id
                } else {
                    List<Car> cars = carDao.queryCarsByBrandId(brandId);
                    System.out.println("[车牌号]" + "\t" + "[品牌名称]" + "\t" + "[位置信息]" + "\t" + "[租赁状态]"
                            + "\t" + "[日租金]");
                    for (Car car : cars) {
                        String s = null;
                        if (car.getStatus() == 1) {
                            s = "已租赁";
                        }
                        if (car.getStatus() == 0) {
                            s = "空闲";
                        }
                        System.out.println(car.getLicense() + "\t" + carDao.findBrand(car.getBrandId()) + "\t\t\t"
                                + car.getLocation() + "\t\t\t" + s + "\t\t" + car.getDailyRent());
                    }
                    break;
                }
            } else {
                // 如果条件只有车牌号
                if (!("-1".equals(license))) {
                    Car car = carDao.queryCarsByLicense(license);
                    String s = null;
                    if (car.getStatus() == 1) {
                        s = "已租赁";
                    }
                    if (car.getStatus() == 0) {
                        s = "空闲";
                    }
                    System.out.println("[车牌号]" + "\t" + "[品牌名称]" + "\t" + "[位置信息]" + "\t" + "[租赁状态]"
                            + "\t" + "[日租金]");
                    System.out.println(car.getLicense() + "\t" + carDao.findBrand(car.getBrandId()) + "\t\t\t"
                            + car.getLocation() + "\t\t\t" + s + "\t\t" + car.getDailyRent());
                    break;
                    
                    // 如果没有条件
                } else {
                    List<Car> cars = carDao.queryCars();
                    System.out.println("[车牌号]" + "\t" + "[品牌名称]" + "\t" + "[位置信息]" + "\t" + "[租赁状态]"
                            + "\t" + "[日租金]");
                    for (Car car : cars) {
                        String s = null;
                        if (car.getStatus() == 1) {
                            s = "已租赁";
                        }
                        if (car.getStatus() == 0) {
                            s = "空闲";
                        }
                        System.out.println(car.getLicense() + "\t" + carDao.findBrand(car.getBrandId()) + "\t\t\t"
                                + car.getLocation() + "\t\t\t" + s + "\t\t" + car.getDailyRent());
                    }
                    break;
                }
            }
            
            
        }
        
        
    }
    
    /**
     * 车辆租用
     */
    @Override
    public void borrowCar() {
        List<Brand> brands = carDao.queryBrand();
        System.out.println("----[品牌列表]----");
        System.out.println("[品牌ID]" + "\t" + "[品牌名称]");
        for (Brand brand : brands) {
            System.out.println(brand.getBrandId() + "\t\t" + brand.getBrandName());
        }
        System.out.println("----[品牌列表]----");
        
        while (true) {
            
            System.out.println("请输入要租用的汽车品牌ID：");
            int brandId = scanner.nextInt();
            if (carDao.findBrand(brandId) != null) {
                List<Car> cars = carDao.queryCarsByBrandId(brandId);
                System.out.println("[车牌号]" + "\t" + "[品牌名称]" + "\t" + "[位置信息]" + "\t" + "[租赁状态]"
                        + "\t" + "[日租金]");
                for (Car car : cars) {
                    String s = null;
                    if (car.getStatus() == 1) {
                        s = "已租赁";
                    }
                    if (car.getStatus() == 0) {
                        s = "空闲";
                    }
                    System.out.println(car.getLicense() + "\t" + carDao.findBrand(car.getBrandId()) + "\t\t\t"
                            + car.getLocation() + "\t\t\t" + s + "\t\t\t\t" + car.getDailyRent());
                }
                break;
            } else {
                System.out.println("输入有误，请重新输入！");
            }
        }
        
        
        String license;
        while (true) {
            System.out.println("请选择你想要租用的车牌号：");
            license = scanner.next();
            if (carDao.findCar(license).getStatus() == 1) {
                System.out.println("该车已租赁，请选择其他汽车！");
            } else {
                break;
            }
            
        }
        
        System.out.println("请输入你想要租用的天数：");
        int day = scanner.nextInt();
        System.out.println("总费用为：" + carDao.findCar(license).getDailyRent().multiply(BigDecimal.valueOf(day)) + "元");
        System.out.print("是否确认租赁（Y：确认 N：取消）：");
        String flag = scanner.next();
        if ("Y".equals(flag)) {
            carDao.updateStatus(license, 1);
            System.out.println("租用成功！");
        }
        if ("N".equals(flag)) {
            System.out.println("取消租用成功！");
        }
        
        
    }
    
    /**
     * 车辆归还
     */
    @Override
    public void returnCar() {
        // 查询所有已租赁的车辆信息
        List<Car> cars = carDao.queryCarsByStatus(1);
        System.out.println("[车牌号]" + "\t\t" + "[品牌名称]" + "\t" + "[位置信息]" + "\t" + "[租赁状态]"
                + "\t" + "[日租金]");
        for (Car car : cars) {
            String s = null;
            if (car.getStatus() == 1) {
                s = "已租赁";
            }
            if (car.getStatus() == 0) {
                s = "空闲";
            }
            System.out.println(car.getLicense() + "\t\t" + carDao.findBrand(car.getBrandId()) + "\t\t\t"
                    + car.getLocation() + "\t\t\t" + s + "\t\t" + car.getDailyRent());
        }
        
        String license;
        while (true) {
            System.out.println("请输入要归还的车辆车牌号：");
            license = scanner.next();
            if (carDao.findCar(license) == null) {
                System.out.println("查询不到该车辆信息，请重新输入车牌号！");
            } else {
                break;
            }
        }
        
        
        System.out.println("是否确认归还（Y：确认 N：取消）：");
        String flag = scanner.next();
        if ("Y".equals(flag)) {
            carDao.updateStatus(license, 0);
            System.out.println("归还成功！");
        }
        if ("N".equals(flag)) {
            System.out.println("取消归还成功！");
        }
        
    }
}