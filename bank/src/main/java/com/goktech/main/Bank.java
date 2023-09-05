package com.goktech.main;

import com.goktech.pojo.Account;
import com.goktech.service.AccountService;
import com.goktech.service.impl.AccountServiceImpl;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * {@code @description:} 银行系统
 */
public class Bank {
    static AccountService accountService = new AccountServiceImpl();
    
    public static void main(String[] args) {
        System.out.println("----欢迎进入中国银行-----");
        Scanner scanner = new Scanner(System.in);
        // 卡号
        long cardId;
        // 密码
        String password;
        // 金额
        String money;
        
        while (true) {
            
            System.out.println("1.申请卡号");
            System.out.println("2.登录系统");
            System.out.println("请选择您的操作：");
            
            int command = scanner.nextInt();
            switch (command) {
                
                // 申请卡号
                case 1:
                    System.out.println("请输入您申请的卡号：");
                    while (true) {
                        cardId = Long.parseLong(scanner.next());
                        // 根据卡号查找账户
                        Account account = accountService.findAccountByCardId(cardId);
                        // 如果没有该卡号的账户
                        if (account == null) {
                            System.out.println("请输入您设置的密码：");
                            password = scanner.next();
                            // 添加账户
                            accountService.addAccount(cardId, password, BigDecimal.valueOf(0));
                            System.out.println("申请成功！");
                            break;
                        } else {
                            System.out.println("该卡号已存在，请输入其他申请的卡号:");
                        }
                    }
                    // 申请完卡号退出
                    break;
                
                // 登录系统
                case 2:
                    System.out.println("----欢迎进入中国银行----");
                    
                    while (true) {
                        System.out.println("请输入您的卡号：");
                        cardId = Long.parseLong(scanner.next());
                        System.out.println("请输入您的密码：");
                        password = scanner.next();
                        
                        Account account = accountService.findAccount(cardId, password);
                        if (account != null) {
                            System.out.println("----欢迎登录----");
                            System.out.println("请选择您的操作：");
                            System.out.println("1.查询余额");
                            System.out.println("2.转账");
                            System.out.println("3.存款");
                            System.out.println("4.取款");
                            System.out.println("5.修改密码");
                            System.out.println("6.退出");
                            
                            while (true) {
                                System.out.print("请输入要执行的操作序号：");
                                // 接收键盘输入的功能选项序号
                                command = Integer.parseInt(scanner.next());
                                // 执行序号对应的功能
                                switch (command) {
                                    // 查询余额
                                    case 1:
                                        BigDecimal balance = accountService.checkBalance(cardId, password);
                                        System.out.println("您的余额为：" + balance);
                                        break;
                                    // 转账
                                    case 2:
                                        while (true) {
                                            System.out.println("请输入你要转账的目标卡号：");
                                            long cardId2 = Long.parseLong(scanner.next());
                                            // 查找目标卡号
                                            Account accountByCardId = accountService.findAccountByCardId(cardId2);
                                            
                                            // 转账目标卡号不为空
                                            if (accountByCardId != null) {
                                                while (true) {
                                                    
                                                    System.out.println("请输入你要转账的金额：");
                                                    money = scanner.next();
                                                    // 如果要转账的金额小于等于余额
                                                    if (new BigDecimal(money).compareTo(accountService.checkBalance(cardId, password)) < 1) {
                                                        accountService.transfer(cardId, password, new BigDecimal(money), cardId2);
                                                        System.out.println("转账成功！您的账户余额为：" + accountService.checkBalance(cardId, password));
                                                        break;
                                                    } else {
                                                        System.out.println("余额不足！");
                                                    }
                                                    break;
                                                }
                                                break;
                                                
                                                // 转账目标卡号为空
                                            } else {
                                                System.out.println("目标卡号为空，请检查你输入的卡号是否正确！");
                                            }
                                        }
                                        break;
                                    
                                    // 存款
                                    case 3:
                                        while (true) {
                                            System.out.println("请输入你要存款的金额：");
                                            money = scanner.next();
                                            // 如果存款的金额大于等于0元
                                            if (new BigDecimal(money).compareTo(BigDecimal.valueOf(0)) > -1) {
                                                accountService.deposit(cardId, password, new BigDecimal(money));
                                                System.out.println("存款成功！您的账户余额为：" + accountService.checkBalance(cardId, password));
                                                break;
                                            } else {
                                                System.out.println("存款金额有误！请重新输入！");
                                            }
                                        }
                                        break;
                                    
                                    
                                    // 取款
                                    case 4:
                                        while (true) {
                                            System.out.println("请输入你要取款的金额：");
                                            money = scanner.next();
                                            
                                            // 取款金额小于等于0
                                            if (new BigDecimal(money).compareTo(BigDecimal.valueOf(0)) < 1) {
                                                System.out.println("取款金额不能是0元或小于0元！请重新输入！");
                                            }
                                            // 取款金额大于余额
                                            else if (new BigDecimal(money).compareTo(accountService.checkBalance(cardId, password)) == 1) {
                                                System.out.println("余额不足！请重新输入！");
                                                
                                            } else {
                                                accountService.withdrawal(cardId, password, new BigDecimal(money));
                                                System.out.println("取款成功！您的账户余额为：" + accountService.checkBalance(cardId, password));
                                                break;
                                            }
                                        }
                                        break;
                                    
                                    // 修改密码
                                    case 5:
                                        while (true) {
                                            System.out.println("请输入你要修改的密码：");
                                            String passwordNew = scanner.next();
                                            
                                            // 如果新密码和原密码密码相等
                                            if (password.equals(passwordNew)) {
                                                System.out.println("新密码不能与原密码相等，请重新输入!");
                                                
                                            } else {
                                                while (true) {
                                                    System.out.println("请再次输入你要修改的密码：");
                                                    String passwordNewAgain = scanner.next();
                                                    
                                                    if (passwordNew.equals(passwordNewAgain)) {
                                                        accountService.changePsd(cardId, passwordNew);
                                                        System.out.println("修改密码成功！");
                                                        break;
                                                    } else {
                                                        System.out.println("两次输入的密码不一致，请重新输入!");
                                                    }
                                                }
                                                break;
                                            }
                                        }
                                        break;
                                    
                                    // 退出
                                    case 6:
                                        System.out.println("----正在退出----");
                                        System.out.println("您已退出系统，再见！");
                                        System.exit(0);
                                        break;
                                    
                                    default:
                                        System.out.println("功能选择有误，请输入正确的功能序号!");
                                        break;
                                }
                            }
                        } else {
                            System.out.println("登录失败！请重新输入卡号和密码！");
                        }
                    }
                
                default:
                    System.out.println("功能选择有误，请输入正确的功能序号!");
                    break;
            }
            
            
        }
        
        
    }
    
    
}