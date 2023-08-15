package com.goktech.view;

import com.goktech.service.OwnerService;
import com.goktech.service.impl.OwnerServiceImpl;

import java.util.Scanner;

/**
 * {@code @Description:} 视图
 */
public class View {
    private static OwnerService ownerService = new OwnerServiceImpl();
    
    public static void getMenu() throws Exception {
        
        System.out.println("----欢迎进入物业收费系统----");
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
                    System.out.println("1.查看业主信息");
                    System.out.println("2.添加业主信息");
                    System.out.println("3.查看账单信息");
                    System.out.println("4.导入账单");
                    System.out.println("5.退出登录");
                    System.out.println("请选择您的操作：");
                    
                    int command = scanner.nextInt();
                    
                    switch (command) {
                        // 查看业主信息
                        case 1:
                            ownerService.queryAllOwners();
                            break;
                        
                        // 添加业主信息
                        case 2:
                            ownerService.addOwner();
                            break;
                        
                        
                        // 查看账单信息
                        case 3:
                            ownerService.queryBill();
                            break;
                        
                        // 导入账单
                        case 4:
                            ownerService.addBill();
                            break;
                        
                        // 退出登录
                        case 5:
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
            
            // 客户进入业主缴费系统
            else {
                System.out.println("----欢迎进入业主缴费系统----");
                
                while (true) {
                    System.out.println("1.查看账单");
                    System.out.println("2.缴费");
                    System.out.println("3.查看收费报表");
                    System.out.println("4.退出登录");
                    System.out.println("请选择您的操作：");
                    
                    int command = scanner.nextInt();
                    
                    switch (command) {
                        // 查看账单
                        case 1:
                            // 将这个输入的用户名当做手机号
                            ownerService.findBillOfOwner(Long.valueOf(username));
                            break;
                        
                        // 缴费
                        case 2:
                            ownerService.pay(Long.valueOf(username));
                            break;
                        
                        
                        // 查看收费报表
                        case 3:
                            ownerService.showForm(Long.valueOf(username));
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
            
        }
    }
    
}