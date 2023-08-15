package com.goktech.view;

import com.goktech.service.CarService;
import com.goktech.service.impl.CarServiceImpl;

import java.util.Scanner;

/**
 * {@code @Description:} 视图
 */
public class View {
    private static CarService carService = new CarServiceImpl();
    
    public static void getMenu() {
        
        
        System.out.println("----欢迎进入汽车租赁系统----");
        Scanner scanner = new Scanner(System.in);
        
        
        while (true) {
            System.out.println("用户名：");
            String username = scanner.next();
            System.out.println("密码：");
            String password = scanner.next();
            
            // 管理员进入后台管理系统
            if ("admin".equals(username) && "admin".equals(password)) {
                System.out.println("----欢迎进入后台管理系统----");
                
                while (true) {
                    
                    System.out.println("1.查看车辆列表");
                    System.out.println("2.录入汽车品牌");
                    System.out.println("3.录入汽车信息");
                    System.out.println("4.退出登录");
                    System.out.println("请选择您的操作：");
                    
                    int command = scanner.nextInt();
                    
                    switch (command) {
                        // 查看车辆列表
                        case 1:
                            carService.queryCars();
                            break;
                        
                        // 录入汽车品牌
                        case 2:
                            carService.addBrand();
                            break;
                        
                        
                        // 录入汽车信息
                        case 3:
                            carService.addCar();
                            break;
                        
                        // 退出登录
                        case 4:
                            System.out.println("正在退出...");
                            System.out.println("您已退出系统，再见！");
                            System.exit(0);
                            break;
                        
                        default:
                            System.out.println("输入有误，请重新输入！");
                            break;
                    }
                    
                    
                }
                
                
            }
            
            // 客户进入汽车租赁系统
            else if ("user".equals(username) && "user".equals(password)) {
                System.out.println("----欢迎进入汽车租赁系统----");
                
                while (true) {
                    System.out.println("1.车辆搜索");
                    System.out.println("2.车辆租赁");
                    System.out.println("3.车辆归还");
                    System.out.println("4.退出登录");
                    System.out.println("请选择您的操作：");
                    
                    int command = scanner.nextInt();
                    
                    switch (command) {
                        // 车辆搜索
                        case 1:
                            carService.searchCars();
                            break;
                        
                        // 车辆租赁
                        case 2:
                            carService.borrowCar();
                            break;
                        
                        
                        // 车辆归还
                        case 3:
                            carService.returnCar();
                            break;
                        
                        // 退出登录
                        case 4:
                            System.out.println("正在退出...");
                            System.out.println("您已退出系统，再见！");
                            System.exit(0);
                            break;
                        
                        default:
                            System.out.println("输入有误，请重新输入！");
                            break;
                    }
                    
                    
                }
            } else {
                System.out.println("用户名或密码错误，请重新输入！");
            }
            
            
        }
    }
    
}